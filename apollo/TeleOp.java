package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Diff Swerve TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    robot robot;

    double worldXPosition = 0;
    double worldYPosition = 0;
    double worldAngle = 0+constant.offSet;
    int lastX;
    int lastY;

    public void init() {

    }

    public void init_loop() {

    }
    public void start (){

    }


    public void loop() {
        vector vector = new vector(gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);
        double vectorLength = vector.getVectorLength();
        double vectorAngle = vector.fieldCentric();
        robot.leftModule.moduleSetPower(vectorAngle, vectorLength, vector.getZ());
        robot.rightModule.moduleSetPower(vectorAngle, vectorLength, vector.getZ());

        //must to be in loop
        worldXPosition = robot.xField.getCurrentPosition();
        worldYPosition = robot.yField.getCurrentPosition();
        worldAngle = robotUtil.getAngle() + constant.offSet;
        worldYPosition += Math.sin(worldAngle)*-(worldYPosition-lastY)+Math.sin(worldAngle+90)*-(worldXPosition-lastX);
        worldXPosition += Math.cos(worldAngle)*-(worldYPosition-lastY)+Math.cos(worldAngle+90)*-(worldXPosition-lastX);
        lastX = robot.xField.getCurrentPosition();
        lastY = robot.yField.getCurrentPosition();
    }
}
