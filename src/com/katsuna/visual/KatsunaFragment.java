package com.katsuna.visual;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by christosmitatakis on 4/2/17.
 */

import android.app.Fragment;
import android.os.Bundle;

public abstract class KatsunaFragment extends Fragment {

    protected BackHandlerInterface mBackHandlerInterface;

    public abstract boolean onBackPressed();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            mBackHandlerInterface = (BackHandlerInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Mark this fragment as the selected Fragment.
        mBackHandlerInterface.setSelectedFragment(this);
    }

    public abstract void onFragmentResume();

    public interface BackHandlerInterface {
        void setSelectedFragment(KatsunaFragment backHandledFragment);
    }

}




