package com.example.crxc.a360safe.splash;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crxc.a360safe.main.MainActivity_;
import com.example.crxc.a360safe.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/8.
 */
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends Fragment implements SplashContract.View {
    private SplashContract.Presenter presenter;

    public static SplashFragment newInstance() {
        return new SplashFragment_();
    }

    @ViewById(R.id.tv_version)
    TextView tv_version;

    @AfterViews
    void afterViews() {
        init();
    }

    private void init() {
        presenter.getUpdate();
        presenter.ShowVersion();
    }

    public void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("监测到一个新的版本")
                .setMessage("是否升级")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.updateFromInternet();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "正在后台下载", Toast.LENGTH_SHORT).show();
                        goToMainActivity();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().finish();
                goToMainActivity();
            }
        }).setCancelable(false).show();
    }

    @Override
    public void goToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), MainActivity_.class);
                startActivity(intent);
                getActivity().finish();
            }
        }, 1000);
    }

    @Override
    public void showVersion(int currentVersionCode, String currentVersionName) {
        tv_version.setText(currentVersionName + ":V" + currentVersionCode);
    }


    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
