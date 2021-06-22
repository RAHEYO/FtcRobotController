package org.firstinspires.ftc.team7316.util.copypastaLib;

/**
 * Created by adumb on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class IntegralPath implements MotionPath {
    private MotionPath speedPath;
    private double position;
    private double lastTime;

    public IntegralPath(MotionPath speedPath) {
        this.speedPath = speedPath;
        this.position = 0.0D;
        this.lastTime = 0.0D;
    }

    public IntegralPath(double start, MotionPath speedPath) {
        this.speedPath = speedPath;
        this.position = start;
        this.lastTime = 0.0D;
    }

    public MotionPath copy() {
        return new IntegralPath(this.position, this.speedPath.copy());
    }

    public double getSpeed(double time) {
        return this.speedPath.getPosition(time);
    }

    public double getAccel(double time) {
        return this.speedPath.getSpeed(time);
    }

    public double getPosition(double time) {
        this.position += this.getSpeed(time) * (time - this.lastTime);
        this.lastTime = time;
        return this.position;
    }

    public double getTotalTime() {
        return this.speedPath.getTotalTime();
    }

    public double getTotalDistance() {
        return this.getPosition(this.getTotalTime());
    }

    public boolean validate() {
        return true;
    }
}

