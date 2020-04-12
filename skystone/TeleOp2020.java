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
    robot2020 robot;
    robotModeControl mode;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start (){

    }


    @Override
    public void loop() {
        mode.TeleopModeChooser(robot);
        robotUtil.worldPosition();
    }
    @Override
    public void stop() {

    }

}
