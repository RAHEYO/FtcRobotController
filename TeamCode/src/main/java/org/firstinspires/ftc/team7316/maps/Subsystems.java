package org.firstinspires.ftc.team7316.maps;

import org.firstinspires.ftc.team7316.subsystems.ContainerSubsystem;
import org.firstinspires.ftc.team7316.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.team7316.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.team7316.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.team7316.subsystems.WobbleSubsystem;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;

/**
 * Every subsystem that needs to be used is placed in here.
 */

public class Subsystems {

    public static Subsystems instance = null;

    public Subsystem[] subsystems;
    public MecanumDriveSubsystem mecanumDriveSubsystem;
    public IntakeSubsystem intake;
    public ShooterSubsystem shoot;
    public ContainerSubsystem contain;
    public WobbleSubsystem wobble;


    private Subsystems () {
        mecanumDriveSubsystem = new MecanumDriveSubsystem();
        intake = new IntakeSubsystem();
        shoot = new ShooterSubsystem();
        contain = new ContainerSubsystem();
        wobble = new WobbleSubsystem();

        subsystems = new Subsystem[] {mecanumDriveSubsystem, intake, shoot, contain, wobble};
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