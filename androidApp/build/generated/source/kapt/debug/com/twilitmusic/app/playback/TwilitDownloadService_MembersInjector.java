package com.twilitmusic.app.playback;

import androidx.media3.exoplayer.offline.DownloadManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TwilitDownloadService_MembersInjector implements MembersInjector<TwilitDownloadService> {
  private final Provider<DownloadManager> injectedDownloadManagerProvider;

  public TwilitDownloadService_MembersInjector(
      Provider<DownloadManager> injectedDownloadManagerProvider) {
    this.injectedDownloadManagerProvider = injectedDownloadManagerProvider;
  }

  public static MembersInjector<TwilitDownloadService> create(
      Provider<DownloadManager> injectedDownloadManagerProvider) {
    return new TwilitDownloadService_MembersInjector(injectedDownloadManagerProvider);
  }

  @Override
  public void injectMembers(TwilitDownloadService instance) {
    injectInjectedDownloadManager(instance, injectedDownloadManagerProvider.get());
  }

  @InjectedFieldSignature("com.twilitmusic.app.playback.TwilitDownloadService.injectedDownloadManager")
  public static void injectInjectedDownloadManager(TwilitDownloadService instance,
      DownloadManager injectedDownloadManager) {
    instance.injectedDownloadManager = injectedDownloadManager;
  }
}
