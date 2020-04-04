package org.firstinspires.ftc.teamcode.apollo.subSystems;

import org.firstinspires.ftc.teamcode.apollo.constant;
import org.firstinspires.ftc.teamcode.apollo.robot;

public class imageProcessing {
    private double x;
    private double y;
    private double width;
    private double height;

    public imageProcessing (double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double percentageArea(){
        double area = width * height;
        return area / constant.pixyOnePercentArea;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
