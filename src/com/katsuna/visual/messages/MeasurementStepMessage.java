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
package com.katsuna.visual.messages;

public class MeasurementStepMessage {

    private float _currentAvgEyeDistance;
    private float _confidence;
    private float _distToFace;
    private float _processTimeForLastFrame;
    private float _eyesDistance;
    private int _measurementsLeft;
    private float _measuredPositionWithoutMistake;

    public float getCurrentAvgEyeDistance() {
        return _currentAvgEyeDistance;
    }

    public void setCurrentAvgEyeDistance(final float currentAvgEyeDistance) {
        _currentAvgEyeDistance = currentAvgEyeDistance;
    }

    public float getConfidence() {
        return _confidence;
    }

    public void setConfidence(final float confidence) {
        _confidence = confidence;
    }

    public float getDistToFace() {
        return _distToFace;
    }

    public void setDistToFace(final float distToFace) {
        _distToFace = distToFace;
    }

    public float getProcessTimeForLastFrame() {
        return _processTimeForLastFrame;
    }

    public void setProcessTimeForLastFrame(final float processTimeForLastFrame) {
        _processTimeForLastFrame = processTimeForLastFrame;
    }

    public float getEyesDistance() {
        return _eyesDistance;
    }

    public void setEyesDistance(final float eyesDistance) {
        _eyesDistance = eyesDistance;
    }

    public int getMeasurementsLeft() {
        return _measurementsLeft;
    }

    public void setMeasurementsLeft(final int measurementsLeft) {
        _measurementsLeft = measurementsLeft;
    }

    public float getMeasuredPositionWithoutMistake() {
        return _measuredPositionWithoutMistake;
    }

    public void setMeasuredPositionWithoutMistake(
            float measuredPositionWithoutMistake) {
        _measuredPositionWithoutMistake = measuredPositionWithoutMistake;
    }

}
