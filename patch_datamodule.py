import sys

with open('app/src/main/java/com/twilitmusic/app/di/DataModule.kt', 'r') as f:
    content = f.read()

content = content.replace('com.twilitmusic.app.data.repository.DemoMusicSource', 'com.twilitmusic.app.data.repository.JamendoMusicSource')
content = content.replace('demoMusicSource: DemoMusicSource', 'jamendoMusicSource: JamendoMusicSource')

with open('app/src/main/java/com/twilitmusic/app/di/DataModule.kt', 'w') as f:
    f.write(content)
