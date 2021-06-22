package org.firstinspires.ftc.team7316.util.input;

/**
 * Wraps the entire joystick.
 */
public class JoystickWrapper {

    public enum Joystick {
        LEFT, RIGHT
    }

    private Joystick input;
    private GamepadWrapper gpSource;

    public JoystickWrapper(Joystick input, GamepadWrapper gpSource) {
        this.input = input;
        this.gpSource = gpSource;
    }

    public double getX() {
        switch (input) {
            case LEFT:
                return deadzone(gpSource.axisValue(GamepadAxis.L_STICK_X));
            case RIGHT:
                return deadzone(gpSource.axisValue(GamepadAxis.R_STICK_X));
        }
        return 0; // Shouldn't happen
    }

    public double getY() {
        switch (input) {
            case LEFT:
                return deadzone(gpSource.axisValue(GamepadAxis.L_STICK_Y));
            case RIGHT:
                return deadzone(gpSource.axisValue(GamepadAxis.R_STICK_Y));
        }
        return 0; // Shouldn't happen at all
    }

    /**
     * Get the current angle of the joystick.
     * @return The angle in radians, counter-clockwise with 0 as pointing right.
     */
    public double getAngle() {
        return Math.atan2(getY(), getX());
    }

    private static double deadzone(double value) {
        double deadzoneVal = 0.01;

        if (Math.abs(value) < deadzoneVal) {
            return 0;
        } else if (value > 0) {
            return deadzoneVal + (1 - deadzoneVal) * value;
        } else {
            return -deadzoneVal + (1 - deadzoneVal) * value;
        }
    }

}
