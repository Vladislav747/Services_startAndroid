package com.example.melo.services_startandroid;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.view.View;

/**
 * Created by Melo on 03.09.2018.
 */

public class Service93_1 extends Activity {
    public void onClickStart(View view){
        startService(new Intent(this, Service93.class).putExtra("time", 7));
        startService(new Intent(this, Service93.class).putExtra("time", 2));
        startService(new Intent(this, Service93.class).putExtra("time", 4));
    }
}
