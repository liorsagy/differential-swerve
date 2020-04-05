package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.apollo.subSystems.moduleControl;
import org.firstinspires.ftc.teamcode.apollo.Enums.side;

public class robot
{
    public DcMotorEx left1;
    public DcMotorEx left2;
    public DcMotorEx right1;
    public DcMotorEx right2;

    public BNO055IMU imu;

    I2cDeviceSynch pixy;

    public DcMotor xField;
    public DcMotor yField;


    public moduleControl leftModule;
    public moduleControl rightModule;

    HardwareMap hwMap           =  null;

    public robot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        left1  = hwMap.get(DcMotorEx.class, "left Main");
        left2  = hwMap.get(DcMotorEx.class, "left scened");
        right1 = hwMap.get(DcMotorEx.class, "right Main");
        right2 = hwMap.get(DcMotorEx.class, "right scened");

        xField = hwMap.get(DcMotor.class, "x field");
        yField = hwMap.get(DcMotor.class, "y field");

        imu    = hwMap.get(BNO055IMU.class, "imu");


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(parameters);

        pixy.setI2cAddress(I2cAddr.create7bit(0x54));
        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow (1, 26, I2cDeviceSynch.ReadMode.REPEAT);
        pixy.setReadWindow(readWindow);

        left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftModule   = new moduleControl(left1, left2, side.left);
        rightModule  = new moduleControl(right1, right2, side.right);


    }
}