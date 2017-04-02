package com.katsuna.visual.core;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.katsuna.visual.BaseActivity;
import com.katsuna.visual.BaseFragment;
import com.katsuna.visual.measurement.Acuity;
import com.katsuna.visual.measurement.C_image;
import com.katsuna.visual.R;
import com.katsuna.visual.messages.MeasurementStepMessage;
import com.katsuna.visual.messages.MessageHUB;
import com.katsuna.visual.messages.MessageListener;
import com.katsuna.visual.screens.MenuFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity implements MessageListener {

    public static final String CAM_SIZE_WIDTH = "intent_cam_size_width";
    public static final String CAM_SIZE_HEIGHT = "intent_cam_size_height";
    public static final String AVG_NUM = "intent_avg_num";
    public static final String PROBANT_NAME = "intent_probant_name";

    private CameraSurfaceView _mySurfaceView;
    Camera _cam;

    private static final int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private final static DecimalFormat _decimalFormater = new DecimalFormat(
            "0.0");

    private float _currentDevicePosition;
    int[] rotate;
    ViewGroup insertPoint;
    int errors = 0;
    private int _cameraHeight;
    private int _cameraWidth;
    private int _avgNum;
    int imageCounter = 0;
    TextView _currentDistanceView;
    Button _calibrateButton;
    ArrayList<C_image> c_images;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Abusing the media controls to create a remote control
     */
    // ComponentName _headSetButtonReceiver;
    // AudioManager _audioManager;
    private void releaseCameraAndPreview() {
        if (_cam != null) {
            _cam.release();
            _cam = null;
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {


        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }



        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FragmentManager manager = getFragmentManager();
                if (manager != null) {
                    Fragment fragment = manager.findFragmentById(R.id.main_activity_fragment_container);
                    if (fragment instanceof BaseFragment)
                        ((BaseFragment) fragment).onFragmentResume();
                }
            }
        });

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.main_activity_fragment_container, new MenuFragment(), MenuFragment.NAME).commit();
        }
