package com.example.crxc.a360safe.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends AppCompatActivity {


    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @AfterViews
    void afterViews(){
        MainFragment_ fragment = MainFragment.getInstance();
        mPresenter = new MainPresenter(fragment, DataRepository.getInstance());
        initFragment(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.preLoad();
    }

    private void initFragment(MainFragment_ fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_main,fragment)
                .commit();
    }


}
