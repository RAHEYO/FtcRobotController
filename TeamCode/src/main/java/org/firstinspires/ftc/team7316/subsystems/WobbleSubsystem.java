package org.firstinspires.ftc.team7316.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.commands.TeleopWobble;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

public class WobbleSubsystem extends Subsystem {
    private double wobbleDownPower = -.4;
    private double wobbleUpPower = .8;
    private double wobbleGrab = 1;
    private double wobbleRest = 0;
    @Override
    public void reset() {
        Hardware.instance.wobbleMotor.setPower(0);
        wobbleGrab();
    }
    public void wobbleDown(){
        Hardware.instance.wobbleMotor.setPower(wobbleDownPower);
    }
    public void wobbleUp(){
        Hardware.instance.wobbleMotor.setPower(wobbleUpPower);
    }
    public void wobbleGrab(){
        Hardware.instance.wobbleServo.setPosition(wobbleGrab);
    }
    public void wobbleRelease(){
        Hardware.instance.wobbleServo.setPosition(wobbleRest);
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopWobble();
    }
}
