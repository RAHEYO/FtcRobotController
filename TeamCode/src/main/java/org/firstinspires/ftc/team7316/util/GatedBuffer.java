package org.firstinspires.ftc.team7316.util;

/**
 * Created by andrew on 1/26/17.
 */
public class GatedBuffer extends Buffer {

    private double lastVal = 0;
    private double maxDelta;

    public GatedBuffer(int bufferSize, double maxDelta) {
        super(bufferSize);
        this.maxDelta = maxDelta;
    }

    @Override
    public void pushValue(double value) {
        if (Math.abs(value - this.lastVal) < this.maxDelta) {
            super.pushValue(value);
        }
        lastVal = value;
    }

}
