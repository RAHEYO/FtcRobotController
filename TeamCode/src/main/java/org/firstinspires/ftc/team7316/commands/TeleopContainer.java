//package org.firstinspires.ftc.team7316.commands;
//
//import org.firstinspires.ftc.team7316.maps.OI;
//import org.firstinspires.ftc.team7316.maps.Subsystems;
//import org.firstinspires.ftc.team7316.util.commands.Command;
//
//public class TeleopContainer extends Command {
//
//    @Override
//    public void init() {
//        requires(Subsystems.instance.contain);
//        Subsystems.instance.contain.reset();
//    }
//
//    @Override
//    public void loop() {
//        if(OI.instance.gp2.a_button.pressedState()){
//            Subsystems.instance.contain.runContainerFlip();
//        }
//        if(OI.instance.gp2.b_button.pressedState()){
//            Subsystems.instance.contain.runContainerSet();
//        }
//    }
//
//    @Override
//    public boolean shouldRemove() {
//        return false;
//    }
//
//    @Override
//    protected void end() {
//
//    }
//}
