package com.example.pratice_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private final String TAG = "MyService.class";
    private IBinder mbinder = new LocalBinder();
    private int cnt_value = 0;
    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mbinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"oncreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        cntThred cntThred = new cntThred();
        cntThred.start();
        return START_STICKY;
    }

    public int getValue(){
        return cnt_value;
    }

    class cntThred extends Thread{

        @Override
        public void run() {
            try{
                for(int time = 0; time <100; time ++){
                    Thread.sleep(1000);
                    cnt_value++;
                    Log.d(TAG,cnt_value+"");
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}