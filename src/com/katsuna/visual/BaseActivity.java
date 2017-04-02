package com.katsuna.visual;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by christosmitatakis on 4/2/17.
 */

public class BaseActivity extends KatsunaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
