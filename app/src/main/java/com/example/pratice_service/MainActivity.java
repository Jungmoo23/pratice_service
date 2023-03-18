package com.example.pratice_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity.class";
    private MyService  myService ;
    private TextView textView;
    private Button btn;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder mb = (MyService.LocalBinder) service;
            myService = mb.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        init();

        Intent intent = new Intent(this,MyService.class);
        startService(intent);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
//        Intent intent = new Intent(this,MyService.class);
//        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");

    }
    private void init(){
        textView = findViewById(R.id.text);
        btn = findViewById(R.id.btn1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        textView.setText("현재 값은: "+myService.getValue());
    }
}