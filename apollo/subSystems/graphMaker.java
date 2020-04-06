package org.firstinspires.ftc.teamcode.apollo.subSystems;


import android.os.Build;
import org.firstinspires.ftc.robotcore.internal.android.dx.rop.cst.CstArray;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.firstinspires.ftc.robotcore.internal.system.Assert.assertTrue;

class graphMaker {
    PrintWriter writer;
    public void startFile(String fileName, double Kp, double target){
        try {
             writer = new PrintWriter(fileName+".csv", "UTF-8");
             writer.print(Kp);
             writer.println(target);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addData(double x , double y){
        writer.print(x);
        writer.print(",");
        writer.print(y);
        writer.println(",");
    }

    public void end(){
        writer.close();
    }

}
