package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.maps.Subsystems;

public class TeleopShooter extends Command {

    @Override
    public void init() {
        requires(Subsystems.instance.shoot);
        Subsystems.instance.shoot.reset();
    }

    @Override
    public void loop() {
        if(OI.instance.gp2.rightTriggerWrapper.pressedState()) {
            Subsystems.instance.shoot.runShooter();
        }
        else {
            Subsystems.instance.shoot.reset();
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
