package org.firstinspires.ftc.team7316.subsystems;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.commands.JoystickDrive;
import org.firstinspires.ftc.team7316.util.copypastaLib.MotionPath;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class MecanumDriveSubsystem extends Subsystem {

    @Override
    public void reset() {
        Hardware.instance.frontLeftMotor.setPower(0);
        Hardware.instance.frontRightMotor.setPower(0);
        Hardware.instance.backLeftMotor.setPower(0);
        Hardware.instance.backRightMotor.setPower(0);
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new JoystickDrive();
    }

    public void setMotors(double lFrontSet, double rFrontSet, double lBackSet, double rBackSet ) {
        Hardware.instance.frontLeftMotor.setPower(-lFrontSet);
        Hardware.instance.frontRightMotor.setPower(rFrontSet);
        Hardware.instance.backLeftMotor.setPower(lBackSet);
        Hardware.instance.backRightMotor.setPower(rBackSet);
    }

    public void setSides(double leftset, double rightset){
        Hardware.instance.frontLeftMotor.setPower(-leftset);
        Hardware.instance.frontRightMotor.setPower(rightset);
        Hardware.instance.backLeftMotor.setPower(leftset);
        Hardware.instance.backRightMotor.setPower(rightset);
    }
    public void setDiagonals(double northEastSet, double northWestSet) {
        Hardware.instance.frontLeftMotor.setPower(-northWestSet);
        Hardware.instance.frontRightMotor.setPower(northEastSet);
        Hardware.instance.backLeftMotor.setPower(northEastSet);
        Hardware.instance.backRightMotor.setPower(northWestSet);
    }
    public void resetMotors(){
        setMotors(0,0,0,0);
    }

    public void resetEncoders(){
        Hardware.instance.frontLeftMotorWrapper.reset();
        Hardware.instance.frontRightMotorWrapper.reset();
        Hardware.instance.backLeftMotorWrapper.reset();
        Hardware.instance.backRightMotorWrapper.reset();
    }
    public void driveWithPID(double dTime){
        Hardware.instance.frontLeftMotorWrapper.setPowerPID(dTime);
        Hardware.instance.frontRightMotorWrapper.setPowerPID(dTime);
        Hardware.instance.backLeftMotorWrapper.setPowerPID(dTime);
        Hardware.instance.backRightMotorWrapper.setPowerPID(dTime);
    }
    public void setMotorPaths(MotionPath path){
        Hardware.instance.frontLeftMotorWrapper.setPath(path);
        Hardware.instance.frontRightMotorWrapper.setPath(path);
        Hardware.instance.backLeftMotorWrapper.setPath(path);
        Hardware.instance.backRightMotorWrapper.setPath(path);
    }
    public void setMotorPathsStrafe(MotionPath path, MotionPath reversePath){
        Hardware.instance.frontLeftMotorWrapper.setPath(reversePath);
        Hardware.instance.frontRightMotorWrapper.setPath(path);
        Hardware.instance.backLeftMotorWrapper.setPath(path);
        Hardware.instance.backRightMotorWrapper.setPath(reversePath);
    }
    public boolean checkMotorsFinished(){
        return Hardware.instance.frontLeftMotorWrapper.completedDistance() && Hardware.instance.frontRightMotorWrapper.completedDistance() && Hardware.instance.backLeftMotorWrapper.completedDistance() && Hardware.instance.backRightMotorWrapper.completedDistance();
    }
}
