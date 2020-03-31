package org.firstinspires.ftc.teamcode.apollo.auto;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.CurvePoint;
import org.firstinspires.ftc.teamcode.apollo.robot;
import org.firstinspires.ftc.teamcode.apollo.auto.purePersuit.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Autonomous(name="Pushbot: Auto Drive By Gyro", group="Pushbot")
public class testPathMecunom extends LinearOpMode {
    robot robot = new robot();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        while (!isStarted()) {
            telemetry.update();
        }
        ArrayList<CurvePoint> allPoint = new ArrayList<>();
        allPoint.add(new CurvePoint(0,0,0,0,0,0,0,0));
        RobotMonement.followCurve(allPoint, Math.toRadians(180));

    }
}