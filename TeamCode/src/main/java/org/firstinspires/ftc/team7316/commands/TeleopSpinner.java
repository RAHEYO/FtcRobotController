package org.firstinspires.ftc.team7316.commands;
import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class TeleopSpinner extends Command {
    @Override
    public void init() { requires(Subsystems.instance.spinnerSub); }

    @Override
    public void loop() {
        if (OI.instance.gp2.x_button.pressedState())
            Subsystems.instance.spinnerSub.backSpin();
        else if (OI.instance.gp2.b_button.pressedState())
            Subsystems.instance.spinnerSub.spin();
        else Subsystems.instance.spinnerSub.reset();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
