package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.copypastaLib.MotionPath;

import java.util.ArrayList;


/**
 * Generic PID class that can be assigned to specific motors.
 */

public class PID {

    private double p, i, d;
    private double previous, sum;
    public ArrayList<String[]> dataPoints = new ArrayList<>();

    private double out;

    private ElapsedTime timer = new ElapsedTime();

    private boolean usePath;
    private MotionPath path;

    // non-motionpath garbage
    private Direction direction;
    private double maxSpeed; // ticks per second usually
    private double previousTime;
    private int targetTicksCurrent = 0;
    private int targetTicksFinal = 0;
    private int startTicks = 0;
    private String name;

    public PID(double p, double i, double d, double maxSpeed,String name) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.name = name;
        this.maxSpeed = maxSpeed;

        out = 0;

        previousTime = 0;
        timer = new ElapsedTime();

        usePath = false;

        reset();
    }

    /**
     * Sets a motion path for the motor to follow instead of just a target distance
     */
    public void setPath(MotionPath path) {
        usePath = true;
        dataPoints.clear();
        this.path = path;
    }

    public boolean usingPath() {
        return usePath;
    }

    /**
     * Sets a specific distance for a target, and uses a basic linear motion path
     * @param targetTicks the desired distance reach
     * @param currentPosition current position of the motor
     */
    public void setTargetTicks(int targetTicks, int currentPosition) {
        usePath = false;
        targetTicksFinal = targetTicks;
        targetTicksCurrent = currentPosition;
        startTicks = currentPosition;
        if(targetTicksCurrent < targetTicksFinal) {
            direction = Direction.FORWARD;
        }
        else {
            direction = Direction.BACKWARD;
        }
    }

    /**
     * Calculates and updates the target of the loop if there is no motion path being used
     */
    public void updateTargetTicksCurrent() {
        double elapsedTime = timer.seconds() - previousTime;
        double distance = maxSpeed * elapsedTime;
        switch (direction) {
            case FORWARD:
                if(targetTicksCurrent + distance > targetTicksFinal) {
                    targetTicksCurrent = targetTicksFinal;
                }
                else {
                    targetTicksCurrent += distance;
                }
                break;
            case BACKWARD:
                if(targetTicksCurrent - distance < targetTicksFinal) {
                    targetTicksCurrent = targetTicksFinal;
                }
                else {
                    targetTicksCurrent -= distance;
                }
                break;
        }
        previousTime = timer.seconds();
    }

    public int getTargetTicksCurrent() {
        if(usePath) {
            return (int)path.getPosition(this.timer.time());
        }
        return targetTicksCurrent;
    }

    public int getTargetTicksFinal() {
        if(usePath) {
            return (int)path.getTotalDistance();
        }
        return targetTicksFinal;
    }

    /**
     * Calculates the actual output to the motors
     */
    public double getPower(double error, double dtime, double sensor, double sensorChange) {
        this.sum += error;

        double delta = (error - this.previous) / dtime;
        this.previous = error;

        double predSpeed = getPredictedSpeed(timer.seconds());

        Hardware.log("delta", delta);

        out = p * error + i * sum + d * delta + predSpeed;

        double speed = (sensorChange) / dtime;
        dataPoints.add(new String[]{String.valueOf(timer.seconds()),
                String.valueOf(error),
                String.valueOf(predSpeed),
                String.valueOf(speed),
                String.valueOf(this.path.getPosition(timer.seconds())),
                String.valueOf(sensor),
                String.valueOf(out)});

        Hardware.log("error"+name, error);
        Hardware.log("speed", predSpeed);
        Hardware.log("power", out);

        return out;
    }

    public Double getElapsedSeconds() {
        return timer.seconds();
    }

    public double getPredictedSpeed(double time) {
        if(usePath) {
            return this.path.getSpeed(time);
        }
        if(time * maxSpeed + startTicks > targetTicksFinal) {
            return 0;
        }
        return maxSpeed;
    }

    public void reset() {
        timer.reset();
        previous = 0;
        sum = 0;

        path = null;
        usePath = false;

        previousTime = 0;
        direction = Direction.STOPPED;
        targetTicksCurrent = 0;
        targetTicksFinal = 0;
    }

    public boolean finished() {
        if (usePath) {
            return timer.seconds() > path.getTotalTime();
        }
        return false;
    }

    /**
     * Will not be used when using a motion path
     */
    enum Direction {
        FORWARD, BACKWARD, STOPPED
    }
}
