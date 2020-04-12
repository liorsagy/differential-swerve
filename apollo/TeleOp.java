package org.firstinspires.ftc.teamcode.apollo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.apollo.Enums.driveMode;
import org.firstinspires.ftc.teamcode.apollo.Enums.robotMode;
import org.firstinspires.ftc.teamcode.apollo.subSystems.imageProcessing;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Diff Swerve TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    robot robot;

    double worldXPosition = 0;
    double worldYPosition = 0;
    double worldAngle = 0+constant.offSet;
    int lastX;
    int lastY;
    vector vector;
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
        robotTeleOpMode = setRobotTeleOpMode();
        driveTeleOpMode = setDriveMode(robotTeleOpMode);
        vector = drive(driveTeleOpMode);
        double vectorLength = vector.getVectorLength();
        double vectorAngle = vector.vectorAngle(constant.offSet);
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
    @Override
    public void stop() {

    }


    //TODO set robot mode
    robotMode robotTeleOpMode = robotMode.driver;
    robotMode setRobotTeleOpMode(){
        if (gamepad1.a){
            if (constant.ifvision){
                constant.ifvision = false;
                robotTeleOpMode = robotMode.autovision;
            } else {
                constant.ifvision = true;
                robotTeleOpMode = robotMode.driver;
            }
        }
        return  robotTeleOpMode;
    }


    driveMode driveTeleOpMode = driveMode.driver;
    driveMode setDriveMode(robotMode robotMode){
        switch (robotMode){
            case driver:
                return driveMode.driver;
            case autovision:
                visionReady = false;
                return driveMode.vision;
            default:
                return driveTeleOpMode;
        }
    }


    vector drive(driveMode driveMode){
        switch (driveMode){
            case driver:
                return new vector(gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);
            case vision:
                double x = vision.getX();
                double y = vision.getY();
                double z = vision.getZ();
                if (x < 500 && y < 500 && z< 5000){
                    visionReady = false;
                } else{
                    visionReady = true;
                }

                if (visionReady){
                    return new vector(0,0,0);
                }else {
                    vision.setValue(0,0,0,0);
                    double xDrive = robotUtil.p(constant.Kp_visionXY,   vision.getX(), constant.readyVisionX);
                    double yDrive = robotUtil.p(constant.Kp_visionXY,   vision.getY(), constant.readyVisionY);
                    double zDrive = robotUtil.p(constant.Kp_visionArea, vision.getZ(), constant.readyVisionZ);
                    return new vector(xDrive,yDrive,zDrive);
                }
            default:
                return new vector(0, 0, 0);
        }
    }

}
