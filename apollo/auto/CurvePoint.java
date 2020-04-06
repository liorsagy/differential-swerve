package org.firstinspires.ftc.teamcode.apollo.auto;

import android.graphics.Point;

public class CurvePoint {
    public double x;
    public double y;
    public double moveSpeed;
    public double turnSpeed;
    public double followDistance;
    public double pointLength;
    public double pointLenght;
    public double slowDownTurnRdians;
    public double slowedDownTurnAmount;


    public CurvePoint( double x, double  y, double moveSpeed, double turnSpeed,
     double followDistance, double pointLength, double slowDownTurnRdians , double slowedDownTurnAmount){

        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
        this.turnSpeed = turnSpeed;
        this.followDistance = followDistance;
        this.pointLength = pointLength;
        this.slowDownTurnRdians = slowDownTurnRdians;
        this.slowedDownTurnAmount = slowedDownTurnAmount;
    }

    public CurvePoint( CurvePoint thispoint){
        x = thispoint.x;
        y = thispoint.y;
        moveSpeed = thispoint.moveSpeed;
        turnSpeed = thispoint.turnSpeed;
        followDistance = thispoint.followDistance;
        pointLenght = thispoint.pointLenght;
        slowDownTurnRdians = thispoint.slowDownTurnRdians;
        slowedDownTurnAmount = thispoint.slowedDownTurnAmount;
    }
    public Point toPoint(){
        return new point(x,y);
    }
    public void  setPoint(Point point){
        x = point.x;
        y = point.y;
    }
}
