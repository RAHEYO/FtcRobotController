package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.commands.AutoRotate;
import org.firstinspires.ftc.team7316.commands.AutoSpinner;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class In extends AutoBaseOpMode {

    @Override
    public void onInit() {
        Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(7))); // 23
        Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(3)));
        Scheduler.instance.add(new AutoSpinner());
        Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-5))); // -15
    }

    @Override
    public void onLoop() {

    }
}
