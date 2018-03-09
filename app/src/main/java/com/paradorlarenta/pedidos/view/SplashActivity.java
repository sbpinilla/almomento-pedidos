package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
            irFiltros();

        }
    };

    @BindView(R.id.img_splash_activity)
    ImageView imgLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        setupConexion();

        fullScreen();
        ejecutarTimer();
    }

    private void setupConexion() {

        SharedPreferences sharedPref = getSharedPreferences(
                "SharedPreferencesPedidos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("apiIP", "192.168.1.33");
        editor.commit();
    }


    private void ejecutarTimer() {
        mHandler.postDelayed(hMyTimeTask, SPLASH_TIMEOUT);
    }


    private void irFiltros() {

        Intent intent = new Intent(SplashActivity.this, FiltrosActivity.class);
        startActivity(intent);
        finish();
    }

    private void fullScreen() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
