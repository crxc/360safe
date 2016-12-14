package com.example.crxc.a360safe.version;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by crxc on 2016/12/10.
 */

interface VersionService {
    @GET("1080MobileSafe/servlet/{name}")
    Observable<AppVersionInfo> getVersionInfo(@Path("name") String name);
}
