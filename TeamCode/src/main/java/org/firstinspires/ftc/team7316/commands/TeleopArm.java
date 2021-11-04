package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class TeleopArm extends Command {
    @Override
    public void init() {
        requires(Subsystems.instance.armSub);
    }

    @Override
    public void loop() {
        // Elevator
        if (OI.instance.gp1.left_bumper.pressedState()) {
            Subsystems.instance.armSub.drop();
        } else if (OI.instance.gp1.right_bumper.pressedState()) {
            Subsystems.instance.armSub.lift();
        } else {
            Subsystems.instance.armSub.reset();
        }

        // Turning servo
        final double y = OI.instance.gp1RightStick.getY();

        Subsystems.instance.armSub.turnServo(y);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {}
}