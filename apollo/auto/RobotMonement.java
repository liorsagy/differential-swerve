package org.firstinspires.ftc.teamcode.apollo.auto;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint;
import org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.MathFunction;
import org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.point;
import org.firstinspires.ftc.teamcode.apollo.robot;
import  org.firstinspires.ftc.teamcode.apollo.robotUtil;
import org.firstinspires.ftc.teamcode.apollo.vector;

import java.util.ArrayList;

public class RobotMonement{

    static robot robot;
    static double worldXPosition = 0;
    static double worldYPosition = 0;

    //hi    

    public static void followCurve(ArrayList<org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint> allPoints, double followAngle){

        org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint followMe = getFollowPointPath(allPoints , new org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.point(worldXPosition, worldYPosition), allPoints.get(0 ).followDistance);

        goToPosition(followMe.x,followMe.y,followMe.moveSpeed, followAngle,followMe.turnSpeed);
    }

    public static org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint getFollowPointPath(ArrayList<org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint> pathPoints, org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.point robotlocation, double followRadius){
        double worldXPosition = 0;
        double worldYPosition = 0;
        double worldAngle_rad = Math.toRadians(robotUtil.getAngle());

        org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint followMe = new org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint(pathPoints.get(0));

        for(int i = 0 ; i < pathPoints.size() ; i ++ ) {
            org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint startLine = pathPoints.get(i);
            CurvePoint endLine = pathPoints.get( i +1 );

            ArrayList<org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.point> intrectionx  = org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.MathFunction.lineCircleintersaction(robotlocation , followRadius , startLine.toPoint(), endLine.toPoint());

            double closetAngle = 100000000;
            for (point thisintractiox : intrectionx){
                double angle = Math.atan2(thisintractiox.x - worldXPosition, thisintractiox.y - worldYPosition);
                double deltaAngle = Math.abs(org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.MathFunction.AngelWarp(angle - worldAngle_rad));
                if ( deltaAngle < closetAngle){
                    closetAngle = deltaAngle;
                    followMe.setPoint(thisintractiox);
                }
            }

        }
        return followMe;
    }






    public static void  goToPosition(double x, double y, double movementSpeed, double preferredAngle, double turnSpeed){

        double worldXPosition = 0;
        double worldYPosition = 0;
        double worldAngle_rad = Math.toRadians(robotUtil.getAngle());

        double distanceToTarget = Math.hypot(x- worldXPosition, y- worldYPosition);
        double absoluteAngleToTarget = Math.atan2(y- worldYPosition, x- worldXPosition);

        double relativeAngleToPoint = MathFunction.AngelWarp(absoluteAngleToTarget - ( worldAngle_rad- Math.toRadians(90)));
        double relativeXtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));
        double movementYPower = relativeYtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));

        double movement_x = movementXPower * movementSpeed;
        double movement_y = movementYPower * movementSpeed;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + preferredAngle;

        double movement_turn = Range.clip(relativeTurnAngle / Math.toRadians(30),-1,1) * turnSpeed;
        if (distanceToTarget< 10){
            movement_turn = 0;
        }
        vector vector = new vector(movement_x, movement_y, movement_turn);

        robot.leftModule.moduleSetPower(vector.fieldCentric(),  vector.getVectorLength(), vector.getZ());
        robot.rightModule.moduleSetPower(vector.fieldCentric(), vector.getVectorLength(), vector.getZ());

    }
}
