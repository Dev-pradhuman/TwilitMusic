import os
import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/NowPlayingScreen.kt'
with open(path, 'r') as f:
    content = f.read()

# Remove rememberReorderableLazyListState and drag logic
content = re.sub(r'val state = rememberReorderableLazyListState.*?dragEndIndex = to\.index\n\s*\}', '', content, flags=re.DOTALL)
content = content.replace('var dragStartIndex by remember { mutableStateOf(-1) }', '')
content = content.replace('var dragEndIndex by remember { mutableStateOf(-1) }', '')
content = content.replace(', state = state', '') # from lazy column

# Remove items block that uses ReorderableItem
# Find the exact items block.
items_re = r'items\(localQueue\.size, key = \{ it \}\) \{ index ->.*?\n\s*ReorderableItem.*?\{ isDragging ->'
content = re.sub(items_re, 'items(localQueue.size) { index ->', content, flags=re.DOTALL)
content = content.replace('Modifier.draggableHandle()', 'Modifier')

# We'll just replace the whole LazyColumn for queue to keep it simple.
# Wait, it's easier to just strip the specific modifier and ReorderableItem.
# Since it's nested heavily, let me just replace the whole queue tab content.

