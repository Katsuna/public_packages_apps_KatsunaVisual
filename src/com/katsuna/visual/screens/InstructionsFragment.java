package com.katsuna.visual.screens;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.katsuna.visual.BaseFragment;
import com.katsuna.visual.R;


public class InstructionsFragment extends BaseFragment {

    public static String NAME = "INSTRUCTIONS_FRAGMENT";
    private static int id_test, steps = 1;


    private static TextView title, distance;
    private FloatingActionButton nextButton, distanceButton, centerDistanceButton;

    public InstructionsFragment() {
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void setDistanceText(String text)
    {
        if(distance != null)
        {
            if (distance.getVisibility() == View.VISIBLE && steps == 1)
            {
                distance.setText(text);
                distance.setTypeface(null, Typeface.BOLD);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_instructions, container, false);

        title = (TextView) view.findViewById(R.id.instructions_title);
        distance = (TextView) view.findViewById(R.id.centrer_text);
        nextButton = (FloatingActionButton) view.findViewById(R.id.nextButton);
        distanceButton = (FloatingActionButton) view.findViewById(R.id.distanceButton);
        centerDistanceButton = (FloatingActionButton) view.findViewById(R.id.centerDistanceButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (steps)
                {
                    case 1:
                        title.setText(getString(R.string.instructions_fragment_instructions_title2));
                        distance.setVisibility(View.GONE);
                        centerDistanceButton.setVisibility(View.VISIBLE);
                        steps++;
                        break;
                    case 2:
                        title.setVisibility(View.INVISIBLE);
                        distanceButton.setVisibility(View.VISIBLE);
                        centerDistanceButton.setVisibility(View.GONE);
                        distance.setVisibility(View.VISIBLE);
                        distance.setText(getString(R.string.instructions_fragment_instructions_center_text));
                        distance.setTypeface(null, Typeface.NORMAL);
                        steps++;
                        break;
                    case 3:
                        Bundle bundle = new Bundle();
                        bundle.putInt("testId", id_test);
                        TestInstructionsFragment fragment = new TestInstructionsFragment();
                        fragment.setArguments(bundle);
                        steps = 1;
                        getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, fragment, TestFragment.NAME).addToBackStack(TestFragment.NAME).commit();
                        break;

                }


            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int testId = bundle.getInt("testId");
            id_test = testId;
        }

        return view;
    }


}
