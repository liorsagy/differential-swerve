package org.firstinspires.ftc.teamcode.apollo;


import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
//TODO fill all the variables
public class constant {

    public static final double gearBox = 17/12/10/10/40/12;
    public static final double tickPerRevolotion = 537.6;
    public static final double tickPerDegree = tickPerRevolotion*gearBox;

    public static boolean reverse;


    public static final int offSet = 0;

    public static final double wheelDiameter = 0;

    public static final double cameraResolution = 1296*976;

    public static final double cameraOnePercentArea = cameraResolution/100;

    public static boolean ifvision = false;


    //PID area
    public static final double Kp_moduleRotation = 0;

    public static  final double Kp_visionXY = 0;

    public static  final double Kp_visionArea = 0;


    //vision area
    public static final double readyVisionX = 0;
    public static final double readyVisionY = 0;
    public static final double readyVisionZ = 0;



}
