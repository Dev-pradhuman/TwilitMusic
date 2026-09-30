import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    content = f.read()

content = content.replace('import org.burnoutcrew.reorderable.ReorderableItem\nimport org.burnoutcrew.reorderable.detectReorderAfterLongPress\nimport org.burnoutcrew.reorderable.rememberReorderableLazyListState\nimport org.burnoutcrew.reorderable.reorderable', 'import sh.calvin.reorderable.ReorderableItem\nimport sh.calvin.reorderable.rememberReorderableLazyListState\nimport androidx.compose.foundation.lazy.rememberLazyListState\nimport sh.calvin.reorderable.longPressDraggableHandle')

content = content.replace('import androidx.compose.runtime.mutableStateListOf', 'import androidx.compose.runtime.mutableStateListOf\nimport androidx.compose.runtime.mutableStateOf\nimport androidx.compose.runtime.getValue\nimport androidx.compose.runtime.setValue')

old_state = '''    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            localQueue = localQueue.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
        },
        onDragEnd = { startIndex, endIndex ->
            viewModel.moveTrack(startIndex, endIndex)
        }
    )'''

new_state = '''    var dragStartIndex by remember { mutableStateOf(-1) }
    var dragEndIndex by remember { mutableStateOf(-1) }

    val lazyListState = rememberLazyListState()
    val state = rememberReorderableLazyListState(lazyListState) { from, to ->
        localQueue = localQueue.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
        if (dragStartIndex == -1) dragStartIndex = from.index
        dragEndIndex = to.index
    }'''
content = content.replace(old_state, new_state)

old_lazy_column = '''        LazyColumn(
            state = state.listState,
            contentPadding = padding,
            modifier = Modifier.reorderable(state)
        ) {'''
new_lazy_column = '''        LazyColumn(
            state = lazyListState,
            contentPadding = padding
        ) {'''
content = content.replace(old_lazy_column, new_lazy_column)

old_row_mod = '''                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.playQueue(localQueue, index) }
                            .background(
                                if (isDragging) MaterialTheme.colorScheme.surfaceVariant
                                else MaterialTheme.colorScheme.surface
                            )
                            .padding(16.dp),'''
new_row_mod = '''                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.playQueue(localQueue, index) }
                            .background(
                                if (isDragging) MaterialTheme.colorScheme.surfaceVariant
                                else MaterialTheme.colorScheme.surface
                            )
                            .padding(16.dp),'''

old_drag_handle = '''                        Icon(
                            imageVector = Icons.Default.DragHandle,
                            contentDescription = null,
                            modifier = Modifier.detectReorderAfterLongPress(state)
                        )'''
new_drag_handle = '''                        Icon(
                            imageVector = Icons.Default.DragHandle,
                            contentDescription = null,
                            modifier = Modifier.longPressDraggableHandle(
                                onDragStopped = {
                                    if (dragStartIndex != -1 && dragEndIndex != -1 && dragStartIndex != dragEndIndex) {
                                        viewModel.moveTrack(dragStartIndex, dragEndIndex)
                                    }
                                    dragStartIndex = -1
                                    dragEndIndex = -1
                                }
                            )
                        )'''
content = content.replace(old_drag_handle, new_drag_handle)

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.write(content)
