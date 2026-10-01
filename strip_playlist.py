import re

path = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui/PlaylistDetailScreen.kt'
with open(path, 'r') as f:
    content = f.read()

content = content.replace('import androidx.compose.material.icons.filled.DragHandle\n', '')
content = content.replace('Icons.Default.DragHandle', 'Icons.Default.Menu')
content = content.replace('Modifier.draggableHandle()', 'Modifier')

# Find ReorderableItem block and remove it
content = re.sub(r'ReorderableItem.*?\{ isDragging ->', 'run {', content, flags=re.DOTALL)

with open(path, 'w') as f:
    f.write(content)
