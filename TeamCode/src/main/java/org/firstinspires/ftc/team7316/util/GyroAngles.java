package org.firstinspires.ftc.team7316.util;

public class GyroAngles {

    public double heading, yaw, pitch, roll;
    public GyroAngles(double heading, double yaw, double pitch, double roll) {
        this.heading = heading;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public void add(GyroAngles other) {
        this.heading += other.heading;
        this.yaw += other.yaw;
        this.pitch += other.pitch;
        this.roll += other.roll;
    }

}
