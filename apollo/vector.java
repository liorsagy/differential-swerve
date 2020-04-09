package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.skystone.robot2020;

public class vector {

    robot2020 robot;
    private double x;
    private double y;
    private double z;

    public vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {return x;}

    public double getY() {return y;}

    public double getZ() {return z;}

    public void setX(double x) {this.x = x;}

    public void setY(double y) {this.y = y;}

    public void setZ(double z) {this.z = z;}

    public double getVectorLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double vectorAngle(double offset) {
        double joystickAngle180 = Math.toDegrees(Math.atan2(y, x));
        double joystickAngle360 = (joystickAngle180 + 360) % 360;
        double robotAngle180 = robotUtil.getAngle() + offset;
        double robotAngle360 = (robotAngle180 + 360) % 360;
        double finalAngle180 = robotAngle360 + joystickAngle360;
        return (finalAngle180 + 360) % 360;
    }

    public void fieldCentric(double offset) {
        double joystickAngle180 = Math.toDegrees(Math.atan2(y, x));
        double joystickAngle360 = (joystickAngle180 + 360) % 360;
        double robotAngle180 = robotUtil.getAngle() + offset;
        double robotAngle360 = (robotAngle180 + 360) % 360;
        double finalAngle180 = robotAngle360 + joystickAngle360;
        double finalAngle380 = (finalAngle180 + 360) % 360;
        double vectorLength = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        double finalX = Math.sin(Math.toRadians(finalAngle380)) * vectorLength;
        double finalY = -Math.cos(Math.toRadians(finalAngle380)) * vectorLength;
        double lfp = finalX +finalY+z;
        double lbp = finalX +finalY+z;
        double rfp = finalX +finalY+z;
        double rbp = finalX +finalY+z;

        robot.lf.setPower(Range.clip(lfp,-1,1));
        robot.lb.setPower(Range.clip(lbp,-1,1));
        robot.rf.setPower(Range.clip(rfp,-1,1));
        robot.rb.setPower(Range.clip(rbp,-1,1));
    }
}
