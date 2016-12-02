package org.edraak.edraaktest.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.edraak.edraaktest.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    @Override
    protected void initViews() {

    }
}
