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

import com.katsuna.visual.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MeasurementPoint {

    /**
     * The actual measured position in the 'real' world
     */
    private float _realPosition;

    /**
     * The position measured by the algorithm
     */
    private float _measurePosition;

    /**
     * The average eye distance during this measurement step
     */
    private float _averageEyeDistance;

    /**
     * How long it took
     */
    private float _processTime;

    /**
     * Amount of faces found
     */
    private int _foundFaces = 0;

    List<Point> _points = new ArrayList<Point>();

    public void setPoints(final List<Point> points) {
        _points = points;
    }

    public List<Point> getPoints() {
        return _points;
    }

    public float getRealPosition() {
        return _realPosition;
    }

    public void setRealPosition(final float realPosition) {
        _realPosition = realPosition;
    }

    public float getMeasurePosition() {
        return _measurePosition;
    }

    public void setMeasurePosition(final float measurePosition) {
        _measurePosition = measurePosition;
    }

    public float getAverageEyeDistance() {
        return _averageEyeDistance;
    }

    public void setAverageEyeDistance(final float averageEyeDistance) {
        _averageEyeDistance = averageEyeDistance;
    }

    public float getProcessTime() {
        return _processTime;
    }

    public void setProcessTime(final float processTime) {
        _processTime = processTime;
    }

    public int getFoundFaces() {
        return _foundFaces;
    }

    public void setFoundFaces(final int foundFaces) {
        this._foundFaces = foundFaces;
    }

    public String[] toStringArray(final Measurement m) {
        String measuredString = "";
        String eyeDistString = "";

        float currMeasAvg = 0;
        float currEyeAvg = 0;

        for (int i = 0; i < 5; ++i) {
            if (i >= _points.size()) {
                if (i == 0 || i == 4) {
                    measuredString += ",0";
                    eyeDistString += ",0";
                } else {
                    measuredString += ",0,0";
                    eyeDistString += ",0,0";
                }
            } else {
                currMeasAvg += _points.get(i).getDeviceDistance();
                currEyeAvg += _points.get(i).getEyeDistance();

                if (i == 0 || i == 4) {

                    measuredString += ","
                            + Util.MM_TO_CM(_points.get(i).getDeviceDistance());
                    eyeDistString += "," + _points.get(i).getEyeDistance();
                } else {
                    measuredString += ","
                            + Util.MM_TO_CM(_points.get(i).getDeviceDistance())
                            + "," + Util.MM_TO_CM((currMeasAvg / (i + 1)));
                    eyeDistString += "," + _points.get(i).getEyeDistance()
                            + "," + (currEyeAvg / (i + 1));
                }
            }
        }

        return (_realPosition + measuredString + "," + _measurePosition
                + eyeDistString + "," + _averageEyeDistance + ","
                + _processTime + "," + _foundFaces).split(",");
    }
}
