package org.firstinspires.ftc.team7316.util.copypastaLib;

/**
 * Created by jerry on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class LinearDerivativePath implements MotionPath {
    private double velStart;
    private double velEnd;
    private double accel;
    private double distance;
    private double totalTime;

    public LinearDerivativePath(double distance, double v0, double v, double a) {
        this.velStart = v0;
        this.velEnd = v;
        this.accel = a;
        this.distance = distance;
        this.totalTime = this.getTotalTime();
    }

    public LinearDerivativePath(double v0, double v, double a) {
        this.velStart = v0;
        this.velEnd = v;
        this.accel = a;
        if(this.accel == 0.0D) {
            throw new IllegalArgumentException("This constructor requires an acceleration");
        } else {
            this.totalTime = this.getTotalTime();
        }
    }

    public LinearDerivativePath(double distance, double v) {
        this.velStart = v;
        this.velEnd = v;
        this.accel = 0.0D;
        this.distance = distance;
        this.totalTime = this.getTotalTime();
    }

    public MotionPath copy() {
        return new LinearDerivativePath(this.distance, this.velStart, this.velEnd, this.accel);
    }

    public double getTotalTime() {
        if(this.totalTime != 0.0D) {
            return this.totalTime;
        } else if(this.accel == 0.0D) {
            this.totalTime = this.distance / this.velStart;
            return this.totalTime;
        } else {
            this.totalTime = (this.velEnd - this.velStart) / this.accel;
            return this.totalTime;
        }
    }

    public double getTotalDistance() {
        if(this.distance != 0.0D) {
            return this.distance;
        } else {
            this.distance = this.getPosition(this.getTotalTime());
            return this.distance;
        }
    }

    public double getSpeed(double time) {
        return this.velStart + this.accel * time;
    }

    public double getAccel(double time) {
        return this.accel;
    }

    public double getPosition(double time) {
        return this.velStart * time + 0.5D * this.getAccel(time) * time * time;
    }

    public boolean validate() {
        return true;
    }
}

