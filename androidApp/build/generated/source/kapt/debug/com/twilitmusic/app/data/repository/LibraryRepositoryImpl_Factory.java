package com.twilitmusic.app.data.repository;

import com.twilitmusic.app.data.local.dao.LikedTrackDao;
import com.twilitmusic.app.data.local.dao.PlayHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class LibraryRepositoryImpl_Factory implements Factory<LibraryRepositoryImpl> {
  private final Provider<LikedTrackDao> likedTrackDaoProvider;

  private final Provider<PlayHistoryDao> playHistoryDaoProvider;

  public LibraryRepositoryImpl_Factory(Provider<LikedTrackDao> likedTrackDaoProvider,
      Provider<PlayHistoryDao> playHistoryDaoProvider) {
    this.likedTrackDaoProvider = likedTrackDaoProvider;
    this.playHistoryDaoProvider = playHistoryDaoProvider;
  }

  @Override
  public LibraryRepositoryImpl get() {
    return newInstance(likedTrackDaoProvider.get(), playHistoryDaoProvider.get());
  }

  public static LibraryRepositoryImpl_Factory create(Provider<LikedTrackDao> likedTrackDaoProvider,
      Provider<PlayHistoryDao> playHistoryDaoProvider) {
    return new LibraryRepositoryImpl_Factory(likedTrackDaoProvider, playHistoryDaoProvider);
  }

  public static LibraryRepositoryImpl newInstance(LikedTrackDao likedTrackDao,
      PlayHistoryDao playHistoryDao) {
    return new LibraryRepositoryImpl(likedTrackDao, playHistoryDao);
  }
}
