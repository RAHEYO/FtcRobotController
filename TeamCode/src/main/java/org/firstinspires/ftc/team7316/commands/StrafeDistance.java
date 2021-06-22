package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class StrafeDistance extends Command {
    private AutoDecision decision;
    ElapsedTime t = new ElapsedTime();
    int ticks;
    long lastTime;
    double pathTime = 0;

    public StrafeDistance(int ticks){this.ticks = ticks;}

    public void init() {
        requires(Subsystems.instance.mecanumDriveSubsystem);
        if(decision!=null){
            this.ticks = (int) decision.findNumber();
        }
        t.reset();
        Subsystems.instance.mecanumDriveSubsystem.resetMotors();
        Subsystems.instance.mecanumDriveSubsystem.resetEncoders();

        CombinedPath.LongitudalTrapezoid pth;
        CombinedPath.LongitudalTrapezoid reversePth;
        if(ticks>0){
            pth = new CombinedPath.LongitudalTrapezoid(0,ticks, Constants.MAX_TICKS_SPEED,Constants.MAX_TICKS_ACCEL);
            reversePth = new CombinedPath.LongitudalTrapezoid(0,-ticks, Constants.MAX_TICKS_SPEED,Constants.MAX_TICKS_ACCEL);
        }
        else {
            pth = new CombinedPath.LongitudalTrapezoid(0,ticks,-Constants.MAX_TICKS_SPEED,-Constants.MAX_TICKS_ACCEL);
            reversePth = new CombinedPath.LongitudalTrapezoid(0,-ticks,-Constants.MAX_TICKS_SPEED,-Constants.MAX_TICKS_ACCEL);
        }

        Subsystems.instance.mecanumDriveSubsystem.setMotorPathsStrafe(pth, reversePth);
        pathTime = pth.getTotalTime();
        Subsystems.instance.mecanumDriveSubsystem.setMotorPathsStrafe(pth, reversePth);
        lastTime = System.currentTimeMillis();


    }

    @Override
    public void loop() {
        long dMilis = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        Subsystems.instance.mecanumDriveSubsystem.driveWithPID((double)dMilis / 1000.0);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {
        Subsystems.instance.mecanumDriveSubsystem.setMotors(0,0,0,0);
    }
}
