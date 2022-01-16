package org.firstinspires.ftc.team7316.maps;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.GyroWrapper;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.motorwrappers.DCMotorWrapper;

/**
 * Contains all the hardware names and actual hardware objects
 */

public class Hardware {

    public static Hardware instance = null;

    public static final String tag = "IronPanthers";

    public static Telemetry telemetry;

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;
    public DcMotor intakeMotor;
    public Servo intakeServo;
    public DcMotor armMotor;
    public Servo armServo;
    public DcMotor spinnerMotor;
    public GyroWrapper gyroWrapper;


    public BNO055IMU imu;
    public DCMotorWrapper frontLeftMotorWrapper;
    public DCMotorWrapper frontRightMotorWrapper;
    public DCMotorWrapper backLeftMotorWrapper;
    public DCMotorWrapper backRightMotorWrapper;
    public DCMotorWrapper spinnerMotorWrapper;
    //Create all the hardware fields
    public final String frontLeftMotorName = "flmotor";
    public final String frontRightMotorName = "frmotor";
    public final String backLeftMotorName = "blmotor";
    public final String backRightMotorName = "brmotor";
    public final String intakeMotorName = "intakemotor";
    public final String intakeServoName = "intakeservo";
    public final String imuname = "gyro";
    public final String armMotorName = "armmotor";
    public final String armServoName = "armservo";
    public final String spinnerName = "spinnermotor";


    /**
     * Initialize all the hardware fields here
     */
    public Hardware (HardwareMap map) {
        frontLeftMotor = map.dcMotor.get(frontLeftMotorName);
        frontRightMotor= map.dcMotor.get(frontRightMotorName);
        backLeftMotor = map.dcMotor.get(backLeftMotorName);
        backRightMotor= map.dcMotor.get(backRightMotorName);
        intakeMotor = map.dcMotor.get(intakeMotorName);
        intakeServo = map.servo.get(intakeServoName);
        armMotor = map.dcMotor.get(armMotorName);
        armServo = map.servo.get(armServoName);
        spinnerMotor = map.dcMotor.get(spinnerName);


        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



//        BNO055IMU.Parameters gyroParams = new BNO055IMU.Parameters();
////        climbSwitch = map.touchSensor.get(climbSwitchName);
////        gyroParams.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
////        gyroParams.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
////        gyroParams.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
////        gyroParams.loggingEnabled      = true;
////        gyroParams.loggingTag          = "IMU";
////        gyroParams.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
////
////        imu = map.get(BNO055IMU.class, imuname);
////        imu.initialize(gyroParams);
////        gyroWrapper = new GyroWrapper(imu);

        frontLeftMotorWrapper = new DCMotorWrapper(frontLeftMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.MAX_TICKS_SPEED,frontLeftMotorName), true);
        frontRightMotorWrapper = new DCMotorWrapper(frontRightMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.MAX_TICKS_SPEED,frontRightMotorName), false);
        backLeftMotorWrapper = new DCMotorWrapper(backLeftMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.MAX_TICKS_SPEED,backLeftMotorName), false);
        backRightMotorWrapper = new DCMotorWrapper(backRightMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.MAX_TICKS_SPEED,backRightMotorName), false);
        spinnerMotorWrapper = new DCMotorWrapper(spinnerMotor, new PID(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.MAX_TICKS_SPEED,spinnerName), false);
    }

    public static void setHardwareMap(HardwareMap map) {
        instance = new Hardware(map);
    }

    public static void setTelemetry(Telemetry telemetry) {
        Hardware.telemetry = telemetry;
    }

    public static void log(String caption, Object value) {
        if (telemetry != null) {
            telemetry.addData(caption, value);
        }
    }
}