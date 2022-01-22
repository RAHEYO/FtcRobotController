package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.commands.AutoArm;
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

            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.33)));

            ++stepIndex;
        } else if (stepIndex == 2 && t.seconds() - startTime > 5) {
            // Spin duck

            Scheduler.instance.add(new AutoSpinner());

            ++stepIndex;
        } else if (stepIndex == 3 && t.seconds() - startTime > 7) {
            // Back

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.77)));

            ++stepIndex;
        } else if (stepIndex == 4 && t.seconds() - startTime > 9) {
            // Rotate right

            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(-1.13)));

            ++stepIndex;
        } else if (stepIndex == 5 && t.seconds() - startTime > 11) {
            // Back

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.47)));

            ++stepIndex;
        } else if (stepIndex == 6 && t.seconds() - startTime > 13) {
            // Dunk

            Scheduler.instance.add(new AutoArm());

            ++stepIndex;
        } else if (stepIndex == 7 && t.seconds() - startTime > 17) {
            // Forward to the blue box

            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(1.47))); // -15

            ++stepIndex;
        }
    }
}
