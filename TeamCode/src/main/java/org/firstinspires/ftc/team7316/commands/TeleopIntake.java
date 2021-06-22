package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class TeleopIntake extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.intake);
        Subsystems.instance.intake.reset();
    }

    @Override
    public void loop() {
        if(OI.instance.gp1.rightTriggerWrapper.pressedState()){
            Subsystems.instance.intake.intake();
        }
        else if( OI.instance.gp1.leftTriggerWrapper.pressedState()){
            Subsystems.instance.intake.outtake();
        }
        else{
            Subsystems.instance.intake.reset();
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
