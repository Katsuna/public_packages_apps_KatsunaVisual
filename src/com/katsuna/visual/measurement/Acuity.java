package com.katsuna.visual.measurement;

/**
 * Created by christosmitatakis on 2/19/17.
 */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Math.*;

public class Acuity {

    public static double distance = 0.4; // in metres
    // In Android, we should pull these values programmatically with system calls
    private  int devicePPI = 423; // Nexus 5X
    private  double scaledDensity = 4; // 480dpi / 160dpi, Nexus 5X

    public Acuity()
    {

    }

    public Acuity(int devicePPI, double scaledDensity)
    {
        this.devicePPI = devicePPI;
        this.scaledDensity = scaledDensity;
    }

    public static void main(String[] args) throws java.lang.Exception {

    }

    public  double getSizeInMM(double distance, double logMAR) {
        double size = 5000 * distance * Math.tan(Math.pow(10, logMAR) * Math.PI / 10800);
        return size;
    }

    public  double getHeightInPx(double heightInMM) {
        // Custom code only for ideone, on Android there are better system calls to be made
        double devicePPMM = devicePPI / 25.4; // 25.4 mm to an inch
        return heightInMM * devicePPMM;
    }

    public  boolean checkIfPossibleSize(double heightInMM, double heightInPx) {
        // The Sloan C gap should be 1 arc min, and the whole drawable should be x5 that
        // Minimum separate distance on a screen is a pixel, so the height must be >=5 pixels
        boolean possible = (heightInMM >= 0.5 && heightInPx >= 5);
        return possible;
    }

    public  int getMinimumSuggestedFontSize(double heightInPx) {
        // according to HFDS, visual angle of the character height should be 16 - 24 arc mins
        // ~= 3.2 - 4.8 x minimum Landolt C height (5 arc mins)
        // Do a rough approx of x4, = 20 arc mins
        double heightOfCharacterInPx = 4 * heightInPx;
        // transform to scaled-independent pixels, created for fonts (sp)
        // Used only on Android
        //float sp = heightOfCharacterInPx / getResources().getDisplayMetrics().scaledDensity;

        int sp = (int) Math.round(heightOfCharacterInPx / scaledDensity);
        return sp;
    }

    public  double round(double doubleVar) {
        double roundOff = (double) Math.round(doubleVar * 1000) / 1000;
        return roundOff;
    }

}
