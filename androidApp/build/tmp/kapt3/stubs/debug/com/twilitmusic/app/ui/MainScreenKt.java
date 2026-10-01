package com.twilitmusic.app.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a<\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0012\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"HomeScreen", "", "uiState", "Lcom/twilitmusic/app/ui/MainUiState;", "onPlayTrack", "Lkotlin/Function1;", "Lcom/twilitmusic/app/domain/model/Track;", "MiniPlayer", "track", "isPlaying", "", "progress", "", "onPlayPause", "Lkotlin/Function0;", "onClick", "TwilitAppScreen", "viewModel", "Lcom/twilitmusic/app/ui/MainViewModel;", "androidApp_debug"})
public final class MainScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void TwilitAppScreen(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.ui.MainViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HomeScreen(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.ui.MainUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.twilitmusic.app.domain.model.Track, kotlin.Unit> onPlayTrack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MiniPlayer(@org.jetbrains.annotations.NotNull()
    com.twilitmusic.app.domain.model.Track track, boolean isPlaying, float progress, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayPause, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}