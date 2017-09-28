package tut.mib.com.stopwatchapp;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int seconds=0;
    boolean running=false;
    boolean wasrunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds =savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");

        }
        runTimer();

    }

    @Override
    protected void onStop() {

        wasrunning = running;
        running=false;
        Log.d("onSaveInstanceState: ","we were called    ONSTOP .......");

        super.onStop();
    }

    @Override
    protected void onRestart() {
        if (wasrunning){
            running=true;
        }
        Log.d("onSaveInstanceState: ","we were called    ONRESTART.......");

        super.onRestart();
    }

    @Override
    protected void onStart() {
        if (wasrunning){
            running=true;
        }
        running=true;
        Log.d("onSaveInstanceState: ","we were called  ONSTART  .......");

        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasrunning",wasrunning);
        Log.d("onSaveInstanceState: ","we were called    .......");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Log.d("onSaveInstanceState: ","we were called    ONDESTROY .......");

        super.onDestroy();
    }

    public void onClickStart(View view) {
        running = true;
        Log.d("onSaveInstanceState: ","we were called   start.. .......");

    }

    public void onClickStop(View view) {
    running=false;
        Log.d("onSaveInstanceState: ","we were called    STOP .......");

    }

    public void onClickReset(View view) {
        running = false;
        seconds =0;
        Log.d("onSaveInstanceState: ","we were called  RESET   .......");

    }
    private  void runTimer(){
        Log.d("onSaveInstanceState: ","Runtimer called::::::::::::::::    .......");

        final TextView timeView = findViewById(R.id.time_View);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }
}
