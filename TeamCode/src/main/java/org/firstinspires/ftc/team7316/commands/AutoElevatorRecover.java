package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class AutoElevatorRecover extends Command {
    ElapsedTime t = new ElapsedTime();
    long lastTime;


    @Override
    public void init() { lastTime = System.currentTimeMillis(); }

    @Override
    public void loop() { Subsystems.instance.armSub.drop(); }

    @Override
    public boolean shouldRemove() {
        return t.seconds() - lastTime > 2;
    }

    @Override
    public void end() { Subsystems.instance.armSub.reset(); }

}

