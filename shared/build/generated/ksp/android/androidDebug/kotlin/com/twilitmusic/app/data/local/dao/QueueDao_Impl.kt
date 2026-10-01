package com.twilitmusic.app.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.twilitmusic.app.`data`.local.entity.PlaybackStateEntity
import com.twilitmusic.app.`data`.local.entity.QueueTrackEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class QueueDao_Impl(
  __db: RoomDatabase,
) : QueueDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfQueueTrackEntity: EntityInsertAdapter<QueueTrackEntity>

  private val __insertAdapterOfPlaybackStateEntity: EntityInsertAdapter<PlaybackStateEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfQueueTrackEntity = object : EntityInsertAdapter<QueueTrackEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `queue_tracks` (`id`,`trackId`,`title`,`artist`,`artUrl`,`sourceUrl`,`position`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: QueueTrackEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.trackId)
        statement.bindText(3, entity.title)
        statement.bindText(4, entity.artist)
        statement.bindText(5, entity.artUrl)
        statement.bindText(6, entity.sourceUrl)
        statement.bindLong(7, entity.position.toLong())
      }
    }
    this.__insertAdapterOfPlaybackStateEntity = object : EntityInsertAdapter<PlaybackStateEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `playback_state` (`id`,`currentIndex`,`positionMs`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaybackStateEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.currentIndex.toLong())
        statement.bindLong(3, entity.positionMs)
      }
    }
  }

  public override suspend fun insertQueue(tracks: List<QueueTrackEntity>): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfQueueTrackEntity.insert(_connection, tracks)
  }

  public override suspend fun savePlaybackState(state: PlaybackStateEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPlaybackStateEntity.insert(_connection, state)
  }

  public override suspend fun saveFullState(tracks: List<QueueTrackEntity>,
      state: PlaybackStateEntity): Unit = performInTransactionSuspending(__db) {
    super@QueueDao_Impl.saveFullState(tracks, state)
  }

  public override suspend fun getQueue(): List<QueueTrackEntity> {
    val _sql: String = "SELECT * FROM queue_tracks ORDER BY position ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfTrackId: Int = getColumnIndexOrThrow(_stmt, "trackId")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _cursorIndexOfArtUrl: Int = getColumnIndexOrThrow(_stmt, "artUrl")
        val _cursorIndexOfSourceUrl: Int = getColumnIndexOrThrow(_stmt, "sourceUrl")
        val _cursorIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _result: MutableList<QueueTrackEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: QueueTrackEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_cursorIndexOfId)
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
              QueueTrackEntity(_tmpId,_tmpTrackId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpPosition)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPlaybackState(): PlaybackStateEntity? {
    val _sql: String = "SELECT * FROM playback_state WHERE id = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfCurrentIndex: Int = getColumnIndexOrThrow(_stmt, "currentIndex")
        val _cursorIndexOfPositionMs: Int = getColumnIndexOrThrow(_stmt, "positionMs")
        val _result: PlaybackStateEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpCurrentIndex: Int
          _tmpCurrentIndex = _stmt.getLong(_cursorIndexOfCurrentIndex).toInt()
          val _tmpPositionMs: Long
          _tmpPositionMs = _stmt.getLong(_cursorIndexOfPositionMs)
          _result = PlaybackStateEntity(_tmpId,_tmpCurrentIndex,_tmpPositionMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearQueue() {
    val _sql: String = "DELETE FROM queue_tracks"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
