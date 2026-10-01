package com.twilitmusic.app.playback;

import android.content.Context;
import com.twilitmusic.app.data.local.dao.QueueDao;
import com.twilitmusic.app.domain.repository.LibraryRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MusicController_Factory implements Factory<MusicController> {
  private final Provider<Context> contextProvider;

  private final Provider<LibraryRepository> libraryRepositoryProvider;

  private final Provider<QueueDao> queueDaoProvider;

  public MusicController_Factory(Provider<Context> contextProvider,
      Provider<LibraryRepository> libraryRepositoryProvider, Provider<QueueDao> queueDaoProvider) {
    this.contextProvider = contextProvider;
    this.libraryRepositoryProvider = libraryRepositoryProvider;
    this.queueDaoProvider = queueDaoProvider;
  }

  @Override
  public MusicController get() {
    return newInstance(contextProvider.get(), libraryRepositoryProvider.get(), queueDaoProvider.get());
  }

  public static MusicController_Factory create(Provider<Context> contextProvider,
      Provider<LibraryRepository> libraryRepositoryProvider, Provider<QueueDao> queueDaoProvider) {
    return new MusicController_Factory(contextProvider, libraryRepositoryProvider, queueDaoProvider);
  }

  public static MusicController newInstance(Context context, LibraryRepository libraryRepository,
      QueueDao queueDao) {
    return new MusicController(context, libraryRepository, queueDao);
  }
}
