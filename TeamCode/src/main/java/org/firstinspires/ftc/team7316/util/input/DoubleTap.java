package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Listenable;

/**
 * Created by Maxim on 1/31/2017.
 */
public class DoubleTap extends Listenable {

    private ElapsedTime timer;
    private double delay;

    private boolean secondPress = false;

    public DoubleTap(double delay) {
        timer = new ElapsedTime();
        this.delay = delay;
    }


    @Override
    public void init() {
        secondPress = false;
    }

    @Override
    public void subLoop() {
        if (state()) {
            if (timer.seconds() <= delay) {
                this.secondPress = true;
            } else {
                timer.reset();
                this.secondPress = false;
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }

    @Override
    public boolean state() {
        return secondPress;
    }

}
