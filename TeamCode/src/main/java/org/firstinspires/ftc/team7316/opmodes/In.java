package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.commands.AutoDunk;
import org.firstinspires.ftc.team7316.commands.AutoDunkRecover;
import org.firstinspires.ftc.team7316.commands.AutoElevator;
import org.firstinspires.ftc.team7316.commands.AutoElevatorRecover;
import org.firstinspires.ftc.team7316.commands.AutoRotate;
import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.commands.AutoSpinner;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class In extends AutoBaseOpMode {
    ElapsedTime t = new ElapsedTime();
    double startTime;
    int stepIndex = 0;


    @Override
    public void onInit() {
        startTime = t.seconds();

    }

    @Override
    public void onLoop() {
//        if (stepIndex == 0) {
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(1.35))); // 23
//
//            ++stepIndex;
//        } else if (stepIndex == 1 && t.seconds() - startTime > 3) {
//            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.33)));
//
//            ++stepIndex;
//        } else if (stepIndex == 2 && t.seconds() - startTime > 5) {
//            Scheduler.instance.add(new AutoSpinner());
//
//            ++stepIndex;
//        } else if (stepIndex == 3 && t.seconds() - startTime > 11) {
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.19))); // -15
//
//            ++stepIndex;
//        }

        // In
        if (stepIndex == 0) {
            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(1.35))); // 23

            ++stepIndex;
        } else if (stepIndex == 1 && t.seconds() - startTime > 3) {
            // Rotate left

            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.13)));

            ++stepIndex;
        } else if (stepIndex == 2 && t.seconds() - startTime > 5) {
            // Spin duck

            Scheduler.instance.add(new AutoSpinner());

            ++stepIndex;
        } else if (stepIndex == 3 && t.seconds() - startTime > 7) {
            // Back

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.55)));

            ++stepIndex;
        } else if (stepIndex == 4 && t.seconds() - startTime > 9) {
            // Rotate right

            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(-0.81)));

            ++stepIndex;
        } else if (stepIndex == 5 && t.seconds() - startTime > 11) {
            // Back

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.3)));

            ++stepIndex;
        } else if (stepIndex == 6 && t.seconds() - startTime > 13) {
            // Elevate

            Scheduler.instance.add(new AutoElevator());


            ++stepIndex;
        } else if (stepIndex == 7 && t.seconds() - startTime > 16) {
            // Dunk

            Scheduler.instance.add(new AutoDunk());

            ++stepIndex;
        } else if (stepIndex == 8 && t.seconds() - startTime > 18) {
            // Dunk recover

            Scheduler.instance.add(new AutoDunkRecover());
            Scheduler.instance.add(new AutoElevatorRecover());

            ++stepIndex;
        } else if (stepIndex == 9 && t.seconds() - startTime > 20) {
            // Forward towards the blue box

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(2))); // -15

            ++stepIndex;
        } else if (stepIndex == 10 && t.seconds() - startTime > 23) {
            // Rotate left

            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.39)));

            ++stepIndex;
        } else if (stepIndex == 11 && t.seconds() - startTime > 26) {
            // Forward fully park

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(0.5)));

            ++stepIndex;
        }
    }
}
