import sys

with open('app/src/main/java/com/twilitmusic/app/data/remote/JamendoApi.kt', 'r') as f:
    content = f.read()

content = content.replace('import retrofit2.http.GET', '')
content = content.replace('import retrofit2.http.Query', '')
content = content.replace('@GET', '@retrofit2.http.GET')
content = content.replace('@Query', '@retrofit2.http.Query')

with open('app/src/main/java/com/twilitmusic/app/data/remote/JamendoApi.kt', 'w') as f:
    f.write(content)
