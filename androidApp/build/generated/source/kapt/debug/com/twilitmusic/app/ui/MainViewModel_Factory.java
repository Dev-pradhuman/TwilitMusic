package com.twilitmusic.app.ui;

import android.app.Application;
import android.content.Context;
import androidx.media3.exoplayer.offline.DownloadManager;
import com.twilitmusic.app.data.local.dao.QueueDao;
import com.twilitmusic.app.domain.repository.LibraryRepository;
import com.twilitmusic.app.domain.repository.MusicSource;
import com.twilitmusic.app.playback.MusicController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<MusicSource> musicSourceProvider;

  private final Provider<MusicController> musicControllerProvider;

  private final Provider<Context> contextProvider;

  private final Provider<DownloadManager> downloadManagerProvider;

  private final Provider<LibraryRepository> libraryRepositoryProvider;

  private final Provider<QueueDao> queueDaoProvider;

  public MainViewModel_Factory(Provider<Application> applicationProvider,
      Provider<MusicSource> musicSourceProvider, Provider<MusicController> musicControllerProvider,
      Provider<Context> contextProvider, Provider<DownloadManager> downloadManagerProvider,
      Provider<LibraryRepository> libraryRepositoryProvider, Provider<QueueDao> queueDaoProvider) {
    this.applicationProvider = applicationProvider;
    this.musicSourceProvider = musicSourceProvider;
    this.musicControllerProvider = musicControllerProvider;
    this.contextProvider = contextProvider;
    this.downloadManagerProvider = downloadManagerProvider;
    this.libraryRepositoryProvider = libraryRepositoryProvider;
    this.queueDaoProvider = queueDaoProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(applicationProvider.get(), musicSourceProvider.get(), musicControllerProvider.get(), contextProvider.get(), downloadManagerProvider.get(), libraryRepositoryProvider.get(), queueDaoProvider.get());
  }

  public static MainViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<MusicSource> musicSourceProvider, Provider<MusicController> musicControllerProvider,
      Provider<Context> contextProvider, Provider<DownloadManager> downloadManagerProvider,
      Provider<LibraryRepository> libraryRepositoryProvider, Provider<QueueDao> queueDaoProvider) {
    return new MainViewModel_Factory(applicationProvider, musicSourceProvider, musicControllerProvider, contextProvider, downloadManagerProvider, libraryRepositoryProvider, queueDaoProvider);
  }

  public static MainViewModel newInstance(Application application, MusicSource musicSource,
      MusicController musicController, Context context, DownloadManager downloadManager,
      LibraryRepository libraryRepository, QueueDao queueDao) {
    return new MainViewModel(application, musicSource, musicController, context, downloadManager, libraryRepository, queueDao);
  }
}
