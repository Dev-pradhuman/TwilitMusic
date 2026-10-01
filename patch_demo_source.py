import sys

with open('app/src/main/java/com/twilitmusic/app/data/repository/DemoMusicSource.kt', 'r') as f:
    content = f.read()

content = content.replace('override suspend fun search(query: String): List<Track> {', 'override suspend fun search(query: String): Result<List<Track>> = runCatching {')
content = content.replace('    return tracks.filter { it.title.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true) }\n}', '    tracks.filter { it.title.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true) }\n}')

content = content.replace('override suspend fun getTrack(id: String): Track? {', 'override suspend fun getTrack(id: String): Result<Track?> = runCatching {')
content = content.replace('    return tracks.find { it.id == id }\n}', '    tracks.find { it.id == id }\n}')

content = content.replace('override suspend fun getStreamUrl(id: String): String? {', 'override suspend fun getStreamUrl(id: String): Result<String?> = runCatching {')
content = content.replace('    return getTrack(id)?.sourceUrl\n}', '    getTrack(id).getOrNull()?.sourceUrl\n}')

content = content.replace('override suspend fun getFeaturedTracks(): List<Track> {', 'override suspend fun getFeaturedTracks(): Result<List<Track>> = runCatching {')
content = content.replace('    return tracks.take(5)\n}', '    tracks.take(5)\n}')

content = content.replace('override suspend fun getNewTracks(): List<Track> {', 'override suspend fun getNewTracks(): Result<List<Track>> = runCatching {')
content = content.replace('    return tracks.drop(5).take(5)\n}', '    tracks.drop(5).take(5)\n}')

new_methods = '''
    override suspend fun getAlbum(albumId: String): Result<List<Track>> = runCatching {
        tracks.filter { it.title.contains(albumId, true) }
    }

    override suspend fun getArtist(artistId: String): Result<List<Track>> = runCatching {
        tracks.filter { it.artist.contains(artistId, true) }
    }
}'''
content = content.replace('}', new_methods)

with open('app/src/main/java/com/twilitmusic/app/data/repository/DemoMusicSource.kt', 'w') as f:
    f.write(content)
