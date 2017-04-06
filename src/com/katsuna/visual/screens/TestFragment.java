package com.katsuna.visual.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katsuna.visual.BaseFragment;
import com.katsuna.visual.R;


public class TestFragment extends BaseFragment {

    public static String NAME = "TEST_FRAGMENT";

    public TestFragment() {
    }



    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test, container, false);
    }

}
