package com.example.crxc.a360safe.main.HarassIntercept;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.main.Constant;
import com.example.crxc.a360safe.util.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import static com.example.crxc.a360safe.R.raw.a;

@EActivity
public class AddBlackListActivity extends AppCompatActivity {

    private DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_black_list);
    }
    @AfterViews
    void afterViews(){
        Intent intent = getIntent();
        switch (intent.getAction()){
            case Constant.aUpdateBlackList:
                addFragment(intent.getExtras());
                break;
            default:
                addFragment();
        }

    }

    private void addFragment() {
        AddBlackListFragment_ fragment = new AddBlackListFragment_();
        mDataRepository = DataRepository.getInstance();
        AddBlackListContract.Presenter presenter=new AddBlackListPresenter(fragment,mDataRepository);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment)
                .commit();
    }

    private void addFragment(Bundle bundle) {
        L.d(String.valueOf(SystemClock.currentThreadTimeMillis()));
        AddBlackListFragment_ fragment = new AddBlackListFragment_();
        fragment.setArguments(bundle);
        mDataRepository = DataRepository.getInstance();
        AddBlackListContract.Presenter presenter=new AddBlackListPresenter(fragment,mDataRepository);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment)
                .commit();
        L.d(String.valueOf(SystemClock.currentThreadTimeMillis()));
    }
}
