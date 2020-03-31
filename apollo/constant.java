package org.firstinspires.ftc.teamcode.apollo;


import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
//TODO fill all the variables
public class constant {

    public static double DEGREE_PER_TICK = 0;

    public static double Kp_moduleRotation;

    public static int offSet = 0;

    public static double wheelDiameter = 0;

    static PIDFCoefficients arm = new PIDFCoefficients(0,0,0,0, MotorControlAlgorithm.PIDF);


}
