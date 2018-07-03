package com.service.lalism.intent_service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.service.lalism.R;

public class Activity_Intent extends AppCompatActivity {


    final String URL = "http://dapp.nyesteventuretech.com/dapp/stud_notes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentservice_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Service_Intent.class);
        intent.putExtra("url", URL);
        intent.putExtra("receiver", new DownloadReceiver(new Handler()));
        startService(intent);

    }



    private class DownloadReceiver extends ResultReceiver {

        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Service_Intent.RESULT) {
                Log.e("result", resultData.getString("result"));

            }

        }
    }
}
