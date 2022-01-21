package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.commands.AutoRotate;
import org.firstinspires.ftc.team7316.commands.AutoSpinner;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
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
        if (stepIndex == 0) {
            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(7))); // 23

            ++stepIndex;
        } else if (stepIndex == 1 && t.seconds() - startTime > 3) {
            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(3)));

            ++stepIndex;
        } else if (stepIndex == 2 && t.seconds() - startTime > 5) {
            Scheduler.instance.add(new AutoSpinner());

            ++stepIndex;
        } else if (stepIndex == 3 && t.seconds() - startTime > 11) {
            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-5))); // -15

            ++stepIndex;
        }
    }
}
