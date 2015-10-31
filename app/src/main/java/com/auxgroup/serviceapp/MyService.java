package com.auxgroup.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public void asyncSendPerson(final String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendContentBroadcast(name);
            }

        }).start();
    }

    private void sendContentBroadcast(String name) {
        Intent intent = new Intent();
        intent.setAction("com.auxgroup.serviceapp.content");
        intent.putExtra("name", name);
        sendBroadcast(intent);
    }
}
