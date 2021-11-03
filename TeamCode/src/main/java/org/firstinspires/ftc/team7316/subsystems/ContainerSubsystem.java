//package org.firstinspires.ftc.team7316.subsystems;
//
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.team7316.commands.TeleopContainer;
//import org.firstinspires.ftc.team7316.maps.Hardware;
//import org.firstinspires.ftc.team7316.util.commands.Command;
//import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
//
//public class ContainerSubsystem extends Subsystem {
//    private double containerSetRest = 0;
//    private double containerSetGo = 1;
//    private double containerFlipRest = 0;
//    private double containerFlipGo = 1;
//    private boolean containerSet = false;
//    private boolean containerFlip = false;
//    @Override
//    public void reset() {
//        Hardware.instance.containerServoSet.setPosition(containerSetRest);
//        Hardware.instance.containerServoFlip.setPosition(containerFlipRest);
//    }
//
//    @Override
//    public Command defaultAutoCommand() {
//        return null;
//    }
//
//    @Override
//    public Command defaultTeleopCommand() {
//        return new TeleopContainer();
//    }
//    public void runContainerSet(){
//        if(containerSet) {
//            Hardware.instance.containerServoSet.setPosition(containerSetRest);
//            containerSet = false;
//        }
//        else if (!containerSet){
//            Hardware.instance.containerServoSet.setPosition(containerSetGo);
//            containerSet = true;
//        }
//    }
//    public void runContainerFlip(){
//        if(containerFlip) {
//            Hardware.instance.containerServoFlip.setPosition(containerFlipRest);
//            containerFlip = false;
//        }
//        else if (!containerFlip) {
//            Hardware.instance.containerServoFlip.setPosition(containerFlipGo);
//            containerFlip = true;
//        }
//    }
//}
