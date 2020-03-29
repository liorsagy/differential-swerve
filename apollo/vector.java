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

    public double getVectorLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getVectorAngle() {
        double angle90 = Math.toDegrees(Math.atan2(y, x));
        return (angle90 + 180) % 180;
    }
}
