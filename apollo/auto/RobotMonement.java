package org.firstinspires.ftc.teamcode.teamcode.apollo.auto;

import android.graphics.Point;

import com.qualcomm.robotcore.util.Range;
import  org.firstinspires.ftc.teamcode.teamcode.apollo.auto.MathFunction;

import java.util.ArrayList;

public class RobotMonement{


    public static void followCurve(ArrayList<CurvePoint> allPoints, point robotLocation, double followAngle){
        double worldXPosition = 0;
        double worldYPosition = 0;
        double worldAngle_rad = Math.toRadians(-180);

        CurvePoint followMe = getFollowPointPath(allPoints , new Point(worldXPosition, worldYPosition), allPoints.get(0 ),followDistance);

        goToPosition(followMe.x,followMe.y,followMe.moveSpeed, followAngle,followMe.turnSpeed);
    }

    public static CurvePoint getFollowPointPath(ArrayList< CurvePoint> pathPoints, Point robotlocation, double followRadius){
        double worldXPosition = 0;
        double worldYPosition = 0;
        double worldAngle_rad = Math.toRadians(-180);

        CurvePoint followMe = new CurvePoint(pathPoints.get(0));

        for(int i = 0 ; i < pathPoints.size() ; i ++ ) {
            CurvePoint startLine = pathPoints.get(i);
            CurvePoint endLine = pathPoints.get( i +1 );

            ArrayList<Point> intrectionx  = MathFunction.lineCircleintersaction(robotlocation , followRadius , startLine.toPoint(), endLine.toPoint());

            double cloosetAngle = 100000000;
            for (Point thisintractiox : intrectionx){
                double angle = Math.atan2(thisintractiox.x - worldXPosition, thisintractiox.y - worldYPosition);
                double deltaAngle = Math.abs(MathFunction.AngelWarp(angle - worldAngle_rad));
                if ( deltaAngle < cloosetAngle){
                    cloosetAngle = deltaAngle;
                    followMe.setPoint(thisintractiox);
                }
            }

        }
        return followMe;
    }






    public static  void  goToPosition(double x,double y , double momementSpeed, double preferredAngle, double turnSpeed){

        double worldXPosition = 0;
        double worldYPosition = 0;
        double worldAngle_rad = Math.toRadians(-180);

        double distanceToTarget = Math.hypot(x- worldXPosition, y- worldYPosition);
        double absoluteAngleToTarget = Math.atan2(y- worldYPosition, x- worldXPosition);

        double relativeAngleToPoint = MathFunction.AngelWarp(absoluteAngleToTarget - ( worldAngle_rad- Math.toRadians(90)));
        double relativeXtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));
        double movementYPower = relativeYtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));

        double movement_x = movementXPower * momementSpeed;
        double movement_y = movementYPower * momementSpeed;;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + preferredAngle;

        double movement_turn = Range.clip(relativeTurnAngle / Math.toRadians(30),-1,1) * turnSpeed;
        if (distanceToTarget< 10){
            movement_turn = 0;
        }

    }
}
