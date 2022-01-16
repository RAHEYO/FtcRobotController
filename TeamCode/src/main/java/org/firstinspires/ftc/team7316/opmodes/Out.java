package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class Out extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-15)));
    }

    @Override
    public void onLoop() {

    }
}
