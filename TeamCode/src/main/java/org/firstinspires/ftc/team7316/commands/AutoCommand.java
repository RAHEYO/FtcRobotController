package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class AutoCommand extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.intake);
        requires(Subsystems.instance.armSub);
    }

    @Override
    public void loop() {
        Subsystems.instance.intake.intake();
        Subsystems.instance.armSub.lift();
        Subsystems.instance.armSub.turnServo(1);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
