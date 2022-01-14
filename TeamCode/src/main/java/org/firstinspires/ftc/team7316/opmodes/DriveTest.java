package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.commands.DriveDistance;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class DriveTest extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(new DriveDistance(Constants.inchesToTicks(10)));
    }

    @Override
    public void onLoop() {

    }
}
