package com.twilitmusic.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.media3.exoplayer.offline.DownloadManager;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.twilitmusic.app.data.local.TwilitDatabase;
import com.twilitmusic.app.data.local.dao.LikedTrackDao;
import com.twilitmusic.app.data.local.dao.PlayHistoryDao;
import com.twilitmusic.app.data.local.dao.PlaylistDao;
import com.twilitmusic.app.data.local.dao.QueueDao;
import com.twilitmusic.app.data.remote.JamendoApi;
import com.twilitmusic.app.data.repository.DemoMusicSource;
import com.twilitmusic.app.data.repository.JamendoMusicSource;
import com.twilitmusic.app.data.repository.LibraryRepositoryImpl;
import com.twilitmusic.app.di.DataModule_ProvideLibraryRepositoryFactory;
import com.twilitmusic.app.di.DataModule_ProvideMusicSourceFactory;
import com.twilitmusic.app.di.DatabaseModule_ProvideLikedTrackDaoFactory;
import com.twilitmusic.app.di.DatabaseModule_ProvidePlayHistoryDaoFactory;
import com.twilitmusic.app.di.DatabaseModule_ProvidePlaylistDaoFactory;
import com.twilitmusic.app.di.DatabaseModule_ProvideQueueDaoFactory;
import com.twilitmusic.app.di.DatabaseModule_ProvideTwilitDatabaseFactory;
import com.twilitmusic.app.di.DownloadModule_ProvideDownloadManagerFactory;
import com.twilitmusic.app.di.NetworkModule_ProvideJamendoApiFactory;
import com.twilitmusic.app.domain.repository.LibraryRepository;
import com.twilitmusic.app.domain.repository.MusicSource;
import com.twilitmusic.app.playback.MusicController;
import com.twilitmusic.app.playback.MusicService;
import com.twilitmusic.app.playback.TwilitDownloadService;
import com.twilitmusic.app.playback.TwilitDownloadService_MembersInjector;
import com.twilitmusic.app.ui.LibraryViewModel;
import com.twilitmusic.app.ui.LibraryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.twilitmusic.app.ui.MainActivity;
import com.twilitmusic.app.ui.MainViewModel;
import com.twilitmusic.app.ui.MainViewModel_HiltModules_KeyModule_ProvideFactory;
import com.twilitmusic.app.ui.PlaylistDetailViewModel;
import com.twilitmusic.app.ui.PlaylistDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerTwilitApp_HiltComponents_SingletonC {
  private DaggerTwilitApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public TwilitApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements TwilitApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements TwilitApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements TwilitApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements TwilitApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements TwilitApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements TwilitApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements TwilitApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public TwilitApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends TwilitApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends TwilitApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends TwilitApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends TwilitApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(LibraryViewModel_HiltModules_KeyModule_ProvideFactory.provide(), MainViewModel_HiltModules_KeyModule_ProvideFactory.provide(), PlaylistDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends TwilitApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<LibraryViewModel> libraryViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<PlaylistDetailViewModel> playlistDetailViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.libraryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.playlistDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, javax.inject.Provider<ViewModel>>of("com.twilitmusic.app.ui.LibraryViewModel", ((Provider) libraryViewModelProvider), "com.twilitmusic.app.ui.MainViewModel", ((Provider) mainViewModelProvider), "com.twilitmusic.app.ui.PlaylistDetailViewModel", ((Provider) playlistDetailViewModelProvider));
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<String, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.twilitmusic.app.ui.LibraryViewModel 
          return (T) new LibraryViewModel(singletonCImpl.provideLibraryRepositoryProvider.get(), singletonCImpl.playlistDao());

          case 1: // com.twilitmusic.app.ui.MainViewModel 
          return (T) new MainViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.provideMusicSourceProvider.get(), singletonCImpl.musicControllerProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDownloadManagerProvider.get(), singletonCImpl.provideLibraryRepositoryProvider.get(), singletonCImpl.queueDao());

          case 2: // com.twilitmusic.app.ui.PlaylistDetailViewModel 
          return (T) new PlaylistDetailViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.playlistDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends TwilitApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends TwilitApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectMusicService(MusicService musicService) {
    }

    @Override
    public void injectTwilitDownloadService(TwilitDownloadService twilitDownloadService) {
      injectTwilitDownloadService2(twilitDownloadService);
    }

    @CanIgnoreReturnValue
    private TwilitDownloadService injectTwilitDownloadService2(TwilitDownloadService instance) {
      TwilitDownloadService_MembersInjector.injectInjectedDownloadManager(instance, singletonCImpl.provideDownloadManagerProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends TwilitApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TwilitDatabase> provideTwilitDatabaseProvider;

    private Provider<LibraryRepository> provideLibraryRepositoryProvider;

    private Provider<DemoMusicSource> demoMusicSourceProvider;

    private Provider<JamendoApi> provideJamendoApiProvider;

    private Provider<JamendoMusicSource> jamendoMusicSourceProvider;

    private Provider<MusicSource> provideMusicSourceProvider;

    private Provider<MusicController> musicControllerProvider;

    private Provider<DownloadManager> provideDownloadManagerProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private LikedTrackDao likedTrackDao() {
      return DatabaseModule_ProvideLikedTrackDaoFactory.provideLikedTrackDao(provideTwilitDatabaseProvider.get());
    }

    private PlayHistoryDao playHistoryDao() {
      return DatabaseModule_ProvidePlayHistoryDaoFactory.providePlayHistoryDao(provideTwilitDatabaseProvider.get());
    }

    private LibraryRepositoryImpl libraryRepositoryImpl() {
      return new LibraryRepositoryImpl(likedTrackDao(), playHistoryDao());
    }

    private PlaylistDao playlistDao() {
      return DatabaseModule_ProvidePlaylistDaoFactory.providePlaylistDao(provideTwilitDatabaseProvider.get());
    }

    private QueueDao queueDao() {
      return DatabaseModule_ProvideQueueDaoFactory.provideQueueDao(provideTwilitDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideTwilitDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<TwilitDatabase>(singletonCImpl, 1));
      this.provideLibraryRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<LibraryRepository>(singletonCImpl, 0));
      this.demoMusicSourceProvider = DoubleCheck.provider(new SwitchingProvider<DemoMusicSource>(singletonCImpl, 3));
      this.provideJamendoApiProvider = DoubleCheck.provider(new SwitchingProvider<JamendoApi>(singletonCImpl, 5));
      this.jamendoMusicSourceProvider = DoubleCheck.provider(new SwitchingProvider<JamendoMusicSource>(singletonCImpl, 4));
      this.provideMusicSourceProvider = DoubleCheck.provider(new SwitchingProvider<MusicSource>(singletonCImpl, 2));
      this.musicControllerProvider = DoubleCheck.provider(new SwitchingProvider<MusicController>(singletonCImpl, 6));
      this.provideDownloadManagerProvider = DoubleCheck.provider(new SwitchingProvider<DownloadManager>(singletonCImpl, 7));
    }

    @Override
    public void injectTwilitApp(TwilitApp twilitApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.twilitmusic.app.domain.repository.LibraryRepository 
          return (T) DataModule_ProvideLibraryRepositoryFactory.provideLibraryRepository(singletonCImpl.libraryRepositoryImpl());

          case 1: // com.twilitmusic.app.data.local.TwilitDatabase 
          return (T) DatabaseModule_ProvideTwilitDatabaseFactory.provideTwilitDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.twilitmusic.app.domain.repository.MusicSource 
          return (T) DataModule_ProvideMusicSourceFactory.provideMusicSource(singletonCImpl.demoMusicSourceProvider.get(), singletonCImpl.jamendoMusicSourceProvider.get());

          case 3: // com.twilitmusic.app.data.repository.DemoMusicSource 
          return (T) new DemoMusicSource();

          case 4: // com.twilitmusic.app.data.repository.JamendoMusicSource 
          return (T) new JamendoMusicSource(singletonCImpl.provideJamendoApiProvider.get());

          case 5: // com.twilitmusic.app.data.remote.JamendoApi 
          return (T) NetworkModule_ProvideJamendoApiFactory.provideJamendoApi();

          case 6: // com.twilitmusic.app.playback.MusicController 
          return (T) new MusicController(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideLibraryRepositoryProvider.get(), singletonCImpl.queueDao());

          case 7: // androidx.media3.exoplayer.offline.DownloadManager 
          return (T) DownloadModule_ProvideDownloadManagerFactory.provideDownloadManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
