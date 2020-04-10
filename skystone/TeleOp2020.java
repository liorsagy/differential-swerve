package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.apollo.robotUtil;
import org.firstinspires.ftc.teamcode.apollo.subSystems.imageProcessing;
import org.firstinspires.ftc.teamcode.apollo.swerve.Enums.driveMode;
import org.firstinspires.ftc.teamcode.apollo.swerve.Enums.robotMode;
import org.firstinspires.ftc.teamcode.apollo.swerve.robotSwerve;
import org.firstinspires.ftc.teamcode.apollo.vector;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Diff Swerve TeleOp", group = "TeleOp")
public class TeleOp2020 extends OpMode {
    robotSwerve robot;


    int lastX;
    int lastY;
    org.firstinspires.ftc.teamcode.apollo.vector vector;
    imageProcessing vision;
    boolean visionReady = false;

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start (){

    }


    @Override
    public void loop() {
        //must to be in loop
        robotUtil.worldPosition();
    }
    @Override
    public void stop() {

    }

}
