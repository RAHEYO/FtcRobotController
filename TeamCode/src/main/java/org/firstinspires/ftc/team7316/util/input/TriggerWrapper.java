package org.firstinspires.ftc.team7316.util.input;

/**
 * A wrapper for a single joystick axis to turn it into a button.
 */
public class TriggerWrapper extends ButtonWrapper {

    private boolean value = false;
    private AxisWrapper axisWrapper;
    private static final double threshold = (double) 0.8;

    public TriggerWrapper(GamepadAxis inputName, GamepadWrapper gpWrapper) {
        super(null, gpWrapper);
        this.axisWrapper = new AxisWrapper(inputName, gpWrapper);
    }

    public double getCurrentValue() {
        return axisWrapper.value();
    }

    /**
     * If used as a button
     * @return If the trigger is sufficiently pressed
     */
    @Override
    public boolean pressedState() {
        addToScheduler();
        return state();
    }

    @Override
    public boolean state() {
        return getCurrentValue() > threshold;
    }
}
