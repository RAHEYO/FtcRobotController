package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Listenable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * A wrapper for a button.
 * Make sure to call super.loop() if
 */
public class ButtonWrapper extends Listenable {

    private GamepadButton gamepadInput;
    private GamepadWrapper gpSource;

    protected boolean addedToScheduler = false;

    private boolean toggledValue = false;
    private boolean prevToggledValue = false;
    private boolean buttonHasBeenTouched = false;

    boolean lastValue; // for detecting rising/falling edges

    public ButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        this.gamepadInput = gamepadInput;
        this.gpSource  = gpSource;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }

    @Override
    public boolean state() {
        return gpSource.buttonState(gamepadInput);
    }

    /**
     * Use the methods below if you are desperate enough.
     */
    public boolean pressedState() {
        return state();
    }

    public boolean toggledState() {
        boolean value = state();
        if(value && !prevToggledValue) {
            toggledValue = !toggledValue;
        }
        prevToggledValue = value;
        return toggledValue;
    }

    public boolean singlePressedState() {
        if(state() && !buttonHasBeenTouched) {
            buttonHasBeenTouched = true;
            return true;
        }
        return false;
    }
}
