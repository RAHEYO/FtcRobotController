package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoElevator extends Command {
    ElapsedTime t = new ElapsedTime();
    double lastTime;

    int elementLevel;

    public AutoElevator(int elementLevel) {
        this.elementLevel = elementLevel;
    }


    @Override
    public void init() {
        Subsystems.instance.armSub.reset();

        lastTime = t.seconds();
    }

    @Override
    public void loop() {
        Subsystems.instance.armSub.lift();
    }

    @Override
    public boolean shouldRemove() {
        if (elementLevel == 0) return t.seconds() - lastTime > 0.7;
        else if (elementLevel == 1) return t.seconds() - lastTime > 1.3;
        else return t.seconds() - lastTime > 1.7;
    }

    @Override
    public void end() {
        Hardware.instance.armMotor.setPower(0);
    }


}

