package org.firstinspires.ftc.teamcode.skystone.purePersuit;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.apollo.robot;

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