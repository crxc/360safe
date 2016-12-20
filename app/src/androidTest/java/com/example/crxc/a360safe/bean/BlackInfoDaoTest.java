package com.example.crxc.a360safe.bean;

import android.support.test.runner.AndroidJUnit4;

import com.example.crxc.a360safe.App;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by crxc on 2016/12/19.
 */
@RunWith(AndroidJUnit4.class)
public class BlackInfoDaoTest {
    @Test
    public void test(){
        BlackInfoDao blackInfoDao = App.getDaoSession().getBlackInfoDao();
        for(int i=0;i<1000;i++){
            blackInfoDao.insert(new BlackInfo(String.valueOf((i+10086)*123),i%3));
        }
    }
}