package org.firstinspires.ftc.team7316.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.maps.Constants;
import org.firstinspires.ftc.team7316.maps.Subsystems;
import org.firstinspires.ftc.team7316.util.commands.AutoDecision;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;

public class AutoArm extends Command {
    ElapsedTime t = new ElapsedTime();
    long lastTime;

//    public AutoArm(boolean shouldReact){
//
//    }


    @Override
    public void init() {
        Subsystems.instance.armSub.reset();

        lastTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
            Subsystems.instance.armSub.lift();
        } else Subsystems.instance.armSub.autoTurnServo();
    }

    @Override
    public boolean shouldRemove() {
        return t.seconds() - lastTime > 5;
    }

    @Override
    public void end() { Subsystems.instance.armSub.reset(); }

}

