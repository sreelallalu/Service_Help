package com.service.lalism.bound_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.service.lalism.R;

public class Activity_Bound_Messenger extends AppCompatActivity {

    private boolean ibindcheck;
    Messenger messenger;
    private Button button2;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__bound__messenger);
        button2 = findViewById(R.id.button3);
        button1 = findViewById(R.id.button2);

    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Service_Bound_Messenger.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ibindcheck = true;
            messenger = new Messenger(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ibindcheck = false;

        }
    };

    public void msg1Click(View view) {

        Message message = Message.obtain(null, Service_Bound_Messenger.MSG1);
        try {

            message.replyTo = new Messenger(new ReplayHandler());
            Bundle bundle = new Bundle();
            bundle.putString("data", "msg1");
            message.setData(bundle);
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void msg2Click(View view) {
        Message message = Message.obtain(null, Service_Bound_Messenger.MSG2);
        try {
            message.replyTo = new Messenger(new ReplayHandler());
            Bundle bundle = new Bundle();
            bundle.putString("data", "msg2");
            message.setData(bundle);
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private class ReplayHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Service_Bound_Messenger.MSG1:

                    String replymsg = msg.getData().getString("data");
                    button2.setText(replymsg);

                    break;
                case Service_Bound_Messenger.MSG2:

                    String replymsg1 = msg.getData().getString("data");
                    button1.setText(replymsg1);

                    break;
            }

        }
    }
}
