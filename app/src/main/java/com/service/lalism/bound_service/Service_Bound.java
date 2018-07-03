package com.service.lalism.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class Service_Bound extends Service {

    IBinder iBinder= new LoadIbinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public int getTime() {

        return (int) System.currentTimeMillis();
    }

    public class LoadIbinder  extends Binder {

      Service_Bound getService(){
          return Service_Bound.this;
        }



    }
}
