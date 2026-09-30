#!/bin/bash
set -e

# Format the code and run detekt/lint if any? No, just the build.
# git commit and push
git add .
git commit -m "Complete Phase 1: Now Playing, Media3, Queue, and UI refinements" || true
git push -u origin main
echo "Push successful!"
