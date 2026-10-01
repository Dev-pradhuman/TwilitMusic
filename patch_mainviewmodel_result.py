import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('musicSource.getFeaturedTracks()', 'musicSource.getFeaturedTracks().getOrDefault(emptyList())')
content = content.replace('musicSource.getNewTracks()', 'musicSource.getNewTracks().getOrDefault(emptyList())')

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
