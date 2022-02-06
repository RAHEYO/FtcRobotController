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
import org.firstinspires.ftc.team7316.commands.AutoElevatorRecover;
import org.firstinspires.ftc.team7316.commands.AutoSlide;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

import java.util.ArrayList;

@Autonomous(name="PID Test")

public class Top extends AutoBaseOpMode {
    ElapsedTime t = new ElapsedTime();
    double startTime;

    int stepIndex;
    int elementLevel = -1;

    ArrayList<Command> commandList = new ArrayList<Command>();
    ArrayList<Double> timeList = new ArrayList<Double>();

    public String lDistanceSensorName = "ldistancesensor";
    public String rDistanceSensorName = "rdistancesensor";
    private DistanceSensor lDistanceSensor;
    private DistanceSensor rDistanceSensor;

    AutoDrive forward = new AutoDrive(1, 1.3);
    AutoDrive farForward = new AutoDrive(1, 2.7);
    AutoDrive back = new AutoDrive(-1, 1.3);
    AutoDrive farBack = new AutoDrive(-1, 2.7);
    AutoSlide slideLeft = new AutoSlide(-1, 1.1);
    AutoSlide slideRight = new AutoSlide(1, 1.1);
    AutoElevator elevate = new AutoElevator(elementLevel);
    AutoElevatorRecover elevateRecover = new AutoElevatorRecover();
    AutoDunk dunk = new AutoDunk();


    @Override
    public void onInit() {
        // Clear Auto before every Auto
        Scheduler.instance.clear();

        // Gets teh time when the Auto is run!
        startTime = t.seconds();

        lDistanceSensor = hardwareMap.get(DistanceSensor.class, lDistanceSensorName);
        rDistanceSensor = hardwareMap.get(DistanceSensor.class, rDistanceSensorName);

        // TOP SETUP
        // Slide left near the element for detection!~
        commandList.add(new AutoSlide(-1, 0.6));
        timeList.add(0.0);

        // Go to the dropping line~ (Logically, the sensor should still detect the game
        // element if it's at 1, even though it was from the dropping line)~
        commandList.add(new AutoDrive(-1, 0.4));
        timeList.add(timeList.get(timeList.size()-1)+1);

        // Slide to the ShippingHub
        commandList.add(new AutoSlide(-1, 0.4));
        timeList.add(timeList.get(timeList.size()-1)+1);

        // Elevate to dunk~
        commandList.add(elevate);
        timeList.add(timeList.get(timeList.size()-1)+1);

        // Dunk!!!!! #_#
        commandList.add(dunk);
        timeList.add(timeList.get(timeList.size()-1)+1.7);

        // Elevator down!
        commandList.add(elevateRecover);
        timeList.add(timeList.get(timeList.size()-1)+1.7);

        // Cycle
        for (int i = 0; i < 7; ++i) {
            // Right crash into the wall!!!
            commandList.add(slideRight);
            timeList.add(timeList.get(timeList.size()-1)+2);

            // Going backward to the Warehouse
            commandList.add(forward);
            timeList.add(timeList.get(timeList.size()-1)+2);

            commandList.add(back);
            timeList.add(timeList.get(timeList.size()-1)+2);

            commandList.add(slideLeft);
            timeList.add(timeList.get(timeList.size()-1)+2);

            commandList.add(elevate);
            timeList.add(timeList.get(timeList.size()-1)+2);

            commandList.add(dunk);
            timeList.add(timeList.get(timeList.size()-1)+1.7);

            commandList.add(elevateRecover);
            timeList.add(timeList.get(timeList.size()-1)+1.7);

//            if (cycleIndex == 0)    Scheduler.instance.add(isTop? slideRight : slideLeft);
//            else if (cycleIndex == 1)   Scheduler.instance.add(isTop? forward : farBack);
//            else if (cycleIndex == 2)   Scheduler.instance.add(isTop? back : farForward);
        }
        commandList.add(slideRight);
        timeList.add(timeList.get(timeList.size()-1)+1.1);

        commandList.add(forward);
        timeList.add(timeList.get(timeList.size()-1)+1.1);

    }

