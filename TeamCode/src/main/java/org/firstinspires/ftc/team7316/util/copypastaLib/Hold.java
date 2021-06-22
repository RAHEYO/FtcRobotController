package org.firstinspires.ftc.team7316.util.copypastaLib;

/**
 * Created by jerry on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Hold implements MotionPath {
    double time;
    double value;

    public Hold(double time) {
        this.time = time;
        this.value = 0.0D;
    }

    public Hold(double time, double value) {
        this.time = time;
        this.value = value;
    }

    public MotionPath copy() {
        return new Hold(this.time, this.value);
    }

    public double getSpeed(double time) {
        return 0.0D;
    }

    public double getAccel(double time) {
        return 0.0D;
    }

    public double getPosition(double time) {
        return this.value;
    }

    public double getTotalTime() {
        return this.time;
    }

    public double getTotalDistance() {
        return 0.0D;
    }

    public boolean validate() {
        return true;
    }
}

