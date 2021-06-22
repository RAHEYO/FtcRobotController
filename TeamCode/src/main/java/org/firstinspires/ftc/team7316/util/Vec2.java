package org.firstinspires.ftc.team7316.util;

/**
 * Created by Maxim on 9/13/2017.
 *
 * lmao you suck
 */

public class Vec2 {

    public static final Vec2 ZERO = new Vec2(0, 0), X = new Vec2(1, 0), Y = new Vec2(0, 1);

    public final double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vec2 magTheta(double mag, double theta) {
        return Vec2.X.scl(mag).rotate(theta);
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 withX(double x) {
        return new Vec2(x, this.y);
    }

    public Vec2 withY(double y) {
        return new Vec2(this.x, y);
    }

    public Vec2 scl(double mag) {
        return new Vec2(this.x * mag, this.y * mag);
    }

    public double mag2() {
        return x*x + y*y;
    }

    public double mag() {
        return (double) Math.sqrt(mag2());
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vec2 norm() {
        if (mag() == 0f) {
            return this;
        }
        return scl(1 / mag());
    }

    public Vec2 rotate(double theta) {
        return new Vec2(
                x * -Math.cos(theta) + y * Math.sin(theta),
                x * Math.sin(theta) + y * Math.cos(theta)
        );
    }

}
