package com.twilitmusic.app.di;

import com.twilitmusic.app.data.local.TwilitDatabase;
import com.twilitmusic.app.data.local.dao.QueueDao;
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
public final class DatabaseModule_ProvideQueueDaoFactory implements Factory<QueueDao> {
  private final Provider<TwilitDatabase> databaseProvider;

  public DatabaseModule_ProvideQueueDaoFactory(Provider<TwilitDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public QueueDao get() {
    return provideQueueDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideQueueDaoFactory create(
      Provider<TwilitDatabase> databaseProvider) {
    return new DatabaseModule_ProvideQueueDaoFactory(databaseProvider);
  }

  public static QueueDao provideQueueDao(TwilitDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideQueueDao(database));
  }
}
