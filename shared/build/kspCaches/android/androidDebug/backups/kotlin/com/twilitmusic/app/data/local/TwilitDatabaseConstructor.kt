package com.twilitmusic.app.`data`.local

import androidx.room.RoomDatabaseConstructor

public actual object TwilitDatabaseConstructor : RoomDatabaseConstructor<TwilitDatabase> {
  actual override fun initialize(): TwilitDatabase =
      com.twilitmusic.app.`data`.local.TwilitDatabase_Impl()
}
