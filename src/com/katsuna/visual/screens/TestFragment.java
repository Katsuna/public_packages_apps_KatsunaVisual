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
package com.katsuna.visual.screens;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuna.visual.BaseFragment;
import com.katsuna.visual.R;
import com.katsuna.visual.core.MainActivity;
import com.katsuna.visual.measurement.Acuity;
import com.katsuna.visual.measurement.C_image;
import com.katsuna.visual.preferences.PreferencesProvider;
import com.katsuna.visual.sqlite.VisualTestResultsDAO;
import com.katsuna.visual.utils.Dialogs;

import java.util.ArrayList;
import java.util.Random;


public class TestFragment extends BaseFragment {

    public static String NAME = "TEST_FRAGMENT";

    int errors = 0;
    int imageCounter = 0;
    int[] rotate;

    private ImageView upLeft,upMiddle, upRight, middleLeft, middleRight, downLeft, downMiddle, downRight;

    TextView steps;

    private static FloatingActionButton cButton, distanceButton;
    ArrayList<C_image> c_images;
    private TextView title;
    private int testId;

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

    public static FloatingActionButton getDistanceButton()
    {
        return distanceButton;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_test, container, false);

        cButton = (FloatingActionButton) view.findViewById(R.id.fab);

        distanceButton = (FloatingActionButton) view.findViewById(R.id.distanceButton);

        upLeft = (ImageView) view.findViewById(R.id.upleft);
        upMiddle = (ImageView) view.findViewById(R.id.upmiddle);
        upRight = (ImageView) view.findViewById(R.id.upright);
        middleLeft = (ImageView) view.findViewById(R.id.middleleft);
        middleRight = (ImageView) view.findViewById(R.id.middleright);
        downLeft = (ImageView) view.findViewById(R.id.downleft);
        downMiddle = (ImageView) view.findViewById(R.id.downmiddle);
        downRight = (ImageView) view.findViewById(R.id.downright);

        steps = (TextView) view.findViewById(R.id.info_steps);

        title = (TextView) view.findViewById(R.id.info_text);

        upLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });



        upMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        upRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        middleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        middleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        downLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        downMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        downRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedC(view);
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            testId = bundle.getInt("testId");
            if(testId == 0)
            {
                MainActivity activity = (MainActivity) getActivity();
                activity.testId = 0;
                initVisualTest();
            }

            else {
                title.setText(getString(R.string.test_fragment_contrast_test_title));
                MainActivity activity = (MainActivity) getActivity();
                activity.testId = 1;
                distanceButton.setVisibility(View.INVISIBLE);
                initContrastTest();
            }
        }



        if (imageCounter < c_images.size()) {
            cButton.setImageBitmap(c_images.get(imageCounter).getBitMap());
            steps.setText(getString(R.string.test_fragment_optical_test_step) + " " + (imageCounter+1) + " " + getString(R.string.test_fragment_optical_test_of) + " " + c_images.size());
            //     image.setImageBitmap(c_images.get(imageCounter).getBitMap());
            imageCounter++;
        }

        return view;
    }


    public void pressedC(final View view) {

        Log.d("CCCCC", "moires:: " + c_images.get(imageCounter - 1).getRotation());

        switch (view.getId()) {
            case R.id.downleft:
                Log.d("choice", "downleft");
                if (c_images.get(imageCounter - 1).getRotation() == 135) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.downmiddle:
                Log.d("choice", "downmiddle");
                if (c_images.get(imageCounter - 1).getRotation() == 90) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.downright:
                Log.d("choice", "downright");
                if (c_images.get(imageCounter - 1).getRotation() == 45) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.middleleft:
                Log.d("choice", "middleleft");
                if (c_images.get(imageCounter - 1).getRotation() == 180) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.middleright:
                Log.d("choice", "middleright");
                if (c_images.get(imageCounter - 1).getRotation() == 0) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.upleft:
                Log.d("choice", "upleft");
                if (c_images.get(imageCounter - 1).getRotation() == 225) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.upmiddle:
                Log.d("choice", "upmiddle");
                if (c_images.get(imageCounter - 1).getRotation() == 270) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
            case R.id.upright:
                Log.d("choice", "upright");
                if (c_images.get(imageCounter - 1).getRotation() == 315) {
                    Log.d("choice", "User gets: " + c_images.get(imageCounter - 1).getScore());
                } else {
                    errors++;
                }
                break;
        }

        Log.d("errors", "Total errors are: " + errors);

        if (imageCounter < c_images.size()) {
            ImageView image = (ImageView) getActivity().findViewById(R.id.c);
            cButton.setImageBitmap(c_images.get(imageCounter).getBitMap());
            steps.setText(getString(R.string.test_fragment_optical_test_step) + " " + (imageCounter+1) + " " + getString(R.string.test_fragment_optical_test_of) + " " + c_images.size());

            imageCounter++;
        }
        else
        {
            MainActivity activity = (MainActivity) getActivity();
            activity.testId = 1;
            Dialogs.ShowFinishTestDialog(getActivity(),getString(R.string.finish_alert_title), getString(R.string.finish_alert_message), getString(R.string.finish_alert_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(testId == 0)
                    {
                        VisualTestResultsDAO visualTestResultsDAO = new VisualTestResultsDAO(getContext());

                        if(PreferencesProvider.FinishAcuityTest(getActivity()))
                        {
                            visualTestResultsDAO.update(testId, "2");
                        }
                        else
                        {
                            visualTestResultsDAO.insert(testId, "1");

                        }
                        PreferencesProvider.SetFinishAcuityTest(getActivity(),true);
                    }
                    else if(testId == 1)
                    {
                        PreferencesProvider.SetFinishContrastTest(getActivity(),true);

                    }
                    Fragment menu = getFragmentManager().findFragmentByTag(MenuFragment.NAME);
                    getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, menu, MenuFragment.NAME).addToBackStack(MenuFragment.NAME).commit();
                }

            }, false);
        }

    }




    private Bitmap adjustedContrast(Bitmap image, double contrastVal) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        final Bitmap contrastedImage = Bitmap.createBitmap(width, height, image.getConfig());

        int A, R, G, B;
        int pixel;

        double contrast = contrastVal;


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixel = image.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                R = truncate(R);

                G = Color.green(pixel);
                G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                G = truncate(G);

                B = Color.blue(pixel);
                B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                B = truncate(B);


                contrastedImage.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return contrastedImage;
    }

    private int truncate(int value) {
        if (value < 0) {
            return 0;
        } else if (value > 255) {
            return 255;
        }

        return value;
    }


    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, float contrast,
                                                        float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());

        Canvas canvas = new Canvas(ret);


        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return ret;
    }


    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void initVisualTest()
    {
        rotate = new int[8];
        rotate[0] = 225;
        rotate[1] = 270;
        rotate[2] = 315;
        rotate[3] = 180;
        rotate[4] = 0;
        rotate[5] = 135;
        rotate[6] = 90;
        rotate[7] = 45;
        Random randomGenerator = new Random();
        c_images = new ArrayList<>();

        int rotation;
        Bitmap bMap;
        Bitmap bMapScaled;
        C_image c_image;


        DisplayMetrics metrics = getResources().getDisplayMetrics();

        System.out.println(metrics.xdpi+ " " + metrics.ydpi+" "+metrics.density);


        Acuity acuity = new Acuity();

        for (int n = 10; n >= 1; n--) {
            double logMAR = acuity.round(0.1 * n);
            // limit to two digits
            double acuityD = acuity.round(Math.pow(10, -logMAR));
            double finalSizeInMM = acuity.round(acuity.getSizeInMM(Acuity.distance, logMAR));
            Double finalSizeInPx = acuity.round(acuity.getHeightInPx(finalSizeInMM));
            // Get minimum suggested font size
            int finalMinimumFontSize = acuity.getMinimumSuggestedFontSize(finalSizeInPx);

            // Due to device screen sizes and resolutions, some of our values are so small
            // that there are not enough pixels for the critical gap. Check for it here
            if (!acuity.checkIfPossibleSize(finalSizeInMM, finalSizeInPx)) {
                System.out.print("logMAR is " + logMAR + ", acuity is " + acuity +
                        ", drawable height = " + finalSizeInMM + " mm, "
                        + "NOT POSSIBLE!" + System.lineSeparator());
            } else {
                System.out.print("logMAR is " + logMAR + ", acuity is " + acuity +
                        ", drawable height = " + finalSizeInMM + " mm, " + finalSizeInPx
                        + " px, min fontsize = " + finalMinimumFontSize
                        + System.lineSeparator());
            }


            rotation = randomGenerator.nextInt(8);
            bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
            bMapScaled = Bitmap.createScaledBitmap(bMap, finalSizeInPx.intValue(), finalSizeInPx.intValue(), true);
            bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);


            c_image = new C_image(bMapScaled, rotate[rotation], logMAR);
            c_images.add(c_image);


        }
    }

    public void initContrastTest()
    {

        rotate = new int[8];
        rotate[0] = 225;
        rotate[1] = 270;
        rotate[2] = 315;
        rotate[3] = 180;
        rotate[4] = 0;
        rotate[5] = 135;
        rotate[6] = 90;
        rotate[7] = 45;
        Random randomGenerator = new Random();
        c_images = new ArrayList<>();

        int rotation;
        Bitmap bMap;
        Bitmap bMapScaled;
        C_image c_image;


        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, 1);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, 0.45);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, 0);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.31);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.52);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.65);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.74);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.84);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.91);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -0.95);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.03);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.08);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.1);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.13);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.16);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -1.19);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);

    }

}
