package org.firstinspires.ftc.team7316.util;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.team7316.maps.Hardware;

/**
 * Created by jerry on 11/10/17.
 */

public class GyroWrapper {

    private BNO055IMU gyro;
    private double currentYaw = 0.0;
    private GyroAngles lastAngles;

    public GyroWrapper(BNO055IMU gyro) {
        this.gyro = gyro;
    }


    public GyroAngles angles() {
        double yaw = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        // https://www.desmos.com/calculator/6lhyvqzpbh
        // Converts from yaw to heading, where 0 = currentYaw, and range is -180 to 180
        double head = yaw - currentYaw;
        if (head > 180) {
            head -= 360;
        } else if (head < -180) {
            head += 360;
        }
        head *= -1;

        double pit = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).secondAngle;
        double roll = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;

        lastAngles = new GyroAngles(head, yaw, pit, roll);
        return lastAngles;
    }

    /**
     * Sets the robot's current heading to zero
     */
    public void resetHeading(double angle) {
        currentYaw = angle;
    }

    public void logAngles() {
        Hardware.log("heading", lastAngles.heading);
        Hardware.log("pitch", lastAngles.pitch);
        Hardware.log("roll", lastAngles.roll);
    }

}
