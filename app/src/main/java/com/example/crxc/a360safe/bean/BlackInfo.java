package com.example.crxc.a360safe.bean;

import com.example.crxc.a360safe.main.Constant;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by crxc on 2016/12/19.
 */
@Entity
public class BlackInfo {
    String mPhone;
    @Id(autoincrement = true)
    Long id;
    int mType;
    @Keep
    public BlackInfo(String phone, int type) {
        mPhone = phone;
        mType = type;
    }

    @Generated(hash = 945851412)
    public BlackInfo(String mPhone, Long id, int mType) {
        this.mPhone = mPhone;
        this.id = id;
        this.mType = mType;
    }
    @Generated(hash = 1364603917)
    public BlackInfo() {
    }
    public String getMPhone() {
        return this.mPhone;
    }
    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getMType() {
        return this.mType;
    }
    public String getMTypeDes(){
        switch (mType){
            case Constant.rbPhone:
                return "电话拦截";
            case Constant.rbSms:
                return "短信拦截";
            case Constant.rbSmsAndPhone:
                return "电话与短信拦截";
        }
        return "无效拦截";
    }
    public void setMType(int mType) {
        this.mType = mType;
    }
}
