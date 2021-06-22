package org.firstinspires.ftc.team7316.util.commands.logs;

import org.firstinspires.ftc.team7316.util.commands.*;
import org.firstinspires.ftc.team7316.maps.Hardware;

/**
 * Created by Maxim on 2/16/2017.
 */

public class LogTelemetryCommand extends Command {

    private Object data;
    private String tag;

    public LogTelemetryCommand(String tag, Object data) {
        this.tag = tag;
        this.data = data;
    }
    
    @Override
    public void init() {
        Hardware.log(tag, data);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override     public void end() {

    }
}
