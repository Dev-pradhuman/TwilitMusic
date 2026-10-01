package com.twilitmusic.app.ui;

import com.twilitmusic.app.data.local.dao.PlaylistDao;
import com.twilitmusic.app.domain.repository.LibraryRepository;
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
public final class LibraryViewModel_Factory implements Factory<LibraryViewModel> {
  private final Provider<LibraryRepository> libraryRepositoryProvider;

  private final Provider<PlaylistDao> playlistDaoProvider;

  public LibraryViewModel_Factory(Provider<LibraryRepository> libraryRepositoryProvider,
      Provider<PlaylistDao> playlistDaoProvider) {
    this.libraryRepositoryProvider = libraryRepositoryProvider;
    this.playlistDaoProvider = playlistDaoProvider;
  }

  @Override
  public LibraryViewModel get() {
    return newInstance(libraryRepositoryProvider.get(), playlistDaoProvider.get());
  }

  public static LibraryViewModel_Factory create(
      Provider<LibraryRepository> libraryRepositoryProvider,
      Provider<PlaylistDao> playlistDaoProvider) {
    return new LibraryViewModel_Factory(libraryRepositoryProvider, playlistDaoProvider);
  }

  public static LibraryViewModel newInstance(LibraryRepository libraryRepository,
      PlaylistDao playlistDao) {
    return new LibraryViewModel(libraryRepository, playlistDao);
  }
}