    @Override
    public void onLoop() {
        Subsystems.instance.intake.intake();

        double lDistance = lDistanceSensor.getDistance(DistanceUnit.CM);
        Hardware.log("Distance", lDistance);

        Hardware.log("ElementLevel: ", elementLevel);

        // TOP
        if (stepIndex >= commandList.size()) {
            Hardware.log("Finished Auto IsTop: ", true);
            return;
        }
        if (t.seconds() - startTime > timeList.get(stepIndex))
        {
            if (stepIndex == 1 && lDistance < 5) elementLevel = 1;
            else if (stepIndex == 2 && elementLevel == -1) {
                 if (lDistance < 5)   elementLevel = 0;
                 else   elementLevel = 2;
            }
            else if (stepIndex == 3) {
                Scheduler.instance.add(new AutoElevator(elementLevel));
                ++stepIndex;

                return;
            }

            Scheduler.instance.add(commandList.get(stepIndex));

            ++stepIndex;
        }


//            // Elevate to dunk~
//        } else if (stepIndex == 3) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoElevator(elementLevel));
//
//                ++stepIndex;
//            }
//
//            // Dunk it!
//        } else if (stepIndex == 4) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoDunk());
//
//                ++stepIndex;
//            }
//
//            // For loop iterate cycles
//        } else if (stepIndex == 5) {
//            if (cycleIndex == 2)    ++stepIndex;
//
//            nextCycleMotion(cycleIndex, true);
//            Hardware.log("Finished cycle: ", cycleIndex);
//
//        } else {
//            Hardware.log("Finished Auto IsTop: ", true);
//        }


//        double rDistance = rDistanceSensor.getDistance(DistanceUnit.CM);
//        Hardware.log("Distance", rDistance);
//
//        // BOTTOM
//        // Sliding right to the element!~
//        if (stepIndex == 0) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoSlide(1, 0.7));
//
//                if (rDistance < 5)   elementLevel = 1;
//
//                ++stepIndex;
//            }
//
//            // If element in center, ignore this~ Or go scan the next one (0 position)~
//        } else if (stepIndex == 1) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                if (elementLevel == -1) {
//                    Scheduler.instance.add(new AutoDrive(1, 0.5));
//                    if (rDistance < 5)   elementLevel = 0;
//                    else  elementLevel = 2;
//                }
//
//                ++stepIndex;
//            }
//
//            // Slide to the left
//        } else if (stepIndex == 2) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoSlide(-1, 0.7));
//
//                ++stepIndex;
//            }
//
//            // Forward towards the Duck Spinner~
//        } else if (stepIndex == 3) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(elementLevel == 1?
//                        new AutoDrive(1, 0.7)
//                        : new AutoDrive(1, 0.3)
//                );
//
//                ++stepIndex;
//            }
//
//            // Spin the Ducks
//        } else if (stepIndex == 4) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoSpinner());
//
//                ++stepIndex;
//            }
//
//        // Slide right to line up with the Shipping Hub preparing for dunking!~
//        } else if (stepIndex == 5) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoSlide(1, 1));
//
//                ++stepIndex;
//            }
//
//        } else if (stepIndex == 6) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoDrive(-1, 1.3));
//
//                ++stepIndex;
//            }
//
//            // Elevate to dunk~
//        } else if (stepIndex == 7) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoElevator(elementLevel));
//
//                ++stepIndex;
//            }
//
//            // Dunk it!
//        } else if (stepIndex == 8) {
//            if (Scheduler.instance.getBuffer().isEmpty()) {
//                Scheduler.instance.add(new AutoDunk());
//
//                ++stepIndex;
//            }
//
//            // For loop iterate cycles
//        } else if (stepIndex == 9) {
//            if (cycleIndex == 2)    ++stepIndex;
//
//            nextCycleMotion(cycleIndex, false);
//            Hardware.log("Finished cycle: ", cycleIndex);
//
//            ++cycleIndex;
//
//        } else {
//            Hardware.log("Auto Finished IsTop: ", false);
//        }

    }

    private void nextCycleMotion(int cycleIndex, boolean isTop) {
        if (cycleIndex == 0)    Scheduler.instance.add(isTop? slideRight : slideLeft);
        else if (cycleIndex == 1)   Scheduler.instance.add(isTop? farBack : forward);
        else if (cycleIndex == 2)   Scheduler.instance.add(isTop? farForward : back);
    }
}
