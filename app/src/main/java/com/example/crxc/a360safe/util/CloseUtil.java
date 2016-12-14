package com.example.crxc.a360safe.util;

/**
 * Created by crxc on 2016/11/30.
 */

public class CloseUtil {
    public static <T extends AutoCloseable> void close(T t){
        if(t!=null){
            try {
                t.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
