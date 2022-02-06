package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoDunk extends Command {
    ElapsedTime t = new ElapsedTime();
    double lastTime;

    @Override
    public void init() { lastTime = t.seconds(); }

    @Override
    public void loop() { Subsystems.instance.armSub.turnServo(0.5); }

    @Override
    public boolean shouldRemove() {
        return t.seconds() - lastTime > 1.5;
    }

    @Override
    public void end() {
        Hardware.instance.armServo.setPosition(0);
    }

}

