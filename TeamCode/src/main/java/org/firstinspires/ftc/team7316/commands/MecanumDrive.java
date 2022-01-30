package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.maps.Hardware;
import org.firstinspires.ftc.team7316.util.Util;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class MecanumDrive extends Command {

    @Override
    public void init() {
        requires(Subsystems.instance.driveSubsystem);
    }

    @Override
    public void loop() {
        double y = OI.instance.gp1LeftStick.getX();
        double x = OI.instance.gp1LeftStick.getY();
        double turn = OI.instance.gp1RightStick.getX();

        Hardware.log("y:", y);
        Hardware.log("x:", x);
        Hardware.log("turn:", turn);

        double magnitude = Math.sqrt(y*y + x*x);
        double angle = Util.getAngleFromPoint(x,y);

        Hardware.log("magnitude:", magnitude);
        Hardware.log("angle:", angle);

        //rotates the axis of the joystick by pi/4 radians so that x and y are proportional to the powers to be given
        //to the mecanum axis. This arises from mecanum wheels exerting force at a diagonal to the direction they are pointing.
        double potentialRotatedAngle = angle - Math.PI/4;
        double rotatedAngle = (potentialRotatedAngle >= 0) ? potentialRotatedAngle : potentialRotatedAngle + 2 * Math.PI;
        Hardware.log("rotated angle:", rotatedAngle);

        double rotatedX = Math.cos(rotatedAngle);
        double rotatedY = Math.sin(rotatedAngle);

        double tempRotatedX = rotatedX;
        double tempRotatedY = rotatedY;

        if(rotatedX > rotatedY) {
            rotatedY = magnitude * Math.abs(rotatedY/rotatedX);
            rotatedX = magnitude;
        }

        else {
            rotatedX = magnitude * Math.abs(rotatedX/rotatedY);
            rotatedY = magnitude;
        }

        rotatedX = Math.copySign(rotatedX, tempRotatedX);
        rotatedY = Math.copySign(rotatedY, tempRotatedY);

        Hardware.log("rotated x:", rotatedX);
        Hardware.log("rotated y:", rotatedY);

        if(OI.instance.gp1.a_button.pressedState()){
            Subsystems.instance.driveSubsystem.setMotors(.7*-(rotatedY + turn),
                    .7*(rotatedX - turn),
                    .7*(rotatedX + turn), .7*(rotatedY - turn));
        }
        else if(magnitude==0){
            Subsystems.instance.driveSubsystem.setMotors(turn, -turn, turn,-turn);
        }
        else{
            Subsystems.instance.driveSubsystem.setMotors(-(rotatedY + turn), rotatedX - turn,
                    (rotatedX + turn), rotatedY - turn);
        }

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void end() {}
}