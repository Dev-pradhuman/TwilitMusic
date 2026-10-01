package com.twilitmusic.app.di;

import com.twilitmusic.app.data.local.TwilitDatabase;
import com.twilitmusic.app.data.local.dao.PlayHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvidePlayHistoryDaoFactory implements Factory<PlayHistoryDao> {
  private final Provider<TwilitDatabase> databaseProvider;

  public DatabaseModule_ProvidePlayHistoryDaoFactory(Provider<TwilitDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PlayHistoryDao get() {
    return providePlayHistoryDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePlayHistoryDaoFactory create(
      Provider<TwilitDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePlayHistoryDaoFactory(databaseProvider);
  }

  public static PlayHistoryDao providePlayHistoryDao(TwilitDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlayHistoryDao(database));
  }
}
