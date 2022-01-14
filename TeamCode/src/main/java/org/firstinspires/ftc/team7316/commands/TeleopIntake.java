package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class TeleopIntake extends Command {
    private boolean isIn = true;

    @Override
    public void init() {
        requires(Subsystems.instance.intake);
        Subsystems.instance.intake.reset();
    }

    @Override
    public void loop() {
        if(OI.instance.gp1.y_button.pressedState()) {
            isIn = !isIn;
        }
        else {
            Subsystems.instance.intake.reset();
        }


        if (isIn) { Subsystems.instance.intake.intake(); }
        else { Subsystems.instance.intake.outtake(); }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
