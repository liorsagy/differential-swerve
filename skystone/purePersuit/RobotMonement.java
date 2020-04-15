package org.firstinspires.ftc.teamcode.skystone.purePersuit;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import java.util.ArrayList;

public class RobotMonement{

    static double worldXPosition = 0;
    static double worldYPosition = 0;
    static double worldAngle_rad = 0;
    private static double x = 0;
    private static double y = 0;

    //hi

    public static void followCurve(ArrayList<CurvePoint> allPoints, double followAngle,
                                   DcMotor lf,
                                   DcMotor rf,
                                   DcMotor lb,
                                   DcMotor rb,
                                   BNO055IMU imu){

        RobotMonement.setWorldPosition(lf,rf,lb,rb,imu);
        CurvePoint followMe = getFollowPointPath(allPoints , new point(worldXPosition, worldYPosition), allPoints.get(0 ).followDistance, lf,rf,lb,rb,imu);

        goToPosition(followMe.x,followMe.y,followMe.moveSpeed, followAngle,followMe.turnSpeed, lf, rf, lb, rb, imu);
    }

    public static CurvePoint getFollowPointPath(ArrayList< CurvePoint> pathPoints, point robotlocation, double followRadius, DcMotor lf, DcMotor rf, DcMotor lb, DcMotor rb, BNO055IMU imu){

        setWorldPosition(lf,rf,lb,rb,imu);
        CurvePoint followMe = new CurvePoint(pathPoints.get(0));

        for(int i = 0 ; i < pathPoints.size()-1 ; i ++ ) {
            CurvePoint startLine = pathPoints.get(i);
            CurvePoint endLine = pathPoints.get( i +1 );

            ArrayList<point> intrectionx  = MathFunction.lineCircleintersaction(robotlocation , followRadius , startLine.toPoint(), endLine.toPoint());

            double closetAngle = 100000000;
            for (point thisintractiox : intrectionx){
                double angle = Math.atan2(thisintractiox.x - worldXPosition, thisintractiox.y - worldYPosition);
                double deltaAngle = Math.abs(MathFunction.AngelWarp(angle - worldAngle_rad));
                if ( deltaAngle < closetAngle){
                    closetAngle = deltaAngle;
                    followMe.setPoint(thisintractiox);
                }
            }

        }
        return followMe;
    }






    public static void  goToPosition(double x, double y, double movementSpeed, double preferredAngle, double turnSpeed,
                                     DcMotor lf,
                                     DcMotor rf,
                                     DcMotor lb,
                                     DcMotor rb,
                                     BNO055IMU imu){

        RobotMonement.setWorldPosition(lf,rf,lb,rb,imu);

        double distanceToTarget = Math.hypot(x- worldXPosition, y- worldYPosition);
        double absoluteAngleToTarget = Math.atan2(y- worldYPosition, x- worldXPosition);

        double relativeAngleToPoint = MathFunction.AngelWarp(absoluteAngleToTarget - ( worldAngle_rad- Math.toRadians(90)));
        double relativeXtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;
        double relativeYtoPoint = Math.cos(relativeAngleToPoint) * distanceToTarget;

        double movementXPower = relativeXtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));
        double movementYPower = relativeYtoPoint / (Math.abs(relativeXtoPoint) + Math.abs(relativeYtoPoint));

        double movement_x = -movementXPower * movementSpeed;
        double movement_y = -movementYPower * movementSpeed;

        double relativeTurnAngle = relativeAngleToPoint - Math.toRadians(180) + preferredAngle;

        double movement_turn = Range.clip(relativeTurnAngle / Math.toRadians(10),-1,1) * turnSpeed;
        if (distanceToTarget< 10){
            movement_turn = 0;
        }

        if (distanceToTarget< 10){
            movement_x = 0;
        }
        if (distanceToTarget< 10){
            movement_y = 0;
        }

        double lfp = movement_y + movement_x - movement_turn;
        double rfp = movement_y - movement_x + movement_turn;
        double lbp = movement_y - movement_x - movement_turn;
        double rbp = movement_y + movement_x + movement_turn;
        lb.setPower(lbp);
        lf.setPower(lfp);
        rf.setPower(rfp);
        rb.setPower(rbp);

    }

    public static void setWorldPosition(DcMotor lf, DcMotor rf, DcMotor lb, DcMotor rb, BNO055IMU imu){
        y = (lf.getCurrentPosition() + rf.getCurrentPosition() + lb.getCurrentPosition() + rb.getCurrentPosition())/4;
        x = ((lf.getCurrentPosition() + rb.getCurrentPosition()) - (rf.getCurrentPosition() + lb.getCurrentPosition()))/4;
        worldYPosition =y;
        worldXPosition =x;
        worldAngle_rad = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
    }
}
