package com.twilitmusic.app.di;

import com.twilitmusic.app.data.remote.JamendoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class NetworkModule_ProvideJamendoApiFactory implements Factory<JamendoApi> {
  @Override
  public JamendoApi get() {
    return provideJamendoApi();
  }

  public static NetworkModule_ProvideJamendoApiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static JamendoApi provideJamendoApi() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideJamendoApi());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideJamendoApiFactory INSTANCE = new NetworkModule_ProvideJamendoApiFactory();
  }
}
