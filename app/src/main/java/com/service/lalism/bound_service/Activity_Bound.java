package com.service.lalism.bound_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.service.lalism.R;

public class Activity_Bound extends AppCompatActivity {

    private boolean ibind;
    private Service_Bound service_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boundservice_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Service_Bound.class);

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
            ibind = true;
            Service_Bound.LoadIbinder loadIbinder = (Service_Bound.LoadIbinder) service;
            service_ref = loadIbinder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ibind = false;
        }
    };

    public void timeClick(View view) {
        if (ibind) {
            Toast.makeText(this, service_ref.getTime() + "", Toast.LENGTH_SHORT).show();

        }
    }
}
