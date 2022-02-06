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
import org.firstinspires.ftc.team7316.commands.AutoSpinner;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

import java.util.ArrayList;

@Autonomous(name="PID Test")

public class Top extends AutoBaseOpMode {
    final boolean isTop = false;
    final boolean isRed = true;

    ElapsedTime t = new ElapsedTime();
    double startTime;

    int stepIndex;
    int elementLevel = 2;

    ArrayList<Command> commandList = new ArrayList<Command>();
    ArrayList<Double> timeList = new ArrayList<Double>();

    public String lDistanceSensorName = "ldistancesensor";
    public String rDistanceSensorName = "rdistancesensor";
    private DistanceSensor lDistanceSensor;
    private DistanceSensor rDistanceSensor;

    AutoDrive forward = new AutoDrive(1, 1.1);
    AutoDrive back = new AutoDrive(-1, 1.1);
    AutoSlide slideLeft = new AutoSlide(-0.9, 1.17);
    AutoSlide slideRight = new AutoSlide(0.9, 1.17);
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

        if (isTop) {
            // TOP SETUP
            // Slide left near the element for detection!~
            commandList.add(new AutoSlide(isRed? -1 : 1, 1));
            timeList.add(0.0);

            // Go to the dropping line~ (Logically, the sensor should still detect the game
            // element if it's at 1, even though it was from the dropping line)~
            commandList.add(new AutoDrive(-1, 0.5));
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
//            for (int i = 0; i < 5; ++i) {
//                // Right crash into the wall!!!
//                commandList.add(slideRight);
//                timeList.add(timeList.get(timeList.size()-1)+2);
//
//                // Going backward to the Warehouse
//                commandList.add(forward);
//                timeList.add(timeList.get(timeList.size()-1)+2);
//
//                commandList.add(back);
//                timeList.add(timeList.get(timeList.size()-1)+2);
//
//                commandList.add(slideLeft);
//                timeList.add(timeList.get(timeList.size()-1)+2);
//
//                commandList.add(elevate);
//                timeList.add(timeList.get(timeList.size()-1)+2);
//
//                commandList.add(dunk);
//                timeList.add(timeList.get(timeList.size()-1)+1.7);
//
//                commandList.add(elevateRecover);
//                timeList.add(timeList.get(timeList.size()-1)+1.7);
//            }
            commandList.add(isRed? slideRight : slideLeft);
            timeList.add(timeList.get(timeList.size()-1)+1.1);

            commandList.add(forward);
            timeList.add(timeList.get(timeList.size()-1)+1.1);

            commandList.add(new AutoDrive(-0.5, 0.7));
            timeList.add(timeList.get(timeList.size()-1)+2);


        } else {
            // BOTTOM SETUP~ #_#

            if (isRed) {
                // Slide left to line up with the Shipping Hub
                commandList.add(new AutoSlide(-1, 0.9));
                timeList.add(0.0);

                // Drive back towards the dunking position
                commandList.add(new AutoDrive(-1, 1));
                timeList.add(timeList.get(timeList.size()-1)+1);

                // Elevate to dunk
                commandList.add(elevate);
                timeList.add(timeList.get(timeList.size()-1)+1);

                // Dunk that SHIT!!!!!
                commandList.add(dunk);
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                // Everyone needs to recover from dunking, right?
                commandList.add(elevateRecover);
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                // Slide to line up with the Carousel
                commandList.add(new AutoSlide(1, 1.5));
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                // Drive forward to place the spinner onto the carousel!~
                commandList.add(new AutoDrive(1, 0.6));
                timeList.add(timeList.get(timeList.size()-1)+2);

                // Spin!!! #_#
                commandList.add(new AutoSpinner(isRed));
                timeList.add(timeList.get(timeList.size()-1)+1);

                // Drive back to park!~ $_$
                commandList.add(new AutoDrive(-1, 0.6));
                timeList.add(timeList.get(timeList.size()-1)+5);
            } else {
                commandList.add(new AutoDrive(1, 0.6));
                timeList.add(0.0);

                commandList.add(new AutoSpinner(isRed));
                timeList.add(timeList.get(timeList.size()-1)+1);

                commandList.add(new AutoSlide(-1, 1.3));
                timeList.add(timeList.get(timeList.size()-1)+5);

                commandList.add(new AutoDrive(-1, 1));
                timeList.add(timeList.get(timeList.size()-1)+2);

                commandList.add(elevate);
                timeList.add(timeList.get(timeList.size()-1)+1);

                commandList.add(dunk);
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                commandList.add(elevateRecover);
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                commandList.add(new AutoDrive(1, 1.1));
                timeList.add(timeList.get(timeList.size()-1)+1.7);

                commandList.add(new AutoSlide(1, 0.6));
                timeList.add(timeList.get(timeList.size()-1)+2);
            }

        }

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
            Scheduler.instance.add(commandList.get(stepIndex));

            ++stepIndex;
        }
    }

}
