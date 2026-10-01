package com.twilitmusic.app.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.twilitmusic.app.`data`.local.dao.LikedTrackDao
import com.twilitmusic.app.`data`.local.dao.LikedTrackDao_Impl
import com.twilitmusic.app.`data`.local.dao.PlayHistoryDao
import com.twilitmusic.app.`data`.local.dao.PlayHistoryDao_Impl
import com.twilitmusic.app.`data`.local.dao.PlaylistDao
import com.twilitmusic.app.`data`.local.dao.PlaylistDao_Impl
import com.twilitmusic.app.`data`.local.dao.QueueDao
import com.twilitmusic.app.`data`.local.dao.QueueDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TwilitDatabase_Impl : TwilitDatabase() {
  private val _likedTrackDao: Lazy<LikedTrackDao> = lazy {
    LikedTrackDao_Impl(this)
  }


  private val _playlistDao: Lazy<PlaylistDao> = lazy {
    PlaylistDao_Impl(this)
  }


  private val _playHistoryDao: Lazy<PlayHistoryDao> = lazy {
    PlayHistoryDao_Impl(this)
  }


  private val _queueDao: Lazy<QueueDao> = lazy {
    QueueDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2,
        "d0bd610c69c916df158424e00e4b729e", "bdfb1c51ae06815bd08e5e9b1b5b308b") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `liked_tracks` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `added_at` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playlists` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `created_at` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playlist_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlist_id` INTEGER NOT NULL, `track_id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL, FOREIGN KEY(`playlist_id`) REFERENCES `playlists`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playlist_tracks_playlist_id` ON `playlist_tracks` (`playlist_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `play_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `track_id` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `played_at` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `queue_tracks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `trackId` TEXT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `artUrl` TEXT NOT NULL, `sourceUrl` TEXT NOT NULL, `position` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playback_state` (`id` INTEGER NOT NULL, `currentIndex` INTEGER NOT NULL, `positionMs` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd0bd610c69c916df158424e00e4b729e')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `liked_tracks`")
        connection.execSQL("DROP TABLE IF EXISTS `playlists`")
        connection.execSQL("DROP TABLE IF EXISTS `playlist_tracks`")
        connection.execSQL("DROP TABLE IF EXISTS `play_history`")
        connection.execSQL("DROP TABLE IF EXISTS `queue_tracks`")
        connection.execSQL("DROP TABLE IF EXISTS `playback_state`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsLikedTracks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLikedTracks.put("id", TableInfo.Column("id", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLikedTracks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLikedTracks.put("artist", TableInfo.Column("artist", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLikedTracks.put("artUrl", TableInfo.Column("artUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLikedTracks.put("sourceUrl", TableInfo.Column("sourceUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsLikedTracks.put("added_at", TableInfo.Column("added_at", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLikedTracks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLikedTracks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoLikedTracks: TableInfo = TableInfo("liked_tracks", _columnsLikedTracks,
            _foreignKeysLikedTracks, _indicesLikedTracks)
        val _existingLikedTracks: TableInfo = read(connection, "liked_tracks")
        if (!_infoLikedTracks.equals(_existingLikedTracks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |liked_tracks(com.twilitmusic.app.data.local.entity.LikedTrackEntity).
              | Expected:
              |""".trimMargin() + _infoLikedTracks + """
              |
              | Found:
              |""".trimMargin() + _existingLikedTracks)
        }
        val _columnsPlaylists: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaylists.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylists.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylists.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaylists: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlaylists: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlaylists: TableInfo = TableInfo("playlists", _columnsPlaylists,
            _foreignKeysPlaylists, _indicesPlaylists)
        val _existingPlaylists: TableInfo = read(connection, "playlists")
        if (!_infoPlaylists.equals(_existingPlaylists)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playlists(com.twilitmusic.app.data.local.entity.PlaylistEntity).
              | Expected:
              |""".trimMargin() + _infoPlaylists + """
              |
              | Found:
              |""".trimMargin() + _existingPlaylists)
        }
        val _columnsPlaylistTracks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaylistTracks.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("playlist_id", TableInfo.Column("playlist_id", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("track_id", TableInfo.Column("track_id", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("artist", TableInfo.Column("artist", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("artUrl", TableInfo.Column("artUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("sourceUrl", TableInfo.Column("sourceUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistTracks.put("position", TableInfo.Column("position", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaylistTracks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPlaylistTracks.add(TableInfo.ForeignKey("playlists", "CASCADE", "NO ACTION",
            listOf("playlist_id"), listOf("id")))
        val _indicesPlaylistTracks: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPlaylistTracks.add(TableInfo.Index("index_playlist_tracks_playlist_id", false,
            listOf("playlist_id"), listOf("ASC")))
        val _infoPlaylistTracks: TableInfo = TableInfo("playlist_tracks", _columnsPlaylistTracks,
            _foreignKeysPlaylistTracks, _indicesPlaylistTracks)
        val _existingPlaylistTracks: TableInfo = read(connection, "playlist_tracks")
        if (!_infoPlaylistTracks.equals(_existingPlaylistTracks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playlist_tracks(com.twilitmusic.app.data.local.entity.PlaylistTrackEntity).
              | Expected:
              |""".trimMargin() + _infoPlaylistTracks + """
              |
              | Found:
              |""".trimMargin() + _existingPlaylistTracks)
        }
        val _columnsPlayHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlayHistory.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("track_id", TableInfo.Column("track_id", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("artist", TableInfo.Column("artist", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("artUrl", TableInfo.Column("artUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("sourceUrl", TableInfo.Column("sourceUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlayHistory.put("played_at", TableInfo.Column("played_at", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlayHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlayHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlayHistory: TableInfo = TableInfo("play_history", _columnsPlayHistory,
            _foreignKeysPlayHistory, _indicesPlayHistory)
        val _existingPlayHistory: TableInfo = read(connection, "play_history")
        if (!_infoPlayHistory.equals(_existingPlayHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |play_history(com.twilitmusic.app.data.local.entity.PlayHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoPlayHistory + """
              |
              | Found:
              |""".trimMargin() + _existingPlayHistory)
        }
        val _columnsQueueTracks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsQueueTracks.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("trackId", TableInfo.Column("trackId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("artist", TableInfo.Column("artist", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("artUrl", TableInfo.Column("artUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("sourceUrl", TableInfo.Column("sourceUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsQueueTracks.put("position", TableInfo.Column("position", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysQueueTracks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesQueueTracks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoQueueTracks: TableInfo = TableInfo("queue_tracks", _columnsQueueTracks,
            _foreignKeysQueueTracks, _indicesQueueTracks)
        val _existingQueueTracks: TableInfo = read(connection, "queue_tracks")
        if (!_infoQueueTracks.equals(_existingQueueTracks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |queue_tracks(com.twilitmusic.app.data.local.entity.QueueTrackEntity).
              | Expected:
              |""".trimMargin() + _infoQueueTracks + """
              |
              | Found:
              |""".trimMargin() + _existingQueueTracks)
        }
        val _columnsPlaybackState: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaybackState.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackState.put("currentIndex", TableInfo.Column("currentIndex", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackState.put("positionMs", TableInfo.Column("positionMs", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaybackState: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlaybackState: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlaybackState: TableInfo = TableInfo("playback_state", _columnsPlaybackState,
            _foreignKeysPlaybackState, _indicesPlaybackState)
        val _existingPlaybackState: TableInfo = read(connection, "playback_state")
        if (!_infoPlaybackState.equals(_existingPlaybackState)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playback_state(com.twilitmusic.app.data.local.entity.PlaybackStateEntity).
              | Expected:
              |""".trimMargin() + _infoPlaybackState + """
              |
              | Found:
              |""".trimMargin() + _existingPlaybackState)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "liked_tracks", "playlists",
        "playlist_tracks", "play_history", "queue_tracks", "playback_state")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(LikedTrackDao::class, LikedTrackDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaylistDao::class, PlaylistDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlayHistoryDao::class, PlayHistoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(QueueDao::class, QueueDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun likedTrackDao(): LikedTrackDao = _likedTrackDao.value

  public override fun playlistDao(): PlaylistDao = _playlistDao.value

  public override fun playHistoryDao(): PlayHistoryDao = _playHistoryDao.value

  public override fun queueDao(): QueueDao = _queueDao.value
}
