package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.commands.TerminatedListener;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Simultaneous command where it terminates after keyCommand finishes
 * Created by andrew on 2/1/18.
 */

public class SimultaneousKeyCommand extends Command implements TerminatedListener {

    private ArrayList<Command> cmds = new ArrayList<>();
    private Command keyCommand;
    private boolean done;

    public SimultaneousKeyCommand(Command keyCommand, Command... cmds) {
        Collections.addAll(this.cmds, cmds);
        this.cmds.add(keyCommand);
        this.keyCommand = keyCommand;

        this.shouldBeReplaced = false;
        for (Command cmd : this.cmds) {
            cmd.terminatedListener = this;
            for (Subsystem subsystem : cmd.requiredSubsystems) {
                this.requires(subsystem);
            }
        }
    }

    @Override
    public void init() {
        done = false;
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = false;
        }

        for (Command cmd : this.cmds) {
            cmd.terminatedListener = this;
            cmd.shouldBeReplaced = false;
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public void loop() {
    }

    @Override
    public boolean shouldRemove() {
        return done;
    }

    @Override
    public void end() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }
        for (Command cmd : this.cmds) {
            cmd.shouldBeReplaced = true;
        }
    }

    @Override
    public void onTerminated(Command terminated) {
        if (terminated == this.keyCommand) {
            this.done = true;
        }
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
