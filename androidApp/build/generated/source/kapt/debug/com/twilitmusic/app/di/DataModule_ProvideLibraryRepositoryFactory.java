package com.twilitmusic.app.di;

import com.twilitmusic.app.data.repository.LibraryRepositoryImpl;
import com.twilitmusic.app.domain.repository.LibraryRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DataModule_ProvideLibraryRepositoryFactory implements Factory<LibraryRepository> {
  private final Provider<LibraryRepositoryImpl> libraryRepositoryImplProvider;

  public DataModule_ProvideLibraryRepositoryFactory(
      Provider<LibraryRepositoryImpl> libraryRepositoryImplProvider) {
    this.libraryRepositoryImplProvider = libraryRepositoryImplProvider;
  }

  @Override
  public LibraryRepository get() {
    return provideLibraryRepository(libraryRepositoryImplProvider.get());
  }

  public static DataModule_ProvideLibraryRepositoryFactory create(
      Provider<LibraryRepositoryImpl> libraryRepositoryImplProvider) {
    return new DataModule_ProvideLibraryRepositoryFactory(libraryRepositoryImplProvider);
  }

  public static LibraryRepository provideLibraryRepository(
      LibraryRepositoryImpl libraryRepositoryImpl) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideLibraryRepository(libraryRepositoryImpl));
  }
}
