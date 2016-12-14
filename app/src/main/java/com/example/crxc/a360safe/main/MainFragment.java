package com.example.crxc.a360safe.main;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.main.phoneGuard.PhoneGuardActivity_;
import com.example.crxc.a360safe.setting.SettingActivity_;
import com.example.crxc.a360safe.util.T;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/9.
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment implements MainContract.View {
    MainContract.Presenter mPresenter;
    private static final int[] IMGRESID = { R.mipmap.sjfd, R.mipmap.srlj, R.mipmap.rjgj, R.mipmap.jcgl,
            R.mipmap.lltj, R.mipmap.sjsd, R.mipmap.hcql, R.mipmap.cygj };
    private static final String[] TITLES = { "手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具" };
    private static final String[] DES = { "远程定位", "全面骚扰拦截", "管理您的软件", "管理运行进程", "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全" };
    public static MainFragment_ getInstance() {
        return new MainFragment_();
    }

    @ViewById(R.id.iv)
    ImageView iv;

//    @ViewById(R.id.rv)
//    RecyclerView rv;

    @ViewById(R.id.grid)
    GridLayout gridLayout;

    @AfterViews()
    void afterView() {
        mPresenter.startHeaderAnimation();
//        initRv();
        initGridLayout();
    }

    private void initGridLayout() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for(int i=0;i<4;i++){
            for (int j=0;j<2;j++){
                int position=i*2+j;
                View view = inflater.inflate(R.layout.fragment_main_gridlayout_item,null);
                view.setOnClickListener(mPresenter.getOnClickListener(position));
//                View view = gridLayout.getChildAt(position);
//                view.setBackgroundColor(Color.BLACK);
                ImageView imageView= (ImageView) view.findViewById(R.id.iv);
                TextView title= (TextView) view.findViewById(R.id.tv_title);
                TextView des= (TextView) view.findViewById(R.id.tv_des);
                imageView.setImageResource(IMGRESID[position]);
                title.setText(TITLES[position]);
                des.setText(DES[position]);
                GridLayout.Spec rowSpec = GridLayout.spec(i);
                GridLayout.Spec columnSpec=GridLayout.spec(j);
                GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams(rowSpec,columnSpec);
                gridLayout.addView(view,layoutParams);
            }
        }
    }

//    private void initRv() {
//        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
////        rv.setLayoutManager(new GridLayoutManager(getActivity(),1));
//        rv.setAdapter(new MyRecylerAdapter());
//    }

    @Click(R.id.iv_setting)
    void openSettingActivity() {
        mPresenter.openSettingActivity();
    }

    @Override
    public void goToSettingActivity() {
        Intent intent = new Intent(getActivity(), SettingActivity_.class);
        startActivity(intent);
    }

    @Override
    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "rotationY", 0, 90, 270, 360);
        animator.setDuration(2000);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }

    @Override
    public void showPwdInitDialog() {
        View view = View.inflate(getActivity(), R.layout.activity_main_dialog_init_confirm, null);
        final EditText edtPwd = (EditText) view.findViewById(R.id.edt_pwd);
        final EditText edtPwdConfirm = (EditText) view.findViewById(R.id.edt_pwd_confirm);
        Button btn_yes = (Button) view.findViewById(R.id.btn_yes);
        Button btn_no = (Button) view.findViewById(R.id.btn_no);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = edtPwd.getText().toString();
                if(TextUtils.isEmpty(pwd)||!pwd.equals(edtPwdConfirm.getText().toString())){
                    T.showShort(getActivity(),"密码不匹配或为空");
                }else {
                    mPresenter.savePwd(pwd);
                    alertDialog.dismiss();
                }
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void showPwdConfirmDialog() {
        View view = View.inflate(getActivity(), R.layout.activity_main_dialog_pwd_confirm, null);
        final EditText edtPwd = (EditText) view.findViewById(R.id.edt_pwd);
        Button btn_yes = (Button) view.findViewById(R.id.btn_yes);
        Button btn_no = (Button) view.findViewById(R.id.btn_no);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = edtPwd.getText().toString();
                if(mPresenter.confirmPwd(pwd)){
                    Intent intent = new Intent(getActivity(), PhoneGuardActivity_.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }else {
                    T.showShort(getActivity(),"密码不匹配");
                }
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


}
