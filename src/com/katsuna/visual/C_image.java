package com.katsuna.visual;

import android.graphics.Bitmap;

/**
 * Created by gmithighracks on 11/29/16.
 */

public class C_image {

    private Bitmap bitMap;
    private int rotation;
    private double score;


    public Bitmap getBitMap() {
        return bitMap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitMap = bitMap;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public C_image(Bitmap b, int r, double s) {
        bitMap = b;
        rotation = r;
        score = s;

    }

}
