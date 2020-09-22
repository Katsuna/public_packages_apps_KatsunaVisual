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
package com.katsuna.visual.measurement;

/**
 * Created by christosmitatakis on 2/19/17.
 */

import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Math.*;

public class Acuity {

    public static double distance = 0.40; // in metres
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

    public  Double round(double doubleVar) {
        Double roundOff = (double) Math.round(doubleVar * 1000) / 1000;
        return roundOff;
    }

}
