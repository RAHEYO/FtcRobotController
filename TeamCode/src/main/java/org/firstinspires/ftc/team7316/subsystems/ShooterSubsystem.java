//package org.firstinspires.ftc.team7316.subsystems;
//
//import org.firstinspires.ftc.team7316.commands.TeleopShooter;
//import org.firstinspires.ftc.team7316.maps.Hardware;
//import org.firstinspires.ftc.team7316.util.commands.Command;
//import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
//
//public class ShooterSubsystem extends Subsystem {
//
//    private double shooterPower = .8;
//    @Override
//    public void reset() {
//        Hardware.instance.shooterMotor.setPower(0);
//    }
//
//    @Override
//    public Command defaultAutoCommand() {
//        return null;
//    }
//
//    @Override
//    public Command defaultTeleopCommand() {
//        return new TeleopShooter();
//    }
//    public void runShooter() {
//        Hardware.instance.shooterMotor.setPower(shooterPower);
//    }
//}
