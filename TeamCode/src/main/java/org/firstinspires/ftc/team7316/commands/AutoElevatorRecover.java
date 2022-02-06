package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class AutoElevatorRecover extends Command {
    ElapsedTime t = new ElapsedTime();
    double lastTime;


    @Override
    public void init() { lastTime = t.seconds(); }

    @Override
    public void loop() { Subsystems.instance.armSub.drop(); }

    @Override
    public boolean shouldRemove() {
        return t.seconds() - lastTime > 1.7;
    }

    @Override
    public void end() { Hardware.instance.armMotor.setPower(0); }

}

