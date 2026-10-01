package com.twilitmusic.app.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.twilitmusic.app.`data`.local.entity.PlayHistoryEntity
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
public class PlayHistoryDao_Impl(
  __db: RoomDatabase,
) : PlayHistoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlayHistoryEntity: EntityInsertAdapter<PlayHistoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlayHistoryEntity = object : EntityInsertAdapter<PlayHistoryEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `play_history` (`id`,`track_id`,`title`,`artist`,`artUrl`,`sourceUrl`,`played_at`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlayHistoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.trackId)
        statement.bindText(3, entity.title)
        statement.bindText(4, entity.artist)
        statement.bindText(5, entity.artUrl)
        statement.bindText(6, entity.sourceUrl)
        statement.bindLong(7, entity.playedAt)
      }
    }
  }

  public override suspend fun addPlayHistory(track: PlayHistoryEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPlayHistoryEntity.insert(_connection, track)
  }

  public override fun getPlayHistory(limit: Int): Flow<List<PlayHistoryEntity>> {
    val _sql: String = "SELECT * FROM play_history ORDER BY played_at DESC LIMIT ?"
    return createFlow(__db, false, arrayOf("play_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfTrackId: Int = getColumnIndexOrThrow(_stmt, "track_id")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _cursorIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _cursorIndexOfArtUrl: Int = getColumnIndexOrThrow(_stmt, "artUrl")
        val _cursorIndexOfSourceUrl: Int = getColumnIndexOrThrow(_stmt, "sourceUrl")
        val _cursorIndexOfPlayedAt: Int = getColumnIndexOrThrow(_stmt, "played_at")
        val _result: MutableList<PlayHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlayHistoryEntity
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
          val _tmpPlayedAt: Long
          _tmpPlayedAt = _stmt.getLong(_cursorIndexOfPlayedAt)
          _item =
              PlayHistoryEntity(_tmpId,_tmpTrackId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpPlayedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
