package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.configuration.ExpansionHubMotorControllerParamsState;

import org.firstinspires.ftc.teamcode.apollo.subSystems.moduleControl;
import org.firstinspires.ftc.teamcode.apollo.swerve.Enums.side;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

public class robot2020
{
    public DcMotorEx lf;
    public DcMotorEx rf;
    public DcMotorEx lb;
    public DcMotorEx rb;

    public DcMotorEx liftHorizontal;
    public DcMotorEx liftVertical;

    public DcMotorEx intakeLeft;
    public DcMotorEx intakeRight;

    public Servo front;
    public Servo back;

    public Servo capstone;

    public Servo foundationLeft;
    public Servo foundationRight;

    public BNO055IMU imu;

    public DcMotor xField;
    public DcMotor yField;

    public DistanceSensor cubeDectector;

    public I2cDeviceSynch pixy;

    public RevBlinkinLedDriver led;


    HardwareMap hwMap;

    constant2020 constant;

    public robot2020(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        lf = hwMap.get(DcMotorEx.class, "lf");
        rf = hwMap.get(DcMotorEx.class, "rf");
        lb = hwMap.get(DcMotorEx.class, "lb");
        rb = hwMap.get(DcMotorEx.class, "rb");

        liftHorizontal = hwMap.get(DcMotorEx.class, "lh");
        liftVertical = hwMap.get(DcMotorEx.class, "lv");

        intakeLeft = hwMap.get(DcMotorEx.class, "il");
        intakeRight = hwMap.get(DcMotorEx.class, "ir");

        front = hwMap.get(Servo.class, "front");
        back = hwMap.get(Servo.class, "back");

        capstone = hwMap.get(Servo.class, "capstone");

        foundationLeft = hwMap.get(Servo.class, "fl");
        foundationRight = hwMap.get(Servo.class, "fr");

        imu    = hwMap.get(BNO055IMU.class, "imu");
        //TODO לבדוק איפה מחובר האינקודרים
        xField = hwMap.get(DcMotor.class, "il");
        yField = hwMap.get(DcMotor.class, "ir");

        cubeDectector = hwMap.get(DistanceSensor.class, "cube");

        pixy = hwMap.get(I2cDeviceSynch.class, "pixy");

        led = hwMap.get(RevBlinkinLedDriver.class, "led");






        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        lf.setZeroPowerBehavior(BRAKE);
        lb.setZeroPowerBehavior(BRAKE);
        rf.setZeroPowerBehavior(BRAKE);
        rb.setZeroPowerBehavior(BRAKE);
        liftVertical.setZeroPowerBehavior(BRAKE);
        liftHorizontal.setZeroPowerBehavior(BRAKE);
        intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);



        liftHorizontal.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, constant.hlPIDF);
        liftVertical.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, constant.vlPIDF);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(parameters);

        pixy.setI2cAddress(I2cAddr.create7bit(0x54));
        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow (1, 26, I2cDeviceSynch.ReadMode.REPEAT);
        pixy.setReadWindow(readWindow);
    }
}