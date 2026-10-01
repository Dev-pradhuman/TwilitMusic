import sys

with open('app/src/main/java/com/twilitmusic/app/di/AppModule.kt', 'r') as f:
    content = f.read()

content = content.replace('import dagger.hilt.components.SingletonComponent', 'import dagger.hilt.components.SingletonComponent\nimport retrofit2.Retrofit\nimport retrofit2.converter.moshi.MoshiConverterFactory\nimport com.twilitmusic.app.data.remote.JamendoApi\nimport com.twilitmusic.app.data.repository.JamendoMusicSource\nimport com.twilitmusic.app.data.repository.DemoMusicSource')

new_prov = '''
    @Provides
    @Singleton
    fun provideJamendoApi(): JamendoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(JamendoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMusicSource(jamendoApi: JamendoApi): MusicSource {
        // Toggle here or use a Setting. For now returning JamendoMusicSource.
        return JamendoMusicSource(jamendoApi)
    }
'''

content = content.replace('fun provideMusicSource(): MusicSource = DemoMusicSource()', new_prov)

with open('app/src/main/java/com/twilitmusic/app/di/AppModule.kt', 'w') as f:
    f.write(content)
