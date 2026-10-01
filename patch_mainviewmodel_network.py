import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

content = content.replace('import androidx.lifecycle.ViewModel', 'import androidx.lifecycle.ViewModel\nimport android.net.ConnectivityManager\nimport android.net.Network\nimport android.net.NetworkCapabilities\nimport android.net.NetworkRequest')

init_code = '''
    val isOffline = MutableStateFlow(false)

    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { isOffline.value = false }
            override fun onLost(network: Network) { isOffline.value = true }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        
        viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)
            loadTracks()
        }
    }'''

if 'val isOffline =' not in content:
    content = content.replace('''    init {
        viewModelScope.launch {
            _uiState.value = MainUiState(isLoading = true)
            loadTracks()
        }
    }''', init_code)

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
