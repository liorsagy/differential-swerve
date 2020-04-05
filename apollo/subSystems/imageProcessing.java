package org.firstinspires.ftc.teamcode.apollo.subSystems;

import org.firstinspires.ftc.teamcode.apollo.constant;
import org.firstinspires.ftc.teamcode.apollo.robot;

public class imageProcessing {
    private double x;
    private double y;
    private double width;
    private double height;

    //must run in loop
    public void setValue(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getZ(){
        double area = width * height;
        return (area / constant.pixyOnePercentArea)*100;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
