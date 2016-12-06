package org.edraak.edraaktest.activities;

import android.os.Bundle;

import org.edraak.edraaktest.R;

/**
 * The first activity
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
    }

    @Override
    protected void initViews() {
        startActivity(SectionsActivity.getIntentInstance(getApplicationContext()));
        finish();
    }
}
