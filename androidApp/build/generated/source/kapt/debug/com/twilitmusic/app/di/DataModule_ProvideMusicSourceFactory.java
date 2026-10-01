package com.twilitmusic.app.di;

import com.twilitmusic.app.data.repository.DemoMusicSource;
import com.twilitmusic.app.data.repository.JamendoMusicSource;
import com.twilitmusic.app.domain.repository.MusicSource;
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
public final class DataModule_ProvideMusicSourceFactory implements Factory<MusicSource> {
  private final Provider<DemoMusicSource> demoMusicSourceProvider;

  private final Provider<JamendoMusicSource> jamendoMusicSourceProvider;

  public DataModule_ProvideMusicSourceFactory(Provider<DemoMusicSource> demoMusicSourceProvider,
      Provider<JamendoMusicSource> jamendoMusicSourceProvider) {
    this.demoMusicSourceProvider = demoMusicSourceProvider;
    this.jamendoMusicSourceProvider = jamendoMusicSourceProvider;
  }

  @Override
  public MusicSource get() {
    return provideMusicSource(demoMusicSourceProvider.get(), jamendoMusicSourceProvider.get());
  }

  public static DataModule_ProvideMusicSourceFactory create(
      Provider<DemoMusicSource> demoMusicSourceProvider,
      Provider<JamendoMusicSource> jamendoMusicSourceProvider) {
    return new DataModule_ProvideMusicSourceFactory(demoMusicSourceProvider, jamendoMusicSourceProvider);
  }

  public static MusicSource provideMusicSource(DemoMusicSource demoMusicSource,
      JamendoMusicSource jamendoMusicSource) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideMusicSource(demoMusicSource, jamendoMusicSource));
  }
}
