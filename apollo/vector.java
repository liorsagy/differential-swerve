package org.firstinspires.ftc.teamcode.apollo;

public class vector {
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

    public double fieldCentric() {
        double joystickAngle180 = Math.toDegrees(Math.atan2(y, x));
        double joystickAngle360 = (joystickAngle180 + 360) % 360;
        double robotAngle180 = robotUtil.getAngle() + constant.offSet;
        double robotAngle360 = (robotAngle180 + 360) % 360;
        double finalAngle180 = robotAngle360 + joystickAngle360;
        return (finalAngle180 + 360) % 360;
    }
}
