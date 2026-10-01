import sys

def patch_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    content = content.replace('import retrofit2.converter.moshi.MoshiConverterFactory', 'import retrofit2.converter.moshi.MoshiConverterFactory\nimport com.squareup.moshi.Moshi\nimport com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory')
    
    moshi_create = '''        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            
        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))'''
            
    content = content.replace('''        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .addConverterFactory(MoshiConverterFactory.create())''', moshi_create)
            
    with open(filepath, 'w') as f:
        f.write(content)

patch_file('app/src/main/java/com/twilitmusic/app/di/NetworkModule.kt')
patch_file('app/src/test/java/com/twilitmusic/app/data/repository/JamendoMusicSourceTest.kt')
