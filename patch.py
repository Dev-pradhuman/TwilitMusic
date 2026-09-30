import sys

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'r') as f:
    lines = f.readlines()

out = []
in_queuesheet = False
for line in lines:
    if 'fun QueueSheet(' in line:
        in_queuesheet = True
        out.append(line)
        continue
    if not in_queuesheet:
        out.append(line)

out.extend([
    '    val queueFlow by viewModel.musicController.queue.collectAsState()\n',
    '    var localQueue by remember { mutableStateOf(queueFlow) }\n',
    '    var dragStartIndex by remember { mutableStateOf(-1) }\n',
    '    var dragEndIndex by remember { mutableStateOf(-1) }\n',
    '\n',
    '    LaunchedEffect(queueFlow) {\n',
    '        localQueue = queueFlow\n',
    '    }\n',
    '\n',
    '    val lazyListState = rememberLazyListState()\n',
    '    val state = rememberReorderableLazyListState(lazyListState) { from, to ->\n',
    '        localQueue = localQueue.toMutableList().apply {\n',
    '            add(to.index, removeAt(from.index))\n',
    '        }\n',
    '        if (dragStartIndex == -1) dragStartIndex = from.index\n',
    '        dragEndIndex = to.index\n',
    '    }\n',
    '\n',
    '    Scaffold(\n',
    '        topBar = {\n',
    '            CenterAlignedTopAppBar(\n',
    '                title = { Text(stringResource(R.string.playing_queue)) },\n',
    '                navigationIcon = {\n',
    '                    IconButton(onClick = onClose) {\n',
    '                        Icon(Icons.Default.KeyboardArrowDown, stringResource(R.string.close))\n',
    '                    }\n',
    '                }\n',
    '            )\n',
    '        }\n',
    '    ) { padding ->\n',
    '        LazyColumn(\n',
    '            state = lazyListState,\n',
    '            contentPadding = padding\n',
    '        ) {\n',
    '            items(localQueue.size, { it }) { index ->\n',
    '                val track = localQueue[index]\n',
    '                ReorderableItem(state, key = index) { isDragging ->\n',
    '                    Row(\n',
    '                        modifier = Modifier\n',
    '                            .fillMaxWidth()\n',
    '                            .clickable { viewModel.playQueue(localQueue, index) }\n',
    '                            .background(\n',
    '                                if (isDragging) MaterialTheme.colorScheme.surfaceVariant\n',
    '                                else MaterialTheme.colorScheme.surface\n',
    '                            )\n',
    '                            .padding(16.dp),\n',
    '                        verticalAlignment = Alignment.CenterVertically\n',
    '                    ) {\n',
    '                        AsyncImage(\n',
    '                            model = track.artUrl,\n',
    '                            contentDescription = track.title,\n',
    '                            modifier = Modifier\n',
    '                                .size(48.dp)\n',
    '                                .clip(MaterialTheme.shapes.small),\n',
    '                            contentScale = ContentScale.Crop\n',
    '                        )\n',
    '                        Spacer(modifier = Modifier.width(16.dp))\n',
    '                        Column(modifier = Modifier.weight(1f)) {\n',
    '                            Text(track.title, style = MaterialTheme.typography.bodyLarge)\n',
    '                            Text(track.artist, style = MaterialTheme.typography.bodyMedium)\n',
    '                        }\n',
    '                        IconButton(onClick = { viewModel.removeTrack(index) }) {\n',
    '                            Icon(Icons.Default.Clear, stringResource(R.string.remove))\n',
    '                        }\n',
    '                        Icon(\n',
    '                            imageVector = Icons.Default.DragHandle,\n',
    '                            contentDescription = null,\n',
    '                            modifier = Modifier.longPressDraggableHandle(\n',
    '                                onDragStopped = {\n',
    '                                    if (dragStartIndex != -1 && dragEndIndex != -1 && dragStartIndex != dragEndIndex) {\n',
    '                                        viewModel.moveTrack(dragStartIndex, dragEndIndex)\n',
    '                                    }\n',
    '                                    dragStartIndex = -1\n',
    '                                    dragEndIndex = -1\n',
    '                                }\n',
    '                            )\n',
    '                        )\n',
    '                    }\n',
    '                }\n',
    '            }\n',
    '        }\n',
    '    }\n',
    '}\n'
])

with open('app/src/main/java/com/twilitmusic/app/ui/NowPlayingScreen.kt', 'w') as f:
    f.writelines(out)

