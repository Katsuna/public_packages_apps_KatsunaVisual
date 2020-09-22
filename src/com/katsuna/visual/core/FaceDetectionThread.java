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
package com.katsuna.visual.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera.Size;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class FaceDetectionThread extends Thread {

    public static final String FACEDETECTIONTHREAD_TAG = "FaceDetectionThread_Tag";

    private Face _currentFace;
    private final byte[] _data;
    private final Size _previewSize;
    private Bitmap _currentFrame;

    public FaceDetectionThread(final byte[] data, final Size previewSize) {
        _data = data;
        _previewSize = previewSize;
    }

    public Face getCurrentFace() {
        return _currentFace;
    }

    public Bitmap getCurrentFrame() {
        return _currentFrame;
    }

    /**
     * bla bla bla
     */
    @Override
    public void run() {

        long t = System.currentTimeMillis();

        YuvImage yuvimage = new YuvImage(_data, ImageFormat.NV21,
                _previewSize.width, _previewSize.height, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if (!yuvimage.compressToJpeg(new Rect(0, 0, _previewSize.width,
                _previewSize.height), 100, baos)) {

       //     Log.e("Camera", "compressToJpeg failed");

        }

     //   Log.i("Timing", "Compression finished: "
      //          + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();

        BitmapFactory.Options bfo = new BitmapFactory.Options();
        bfo.inPreferredConfig = Bitmap.Config.RGB_565;

        _currentFrame = BitmapFactory.decodeStream(new ByteArrayInputStream(
                baos.toByteArray()), null, bfo);

      //  Log.i("Timing", "Decode Finished: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();

        // Rotate the so it siuts our portrait mode
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        matrix.preScale(-1, 1);
        // We rotate the same Bitmap
        _currentFrame = Bitmap.createBitmap(_currentFrame, 0, 0,
                _previewSize.width, _previewSize.height, matrix, false);

      //  Log.i("Timing",
      //          "Rotate, Create finished: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();

        if (_currentFrame == null) {
          //  Log.e(FACEDETECTIONTHREAD_TAG, "Could not decode Image");
            return;
        }

        FaceDetector d = new FaceDetector(_currentFrame.getWidth(),
                _currentFrame.getHeight(), 1);

        Face[] faces = new Face[1];
        d.findFaces(_currentFrame, faces);

      //  Log.i("Timing",
     //           "FaceDetection finished: " + (System.currentTimeMillis() - t));
        t = System.currentTimeMillis();

        _currentFace = faces[0];
   //     Log.d(FACEDETECTIONTHREAD_TAG, "Found: " + faces[0] + " Faces");
    }
}
