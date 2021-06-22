package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.commands.flow.WhileHeldWrapper;

public class TeleopWobble extends Command {

    @Override
    public void init() {
        requires(Subsystems.instance.wobble);
        Subsystems.instance.wobble.reset();
    }

    @Override
    public void loop() {
        if(OI.instance.gp2.x_button.pressedState()){
            Subsystems.instance.wobble.wobbleDown();
        }
        else if (OI.instance.gp2.y_button.pressedState()){
            Subsystems.instance.wobble.wobbleUp();
        }
        if(OI.instance.gp2.right_bumper.pressedState()){
            Subsystems.instance.wobble.wobbleGrab();
        }
        else if(OI.instance.gp2.left_bumper.pressedState()){
            Subsystems.instance.wobble.wobbleRelease();
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
