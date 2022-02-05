package org.firstinspires.ftc.team7316.opmodes;

//import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.commands.AutoDrive;
import org.firstinspires.ftc.team7316.commands.AutoDunk;
import org.firstinspires.ftc.team7316.commands.AutoDunkRecover;
import org.firstinspires.ftc.team7316.commands.AutoElevator;
import org.firstinspires.ftc.team7316.commands.AutoSlide;
import org.firstinspires.ftc.team7316.commands.AutoSpinner;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class Top extends AutoBaseOpMode {
    ElapsedTime t = new ElapsedTime();
    double startTime;

    int stepIndex;
    int cycleIndex = 0;
    int elementLevel = -1;

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

        // TOP
        // If element in center, go to the dropping line~ Or go scan the next one~
        if (stepIndex == 0) {
            if (distance < 2) {
               elementLevel = 1;
            }
            if (Scheduler.instance.getBuffer().isEmpty()) {
                Scheduler.instance.add(new AutoDrive(-1, 0.3));

                stepIndex += 1;
            }


            // Slide to the ShippingHub
        } else if (stepIndex == 1) {
            if (elementLevel != -1) {
                if (distance < 2)   elementLevel = 0;
                else    elementLevel = 2;
            }

            if (Scheduler.instance.getBuffer().isEmpty()) {
                Scheduler.instance.add(new AutoSlide(1, 1.1));

                stepIndex += 1;
            }

            // Elevate to dunk~
        } else if (stepIndex == 2) {
            if (Scheduler.instance.getBuffer().isEmpty()) {
                Scheduler.instance.add(new AutoElevator(elementLevel));

                stepIndex += 1;
            }

            // Dunk it!
        } else if (stepIndex == 3) {
            if (Scheduler.instance.getBuffer().isEmpty()) {
                Scheduler.instance.add(new AutoDunk());

                stepIndex += 1;
            }

            // For loop iterate cycles
        } else if (stepIndex == 4) {
            cycle(cycleIndex);
            Hardware.log("Finished cycle: ", cycleIndex);
        }

//        // BOTTOM
//        if (stepIndex == 0) {
//            if (distance < 2) {
//                nextMove = new AutoDrive(1, 0.9);
//                elementLevel = 1;
//            }
//            else   nextMove = new AutoDrive(1, 0.3);
//
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove()) ++stepIndex;
//
//            // Go to the duck spinner
//        } else if (stepIndex == 1) {
//            if (distance < 2) elementLevel = 0;
//            else elementLevel = 2;
//
//            nextMove = new AutoDrive(1, 0.9);
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove()) ++stepIndex;
//
//            // Spin the duck
//        } else if (stepIndex == 2) {
//            nextMove = new AutoSpinner();
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove()) ++stepIndex;
//
//        } else if (stepIndex == 3) {
//            nextMove = new AutoSlide(1, 1);
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove()) ++stepIndex;
//
//            // Move back to the Shipping Hub
//        } else if (stepIndex == 4) {
//            nextMove = new AutoDrive(-1, 1);
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove())    ++stepIndex;
//
//            // Dunk it~
//        } else if (stepIndex ==5) {
//            nextMove = new AutoElevator(elementLevel);
//            Scheduler.instance.add(nextMove);
//
//            if (nextMove.shouldRemove())    stepIndex += 1;
//
//            // For loop iterate cycles
//        } else if (stepIndex == 6) {
//            for (int i = 0; i<9; ++i) {
//                nextMove = slideLeft;
//                if (nextMove.shouldRemove()) {
//                    nextMove = back;
//
//                    if (nextMove.shouldRemove()) {
//                        nextMove = forward;
//                        Scheduler.instance.add(nextMove);
//
//                        Hardware.log("Finished cycle: ", i);
//                    }
//                }
//
//
//            }
//        }


    }

    private void cycle(int cycleIndex) {
        if (cycleIndex == 0)    Scheduler.instance.add(slideLeft);
        else if (cycleIndex == 1)   Scheduler.instance.add(forward);
        else if (cycleIndex == 2)   Scheduler.instance.add(back);
    }
}
