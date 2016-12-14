package com.example.crxc.a360safe.splash;

import android.support.v7.app.AppCompatActivity;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;



/**
 * Created by crxc on 2016/12/8.
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {
   @AfterViews
    void afterViews(){
       SplashFragment_ fragment= (SplashFragment_) SplashFragment.newInstance();
       SplashPresenter presenter=new SplashPresenter(fragment, DataRepository.getInstance());
       getFragmentManager().beginTransaction()
               .replace(R.id.frame,fragment)
               .commit();
   }
}
