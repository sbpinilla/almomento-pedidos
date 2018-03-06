package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.paradorlarenta.pedidos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final String LOG_ACTIVITY = "ActivitySplash";
    private static final int SPLASH_TIMEOUT = 3000;
    private Activity activity;

    Handler mHandler = new Handler();

    private Runnable hMyTimeTask = new Runnable() {
        public void run() {

            //TODO: Validar preferencias en caso que se necesite sesion
            irPedidos();

        }
    };

    @BindView(R.id.img_splash_activity)
    ImageView imgLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        fullScreen();
        ejecutarTimer();
    }


    private void ejecutarTimer() {
        mHandler.postDelayed(hMyTimeTask, SPLASH_TIMEOUT);
    }


    private void irPedidos() {

        Intent intent = new Intent(SplashActivity.this, ProductosActivity.class);
        startActivity(intent);
        finish();
    }

    private void fullScreen() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
