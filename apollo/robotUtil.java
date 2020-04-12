package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.skystone.robot2020;
import org.opencv.core.Mat;

import java.nio.ByteBuffer;

import static org.firstinspires.ftc.teamcode.skystone.constant2020.*;


public class robotUtil {
    static robot2020 robot;
    public static double getAngle(){
        return robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public static double p(double Kp, double current, double target){
         return (target - current) * Kp;
    }

    public static void worldPosition(){
        worldXPosition = robot.xField.getCurrentPosition();
        worldYPosition = robot.yField.getCurrentPosition();
        worldAngle = robotUtil.getAngle() + offSet;
        worldYPosition += Math.sin(worldAngle)*-(worldYPosition-lastY)+Math.sin(worldAngle+90)*-(worldXPosition-lastX);
        worldXPosition += Math.cos(worldAngle)*-(worldYPosition-lastY)+Math.cos(worldAngle+90)*-(worldXPosition-lastX);
        lastX = robot.xField.getCurrentPosition();
        lastY = robot.yField.getCurrentPosition();
    }

    public static void setPower(DcMotorEx motor, int target, int min, int max){
        motor.setTargetPosition(Range.clip(target, min, max));
    }
}
