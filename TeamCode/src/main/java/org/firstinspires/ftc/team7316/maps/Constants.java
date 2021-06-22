package org.firstinspires.ftc.team7316.maps;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double SQRT2 = Math.sqrt(2);
    public static final double RADIAN_TO_DEGREES = 180 / Math.PI;
    public static final double DEGREES_TO_RADIANS = Math.PI / 180;

    // These rudimentary variables describe the robot for important auto calculations
    // Change them as you please
    public static final double JOYSTICK_DRIVE_DEADZONE = 0.05;
    public static final double DRIVER_MOTOR_DEADZONE = 0.1;
    public static final double ENCODER_TICK_PER_REV = 1120;
    public static final double ENCODER_REV_PER_WHEEL_REV = 0.5;
    public static final double WHEEL_RADIUS = 2; // inches
    public static final double WHEEL_CIRCUMFERENCE = WHEEL_RADIUS * 2 * Math.PI;
    public static final double SLOW_SPEED = 0.6;
    public static final double trayServoDown=.5;
    public static final double trayServoUp=0;


    public static final int DISTANCE_ERROR_RANGE_TICKS = 10;
    public static final double ACCELERATION_SPEED = 0.2;

    public static final double DRIVE_P = .01;
    public static final double DRIVE_I = 0;//0.00005;
    public static final double DRIVE_D = 0;

    private static final double ROBOT_RADIUS = 7; // inches
    public static final double TURN_P_LEFT = 0.072;
    public static final double TURN_P_RIGHT = 0.072;
    public static final double TURN_DEADZONE_LEFT = 0.15;
    public static final double TURN_DEADZONE_RIGHT = 0.1;
    public static final double TRAY_DEADZONE=0.2;
    public static final double TURN_I = 0;
    public static final double TURN_D = 0; //0.005;
    public static final double TURN_M = 551;
    public static final double TURN_B = 117;

    public static final double CHEDDAR_DISTANCE1 = 29;
    public static final double CHEDDAR_DISTANCE2 = 30;
    public static final double CHEDDAR_DISTANCE2_RETURN = 30;
    public static final double RETURN_SPACING = 12;
    public static final double CORRIDOR_DISTANCE = 55;
    public static final double BOX_DISTANCE = 47;
    public static final double CRATER_DISTANCE = 115;
    public static final int TURN_TOWARDS_ZONE_2 = 20;
    public static final double DRIVE_TOWARDS_ZONE_2_CENTER=32;
    public static final double DRIVE_TOWARDS_ZONE_2_SIDES=34;
    public static final int TURN_TOWARDS_CRATER_2_RIGHT = 115;
    public static final int TURN_TOWARDS_CRATER_2_CENTER = 135;
    public static final int TURN_TOWARDS_CRATER_2_LEFT = 155;

    public static final double MAX_TICKS_SPEED = 2000; // ticks per second
    public static final double MAX_TICKS_ACCEL = 900; // ticks per second per second
    public static final double MAX_DEGREES_SPEED = 120; // degrees per second
    public static final double MAX_DEGREES_ACCEL = 75; // degrees per second per second
    public static final double MIN_DRIVE_POWER = 0.15;

    public static int degreesToTicks(int degrees) {
        double d = (double) degrees;
        double inches = degrees * Math.PI * ROBOT_RADIUS / 180;
        return inchesToTicks(inches);
    }

    public static double pixelsToDegrees(double pixels) {
        return pixels * -0.153549166679 + 45.450553337;
    }

    /**
     * @param dist inches
     */
    public static int inchesToTicks(double dist) {
        return (int)(ENCODER_TICK_PER_REV * ENCODER_REV_PER_WHEEL_REV / WHEEL_CIRCUMFERENCE * dist);
    }

    /**
     * @param dist millimeters
     */
    public static int millimetersToTicks(double dist) {
        return (int)(ENCODER_TICK_PER_REV * ENCODER_REV_PER_WHEEL_REV / (WHEEL_CIRCUMFERENCE * 25.4) * dist);
    }
}
