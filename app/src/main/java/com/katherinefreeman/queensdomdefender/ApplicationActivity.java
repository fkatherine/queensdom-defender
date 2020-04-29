package com.katherinefreeman.queensdomdefender;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class ApplicationActivity extends AppCompatActivity {

    @Inject
    ApplicationActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectActivityDependencies();

        setContentView(R.layout.activity_main);
        configureFullscreenMode();

        viewModel.startNewGame();
    }

    private void injectActivityDependencies() {
        ((Application) getApplicationContext())
                .getApplicationComponent()
                .inject(this);
    }

    private void configureFullscreenMode() {
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}

