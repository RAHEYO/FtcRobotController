package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoRotate extends Command {
    private AutoDecision decision;
    ElapsedTime t = new ElapsedTime();
    int ticks;
    long lastTime;
    double pathTime = 0;

    public AutoRotate(int ticks){
        this.ticks=ticks;
    }

    public AutoRotate(AutoDecision decision){
        this.decision = decision;
    }
    @Override
    public void init() {
        requires(Subsystems.instance.driveSubsystem);
        if(decision!=null){
            this.ticks = (int) decision.findNumber();
        }
        t.reset();
        Subsystems.instance.driveSubsystem.resetMotors();
        Subsystems.instance.driveSubsystem.resetEncoders();

        CombinedPath.LongitudalTrapezoid pth;
        if(ticks>0){
            pth = new CombinedPath.LongitudalTrapezoid(0,ticks,Constants.MAX_TICKS_SPEED,Constants.MAX_TICKS_ACCEL);
        }
        else {
            pth = new CombinedPath.LongitudalTrapezoid(0,ticks,-Constants.MAX_TICKS_SPEED,-Constants.MAX_TICKS_ACCEL);
        }

        Subsystems.instance.driveSubsystem.setMotorPaths(pth);
        pathTime = pth.getTotalTime();
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        long dMilis = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        Subsystems.instance.driveSubsystem.driveWithPID((double)dMilis / 1000.0, -1, 1, -1, 1);
    }

    @Override
    public boolean shouldRemove() {
        return Subsystems.instance.driveSubsystem.isPIDDone();
    }

    @Override
    public void end() {
        Subsystems.instance.driveSubsystem.setMotors(0,0,0,0);
    }
}

