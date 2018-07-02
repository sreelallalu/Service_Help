package com.service.lalism.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Service_Intent extends IntentService {


 public static  final  int RESULT=200;

    public Service_Intent() {
        super("Service_Intent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String resultvalue = intent.getStringExtra("url");
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        if (!resultvalue.isEmpty()) {

            try {
                String result = DownloadFile(resultvalue);
                if(result!=null)
                {
                    Bundle bundle=new Bundle();
                    bundle.putString("result",result);
                    receiver.send(RESULT,bundle);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        this.stopSelf();
    }

    private String DownloadFile(String resultvalue) throws IOException {
        URL url = new URL(resultvalue);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Context-Type", "application/json");
        connection.setRequestProperty("Accept", "json");


        InputStream stream = new BufferedInputStream(connection.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String start = "";
        StringBuilder buffer = new StringBuilder();

        while ((start = reader.readLine()) != null)

        {
            buffer.append(start);
        }
        if (reader != null)

        {
            reader.close();
        }
        if (stream != null) {
            stream.close();

        }


        return buffer.toString();
    }
}
