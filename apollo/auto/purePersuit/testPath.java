package org.firstinspires.ftc.teamcode.apollo.auto.purePersuit;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.skystone.purePersuit.RobotMonement.worldAngle_rad;
import static org.firstinspires.ftc.teamcode.skystone.purePersuit.RobotMonement.worldXPosition;
import static org.firstinspires.ftc.teamcode.skystone.purePersuit.RobotMonement.worldYPosition;


@Autonomous(name="pure pursuite", group="Pushbot")
public class testPath extends OpMode {
    DcMotor lb;
    DcMotor lf;
    DcMotor rf;
    DcMotor rb;
    BNO055IMU imu;
    @Override
    public void init() {
        lb= hardwareMap.dcMotor.get("back_left_motor");
        lf= hardwareMap.dcMotor.get("front_left_motor");
        rf= hardwareMap.dcMotor.get("front_right_motor");
        rb= hardwareMap.dcMotor.get("back_right_motor");
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.accelerationIntegrationAlgorithm = null;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationData = null;
        parameters.calibrationDataFile = "";
        parameters.loggingEnabled = false;
        parameters.loggingTag = "Who cares.";

        imu.initialize(parameters);
    }

    @Override
    public void loop() {

        ArrayList<CurvePoint> allPoint = new ArrayList<>();
        allPoint.add(new CurvePoint(0,0,1.0,1.0,50.0,Math.toRadians(50),1.0));
        allPoint.add(new CurvePoint(0,100,1.0,1.0,50.0,Math.toRadians(50),1.0));

        //RobotMonement.followCurve(allPoint, Math.toRadians(180), lf, rf,lb,rb,imu);
        telemetry.addData("x", worldXPosition);
        telemetry.addData("y", worldYPosition);
        telemetry.addData("angle", worldAngle_rad);
        RobotMonement.goToPosition(100,100,1.0,Math.toRadians(90), 1.0, lf,rf,lb,rb,imu);

    }


}