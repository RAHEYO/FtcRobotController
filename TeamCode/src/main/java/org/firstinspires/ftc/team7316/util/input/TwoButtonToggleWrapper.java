package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.commands.*;

/**
 * Created by andrew on 11/10/16.
 */


public class TwoButtonToggleWrapper extends Command {

    public enum TwoButtonToggleState {
        NEUTRAL,
        FORWARD,
        BACKWARD
    }

    private ButtonWrapper button1, button2;
    public boolean button1Value, button2Value;
    private boolean button1Last, button2Last;

    public TwoButtonToggleWrapper(ButtonWrapper forwardButton, ButtonWrapper backwardButton) {
        this.button1 = forwardButton;
        this.button2 = backwardButton;

        button1Value = false;
        button2Value = false;

        button1Last = false;
        button2Last = false;
    }

    public TwoButtonToggleState buttonsState() {
        if (!(button1Value || button2Value)) {
            return TwoButtonToggleState.NEUTRAL;
        } else if (button1Value) {
            return TwoButtonToggleState.FORWARD;
        } else {
            return TwoButtonToggleState.BACKWARD;
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void loop() {
        boolean currentButton1 = button1.state();
        boolean currentButton2 = button2.state();

        if (!this.button1Last && currentButton1)
        {
            if (this.button2Value == true) {
                this.button1Value = false;
            } else {
                this.button1Value = !this.button1Value;
            }
            this.button2Value = false;
        }
        else if (!this.button2Last && currentButton2)
        {
            if (this.button1Value == true) {
                this.button2Value = false;
            } else {
                this.button2Value = !this.button2Value;
            }
            this.button1Value = false;
        }

        this.button1Last = currentButton1;
        this.button2Last = currentButton2;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {

    }
}
