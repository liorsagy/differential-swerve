package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.apollo.subSystems.moduleControl;
import org.firstinspires.ftc.teamcode.apollo.subSystems.side;

public class robot
{
    public DcMotor left1;
    public DcMotor left2;
    public DcMotor right1;
    public DcMotor right2;

    public BNO055IMU imu;

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

        left1  = hwMap.get(DcMotor.class, "left Main");
        left2  = hwMap.get(DcMotor.class, "left scened");
        right1 = hwMap.get(DcMotor.class, "right Main");
        right2 = hwMap.get(DcMotor.class, "right scened");

        xField = hwMap.get(DcMotor.class, "x field");
        yField = hwMap.get(DcMotor.class, "y field");

        imu    = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(parameters);

        leftModule   = new moduleControl(left1, left2, side.left);
        rightModule  = new moduleControl(right1, right2, side.right);
    }
}