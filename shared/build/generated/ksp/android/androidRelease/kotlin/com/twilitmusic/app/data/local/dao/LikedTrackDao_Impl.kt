package com.twilitmusic.app.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.twilitmusic.app.`data`.local.entity.LikedTrackEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class LikedTrackDao_Impl(
  __db: RoomDatabase,
) : LikedTrackDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfLikedTrackEntity: EntityInsertAdapter<LikedTrackEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfLikedTrackEntity = object : EntityInsertAdapter<LikedTrackEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `liked_tracks` (`id`,`title`,`artist`,`artUrl`,`sourceUrl`,`added_at`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LikedTrackEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.artist)
        statement.bindText(4, entity.artUrl)
        statement.bindText(5, entity.sourceUrl)
        statement.bindLong(6, entity.addedAt)
      }
    }
  }

  public override suspend fun addLikedTrack(track: LikedTrackEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfLikedTrackEntity.insert(_connection, track)
  }

  public override fun getLikedTracks(): Flow<List<LikedTrackEntity>> {
    val _sql: String = "SELECT * FROM liked_tracks ORDER BY added_at DESC"
    return createFlow(__db, false, arrayOf("liked_tracks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _cursorIndexOfArtUrl: Int = getColumnIndexOrThrow(_stmt, "artUrl")
        val _cursorIndexOfSourceUrl: Int = getColumnIndexOrThrow(_stmt, "sourceUrl")
        val _cursorIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<LikedTrackEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LikedTrackEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_cursorIndexOfId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_cursorIndexOfTitle)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_cursorIndexOfArtist)
          val _tmpArtUrl: String
          _tmpArtUrl = _stmt.getText(_cursorIndexOfArtUrl)
          val _tmpSourceUrl: String
          _tmpSourceUrl = _stmt.getText(_cursorIndexOfSourceUrl)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_cursorIndexOfAddedAt)
          _item = LikedTrackEntity(_tmpId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun isLiked(id: String): Flow<Boolean> {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM liked_tracks WHERE id = ?)"
    return createFlow(__db, false, arrayOf("liked_tracks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _result: Boolean
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp != 0
        } else {
          _result = false
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeLikedTrack(id: String) {
    val _sql: String = "DELETE FROM liked_tracks WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
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
