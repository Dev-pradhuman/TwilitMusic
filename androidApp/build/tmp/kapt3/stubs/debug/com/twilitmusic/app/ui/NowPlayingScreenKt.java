package com.twilitmusic.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\u001aX\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u001e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u00a8\u0006\u0012"}, d2 = {"NowPlayingScreen", "", "track", "Lcom/twilitmusic/app/domain/model/Track;", "isPlaying", "", "onClose", "Lkotlin/Function0;", "onPlayPause", "onNext", "onPrev", "viewModel", "Lcom/twilitmusic/app/ui/MainViewModel;", "QueueSheet", "formatTime", "", "ms", "", "androidApp_debug"})
public final class NowPlayingScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void NowPlayingScreen(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, boolean isPlaying, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClose, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayPause, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNext, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPrev, @org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.ui.MainViewModel viewModel) {
    }
    
    private static final java.lang.String formatTime(long ms) {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void QueueSheet(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.ui.MainViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClose) {
    }
}