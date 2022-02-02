package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoSlide extends Command {
    ElapsedTime t = new ElapsedTime();
    double lastTime;
    int motorPower;
    double duration;


    public AutoSlide(int motorPower, double duration){
        this.motorPower=motorPower;
        this.duration = duration;
    }

    @Override
    public void init() {
        requires(Subsystems.instance.driveSubsystem);

        lastTime = t.seconds();
    }

    @Override
    public void loop() {
        Subsystems.instance.driveSubsystem.setMotors(
                motorPower,
                -motorPower,
                -motorPower,
                motorPower
        );
    }

    @Override
    public boolean shouldRemove() { return t.seconds() - lastTime > duration; }

    @Override
    public void end() {
        Subsystems.instance.driveSubsystem.setMotors(0,0,0,0);
    }
}

