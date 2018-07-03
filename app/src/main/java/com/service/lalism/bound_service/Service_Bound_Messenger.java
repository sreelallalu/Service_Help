package com.service.lalism.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class Service_Bound_Messenger extends Service {

    public final static int MSG1 = 10;
    public final static int MSG2 = 12;

    Messenger messenger = new Messenger(new Loaderclass());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class Loaderclass extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG1:
                    String mesaage = msg.getData().getString("data");
                  //  Toast.makeText(getApplicationContext(), mesaage, Toast.LENGTH_SHORT).show();
                    Message ms = Message.obtain(null, Service_Bound_Messenger.MSG1);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", ""+(int)System.currentTimeMillis());
                    ms.setData(bundle);
                    try {
                        msg.replyTo.send(ms);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                case MSG2:


                    String mesaage2 = msg.getData().getString("data");
                   // Toast.makeText(getApplicationContext(), mesaage2, Toast.LENGTH_SHORT).show();
                    Message ms2 = Message.obtain(null, Service_Bound_Messenger.MSG2);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("data", ""+(int)System.currentTimeMillis());
                    ms2.setData(bundle2);
                    try {
                        msg.replyTo.send(ms2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
            }

        }
    }
}
