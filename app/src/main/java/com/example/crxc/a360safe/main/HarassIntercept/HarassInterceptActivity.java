package com.example.crxc.a360safe.main.HarassIntercept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/19.
 */
@EActivity(R.layout.activity_harass)
public class HarassInterceptActivity extends AppCompatActivity {
    @ViewById(R.id.frame)
    FrameLayout mFrameLayout;
    private DataRepository mDataRepository;

    @AfterViews
    void afterViews(){
        HarassInterceptFragment view = new HarassInterceptFragment_();
        mDataRepository = DataRepository.getInstance();
        HarassInterceptContract.Presenter presenter = new HarassInterceptPresenter(view, mDataRepository);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,view).commit();
    }


}
