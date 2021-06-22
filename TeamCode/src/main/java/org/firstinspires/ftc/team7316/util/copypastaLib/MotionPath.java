package org.firstinspires.ftc.team7316.util.copypastaLib;

/**
 * Created by Adumb on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public interface MotionPath {
    MotionPath copy();

    double getSpeed(double var1);

    double getAccel(double var1);

    double getPosition(double var1);

    double getTotalTime();

    double getTotalDistance();

    boolean validate();
}
