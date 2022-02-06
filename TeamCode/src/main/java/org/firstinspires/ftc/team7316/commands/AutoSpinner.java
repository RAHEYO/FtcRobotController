package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoSpinner extends Command {
    ElapsedTime t = new ElapsedTime();
    double startTime;

    boolean isRed;

    public AutoSpinner(boolean isRed) { this.isRed = isRed; }

    @Override
    public void init() {
        startTime = t.seconds();
    }

    @Override
    public void loop() {
        Subsystems.instance.spinnerSub.autoSpin(isRed);

        Hardware.log("Spinner DTime: ", t.seconds()-startTime);
    }

    @Override
    public boolean shouldRemove() { return t.seconds() - startTime > 5; }

    @Override
    public void end() { Subsystems.instance.spinnerSub.reset(); }
}

