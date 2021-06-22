package org.firstinspires.ftc.team7316.util.commands.flow;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 11/19/16.
 */
public class Wait extends Command {

    private ElapsedTime time;
    private double wantedTime;

    public Wait(double wantedTime) {
        this.wantedTime = wantedTime;
        this.time = new ElapsedTime();
    }

    @Override
    public void init() {
        this.time.reset();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return time.seconds() > this.wantedTime;
    }

    @Override
    public void end() {

    }
}
