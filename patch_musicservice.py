import sys

with open('app/src/main/java/com/twilitmusic/app/playback/MusicService.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.media3.exoplayer.ExoPlayer', 'import androidx.media3.exoplayer.ExoPlayer\nimport androidx.media3.datasource.cache.CacheDataSource\nimport androidx.media3.datasource.DefaultDataSource\nimport androidx.media3.exoplayer.source.DefaultMediaSourceFactory')

old_player = '''        val player = ExoPlayer.Builder(this)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()'''

new_player = '''        val simpleCache = CacheManager.getInstance(this)
        val dataSourceFactory = DefaultDataSource.Factory(this)
        val cacheDataSourceFactory = CacheDataSource.Factory()
            .setCache(simpleCache)
            .setUpstreamDataSourceFactory(dataSourceFactory)
            
        val player = ExoPlayer.Builder(this)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .setMediaSourceFactory(DefaultMediaSourceFactory(this).setDataSourceFactory(cacheDataSourceFactory))
            .build()'''

content = content.replace(old_player, new_player)

with open('app/src/main/java/com/twilitmusic/app/playback/MusicService.kt', 'w') as f:
    f.write(content)
