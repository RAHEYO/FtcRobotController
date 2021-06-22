package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;

/**
 * A Command with a required subsystem
 */

public abstract class Command {

    public TerminatedListener terminatedListener = null;
    public ArrayList<Subsystem> requiredSubsystems = new ArrayList<>();
    public boolean shouldBeReplaced = true;

    public void requires(Subsystem subsystem) {
        if (!requiredSubsystems.contains(subsystem)) { // just to be sure duplicates don't happen
            requiredSubsystems.add(subsystem);
        }
    }

    public abstract void init();
    public abstract void loop();
    public abstract boolean shouldRemove();

    public void _end() {
        if (terminatedListener != null) {
            terminatedListener.onTerminated(this);
        }
        end();
    }
    protected abstract void end();
    public void interrupt() {
        this.end();
    }

    //  static methods

    public static void run(Command c, LinearOpMode opMode) {
        c.init();
        while(!c.shouldRemove()) {
            if(opMode.isStopRequested()) {
                c.interrupt();
                break;
            }
            Scheduler.instance.loop();
            c.loop();
            Hardware.telemetry.update();
        }
        c.end();
    }

    public static void run(Command[] cmds, LinearOpMode opMode) {
        for(Command c : cmds) {
            if(opMode.isStopRequested()) {
                break;
            }
            run(c, opMode);
        }
    }
}
