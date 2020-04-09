package org.firstinspires.ftc.teamcode.skystone;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.apollo.vector;

import java.util.Vector;

//TODO fill all the variables
public class constant2020 {

    public static final int offSet = 0;

    public static final double wheelDiameter = 4;

    public static final double cameraResolution = 1296*976;

    public static final double cameraOnePercentArea = cameraResolution/100;

    public static boolean ifvision = false;


    //PID vision area
    public static final double Kp_moduleRotation = 0;

    public static  final double Kp_visionXY = 0;

    public static  final double Kp_visionArea = 0;


    //vision area
    public static final double readyVisionX = 0;
    public static final double readyVisionY = 0;
    public static final double readyVisionZ = 0;


    //field odometry area
    public static double worldXPosition;
    public static double worldYPosition;
    public static double worldAngle;
    public static int lastX = 0;
    public static int lastY = 0;



    //vertical lift area
    public static final double vl_Kp = 0;
    public static final double vl_Ki = 0;
    public static final double vl_Kd = 0;
    public static final double vl_Kf = 0;
    public static final double vl_Kv = 0;//motor rpm
    public static final PIDFCoefficients vlPIDF = new PIDFCoefficients(vl_Kp, vl_Ki,vl_Kd,vl_Kf);

    //horiznotal lift area
    public static final double hl_Kp = 0;
    public static final double hl_Ki = 0;
    public static final double hl_Kd = 0;
    public static final double hl_Kf = 0;
    public static final double hl_Kv = 0;//motor rpm
    public static final PIDFCoefficients hlPIDF = new PIDFCoefficients(hl_Kp, hl_Ki, hl_Kd, hl_Kf);

    //servos area
    public static final double capston_open =
;
    public static final double capston_close = 0;

    public static final double front_open = 0;
    public static final double front_close = 0;

    public static final double back_open = 0;
    public static final double back_close = 0;

    public static final double foundation_open = 0;
    public static final double foundation_close = 0;

    //mode area
    robotMode robotMode;
    driveMode driveMode;
    verticalLiftMode verticalLiftMode;
    horizntoalLiftMode horizntoalLiftMode;
    catchBackMode catchBackMode;
    catchFrontMode catchFrontMode;
    intakeMode intakeMode;
    capstoneMode capstoneMode;
    foundationMode foundationMode;
    RevBlinkinLedDriver.BlinkinPattern ledcolor;


    vector driveVector;

    //pos area
    public static double verticalWantedPos;
    public static double horizontalWantedPos;
    public static double capstoneWantedPos;
    public static double foundationWantedPos;
    public static double backWantedPos;
    public static double frontWantedPos;

}
