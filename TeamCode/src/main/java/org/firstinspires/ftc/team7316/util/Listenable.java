package org.firstinspires.ftc.team7316.util;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.util.commands.flow.WhileHeldWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11/29/16.
 */
public abstract class Listenable extends Command {
    private List<Command> onPressed = new ArrayList<>();
    private List<WhileHeldWrapper> whileHeld = new ArrayList<>();
    private boolean lastValue = false;
    private boolean addedToScheduler = false;

    public void onPressed(Command listener) {
        this.addToScheduler();
        this.onPressed.add(listener);
    }
    public void whileHeld(WhileHeldWrapper listener) {
        this.addToScheduler();
        this.whileHeld.add(listener);
    }

    public void subLoop() {

    }

    @Override
    public void loop() {
        boolean currentValue = state();
        subLoop();

        if (currentValue && !lastValue) { // Rising edge
            for (Command listener : onPressed) {
                Scheduler.instance.add(listener);
            }

            for (WhileHeldWrapper listener : whileHeld) {
                Scheduler.instance.add(listener);
            }
        }

        if (!currentValue && lastValue) { //falling edge
            for (WhileHeldWrapper listener: whileHeld) {
                listener.needsEnd = true;
            }
        }

        lastValue = currentValue;
    }

    /**
     * This button adds itself to the scheduler on call.
     * Used so that unused buttons aren't added to the scheduler for efficiency.
     * Call this on any method that retrieves state so it knows when to add itself to scheduler.
     */
    protected void addToScheduler() {
        if(!addedToScheduler) {
            Scheduler.instance.add(this);
            addedToScheduler = true;
        }
    }

    public abstract boolean state();
}
