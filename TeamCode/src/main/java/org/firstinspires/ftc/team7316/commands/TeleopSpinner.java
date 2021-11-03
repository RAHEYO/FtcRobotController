package org.firstinspires.ftc.team7316.commands;
import org.firstinspires.ftc.team7316.maps.OI;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class TeleopSpinner extends Command {
    private final double spinSpeed = 0.7;
    private final double backSpinSpeed = -0.7;

    @Override
    public void init() { requires(Subsystems.instance.spinnerSub); }

    @Override
    public void loop() {
        if (OI.instance.gp1.x_button.pressedState())
            Subsystems.instance.spinnerSub.backSpin();
        else if (OI.instance.gp1.b_button.pressedState())
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
