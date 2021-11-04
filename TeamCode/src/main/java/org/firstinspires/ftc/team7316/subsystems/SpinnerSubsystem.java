package org.firstinspires.ftc.team7316.subsystems;
import org.firstinspires.ftc.team7316.commands.TeleopArm;
import org.firstinspires.ftc.team7316.commands.TeleopSpinner;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class SpinnerSubsystem extends Subsystem {
    private final double spinSpeed = 0.5;
    private final double backSpinSpeed = -0.5;

    @Override
    public void reset() { this.setPower(0); }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopSpinner();
    }

    private void setPower(double newSpeed) {
        Hardware.instance.spinnerMotor.setPower(newSpeed);
    }

    public void spin() {
        this.setPower(spinSpeed);
    }

    public void backSpin() {
        this.setPower(backSpinSpeed);
    }
}