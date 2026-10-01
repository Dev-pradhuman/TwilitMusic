import sys

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'r') as f:
    content = f.read()

network_code = '''
    val isOffline = MutableStateFlow(false)

    init {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) { isOffline.value = false }
            override fun onLost(network: Network) { isOffline.value = true }
        }
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        
        viewModelScope.launch {'''

content = content.replace('    init {\n        viewModelScope.launch {', network_code)

with open('app/src/main/java/com/twilitmusic/app/ui/MainViewModel.kt', 'w') as f:
    f.write(content)
