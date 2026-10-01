package com.twilitmusic.app.data.repository;

import com.twilitmusic.app.data.remote.JamendoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class JamendoMusicSource_Factory implements Factory<JamendoMusicSource> {
  private final Provider<JamendoApi> apiProvider;

  public JamendoMusicSource_Factory(Provider<JamendoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public JamendoMusicSource get() {
    return newInstance(apiProvider.get());
  }

  public static JamendoMusicSource_Factory create(Provider<JamendoApi> apiProvider) {
    return new JamendoMusicSource_Factory(apiProvider);
  }

  public static JamendoMusicSource newInstance(JamendoApi api) {
    return new JamendoMusicSource(api);
  }
}
