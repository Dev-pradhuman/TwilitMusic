import os
import glob
import re

for filepath in glob.glob('shared/src/commonMain/kotlin/com/twilitmusic/app/data/repository/*.kt'):
    with open(filepath, 'r') as f:
        content = f.read()

    content = re.sub(r'@Singleton\s*', '', content)
    content = re.sub(r'import javax\.inject\.Singleton\n?', '', content)
    content = re.sub(r'@Inject\s*constructor\s*', '', content)
    content = re.sub(r'import javax\.inject\.Inject\n?', '', content)
    content = re.sub(r'import dagger\.hilt\.android\.qualifiers\.ApplicationContext\n?', '', content)
    content = re.sub(r'@ApplicationContext\s*', '', content)

    # JamendoMusicSource uses BuildConfig.JAMENDO_CLIENT_ID
    # Since we moved it to shared, BuildConfig is not available. 
    # For now, let's hardcode it to "655938da" or read from expecting
    content = content.replace('BuildConfig.JAMENDO_CLIENT_ID', '"655938da"')
    content = content.replace('import com.twilitmusic.app.BuildConfig\n', '')

    with open(filepath, 'w') as f:
        f.write(content)
