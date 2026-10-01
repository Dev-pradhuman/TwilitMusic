package com.twilitmusic.app.playback;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0014J\u001e\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0013"}, d2 = {"Lcom/twilitmusic/app/playback/TwilitDownloadService;", "Landroidx/media3/exoplayer/offline/DownloadService;", "()V", "injectedDownloadManager", "Landroidx/media3/exoplayer/offline/DownloadManager;", "getInjectedDownloadManager", "()Landroidx/media3/exoplayer/offline/DownloadManager;", "setInjectedDownloadManager", "(Landroidx/media3/exoplayer/offline/DownloadManager;)V", "getDownloadManager", "getForegroundNotification", "Landroid/app/Notification;", "downloads", "", "Landroidx/media3/exoplayer/offline/Download;", "notMetRequirements", "", "getScheduler", "Landroidx/media3/exoplayer/scheduler/Scheduler;", "androidApp_debug"})
@androidx.annotation.OptIn(markerClass = {androidx.media3.common.util.UnstableApi.class})
public final class TwilitDownloadService extends androidx.media3.exoplayer.offline.DownloadService {
    @javax.inject.Inject()
    public androidx.media3.exoplayer.offline.DownloadManager injectedDownloadManager;
    
    public TwilitDownloadService() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.media3.exoplayer.offline.DownloadManager getInjectedDownloadManager() {
        return null;
    }
    
    public final void setInjectedDownloadManager(@org.jetbrains.annotations.NotNull()
    androidx.media3.exoplayer.offline.DownloadManager p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    protected androidx.media3.exoplayer.offline.DownloadManager getDownloadManager() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    protected androidx.media3.exoplayer.scheduler.Scheduler getScheduler() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    protected android.app.Notification getForegroundNotification(@org.jetbrains.annotations.NotNull()
    java.util.List<androidx.media3.exoplayer.offline.Download> downloads, int notMetRequirements) {
        return null;
    }
}