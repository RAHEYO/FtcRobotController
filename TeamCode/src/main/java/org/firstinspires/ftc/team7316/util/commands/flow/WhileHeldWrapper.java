package org.firstinspires.ftc.team7316.util.commands.flow;

import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 10/21/17.
 */

public abstract class WhileHeldWrapper extends Command {

    public boolean needsEnd = false;

    protected abstract void onInit();
    @Override
    public void init() {
        needsEnd = false;
        onInit();
    }

    protected abstract boolean wantRemove();
    @Override
    public boolean shouldRemove() {
        if (needsEnd) {
            return true;
        } else {
            return wantRemove();
        }
    }

}
