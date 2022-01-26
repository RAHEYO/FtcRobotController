package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class TireDrive extends Command {

    @Override
    public void init() {
        requires(Subsystems.instance.driveSubsystem);
    }

    @Override
    public void loop() {
//         Flipped because of the controller issue
        double x = -OI.instance.gp1RightStick.getX();
        double y = -OI.instance.gp1LeftStick.getY();

        double MOTOR_POWER = 0.7;

        if(OI.instance.gp1.rightTriggerWrapper.pressedState())
            MOTOR_POWER *= 0.5;

        // Forward
        if (y >= 0.5)
            //Subsystems.instance.mecanumDriveSubsystem.setMotors(MOTOR_POWER, -MOTOR_POWER, -MOTOR_POWER, MOTOR_POWER);
            Subsystems.instance.driveSubsystem.setMotors(MOTOR_POWER, MOTOR_POWER, -MOTOR_POWER,
                    -MOTOR_POWER);
            // Backwards
        else if (y <= -0.5)
            //Subsystems.instance.mecanumDriveSubsystem.setMotors(-MOTOR_POWER, MOTOR_POWER, MOTOR_POWER, -MOTOR_POWER);
            Subsystems.instance.driveSubsystem.setMotors(-MOTOR_POWER, -MOTOR_POWER, MOTOR_POWER,
                    MOTOR_POWER);
            // Right
        else if (x >= 0.5)
            //Subsystems.instance.mecanumDriveSubsystem.setMotors(MOTOR_POWER, -MOTOR_POWER, MOTOR_POWER, -MOTOR_POWER);
            Subsystems.instance.driveSubsystem.setMotors(MOTOR_POWER, -MOTOR_POWER, -MOTOR_POWER,
                    MOTOR_POWER);
            //
        else if (x <= -0.5)
            //Subsystems.instance.mecanumDriveSubsystem.setMotors(-MOTOR_POWER, MOTOR_POWER, -MOTOR_POWER, MOTOR_POWER);
            Subsystems.instance.driveSubsystem.setMotors(-MOTOR_POWER, MOTOR_POWER, MOTOR_POWER,
                    -MOTOR_POWER);
        else
            Subsystems.instance.driveSubsystem.setMotors(0, 0, 0, 0);

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {}
}
