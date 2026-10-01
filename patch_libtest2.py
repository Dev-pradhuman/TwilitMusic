import sys

with open('app/src/test/java/com/twilitmusic/app/data/repository/LibraryRepositoryImplTest.kt', 'r') as f:
    content = f.read()

content = content.replace('PlayHistoryEntity("1", "T1", "A1", "U1", "S1", 123)', 'PlayHistoryEntity(id = 0L, trackId = "1", title = "T1", artist = "A1", artUrl = "U1", sourceUrl = "S1", playedAt = 123L)')
content = content.replace('repository.addToHistory(sampleTrack)', 'repository.addPlayHistory(sampleTrack)')

with open('app/src/test/java/com/twilitmusic/app/data/repository/LibraryRepositoryImplTest.kt', 'w') as f:
    f.write(content)
