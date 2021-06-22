package org.firstinspires.ftc.team7316.util.copypastaLib;

/**
 * Created by jerry on 1/9/18.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public Util() {
    }

    public static boolean lessThan(double a, double b, double error) {
        return a < b - error || a < b + error;
    }

    public static double[] getDoubleArr(List<Double> list) {
        double[] out = new double[list.size()];

        for(int i = 0; i < out.length; ++i) {
            out[i] = ((Double)list.get(i)).doubleValue();
        }

        return out;
    }

    public static void writeCSV(String location, List<Double> times, List<Double> speeds, List<Double> positions, List<Double> accelerations) {
        Date date = new Date(System.currentTimeMillis());
        String timestamp = (new SimpleDateFormat("yyyyMMdd_HH-mm-ss")).format(date);

        try {
            File dir = new File(location + "_" + timestamp + ".csv");
            dir.createNewFile();
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
            os.write("times,speeds,positions,accelerations\n");

            for(int i = 0; i < times.size(); ++i) {
                os.write(String.format("%s,%s,%s,%s\n", new Object[]{times.get(i), speeds.get(i), positions.get(i), accelerations.get(i)}));
            }

            os.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }
}

