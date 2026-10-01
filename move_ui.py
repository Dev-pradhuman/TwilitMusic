import os
import shutil
import re

ui_src = 'androidApp/src/main/java/com/twilitmusic/app/ui'
ui_dst = 'shared/src/commonMain/kotlin/com/twilitmusic/app/ui'

if not os.path.exists(ui_dst):
    os.makedirs(ui_dst)

for root, _, files in os.walk(ui_src):
    for f in files:
        if f == 'MainActivity.kt':
            continue
        src_path = os.path.join(root, f)
        dst_dir = root.replace(ui_src, ui_dst)
        if not os.path.exists(dst_dir):
            os.makedirs(dst_dir)
        
        dst_path = os.path.join(dst_dir, f)
        with open(src_path, 'r') as file:
            content = file.read()
        
        # Strip Hilt and replace viewModel with koinViewModel
        content = re.sub(r'@HiltViewModel\n?', '', content)
        content = re.sub(r'import dagger\.hilt\.android\.lifecycle\.HiltViewModel\n?', '', content)
        content = re.sub(r'@Inject\s*constructor\s*', '', content)
        content = re.sub(r'import javax\.inject\.Inject\n?', '', content)
        
        content = content.replace('androidx.lifecycle.viewModelScope', 'androidx.lifecycle.viewModelScope') # keep viewModelScope
        
        # Navigation
        content = content.replace('import androidx.hilt.navigation.compose.hiltViewModel', 'import org.koin.compose.viewmodel.koinViewModel')
        content = content.replace('hiltViewModel()', 'koinViewModel()')
        
        # Replace Android ViewModels with Jetbrains ViewModels
        content = content.replace('import androidx.lifecycle.ViewModel', 'import androidx.lifecycle.ViewModel')
        
        # Media3 imports in ViewModels need to be abstracted
        # (This is already done for AudioPlayer, but we need to check if there are left-overs)
        
        with open(dst_path, 'w') as file:
            file.write(content)