//
//
//        rotate = new int[8];
//        rotate[0] = 225;
//        rotate[1] = 270;
//        rotate[2] = 315;
//        rotate[3] = 180;
//        rotate[4] = 0;
//        rotate[5] = 135;
//        rotate[6] = 90;
//        rotate[7] = 45;
//        Random randomGenerator = new Random();
//        c_images = new ArrayList<>();
//
//        int rotation;
//        Bitmap bMap;
//        Bitmap bMapScaled;
//        C_image c_image;
//
//
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//
//        System.out.println(metrics.xdpi+ " " + metrics.ydpi+" "+metrics.density);
//
//
//        Acuity acuity = new Acuity();
//
//        for (int n = 10; n >= -3; n--) {
//            double logMAR = acuity.round(0.1 * n);
//            // limit to two digits
//            double acuityD = acuity.round(Math.pow(10, -logMAR));
//            double finalSizeInMM = acuity.round(acuity.getSizeInMM(Acuity.distance, logMAR));
//            Double finalSizeInPx = acuity.round(acuity.getHeightInPx(finalSizeInMM));
//            // Get minimum suggested font size
//            int finalMinimumFontSize = acuity.getMinimumSuggestedFontSize(finalSizeInPx);
//
//            // Due to device screen sizes and resolutions, some of our values are so small
//            // that there are not enough pixels for the critical gap. Check for it here
//            if (!acuity.checkIfPossibleSize(finalSizeInMM, finalSizeInPx)) {
//                System.out.print("logMAR is " + logMAR + ", acuity is " + acuity +
//                        ", drawable height = " + finalSizeInMM + " mm, "
//                        + "NOT POSSIBLE!" + System.lineSeparator());
//            } else {
//                System.out.print("logMAR is " + logMAR + ", acuity is " + acuity +
//                        ", drawable height = " + finalSizeInMM + " mm, " + finalSizeInPx
//                        + " px, min fontsize = " + finalMinimumFontSize
//                        + System.lineSeparator());
//            }
//
//
//            rotation = randomGenerator.nextInt(8);
//            bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//            bMapScaled = Bitmap.createScaledBitmap(bMap, finalSizeInPx.intValue(), finalSizeInPx.intValue(), true);
//            bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//
//
//            c_image = new C_image(bMapScaled, rotate[rotation], logMAR);
//            c_images.add(c_image);
//
//
//        }
//
//
//
//
//
//
//
//
//
////        rotation = randomGenerator.nextInt(8);
////        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 102, 102, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 48);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 81, 82, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 38);
////
////        c_images.add(c_image);
////
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 64, 65, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 30);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 51, 52, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 24);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 40, 42, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 19);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 32, 33, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 15);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 25, 27, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 12);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 20, 21, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], (6 / 9.5));
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 16, 17, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], (6 / 7.5));
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 13, 14, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 6);
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 10, 11, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], (6 / 4.8));
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 8, 9, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], (6 / 3.8));
////        c_images.add(c_image);
////        rotation = randomGenerator.nextInt(8);
////
////        bMapScaled = Bitmap.createScaledBitmap(bMap, 6, 7, true);
////        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
////        c_image = new C_image(bMapScaled, rotate[rotation], (6 / 3));
////        c_images.add(c_image);
//
//
//        //contrast images
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, 1);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, 0.45);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, 0);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.31);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.52);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.65);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.74);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.84);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.91);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
//        rotation = randomGenerator.nextInt(8);
//        bMap = BitmapFactory.decodeResource(getResources(), R.drawable.c);
//        bMapScaled = Bitmap.createScaledBitmap(bMap, 128, 128, true);
//        bMapScaled = RotateBitmap(bMapScaled, rotate[rotation]);
//        bMapScaled = adjustedContrast(bMapScaled, -0.95);
//        c_image = new C_image(bMapScaled, rotate[rotation], 6 / 60);
//        c_images.add(c_image);
//
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


        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurement_activity);


        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
                (int) (0.95 * this.getResources().getDisplayMetrics().widthPixels),
                (int) (0.6 * this.getResources().getDisplayMetrics().heightPixels));

        layout.setMargins(0, (int) (0.05 * this.getResources()
                .getDisplayMetrics().heightPixels), 0, 0);


        _mySurfaceView = (CameraSurfaceView) findViewById(R.id.surface_camera);

        _mySurfaceView.setZOrderOnTop(true);    // necessary
        SurfaceHolder sfhTrackHolder = _mySurfaceView.getHolder();
        sfhTrackHolder.setFormat(PixelFormat.TRANSPARENT);


        //	_mySurfaceView.setLayoutParams(layout);
        _currentDistanceView = (TextView) findViewById(R.id.currentDistance);
     //   _calibrateButton = (Button) findViewById(R.id.calibrateButton);

        // _audioManager = (AudioManager) this
        // .getSystemService(Context.AUDIO_SERVICE);
