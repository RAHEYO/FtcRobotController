package org.firstinspires.ftc.team7316.util;

import android.content.Context;
import android.os.Environment;

import org.firstinspires.ftc.robotcore.external.Const;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 2/17/2017.
 */

public class Util {

    /**
     * Map a value x in the range [a1, b1] to a new value in the range [a2, b2]
     */
    public static double map(double x, double a1, double b1, double a2, double b2) {
        return (b2 - a2) * (x - a1) / (b1 - a1) + a2;
    }

    public static double modBueno(double a, double b) {
        if (a > 0) {
            return a % b;
        } else {
            return a % b + b;
        }
    }

    public static double wrap(double theta) {
        double num = (theta - 180) % (360);

        if (num < 0) {
            num += 180;
        } else {
            num -= 180;
        }
        return num;
    }

    /**
     * Clamps value within the values lo and hi
     * @return a value between lo and hi
     */
    public static double clamp(double value, double lo, double hi) {
        if(value < lo) {
            return lo;
        }
        if(value > hi) {
            return hi;
        }
        return value;
    }

    /**
     * finds angle from positive x axis based on x and y point
     * @param x
     * @param y
     * @return
     */
    public static double getAngleFromPoint(double x, double y) {
        double referenceAngle = Math.atan( Math.abs(y/x) );
        if(x >= 0 && y > 0) {
            return referenceAngle;
        }
        else if(x < 0 && y > 0) {
            return Math.PI - referenceAngle;
        }
        else if(x < 0 && y <= 0) {
            return Math.PI + referenceAngle;
        }
        else if(x >= 0 && y <= 0) {
            return 2 * Math.PI - referenceAngle;
        }
        return 0.0;
    }

}
