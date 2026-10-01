import sys

with open('app/src/test/java/com/twilitmusic/app/data/repository/JamendoMusicSourceTest.kt', 'r') as f:
    content = f.read()

moshi_create = '''        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))'''

content = content.replace('''        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())''', moshi_create)

with open('app/src/test/java/com/twilitmusic/app/data/repository/JamendoMusicSourceTest.kt', 'w') as f:
    f.write(content)