//        if (imageCounter < c_images.size()) {
//            ImageView image = (ImageView) findViewById(R.id.c);
//            image.setImageBitmap(c_images.get(imageCounter).getBitMap());
//            imageCounter++;
//        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    @Override
    public void onResume() {
        super.onResume();


        // _audioManager.registerMediaButtonEventReceiver(_headSetButtonReceiver);

        // 1 for front cam. No front cam ? Not my fault!
        //releaseCameraAndPreview();


//        if (!hasPermissions(this, PERMISSIONS)) {
//            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
//        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {




            cameraSetup();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        MessageHUB.get().unregisterListener(this);

        // _audioManager
        // .unregisterMediaButtonEventReceiver(_headSetButtonReceiver);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            resetCam();

        }
    }

    @Override
    public void onDestroy()
    {

        MessageHUB.get().unregisterListener(this);

        // _audioManager
        // .unregisterMediaButtonEventReceiver(_headSetButtonReceiver);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            resetCam();
        }
        super.onDestroy();


    }

    private void initializeCameraView(){

        RelativeLayout preview = (RelativeLayout) findViewById(R.id.layout);
        //removes the problem with the camera freezing onResume
        if(_mySurfaceView != null) {
            preview.removeView(_mySurfaceView);
        }
        preview.addView(_mySurfaceView);
    }

    public void cameraSetup()
    {
        if (_cam == null) {
            MessageHUB.get().registerListener(this);
            initializeCameraView();
            _cam = Camera.open(1);

            Camera.Parameters param = _cam.getParameters();


            // Find the best suitable camera picture size for your device. Competent
            // research has shown that a smaller size gets better results up to a
            // certain point.
            // http://ieeexplore.ieee.org/xpl/login.jsp?tp=&arnumber=6825217&url=http%3A%2F%2Fieeexplore.ieee.org%2Fiel7%2F6816619%2F6825201%2F06825217.pdf%3Farnumber%3D6825217
            List<Size> pSize = param.getSupportedPictureSizes();
            double deviceRatio = (double) this.getResources().getDisplayMetrics().widthPixels
                    / (double) this.getResources().getDisplayMetrics().heightPixels;

            Size bestSize = pSize.get(0);
            double bestRation = (double) bestSize.width / (double) bestSize.height;

            for (Size size : pSize) {
                double sizeRatio = (double) size.width / (double) size.height;

                if (Math.abs(deviceRatio - bestRation) > Math.abs(deviceRatio
                        - sizeRatio)) {
                    bestSize = size;
                    bestRation = sizeRatio;
                }
            }
            _cameraHeight = bestSize.height;
            _cameraWidth = bestSize.width;

            Log.d("PInfo", _cameraWidth + " x " + _cameraHeight);

            param.setPreviewSize(_cameraWidth, _cameraHeight);
            _cam.setParameters(param);


            _mySurfaceView.setCamera(_cam);


            pressedCalibrate();
        }
    }

    public void pressedCalibrate() {

        if (!_mySurfaceView.isCalibrated()) {

//            _calibrateButton.setBackgroundResource(R.drawable.yellow_button);
            _mySurfaceView.calibrate();
        }
    }

    public void pressedReset() {

        if (_mySurfaceView.isCalibrated()) {

   //         _calibrateButton.setBackgroundResource(R.drawable.red_button);
            _mySurfaceView.reset();
        }
    }

    public void onShowMiddlePoint(final View view) {
        // Is the toggle on?
        boolean on = ((Switch) view).isChecked();
        _mySurfaceView.showMiddleEye(on);
    }

    public void onShowEyePoints(final View view) {
        // Is the toggle on?
        boolean on = ((Switch) view).isChecked();
        _mySurfaceView.showEyePoints(on);
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
            ImageView image = (ImageView) findViewById(R.id.c);
            image.setImageBitmap(c_images.get(imageCounter).getBitMap());
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


    public void updateUI(final MeasurementStepMessage message) {

//        _currentDistanceView.setText(_decimalFormater.format(message
//                .getDistToFace()) + " cm");

        Log.d("distance", _decimalFormater.format(message
                .getDistToFace()) + " cm");
        float fontRatio = message.getDistToFace() / 29.7f;

//        _currentDistanceView.setTextSize(fontRatio * 20);

    }

    private void resetCam() {
        _mySurfaceView.reset();
        if(_cam != null) {
            _cam.stopPreview();
            _cam.setPreviewCallback(null);
            _cam.release();
            _cam = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL: {
                // If request is cancelled, the result arrays are empty.
                System.out.println("Perm length: " + grantResults.length);
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    cameraSetup();

                } else {

                    System.out.println("Permissions problem!");
                    finish();
                }
                return;
            }


        }
    }


    @Override
    public void onMessage(final int messageID, final Object message) {


        switch (messageID) {

            case MessageHUB.MEASUREMENT_STEP:
                updateUI((MeasurementStepMessage) message);
                break;

            case MessageHUB.DONE_CALIBRATION:

//                _calibrateButton.setBackgroundResource(R.drawable.green_button);

                break;
            default:
                break;
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            cameraSetup();
        }

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            cameraSetup();
        }

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}