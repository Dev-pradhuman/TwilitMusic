package com.twilitmusic.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.twilitmusic.app.data.local.dao.LikedTrackDao;
import com.twilitmusic.app.data.local.dao.LikedTrackDao_Impl;
import com.twilitmusic.app.data.local.dao.PlayHistoryDao;
import com.twilitmusic.app.data.local.dao.PlayHistoryDao_Impl;
import com.twilitmusic.app.data.local.dao.PlaylistDao;
import com.twilitmusic.app.data.local.dao.PlaylistDao_Impl;
import com.twilitmusic.app.data.local.dao.QueueDao;
import com.twilitmusic.app.data.local.dao.QueueDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TwilitDatabase_Impl extends TwilitDatabase {
  private volatile LikedTrackDao _likedTrackDao;

  private volatile PlaylistDao _playlistDao;

  private volatile PlayHistoryDao _playHistoryDao;

  private volatile QueueDao _queueDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `liked_tracks` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `added_at` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playlists` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `created_at` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playlist_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlist_id` INTEGER NOT NULL, `track_id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL, FOREIGN KEY(`playlist_id`) REFERENCES `playlists`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_playlist_tracks_playlist_id` ON `playlist_tracks` (`playlist_id`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `play_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `track_id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `played_at` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `queue_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trackId` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playback_state` (`id` INTEGER NOT NULL, `currentIndex` INTEGER NOT NULL, `positionMs` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd0bd610c69c916df158424e00e4b729e')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `liked_tracks`");
        db.execSQL("DROP TABLE IF EXISTS `playlists`");
        db.execSQL("DROP TABLE IF EXISTS `playlist_tracks`");
        db.execSQL("DROP TABLE IF EXISTS `play_history`");
        db.execSQL("DROP TABLE IF EXISTS `queue_tracks`");
        db.execSQL("DROP TABLE IF EXISTS `playback_state`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLikedTracks = new HashMap<String, TableInfo.Column>(6);
        _columnsLikedTracks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLikedTracks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLikedTracks.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLikedTracks.put("artUrl", new TableInfo.Column("artUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLikedTracks.put("sourceUrl", new TableInfo.Column("sourceUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLikedTracks.put("added_at", new TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLikedTracks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLikedTracks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLikedTracks = new TableInfo("liked_tracks", _columnsLikedTracks, _foreignKeysLikedTracks, _indicesLikedTracks);
        final TableInfo _existingLikedTracks = TableInfo.read(db, "liked_tracks");
        if (!_infoLikedTracks.equals(_existingLikedTracks)) {
          return new RoomOpenHelper.ValidationResult(false, "liked_tracks(com.twilitmusic.app.data.local.entity.LikedTrackEntity).\n"
                  + " Expected:\n" + _infoLikedTracks + "\n"
                  + " Found:\n" + _existingLikedTracks);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylists = new HashMap<String, TableInfo.Column>(3);
        _columnsPlaylists.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylists.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylists = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylists = new TableInfo("playlists", _columnsPlaylists, _foreignKeysPlaylists, _indicesPlaylists);
        final TableInfo _existingPlaylists = TableInfo.read(db, "playlists");
        if (!_infoPlaylists.equals(_existingPlaylists)) {
          return new RoomOpenHelper.ValidationResult(false, "playlists(com.twilitmusic.app.data.local.entity.PlaylistEntity).\n"
                  + " Expected:\n" + _infoPlaylists + "\n"
                  + " Found:\n" + _existingPlaylists);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylistTracks = new HashMap<String, TableInfo.Column>(8);
        _columnsPlaylistTracks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("playlist_id", new TableInfo.Column("playlist_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("track_id", new TableInfo.Column("track_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("artUrl", new TableInfo.Column("artUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("sourceUrl", new TableInfo.Column("sourceUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistTracks.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylistTracks = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPlaylistTracks.add(new TableInfo.ForeignKey("playlists", "CASCADE", "NO ACTION", Arrays.asList("playlist_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPlaylistTracks = new HashSet<TableInfo.Index>(1);
        _indicesPlaylistTracks.add(new TableInfo.Index("index_playlist_tracks_playlist_id", false, Arrays.asList("playlist_id"), Arrays.asList("ASC")));
        final TableInfo _infoPlaylistTracks = new TableInfo("playlist_tracks", _columnsPlaylistTracks, _foreignKeysPlaylistTracks, _indicesPlaylistTracks);
        final TableInfo _existingPlaylistTracks = TableInfo.read(db, "playlist_tracks");
        if (!_infoPlaylistTracks.equals(_existingPlaylistTracks)) {
          return new RoomOpenHelper.ValidationResult(false, "playlist_tracks(com.twilitmusic.app.data.local.entity.PlaylistTrackEntity).\n"
                  + " Expected:\n" + _infoPlaylistTracks + "\n"
                  + " Found:\n" + _existingPlaylistTracks);
        }
        final HashMap<String, TableInfo.Column> _columnsPlayHistory = new HashMap<String, TableInfo.Column>(7);
        _columnsPlayHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("track_id", new TableInfo.Column("track_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("artUrl", new TableInfo.Column("artUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("sourceUrl", new TableInfo.Column("sourceUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayHistory.put("played_at", new TableInfo.Column("played_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayHistory = new TableInfo("play_history", _columnsPlayHistory, _foreignKeysPlayHistory, _indicesPlayHistory);
        final TableInfo _existingPlayHistory = TableInfo.read(db, "play_history");
        if (!_infoPlayHistory.equals(_existingPlayHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "play_history(com.twilitmusic.app.data.local.entity.PlayHistoryEntity).\n"
                  + " Expected:\n" + _infoPlayHistory + "\n"
                  + " Found:\n" + _existingPlayHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsQueueTracks = new HashMap<String, TableInfo.Column>(7);
        _columnsQueueTracks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("trackId", new TableInfo.Column("trackId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("artUrl", new TableInfo.Column("artUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("sourceUrl", new TableInfo.Column("sourceUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueueTracks.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQueueTracks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQueueTracks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQueueTracks = new TableInfo("queue_tracks", _columnsQueueTracks, _foreignKeysQueueTracks, _indicesQueueTracks);
        final TableInfo _existingQueueTracks = TableInfo.read(db, "queue_tracks");
        if (!_infoQueueTracks.equals(_existingQueueTracks)) {
          return new RoomOpenHelper.ValidationResult(false, "queue_tracks(com.twilitmusic.app.data.local.entity.QueueTrackEntity).\n"
                  + " Expected:\n" + _infoQueueTracks + "\n"
                  + " Found:\n" + _existingQueueTracks);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaybackState = new HashMap<String, TableInfo.Column>(3);
        _columnsPlaybackState.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackState.put("currentIndex", new TableInfo.Column("currentIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaybackState.put("positionMs", new TableInfo.Column("positionMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaybackState = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaybackState = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaybackState = new TableInfo("playback_state", _columnsPlaybackState, _foreignKeysPlaybackState, _indicesPlaybackState);
        final TableInfo _existingPlaybackState = TableInfo.read(db, "playback_state");
        if (!_infoPlaybackState.equals(_existingPlaybackState)) {
          return new RoomOpenHelper.ValidationResult(false, "playback_state(com.twilitmusic.app.data.local.entity.PlaybackStateEntity).\n"
                  + " Expected:\n" + _infoPlaybackState + "\n"
                  + " Found:\n" + _existingPlaybackState);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d0bd610c69c916df158424e00e4b729e", "bdfb1c51ae06815bd08e5e9b1b5b308b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "liked_tracks","playlists","playlist_tracks","play_history","queue_tracks","playback_state");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `liked_tracks`");
      _db.execSQL("DELETE FROM `playlists`");
      _db.execSQL("DELETE FROM `playlist_tracks`");
      _db.execSQL("DELETE FROM `play_history`");
      _db.execSQL("DELETE FROM `queue_tracks`");
      _db.execSQL("DELETE FROM `playback_state`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LikedTrackDao.class, LikedTrackDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlaylistDao.class, PlaylistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlayHistoryDao.class, PlayHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(QueueDao.class, QueueDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LikedTrackDao likedTrackDao() {
    if (_likedTrackDao != null) {
      return _likedTrackDao;
    } else {
      synchronized(this) {
        if(_likedTrackDao == null) {
          _likedTrackDao = new LikedTrackDao_Impl(this);
        }
        return _likedTrackDao;
      }
    }
  }

  @Override
  public PlaylistDao playlistDao() {
    if (_playlistDao != null) {
      return _playlistDao;
    } else {
      synchronized(this) {
        if(_playlistDao == null) {
          _playlistDao = new PlaylistDao_Impl(this);
        }
        return _playlistDao;
      }
    }
  }

  @Override
  public PlayHistoryDao playHistoryDao() {
    if (_playHistoryDao != null) {
      return _playHistoryDao;
    } else {
      synchronized(this) {
        if(_playHistoryDao == null) {
          _playHistoryDao = new PlayHistoryDao_Impl(this);
        }
        return _playHistoryDao;
      }
    }
  }

  @Override
  public QueueDao queueDao() {
    if (_queueDao != null) {
      return _queueDao;
    } else {
      synchronized(this) {
        if(_queueDao == null) {
          _queueDao = new QueueDao_Impl(this);
        }
        return _queueDao;
      }
    }
  }
}
