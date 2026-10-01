import sys

with open('app/src/main/java/com/twilitmusic/app/domain/repository/MusicSource.kt', 'r') as f:
    content = f.read()

content = content.replace('suspend fun search(query: String): List<Track>', 'suspend fun search(query: String): Result<List<Track>>')
content = content.replace('suspend fun getTrack(id: String): Track?', 'suspend fun getTrack(id: String): Result<Track?>')
content = content.replace('suspend fun getStreamUrl(id: String): String?', 'suspend fun getStreamUrl(id: String): Result<String?>')
content = content.replace('suspend fun getFeaturedTracks(): List<Track>', 'suspend fun getFeaturedTracks(): Result<List<Track>>')
content = content.replace('suspend fun getNewTracks(): List<Track>', 'suspend fun getNewTracks(): Result<List<Track>>')

new_methods = '''    suspend fun getAlbum(albumId: String): Result<List<Track>>
    suspend fun getArtist(artistId: String): Result<List<Track>>
}'''
content = content.replace('}', new_methods)

with open('app/src/main/java/com/twilitmusic/app/domain/repository/MusicSource.kt', 'w') as f:
    f.write(content)
