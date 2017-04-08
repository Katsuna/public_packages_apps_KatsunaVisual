package com.katsuna.visual.screens;

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
import com.katsuna.visual.measurement.Acuity;
import com.katsuna.visual.measurement.C_image;

import java.util.ArrayList;
import java.util.Random;


public class TestFragment extends BaseFragment {

    public static String NAME = "TEST_FRAGMENT";

    int errors = 0;
    int imageCounter = 0;
    int[] rotate;

    private ImageView upLeft,upMiddle, upRight, middleLeft, middleRight, downLeft, downMiddle, downRight;

    TextView steps;

    private FloatingActionButton cButton;
    ArrayList<C_image> c_images;

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


        View view = inflater.inflate(R.layout.fragment_test, container, false);

        cButton = (FloatingActionButton) view.findViewById(R.id.fab);

        upLeft = (ImageView) view.findViewById(R.id.upleft);
        upMiddle = (ImageView) view.findViewById(R.id.upmiddle);
        upRight = (ImageView) view.findViewById(R.id.upright);
        middleLeft = (ImageView) view.findViewById(R.id.middleleft);
        middleRight = (ImageView) view.findViewById(R.id.middleright);
        downLeft = (ImageView) view.findViewById(R.id.downleft);
        downMiddle = (ImageView) view.findViewById(R.id.downmiddle);
        downRight = (ImageView) view.findViewById(R.id.downright);

        steps = (TextView) view.findViewById(R.id.info_steps);

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


        initVisualTest();

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

}
