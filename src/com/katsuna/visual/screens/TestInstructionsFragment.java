package com.katsuna.visual.screens;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
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
import com.katsuna.visual.utils.Dialogs;

import java.util.ArrayList;
import java.util.Random;


public class TestInstructionsFragment extends BaseFragment {

    public static String NAME = "TEST_INSTRUCTIONS_FRAGMENT";

    int errors = 0;
    int imageCounter = 0;
    int[] rotate;

    private int currentStep = 1;

    private boolean clickable = false;

    private ImageView upLeft,upMiddle, upRight, middleLeft, middleRight, downLeft, downMiddle, downRight;
    private FloatingActionButton nextButton;

    TextView steps;

    private static FloatingActionButton cButton, distanceButton;
    ArrayList<C_image> c_images;
    private TextView title;

    public TestInstructionsFragment() {
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


        View view = inflater.inflate(R.layout.fragment_instructions_test, container, false);

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

        nextButton = (FloatingActionButton) view.findViewById(R.id.nextInstructionsButton) ;

        steps = (TextView) view.findViewById(R.id.info_steps);

        title = (TextView) view.findViewById(R.id.info_text);

        final double  logMAR = 0.0;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int testId = bundle.getInt("testId");
            if (testId == 0) {

                title.setText(getString(R.string.instructions_fragment_instructions_test_title));
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int rotation;
                        Bitmap bMap;
                        Bitmap bMapScaled;
                        C_image c_image;
                        downRight.setClickable(false);
                        upRight.setClickable(false);


                        switch (currentStep) {
                            case 1:
                                rotation = -45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_2));
                                currentStep++;
                                break;
                            case 2:
                                upLeft.setAlpha(0.5f);
                                upMiddle.setAlpha(0.5f);
                                middleLeft.setAlpha(0.5f);
                                middleRight.setAlpha(0.5f);
                                downLeft.setAlpha(0.5f);
                                downMiddle.setAlpha(0.5f);
                                downRight.setAlpha(0.5f);


                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_3));
                                currentStep++;
                                break;
                            case 3 :
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_4));
                                clickable = true;
                                nextButton.setClickable(false);
                                upRight.setClickable(true);
                                currentStep++;
                                break;
                            case 4:
                                nextButton.setClickable(true);
                                upRight.setClickable(false);
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_5));
                                upLeft.setAlpha(1f);
                                upMiddle.setAlpha(1f);
                                middleLeft.setAlpha(1f);
                                middleRight.setAlpha(1f);
                                downLeft.setAlpha(1f);
                                downMiddle.setAlpha(1f);
                                downRight.setAlpha(1f);
                                rotation = 45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                                currentStep++;
                                break;
                            case 5:
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_6));
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                                nextButton.setClickable(false);
                                downRight.setClickable(true);
                                currentStep++;
                                break;
                            case 6:
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_7));
                                nextButton.setClickable(true);
                                currentStep++;
                                break;
                            case 7:
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_8));
                                currentStep++;
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                                break;
                            case 8:
                                Bundle bundle = new Bundle();
                                bundle.putInt("testId", 0);
                                TestFragment fragment = new TestFragment();
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, fragment, TestFragment.NAME).addToBackStack(TestFragment.NAME).commit();
                                break;






                        }
                    }
                });
            } else {

                title.setText(getString(R.string.instructions_fragment_instructions_contrast_test_title));
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int rotation;
                        Bitmap bMap;
                        Bitmap bMapScaled;
                        C_image c_image;
                        downRight.setClickable(false);
                        upRight.setClickable(false);


                        switch (currentStep) {
                            case 1:
                                rotation = -45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_2));
                                currentStep++;
                                break;
                            case 2:
                                rotation = -45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                bMapScaled = adjustedContrast(bMapScaled, -0.52);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                upLeft.setAlpha(0.5f);
                                upMiddle.setAlpha(0.5f);
                                middleLeft.setAlpha(0.5f);
                                middleRight.setAlpha(0.5f);
                                downLeft.setAlpha(0.5f);
                                downMiddle.setAlpha(0.5f);
                                downRight.setAlpha(0.5f);


                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_3));
                                currentStep++;
                                break;
                            case 3 :
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_4));
                                clickable = true;
                                nextButton.setClickable(false);
                                upRight.setClickable(true);
                                currentStep++;
                                break;
                            case 4:
                                nextButton.setClickable(true);
                                upRight.setClickable(false);
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_5));
                                upLeft.setAlpha(1f);
                                upMiddle.setAlpha(1f);
                                middleLeft.setAlpha(1f);
                                middleRight.setAlpha(1f);
                                downLeft.setAlpha(1f);
                                downMiddle.setAlpha(1f);
                                downRight.setAlpha(1f);
                                rotation = 0;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                                currentStep++;
                                break;
                            case 5:
                                rotation = 45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                bMapScaled = adjustedContrast(bMapScaled, -0.52);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_6));
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                                nextButton.setClickable(false);
                                downRight.setClickable(true);
                                currentStep++;
                                break;
                            case 6:
                                rotation = 45;
                                bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
                                bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
                                bMapScaled = RotateBitmap(bMapScaled, rotation);
                                c_image = new C_image(bMapScaled, rotation, logMAR);
                                cButton.setImageBitmap(c_image.getBitMap());
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_7));
                                nextButton.setClickable(true);
                                currentStep++;
                                break;
                            case 7:
                                title.setText(getString(R.string.instructions_fragment_instructions_test_title_8));
                                currentStep++;
                                nextButton.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                                break;
                            case 8:
                                Bundle bundle = new Bundle();
                                bundle.putInt("testId", 1);
                                TestFragment fragment = new TestFragment();
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.main_activity_fragment_container, fragment, TestFragment.NAME).addToBackStack(TestFragment.NAME).commit();
                                break;






                        }
                    }
                });
            }


        }

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
                nextScreen(view);
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
                correct(view);
            }
        });

        return view;
    }


    public void nextScreen(final View view)
    {
        if(clickable)
            nextButton.performClick();
    }

    public void correct(final View view)
    {
        if(clickable)
            nextButton.performClick();
    }

    public void pressedC(final View view) {

        if(clickable) {


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

        for (int n = 10; n >= -3; n--) {
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

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.03);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.08);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.1);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.13);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.16);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

        rotation = randomGenerator.nextInt(8);
        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
        bMapScaled = adjustedContrast(bMapScaled, -1.19);
        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
        c_images.add(c_image);

    }

}
