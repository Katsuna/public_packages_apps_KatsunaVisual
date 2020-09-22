/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
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