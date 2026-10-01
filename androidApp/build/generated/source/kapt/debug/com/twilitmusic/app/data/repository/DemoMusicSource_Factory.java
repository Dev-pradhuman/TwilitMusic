package com.twilitmusic.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DemoMusicSource_Factory implements Factory<DemoMusicSource> {
  @Override
  public DemoMusicSource get() {
    return newInstance();
  }

  public static DemoMusicSource_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DemoMusicSource newInstance() {
    return new DemoMusicSource();
  }

  private static final class InstanceHolder {
    private static final DemoMusicSource_Factory INSTANCE = new DemoMusicSource_Factory();
  }
}
