package com.auxgroup.serviceapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private MyServiceConn conn;
    private ContentReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = new MyServiceConn();
        bindService(new Intent(MainActivity.this, MyService.class), conn, BIND_AUTO_CREATE);
        tv = (TextView) findViewById(R.id.tv);


        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        doRegisterReceiver();
    }

    /**
     * 注册广播接收者
     */
    private void doRegisterReceiver() {
        receiver = new ContentReceiver();
        IntentFilter filter = new IntentFilter("com.auxgroup.serviceapp.content");
        registerReceiver(receiver, filter);
    }


    public class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public class ContentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            Person person = new Person();
            person.setName(name);
            tv.setText(person.toString());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

    }
}
