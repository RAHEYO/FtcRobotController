package org.firstinspires.ftc.team7316.opmodes;

//import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.commands.AutoElevator;
import org.firstinspires.ftc.team7316.commands.AutoSlide;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class Top extends AutoBaseOpMode {
    ElapsedTime t = new ElapsedTime();
    double startTime;

    int stepIndex;
    int elementLevel = -1;
    Command nextMove;

    public String lDistanceSensorName = "ldistancesensor";
    public String rDistanceSensorName = "rdistancesensor";
    private DistanceSensor lDistanceSensor;
    private DistanceSensor rDistanceSensor;

    AutoDrive forward = new AutoDrive(1, 2.5);
    AutoDrive back = new AutoDrive(-1, 2.5);
    AutoSlide slideLeft = new AutoSlide(-1, 1.1);
    AutoSlide slideRight = new AutoSlide(1, 1.1);


    @Override
    public void onInit() {
        startTime = t.seconds();

        lDistanceSensor = hardwareMap.get(DistanceSensor.class, lDistanceSensorName);
        rDistanceSensor = hardwareMap.get(DistanceSensor.class, rDistanceSensorName);
    }

    @Override
    public void onLoop() {
        double distance = lDistanceSensor.getDistance(DistanceUnit.METER);
        Hardware.log("Distance", distance);

        // If element in center, go to the dropping line~ Or go scan the next one~
        if (stepIndex == 0) {
            if (distance < 2) {
               nextMove = new AutoDrive(1, 0.5);
               elementLevel = 1;
            }
            else   nextMove = new AutoDrive(1, 0.3);

            Scheduler.instance.add(nextMove);

            if (nextMove.shouldRemove()) stepIndex += 1;

            // Slide to the ShippingHub
        } else if (stepIndex == 1) {
            //
            if (distance < 2)   elementLevel = 0;
            else    elementLevel = 2;

            nextMove = new AutoSlide(1, 1.1);
            Scheduler.instance.add(nextMove);

            if (nextMove.shouldRemove())    stepIndex += 1;

            // Dunk it~
        } else if (stepIndex == 2) {
            nextMove = new AutoElevator(elementLevel);
            Scheduler.instance.add(nextMove);

            if (nextMove.shouldRemove())    stepIndex += 1;

            // For loop iterate cycles
        } else if (stepIndex == 3) {
            for (int i = 0; i<9; ++i) {
                nextMove = slideLeft;
                if (nextMove.shouldRemove()) {
                    nextMove = back;

                    if (nextMove.shouldRemove()) {
                        nextMove = forward;
                        Scheduler.instance.add(nextMove);

                        Hardware.log("Finished cycle: ", i);
                    }
                }


            }
        }

//        // In
//        if (stepIndex == 0) {
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(1.35))); // 23
//
//            ++stepIndex;
//        } else if (stepIndex == 1 && t.seconds() - startTime > 3) {
//            // Rotate left
//
//            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.13)));
//
//            ++stepIndex;
//        } else if (stepIndex == 2 && t.seconds() - startTime > 5) {
//            // Spin duck
//
//            Scheduler.instance.add(new AutoSpinner());
//
//            ++stepIndex;
//        } else if (stepIndex == 3 && t.seconds() - startTime > 7) {
//            // Back
//
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.55)));
//
//            ++stepIndex;
//        } else if (stepIndex == 4 && t.seconds() - startTime > 9) {
//            // Rotate right
//
//            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(-0.81)));
//
//            ++stepIndex;
//        } else if (stepIndex == 5 && t.seconds() - startTime > 11) {
//            // Back
//
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(-1.3)));
//
//            ++stepIndex;
//        } else if (stepIndex == 6 && t.seconds() - startTime > 13) {
//            // Elevate
//
//            Scheduler.instance.add(new AutoElevator());
//
//
//            ++stepIndex;
//        } else if (stepIndex == 7 && t.seconds() - startTime > 16) {
//            // Dunk
//
//            Scheduler.instance.add(new AutoDunk());
//
//            ++stepIndex;
//        } else if (stepIndex == 8 && t.seconds() - startTime > 18) {
//            // Dunk recover
//
//            Scheduler.instance.add(new AutoDunkRecover());
//            Scheduler.instance.add(new AutoElevatorRecover());
//
//            ++stepIndex;
//        } else if (stepIndex == 9 && t.seconds() - startTime > 20) {
//            // Forward towards the blue box
//
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(2))); // -15
//
//            ++stepIndex;
//        } else if (stepIndex == 10 && t.seconds() - startTime > 23) {
//            // Rotate left
//
//            Scheduler.instance.add(new AutoRotate(Constants.inchesToTicks(1.39)));
//
//            ++stepIndex;
//        } else if (stepIndex == 11 && t.seconds() - startTime > 26) {
//            // Forward fully park
//
//            Scheduler.instance.add(new AutoDrive(Constants.inchesToTicks(0.5)));
//
//            ++stepIndex;
//        }
    }
}
