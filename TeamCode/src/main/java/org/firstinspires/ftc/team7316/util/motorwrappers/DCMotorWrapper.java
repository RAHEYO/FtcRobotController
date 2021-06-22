package org.firstinspires.ftc.team7316.util.motorwrappers;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.copypastaLib.MotionPath;

/**
 * Created by jerry on 11/15/17.
 */

public class DCMotorWrapper {

    private DcMotor motor;
    public PID pid;
    private long lastTicks;
    private boolean isInverted;
    private double maxPower;
    private boolean inverted;

    public DCMotorWrapper(DcMotor motor, PID pid, boolean encInverted) {
        this.motor = motor;
        this.pid = pid;
        maxPower = 1;
        inverted = encInverted;
    }

    public void setPath(MotionPath path) {
        pid.setPath(path);
    }

    public void setTargetTicks(int ticks) {
        pid.setTargetTicks(motor.getCurrentPosition() + ticks, motor.getCurrentPosition());
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public int getError() {
        Hardware.log("encoder pos", motor.getCurrentPosition());
        return pid.getTargetTicksCurrent() - motor.getCurrentPosition();
    }

    public void setPowerPID(double dTime) {
        if (!pid.usingPath()) {
            pid.updateTargetTicksCurrent();
        }

        double pow = pid.getPower(getError(), dTime, motor.getCurrentPosition(), motor.getCurrentPosition() - lastTicks);
        lastTicks = motor.getCurrentPosition();

        if (Math.abs(pow) > maxPower) {
            pow = (pow > 0) ? maxPower : -maxPower;
        }
        if(isInverted==true){
            motor.setPower(-pow);
        }
        else {
            motor.setPower(pow);
        }
    }

    public boolean completedDistance() {
        return pid.finished() || Math.abs(pid.getTargetTicksFinal() - motor.getCurrentPosition()) < Constants.DISTANCE_ERROR_RANGE_TICKS;

    }

    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public void stop() {
        motor.setPower(0);
    }

    public void reset() {
        stop();
        resetEncoder();
        maxPower = 1;

        pid.reset();
    }

}
