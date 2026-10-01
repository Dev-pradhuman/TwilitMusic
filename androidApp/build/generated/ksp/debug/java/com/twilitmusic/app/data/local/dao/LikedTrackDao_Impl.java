package com.twilitmusic.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.twilitmusic.app.data.local.entity.LikedTrackEntity;
import java.lang.Boolean;
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
public final class LikedTrackDao_Impl implements LikedTrackDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LikedTrackEntity> __insertionAdapterOfLikedTrackEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveLikedTrack;

  public LikedTrackDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLikedTrackEntity = new EntityInsertionAdapter<LikedTrackEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `liked_tracks` (`id`,`title`,`artist`,`artUrl`,`sourceUrl`,`added_at`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LikedTrackEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getArtist());
        statement.bindString(4, entity.getArtUrl());
        statement.bindString(5, entity.getSourceUrl());
        statement.bindLong(6, entity.getAddedAt());
      }
    };
    this.__preparedStmtOfRemoveLikedTrack = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM liked_tracks WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object addLikedTrack(final LikedTrackEntity track,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLikedTrackEntity.insert(track);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeLikedTrack(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveLikedTrack.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveLikedTrack.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LikedTrackEntity>> getLikedTracks() {
    final String _sql = "SELECT * FROM liked_tracks ORDER BY added_at DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"liked_tracks"}, new Callable<List<LikedTrackEntity>>() {
      @Override
      @NonNull
      public List<LikedTrackEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "artUrl");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "added_at");
          final List<LikedTrackEntity> _result = new ArrayList<LikedTrackEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LikedTrackEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final String _tmpArtUrl;
            _tmpArtUrl = _cursor.getString(_cursorIndexOfArtUrl);
            final String _tmpSourceUrl;
            _tmpSourceUrl = _cursor.getString(_cursorIndexOfSourceUrl);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new LikedTrackEntity(_tmpId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpAddedAt);
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

  @Override
  public Flow<Boolean> isLiked(final String id) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM liked_tracks WHERE id = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"liked_tracks"}, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
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
