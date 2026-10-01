import sys

with open('app/src/main/java/com/twilitmusic/app/ui/AlbumArtistDetailScreens.kt', 'r') as f:
    content = f.read()

content = content.replace('uiState.homeTracks.filter', '(uiState.featuredTracks + uiState.newTracks).filter')

with open('app/src/main/java/com/twilitmusic/app/ui/AlbumArtistDetailScreens.kt', 'w') as f:
    f.write(content)
