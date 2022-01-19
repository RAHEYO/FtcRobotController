package org.firstinspires.ftc.team7316.util.commands;


import org.firstinspires.ftc.team7316.commands.AutoRotate;
import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;

/**
 * Contains all the sequential commands to run
 */
public class AutoCodes {

    /*
    public static SequentialCommand exampleAutoSequence() {
        SomeCommand cmd1 = new SomeCommand();
        SomeCommand cmd2 = new SomeCommand();
        Command[] cmds = {cmd1, cmd2};
        return new SequentialCommand(cmds);
    }
    */

    public static SequentialCommand KILLYOURSELF() {
        AutoRotate cmd1 = new AutoRotate(Constants.inchesToTicks(10));
//        StrafeDistance cmd2 = new StrafeDistance(Constants.inchesToTicks(-10));
        AutoRotate cmd3 = new AutoRotate(Constants.inchesToTicks(10));
//        StrafeDistance cmd4 = new StrafeDistance(Constants.inchesToTicks(10));
        Command[] cmds = {cmd1, cmd3};
        return new SequentialCommand(cmds);
    }

//    public static SequentialCommand KILLYOURSELF() {
//        DriveDistance cmd1 = new DriveDistance(Constants.inchesToTicks(10));
//        StrafeDistance cmd2 = new StrafeDistance(Constants.inchesToTicks(-10));
//        DriveDistance cmd3 = new DriveDistance(Constants.inchesToTicks(10));
//        StrafeDistance cmd4 = new StrafeDistance(Constants.inchesToTicks(10));
//        Command[] cmds = {cmd1, cmd2, cmd3, cmd4};
//        return new SequentialCommand(cmds);
//    }

}
