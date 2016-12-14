package com.example.crxc.a360safe.version;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by crxc on 2016/12/10.
 */

public interface VersionUpdateService {
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);
}
