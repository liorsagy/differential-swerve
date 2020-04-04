package org.firstinspires.ftc.teamcode.apollo;


import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
//TODO fill all the variables
public class constant {

    public static final double DEGREE_PER_TICK = 0;

    public static final double Kp_moduleRotation = 0;

    public static final int offSet = 0;

    public static final double wheelDiameter = 0;

    static final PIDFCoefficients arm = new PIDFCoefficients(0,0,0,0, MotorControlAlgorithm.PIDF);

    public static final double pixyResolution = 1296*976;

    public static final double pixyOnePercentArea = 12648.96;
}
