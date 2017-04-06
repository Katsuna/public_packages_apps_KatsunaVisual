package com.katsuna.visual.screens;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.katsuna.visual.BaseFragment;
import com.katsuna.visual.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MenuFragment extends BaseFragment {

    public static String NAME = "MENU_FRAGMENT";


    int[] listviewImage = new int[]{
            R.drawable.c ,R.drawable.c,
    };

    private ListView listView;

    public MenuFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        listView = (ListView) rootView.findViewById(R.id.mobile_list);
        String[] listviewTitle ={
                getString(R.string.menu_fragment_optical_test_title),
                getString(R.string.menu_fragment_contrast_test_title)

        };

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 2; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.icon, R.id.Itemname};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.test_list, from, to);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Button okButton, cancelButton;
                switch (position)
                {
                    case 0:
                        okButton = (Button) view.findViewById(R.id.okButton);
                        cancelButton = (Button) view.findViewById(R.id.cancelButton);

                        listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, listView.getHeight() + okButton.getMinHeight()));
                        listView.requestLayout();
                        okButton.setVisibility(View.VISIBLE);
                        cancelButton.setVisibility(View.VISIBLE);
                        final Button finalCancelButton = cancelButton;
                        final Button finalOkButton = okButton;
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new TestFragment(), TestFragment.NAME).addToBackStack(TestFragment.NAME).commit();

                            }
                        });
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finalCancelButton.setVisibility(View.GONE);
                                finalOkButton.setVisibility(View.GONE);
                            }
                        });

                        break;


                    case 1:
                        okButton = (Button) view.findViewById(R.id.okButton);
                        cancelButton = (Button) view.findViewById(R.id.cancelButton);
                        listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, listView.getHeight() + okButton.getMinHeight()));
                        listView.requestLayout();

                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, new TestFragment(), TestFragment.NAME).addToBackStack(TestFragment.NAME).commit();

                            }
                        });

                        final Button finalCancelButton1 = cancelButton;
                        final Button finalOkButton1 = okButton;
                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finalCancelButton1.setVisibility(View.GONE);
                                finalOkButton1.setVisibility(View.GONE);
                            }
                        });

                        okButton.setVisibility(View.VISIBLE);
                        cancelButton.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        return rootView;
    }


}
