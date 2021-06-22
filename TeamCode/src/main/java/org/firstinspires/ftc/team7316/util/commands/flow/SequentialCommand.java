package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Created by andrew on 10/21/17.
 */

public class SequentialCommand extends Command implements TerminatedListener {

    private Command[] cmds;
    private int index = 0;
    private boolean done = false;
    private String cmdName = "";

    public SequentialCommand(Command... commands) {
        this.cmds = commands;

        this.shouldBeReplaced = false;
        for (Command cmd : commands) {
            cmd.terminatedListener = this;
            for (Subsystem sub : cmd.requiredSubsystems) {
                this.requires(sub);
            }
        }
    }

    @Override
    public void init() {
        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = false;
        }
        for (Command cmd : this.cmds) {
            cmd.terminatedListener = this;
        }
        index = 0;

        if (index < cmds.length) {
            Command cmd = cmds[index];
            cmdName = cmd.getClass().toString();
            cmd.shouldBeReplaced = false;
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public void loop() {
        Hardware.log("command num", this.index);
    }

    @Override
    public boolean shouldRemove() {
        return index >= cmds.length;
    }

    @Override
    public void end() {

        this.done = true;

        for (Subsystem subsystem : this.requiredSubsystems) {
            subsystem.needsDefault = true;
        }
        for (Command cmd : this.cmds) {
            cmd.shouldBeReplaced = true;
        }
    }

    @Override
    public void onTerminated(Command terminated) {

        index++;
        if (index < cmds.length) {
            Command cmd = cmds[index];
            cmdName = cmd.getClass().toString();
            cmd.shouldBeReplaced = false;
            Scheduler.instance.add(cmd);
        }
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
