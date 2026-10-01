package com.twilitmusic.app.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001JN\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/twilitmusic/app/data/remote/JamendoApi;", "", "getTracks", "Lcom/twilitmusic/app/data/remote/JamendoResponse;", "clientId", "", "format", "limit", "", "order", "tags", "search", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "androidApp_debug"})
public abstract interface JamendoApi {
    
    @retrofit2.http.GET(value = "v3.0/tracks/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTracks(@retrofit2.http.Query(value = "client_id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String clientId, @retrofit2.http.Query(value = "format")
    @org.jetbrains.annotations.NotNull()
    java.lang.String format, @retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "order")
    @org.jetbrains.annotations.NotNull()
    java.lang.String order, @retrofit2.http.Query(value = "tags")
    @org.jetbrains.annotations.Nullable()
    java.lang.String tags, @retrofit2.http.Query(value = "search")
    @org.jetbrains.annotations.Nullable()
    java.lang.String search, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.twilitmusic.app.data.remote.JamendoResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}