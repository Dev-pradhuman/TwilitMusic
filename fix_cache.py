import re

path = 'androidApp/src/main/java/com/twilitmusic/app/playback/CacheManager.kt'
with open(path, 'r') as f:
    content = f.read()

content = re.sub(r'import dagger\.hilt\..*\n', '', content)
content = re.sub(r'import javax\.inject\..*\n', '', content)
content = re.sub(r'@Singleton\nclass CacheManager @Inject constructor\(\n\s*@ApplicationContext private val context: Context\n\)', 'class CacheManager(private val context: Context)', content, flags=re.DOTALL)

with open(path, 'w') as f:
    f.write(content)

