package com.twilitmusic.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.twilitmusic.app.data.local.entity.PlaybackStateEntity;
import com.twilitmusic.app.data.local.entity.QueueTrackEntity;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class QueueDao_Impl implements QueueDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QueueTrackEntity> __insertionAdapterOfQueueTrackEntity;

  private final EntityInsertionAdapter<PlaybackStateEntity> __insertionAdapterOfPlaybackStateEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearQueue;

  public QueueDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQueueTrackEntity = new EntityInsertionAdapter<QueueTrackEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `queue_tracks` (`id`,`trackId`,`title`,`artist`,`artUrl`,`sourceUrl`,`position`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QueueTrackEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTrackId());
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getArtist());
        statement.bindString(5, entity.getArtUrl());
        statement.bindString(6, entity.getSourceUrl());
        statement.bindLong(7, entity.getPosition());
      }
    };
    this.__insertionAdapterOfPlaybackStateEntity = new EntityInsertionAdapter<PlaybackStateEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `playback_state` (`id`,`currentIndex`,`positionMs`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlaybackStateEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCurrentIndex());
        statement.bindLong(3, entity.getPositionMs());
      }
    };
    this.__preparedStmtOfClearQueue = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM queue_tracks";
        return _query;
      }
    };
  }

  @Override
  public Object insertQueue(final List<QueueTrackEntity> tracks,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQueueTrackEntity.insert(tracks);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object savePlaybackState(final PlaybackStateEntity state,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlaybackStateEntity.insert(state);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object saveFullState(final List<QueueTrackEntity> tracks, final PlaybackStateEntity state,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> QueueDao.DefaultImpls.saveFullState(QueueDao_Impl.this, tracks, state, __cont), $completion);
  }

  @Override
  public Object clearQueue(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearQueue.acquire();
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
          __preparedStmtOfClearQueue.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getQueue(final Continuation<? super List<QueueTrackEntity>> $completion) {
    final String _sql = "SELECT * FROM queue_tracks ORDER BY position ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<QueueTrackEntity>>() {
      @Override
      @NonNull
      public List<QueueTrackEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTrackId = CursorUtil.getColumnIndexOrThrow(_cursor, "trackId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "artUrl");
          final int _cursorIndexOfSourceUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUrl");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final List<QueueTrackEntity> _result = new ArrayList<QueueTrackEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QueueTrackEntity _item;
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
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            _item = new QueueTrackEntity(_tmpId,_tmpTrackId,_tmpTitle,_tmpArtist,_tmpArtUrl,_tmpSourceUrl,_tmpPosition);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPlaybackState(final Continuation<? super PlaybackStateEntity> $completion) {
    final String _sql = "SELECT * FROM playback_state WHERE id = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlaybackStateEntity>() {
      @Override
      @Nullable
      public PlaybackStateEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCurrentIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "currentIndex");
          final int _cursorIndexOfPositionMs = CursorUtil.getColumnIndexOrThrow(_cursor, "positionMs");
          final PlaybackStateEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCurrentIndex;
            _tmpCurrentIndex = _cursor.getInt(_cursorIndexOfCurrentIndex);
            final long _tmpPositionMs;
            _tmpPositionMs = _cursor.getLong(_cursorIndexOfPositionMs);
            _result = new PlaybackStateEntity(_tmpId,_tmpCurrentIndex,_tmpPositionMs);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
