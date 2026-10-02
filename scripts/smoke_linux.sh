#!/bin/bash
set -e

echo "Building Linux Desktop App..."
./gradlew :desktopApp:packageDeb

echo "Starting Xvfb..."
# Attempt to start Xvfb if available, otherwise just warn
if command -v Xvfb >/dev/null 2>&1; then
    Xvfb :99 -screen 0 1024x768x24 &
    XVFB_PID=$!
    export DISPLAY=:99
else
    echo "Xvfb not found, trying with current display $DISPLAY"
fi

echo "Running TwilitMusic via Gradle..."
./gradlew :desktopApp:run &
APP_PID=$!

echo "Waiting for app to start..."
sleep 20

echo "Taking screenshot..."
mkdir -p build/reports
if command -v xwd >/dev/null 2>&1 && command -v convert >/dev/null 2>&1; then
    xwd -root -display $DISPLAY | convert xwd:- build/reports/smoke_screenshot.png
    echo "Screenshot saved to build/reports/smoke_screenshot.png"
else
    echo "xwd or imagemagick (convert) not found. Skipping screenshot."
fi

echo "Cleaning up..."
kill $APP_PID || true
if [ -n "$XVFB_PID" ]; then
    kill $XVFB_PID || true
fi

echo "Smoke test script complete."
