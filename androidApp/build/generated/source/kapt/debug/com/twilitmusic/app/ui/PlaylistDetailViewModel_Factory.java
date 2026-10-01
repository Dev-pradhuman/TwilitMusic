package com.twilitmusic.app.ui;

import androidx.lifecycle.SavedStateHandle;
import com.twilitmusic.app.data.local.dao.PlaylistDao;
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
public final class PlaylistDetailViewModel_Factory implements Factory<PlaylistDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<PlaylistDao> playlistDaoProvider;

  public PlaylistDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PlaylistDao> playlistDaoProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.playlistDaoProvider = playlistDaoProvider;
  }

  @Override
  public PlaylistDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), playlistDaoProvider.get());
  }

  public static PlaylistDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<PlaylistDao> playlistDaoProvider) {
    return new PlaylistDetailViewModel_Factory(savedStateHandleProvider, playlistDaoProvider);
  }

  public static PlaylistDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      PlaylistDao playlistDao) {
    return new PlaylistDetailViewModel(savedStateHandle, playlistDao);
  }
}
