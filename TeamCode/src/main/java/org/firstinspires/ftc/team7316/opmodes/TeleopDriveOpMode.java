package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.modes.TeleopBaseOpMode;

@TeleOp(name="teleop")
public class TeleopDriveOpMode extends TeleopBaseOpMode {

    @Override
    public void onInit() {

    }

    @Override
    public void onLoop() {
        Hardware.log("shit", 0.0);
    }
}