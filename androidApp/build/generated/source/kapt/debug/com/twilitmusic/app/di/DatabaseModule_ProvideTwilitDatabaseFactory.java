package com.twilitmusic.app.di;

import android.content.Context;
import com.twilitmusic.app.data.local.TwilitDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideTwilitDatabaseFactory implements Factory<TwilitDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideTwilitDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TwilitDatabase get() {
    return provideTwilitDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideTwilitDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideTwilitDatabaseFactory(contextProvider);
  }

  public static TwilitDatabase provideTwilitDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTwilitDatabase(context));
  }
}
