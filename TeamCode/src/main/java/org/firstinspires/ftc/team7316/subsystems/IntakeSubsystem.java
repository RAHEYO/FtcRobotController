package org.firstinspires.ftc.team7316.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.commands.TeleopIntake;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private final double intakeSpeed = 1;
    private final double outtakeSpeed = -1;

    @Override
    public void reset() {
        Hardware.instance.intakeMotor.setPower(0);
    }
    public void intake(){ Hardware.instance.intakeMotor.setPower(intakeSpeed); }
    public void outtake(){
        Hardware.instance.intakeMotor.setPower(outtakeSpeed);
    }
    public void turnServo(Servo.Direction direction) { Hardware.instance.intakeServo.setDirection(direction); }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopIntake();
    }
}
