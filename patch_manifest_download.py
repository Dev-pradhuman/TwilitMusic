import sys

with open('app/src/main/AndroidManifest.xml', 'r') as f:
    content = f.read()

if 'TwilitDownloadService' not in content:
    content = content.replace('</application>', '    <service android:name=".playback.TwilitDownloadService" android:exported="false">\n            <intent-filter>\n                <action android:name="androidx.media3.exoplayer.downloadService.action.RESTART"/>\n            </intent-filter>\n        </service>\n    </application>')

with open('app/src/main/AndroidManifest.xml', 'w') as f:
    f.write(content)
