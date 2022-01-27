package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="PID Test")

public class Top extends AutoBaseOpMode {
    ElapsedTime t = new ElapsedTime();
    double startTime;
    int stepIndex = 0;

    public String distanceSensorName = "distanceSensor";
    private DistanceSensor distanceSensor;


    @Override
    public void onInit() {
        startTime = t.seconds();

        distanceSensor = hardwareMap.get(DistanceSensor.class, distanceSensorName);
    }

    @Override
    public void onLoop() {
        double distance = distanceSensor.getDistance(DistanceUnit.METER);

        telemetry.addData("Distance: ", distance);
        telemetry.update();

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
