package com.twilitmusic.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.twilitmusic.app.data.local.entity.PlayHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayHistoryDao_Impl implements PlayHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlayHistoryEntity> __insertionAdapterOfPlayHistoryEntity;

  public PlayHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlayHistoryEntity = new EntityInsertionAdapter<PlayHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `play_history` (`id`,`track_id`,`title`,`artist`,`artUrl`,`sourceUrl`,`played_at`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlayHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTrackId());
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getArtist());
        statement.bindString(5, entity.getArtUrl());
        statement.bindString(6, entity.getSourceUrl());
        statement.bindLong(7, entity.getPlayedAt());
      }
    };
  }

  @Override
  public Object addPlayHistory(final PlayHistoryEntity track,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlayHistoryEntity.insert(track);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PlayHistoryEntity>> getPlayHistory(final int limit) {
    final String _sql = "SELECT * FROM play_history ORDER BY played_at DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"play_history"}, new Callable<List<PlayHistoryEntity>>() {
      @Override
      @NonNull
      public List<PlayHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrackId = CursorUtil.getColumnIndexOrThrow(_cursor, "track_id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "artUrl");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfPlayedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "played_at");
          final List<PlayHistoryEntity> _result = new ArrayList<PlayHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlayHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTrackId;
            _tmpTrackId = _cursor.getString(_cursorIndexOfTrackId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final String _tmpArtUrl;
            _tmpArtUrl = _cursor.getString(_cursorIndexOfArtUrl);
            final String _tmpSourceUrl;
            _tmpSourceUrl = _cursor.getString(_cursorIndexOfSourceUrl);
            final long _tmpPlayedAt;
            _tmpPlayedAt = _cursor.getLong(_cursorIndexOfPlayedAt);
            _item = new PlayHistoryEntity(_tmpId,_tmpTrackId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpPlayedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
