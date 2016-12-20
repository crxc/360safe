package com.example.crxc.a360safe.main.phoneGuard;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import com.example.crxc.a360safe.App;
import com.example.crxc.a360safe.R;
import com.example.crxc.a360safe.data.DataRepository;
import com.example.crxc.a360safe.util.L;

import static com.example.crxc.a360safe.R.drawable.lock;

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        DataRepository repository = DataRepository.getInstance();
        for(Object pdu:pdus){
            SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
            String phone=message.getOriginatingAddress();
            String body=message.getMessageBody();
            String safeNum=repository.getSafePhoneNum();
            if(!safeNum.equals(phone.trim())){
                return;
            }
            switch (body){
                case "#*music*#":
                    playMusic(context);
                    break;
                case "#*location*#":
                    startGPSService(context);
                    break;
                case "#*wipedata*#":
                    wipeData(context);
                    break;
                case "#*lockscreen*#":
                    lockScreen(context);
            }

        }

    }

    private void lockScreen(Context context) {
        DevicePolicyManager devicePolicyManager= (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        boolean adminActive = devicePolicyManager.isAdminActive(new ComponentName(context, MobileSafeAdminReceiver.class));
        if(adminActive){
            devicePolicyManager.lockNow();
            devicePolicyManager.resetPassword("666666",0);
        }
    }

    private void wipeData(Context context) {
        DevicePolicyManager devicePolicyManager= (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        boolean adminActive = devicePolicyManager.isAdminActive(new ComponentName(context, MobileSafeAdminReceiver.class));
        if(adminActive){
            devicePolicyManager.wipeData(2);
        }
    }

    private void startGPSService(Context context) {
        Intent intent = new Intent(context, GPSService.class);
        context.startService(intent);
    }

    private void playMusic(Context context) {
        MediaPlayer mediaPlayer=MediaPlayer.create(context, R.raw.a);
        mediaPlayer.setVolume(1.0f, 1.0f);// 开启左右声道最大
        mediaPlayer.setLooping(true);// 无限循环播放
        mediaPlayer.start();
    }
}
