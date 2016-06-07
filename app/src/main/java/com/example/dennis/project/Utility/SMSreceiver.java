package com.example.dennis.project.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by dennis on 6/6/2016.
 */
public class SMSreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] msg1 = (Object[]) bundle.get("pdus");
        String msgText = "";
        for (int i = 0; i < msg1.length; i++) {
            SmsMessage smsMessage= SmsMessage.createFromPdu((byte[]) msg1[i]);
            msgText += smsMessage.getMessageBody();
            if (i+1<msg1.length)msgText+="\n";
        }
        Log.d("Sms", "onReceive: "+msgText);

    }
}
