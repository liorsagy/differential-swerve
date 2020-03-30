package org.firstinspires.ftc.teamcode.teamcode.apollo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Diff Swerve TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {

    DcMotor left1;
    DcMotor left2;
    DcMotor right1;
    DcMotor right2;

    BNO055IMU imu;

    public void init() {
        left1   = hardwareMap.get(DcMotor.class, "left1");
        left2   = hardwareMap.get(DcMotor.class, "left2");
        right1  = hardwareMap.get(DcMotor.class, "right1");
        right2  = hardwareMap.get(DcMotor.class, "right2");
        imu     = hardwareMap.get(BNO055IMU.class, "imu");
    }

    public void init_loop() {

    }
    public void start (){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(parameters);
    }


    public void loop() {
        vector vector = new vector(gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);
        moduleControl leftModule = new moduleControl(left1, left2, side.left);
        moduleControl rightModule = new moduleControl(right1, right2, side.right);
        double vectorLength = vector.getVectorLength();
        double vectorAngle = vector.getVectorAngle();
        leftModule.moduleSetPower(vectorAngle, vectorLength, vector.getZ());
        rightModule.moduleSetPower(vectorAngle, vectorLength, vector.getZ());
    }
}
