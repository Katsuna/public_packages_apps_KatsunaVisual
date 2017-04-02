package com.katsuna.visual;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by christosmitatakis on 4/2/17.
 */

public class KatsunaActivity extends Activity implements KatsunaFragment.BackHandlerInterface {

    protected KatsunaFragment mSelectedFragment;
    private OnUserInteractionListener mOnUserInteractionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void setSelectedFragment(KatsunaFragment backHandledFragment) {
        mSelectedFragment = backHandledFragment;
    }

    @Override
    public void onUserInteraction() {

        super.onUserInteraction();

        if (mOnUserInteractionListener != null) {
            mOnUserInteractionListener.OnUserInteraction();
        }
    }

    public void setOnUserInteractionListener(OnUserInteractionListener listener) {
        this.mOnUserInteractionListener = listener;
    }



    public interface OnUserInteractionListener {
        void OnUserInteraction();
    }

}