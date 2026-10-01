import sys

with open('app/src/main/java/com/twilitmusic/app/di/NetworkModule.kt', 'r') as f:
    content = f.read()

content = content.replace('import retrofit2.Retrofit', 'import okhttp3.OkHttpClient\nimport retrofit2.Retrofit\nimport java.util.concurrent.TimeUnit')

new_client = '''
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
            
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))'''

content = content.replace('''        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))''', new_client)

with open('app/src/main/java/com/twilitmusic/app/di/NetworkModule.kt', 'w') as f:
    f.write(content)
