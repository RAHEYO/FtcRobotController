package org.firstinspires.ftc.team7316.util.copypastaLib;

import android.util.Log;

/**
 * Created by jerry on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class CombinedPath implements MotionPath {
    private MotionPath[] paths;
    private double travelledPathDistance;
    private double start;
    private double distance;
    private double totTime;

    public CombinedPath(double start) {
        this.start = start;
    }

    public CombinedPath(double start, MotionPath... p) {
        this.paths = p;
        this.start = start;
    }

    private MotionPath getCurve(double time) {
        this.travelledPathDistance = 0.0D;
        if(time <= this.paths[0].getTotalTime()) {
            return this.paths[0];
        } else {
            for(int i = 1; i < this.paths.length; ++i) {
                double sum = this.paths[i].getTotalTime();
                double dsum = 0.0D;

                for(int j = i - 1; j >= 0; --j) {
                    sum += this.paths[j].getTotalTime();
                    dsum += this.paths[j].getTotalDistance();
                }

                if(time <= sum) {
                    this.travelledPathDistance = dsum;
                    return this.paths[i];
                }
            }

            return this.paths[this.paths.length - 1];
        }
    }

    private double getDeltaTime(double time) {
        double maxSum = 0.0D;
        if(time <= this.paths[0].getTotalTime()) {
            return time;
        } else {
            for(int i = 1; i < this.paths.length; ++i) {
                double sum = 0.0D;

                for(int j = i - 1; j >= 0; --j) {
                    sum += this.paths[j].getTotalTime();
                }

                maxSum = sum + this.paths[i].getTotalTime();
                if(time <= maxSum) {
                    return time - sum;
                }
            }

            return 0;
        }
    }

    public void setPath(MotionPath[] p) {
        this.paths = p;
    }

    public MotionPath copy() {
        MotionPath[] paf = new MotionPath[this.paths.length];

        for(int i = 0; i < paf.length; ++i) {
            paf[i] = this.paths[i].copy();
        }

        return new CombinedPath(this.start, paf);
    }

    public double getSpeed(double time) {
        double dt = this.getDeltaTime(time);
        return time > this.getTotalTime()?this.getCurve(time).getSpeed(dt + dt < this.getCurve(time).getTotalTime()?this.getCurve(time).getTotalTime():0.0D):this.getCurve(time).getSpeed(dt);
    }

    public double getAccel(double time) {
        double dt = this.getDeltaTime(time);
        return this.getCurve(time).getAccel(dt);
    }

    public double getPosition(double time) {
        double dt = this.getDeltaTime(time);
        return time >= this.getTotalTime() ? this.start + this.getTotalDistance() : this.start + this.getCurve(time).getPosition(dt) + this.travelledPathDistance;
    }

    public double getTotalTime() {
        if(this.totTime != 0.0D) {
            return this.totTime;
        } else {
            double sum = 0.0D;
            MotionPath[] var6 = this.paths;
            int var5 = this.paths.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                MotionPath p = var6[var4];
                sum += p.getTotalTime();
            }

            this.totTime = sum;
            return sum;
        }
    }

    public double getTotalDistance() {
        if(this.distance != 0.0D) {
            return this.distance;
        } else {
            double sum = 0.0D;
            MotionPath[] var6 = this.paths;
            int var5 = this.paths.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                MotionPath p = var6[var4];
                sum += p.getTotalDistance();
            }

            this.distance = sum;
            return sum;
        }
    }

    public boolean validate() {
        return true;
    }

    public static class LongitudalTrapezoid extends CombinedPath {
        public LongitudalTrapezoid(double start, double distance, double maxV, double a) {
            this(start, distance, 0, maxV, a);
        }

        public LongitudalTrapezoid(double start, double distance, double startV, double maxV, double a) {
            super(start);
            MotionPath[] p = new MotionPath[]{new LinearDerivativePath(startV, maxV, a), null, new LinearDerivativePath(maxV, 0.0D, -a)};
            p[1] = new LinearDerivativePath(distance - 2.0D * p[0].getTotalDistance(), maxV);
            if(Math.abs(p[0].getTotalDistance()) > Math.abs(distance / 2.0D)) {
                double newTime = Math.sqrt(distance / a);
                Log.d("-----PATH PLANNING-----", "Couldn't get to target speed in time");
                p[0] = new LinearDerivativePath(0.0D, newTime * a, a);
                p[1] = new Hold(0.0D);
                p[2] = new LinearDerivativePath(newTime * a, 0.0D, -a);
            }

            this.setPath(p);
        }
    }
}

