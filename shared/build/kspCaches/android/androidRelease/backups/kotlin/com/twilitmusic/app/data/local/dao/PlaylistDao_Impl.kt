package com.twilitmusic.app.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.twilitmusic.app.`data`.local.entity.PlaylistEntity
import com.twilitmusic.app.`data`.local.entity.PlaylistTrackEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaylistDao_Impl(
  __db: RoomDatabase,
) : PlaylistDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlaylistEntity: EntityInsertAdapter<PlaylistEntity>

  private val __insertAdapterOfPlaylistTrackEntity: EntityInsertAdapter<PlaylistTrackEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlaylistEntity = object : EntityInsertAdapter<PlaylistEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `playlists` (`id`,`name`,`created_at`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.createdAt)
      }
    }
    this.__insertAdapterOfPlaylistTrackEntity = object : EntityInsertAdapter<PlaylistTrackEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `playlist_tracks` (`id`,`playlist_id`,`track_id`,`title`,`artist`,`artUrl`,`sourceUrl`,`position`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistTrackEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.playlistId)
        statement.bindText(3, entity.trackId)
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.artist)
        statement.bindText(6, entity.artUrl)
        statement.bindText(7, entity.sourceUrl)
        statement.bindLong(8, entity.position.toLong())
      }
    }
  }

  public override suspend fun createPlaylist(playlist: PlaylistEntity): Long =
      performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlaylistEntity.insertAndReturnId(_connection, playlist)
    _result
  }

  public override suspend fun addTrackToPlaylist(track: PlaylistTrackEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPlaylistTrackEntity.insert(_connection, track)
  }

  public override fun getAllPlaylists(): Flow<List<PlaylistEntity>> {
    val _sql: String = "SELECT * FROM playlists ORDER BY created_at DESC"
    return createFlow(__db, false, arrayOf("playlists")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<PlaylistEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_cursorIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_cursorIndexOfCreatedAt)
          _item = PlaylistEntity(_tmpId,_tmpName,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTracksForPlaylist(playlistId: Long): Flow<List<PlaylistTrackEntity>> {
    val _sql: String = "SELECT * FROM playlist_tracks WHERE playlist_id = ? ORDER BY position ASC"
    return createFlow(__db, false, arrayOf("playlist_tracks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfPlaylistId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _cursorIndexOfTrackId: Int = getColumnIndexOrThrow(_stmt, "track_id")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _cursorIndexOfArtUrl: Int = getColumnIndexOrThrow(_stmt, "artUrl")
        val _cursorIndexOfSourceUrl: Int = getColumnIndexOrThrow(_stmt, "sourceUrl")
        val _cursorIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _result: MutableList<PlaylistTrackEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistTrackEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_cursorIndexOfId)
          val _tmpPlaylistId: Long
          _tmpPlaylistId = _stmt.getLong(_cursorIndexOfPlaylistId)
          val _tmpTrackId: String
          _tmpTrackId = _stmt.getText(_cursorIndexOfTrackId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_cursorIndexOfTitle)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_cursorIndexOfArtist)
          val _tmpArtUrl: String
          _tmpArtUrl = _stmt.getText(_cursorIndexOfArtUrl)
          val _tmpSourceUrl: String
          _tmpSourceUrl = _stmt.getText(_cursorIndexOfSourceUrl)
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_cursorIndexOfPosition).toInt()
          _item =
              PlaylistTrackEntity(_tmpId,_tmpPlaylistId,_tmpTrackId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpPosition)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deletePlaylist(playlistId: Long) {
    val _sql: String = "DELETE FROM playlists WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeTrackFromPlaylist(playlistTrackId: Long) {
    val _sql: String = "DELETE FROM playlist_tracks WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistTrackId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
