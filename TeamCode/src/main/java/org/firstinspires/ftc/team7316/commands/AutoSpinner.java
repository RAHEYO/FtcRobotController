package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoSpinner extends Command {
//    private AutoDecision decision;
    ElapsedTime t = new ElapsedTime();
    double startTime;
//    long lastTime;
//    double pathTime = 0;

    @Override
    public void init() {
        startTime = t.seconds();
    }

    @Override
    public void loop() {
//        long dMilis = System.currentTimeMillis() - lastTime;
//        lastTime = System.currentTimeMillis();

//        Subsystems.instance.driveSubsystem.driveWithPID((double)dMilis / 1000.0, 1, 1, 1, 1);
        Subsystems.instance.spinnerSub.autoSpin();
    }

    @Override
    public boolean shouldRemove() { return (t.seconds() - startTime) > 7; }

    @Override
    public void end() { Subsystems.instance.spinnerSub.reset(); }
}

