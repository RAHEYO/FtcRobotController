
package org.firstinspires.ftc.team7316.subsystems;

import org.firstinspires.ftc.team7316.commands.TeleopArm;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class ArmSubsystem extends Subsystem {
    private final double upPower = 0.77;
    private final double downPower = -0.77;

    @Override
    public void reset() {
        Hardware.instance.armMotor.setPower(0);
        Hardware.instance.armServo.setPosition(0);
    }

    public void lift(){ Hardware.instance.armMotor.setPower(upPower); }
    public void drop(){ Hardware.instance.armMotor.setPower(downPower); }

    public void turnServo(double position) { Hardware.instance.armServo.setPosition(position); }

    public void cap(boolean isLift) {
        double currentPosition = Hardware.instance.armServo.getPosition();
        double nextPosition;

        if (isLift) nextPosition = currentPosition + 0.1;
        else    nextPosition = currentPosition - 0.1;

        Hardware.instance.armServo.setPosition(nextPosition);
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopArm();
    }
}
