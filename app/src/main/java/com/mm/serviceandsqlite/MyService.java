package com.mm.serviceandsqlite;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public Runnable mRunnable = null;
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Handler mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());
                boolean isInfoAvailable = myDBHelper.isAnyInfoAvailable(getApplicationContext());
                Toast.makeText(getApplicationContext(), String.valueOf(isInfoAvailable), Toast.LENGTH_LONG).show();
                mHandler.postDelayed(mRunnable, 10 * 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 10 * 1000);

        return super.onStartCommand(intent, flags, startId);
    }
}
