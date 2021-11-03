package org.firstinspires.ftc.team7316.maps;

import org.firstinspires.ftc.team7316.subsystems.ArmSubsystem;
import org.firstinspires.ftc.team7316.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.team7316.subsystems.DriveSubsystem;
import org.firstinspires.ftc.team7316.subsystems.SpinnerSubsystem;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Every subsystem that needs to be used is placed in here.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public Subsystem[] subsystems;
    public DriveSubsystem driveSubsystem;
    public IntakeSubsystem intake;
//    public ShooterSubsystem shoot;
//    public ContainerSubsystem contain;
//    public WobbleSubsystem wobble;
    public ArmSubsystem armSub;
    public SpinnerSubsystem spinnerSub;


    private Subsystems () {
        driveSubsystem = new DriveSubsystem();
        intake = new IntakeSubsystem();
//        shoot = new ShooterSubsystem();
//        contain = new ContainerSubsystem();
//        wobble = new WobbleSubsystem();
        armSub = new ArmSubsystem();
        spinnerSub = new SpinnerSubsystem();

        subsystems = new Subsystem[] {driveSubsystem, intake, armSub, spinnerSub};
    }

    public static void createSubsystems() {
        instance = new Subsystems();

        Scheduler.instance.addDefaultCommands();

        instance.resetSubsystems();
    }

    public void resetSubsystems() {
        for(Subsystem s : subsystems) {
            s.reset();
        }
    }
}