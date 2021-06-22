package org.firstinspires.ftc.team7316.util.modes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.maps.Subsystems;

/**
 * Opmode that does all the stuff needed for auto only
 */

public abstract class AutoBaseOpMode extends OpMode {

    /**
     * The timeout allowed is only this long since Vuforia took a long time to initialize.
     * Remove the constructor body if you want.
     */
    public AutoBaseOpMode() {
        msStuckDetectInit = 10000;
    }

    @Override
    public void init() {
        Log.i(Hardware.tag, "=========================== STARTING AUTO ================================");
        Scheduler.inTeleop = false;
        Scheduler.instance.wipe();
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        Subsystems.createSubsystems();
        onInit();
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        onLoop();
    }

    @Override
    public void stop() {
        Subsystems.instance.resetSubsystems();
    }

    /**
     * Runs after Hardware is initialized
     */
    public abstract void onInit();

    /**
     * Runs every loop after Scheduler loops
     */
    public abstract void onLoop();
}
