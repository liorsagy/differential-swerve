package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.apollo.constant;
import org.firstinspires.ftc.teamcode.apollo.robotUtil;
import org.firstinspires.ftc.teamcode.apollo.subSystems.imageProcessing;
import org.firstinspires.ftc.teamcode.apollo.vector;

public class robotModeControl extends OpMode {

    constant2020 constant;
    robot2020 robot;
    imageProcessing vision;

    public void robotModeSelaction(){
        if (gamepad1.x) constant.robotMode = robotMode.travel;
        if (gamepad1.a) constant.robotMode =  robotMode.capstone;
        if (gamepad1.b) constant.robotMode =  robotMode.emission;
        if (gamepad1.y) constant.robotMode =  robotMode.emissionWheel;
        if (gamepad1.left_bumper) constant.robotMode = robotMode.intake;
        if (gamepad1.right_bumper) constant.robotMode = robotMode.foundation;
        if (false) constant.robotMode = robotMode.FAULT;
    }

    public void driveModeSelaction(I2cDeviceSynch pixy){
        switch (constant.robotMode){
            case emission:
            case foundation:
            case capstone:
                constant.driveMode = driveMode.driverAndVision;
            case intake:
                constant.driveMode = driveMode.vision;

            case FAULT:
            case travel:
            case emissionWheel:
                constant.driveMode = driveMode.driver1;
            default:
                constant.driveMode = constant.driveMode;
        }

        switch (constant.driveMode) {
            case driver1:
                constant.driveVector = new vector(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            case vision:
                vision.pixyContorl(pixy);
                double x = vision.getX();
                double y = vision.getY();
                double z = vision.getZ();
                if (constant.xRange + 50 > x && constant.xRange - 50 < x && constant.yRange + 50 > y && constant.yRange - 50 < x && constant.zRange + 50 > z && constant.zRange - 50 < z) {
                    constant.visionReady = false;
                } else {
                    constant.visionReady = true;
                }

                if (constant.visionReady) {
                    constant.driveVector = new vector(0, 0, 0);
                } else {
                    vision.setValue(0, 0, 0, 0);
                    double xDrive = robotUtil.p(org.firstinspires.ftc.teamcode.apollo.constant.Kp_visionXY, vision.getX(), org.firstinspires.ftc.teamcode.apollo.constant.readyVisionX);
                    double yDrive = robotUtil.p(org.firstinspires.ftc.teamcode.apollo.constant.Kp_visionXY, vision.getY(), org.firstinspires.ftc.teamcode.apollo.constant.readyVisionY);
                    double zDrive = robotUtil.p(org.firstinspires.ftc.teamcode.apollo.constant.Kp_visionArea, vision.getZ(), org.firstinspires.ftc.teamcode.apollo.constant.readyVisionZ);
                    constant.driveVector =  new vector(yDrive, zDrive, xDrive);
                }
            case driverAndVision:
                vision.pixyContorl(pixy);
                double x2 = vision.getX();
                double y2 = vision.getY();
                if (constant.xRange + 50 > x2 && constant.xRange - 50 < x2 && constant.yRange + 50 > y2 && constant.yRange - 50 < y2) {
                    constant.visionReady = false;
                } else {
                    constant.visionReady = true;
                }

                if (constant.visionReady) {
                    constant.driveVector = new vector(0, gamepad1.left_stick_y, 0);
                } else {
                    vision.setValue(0, 0, 0, 0);
                    double xDrive = robotUtil.p(org.firstinspires.ftc.teamcode.apollo.constant.Kp_visionXY, vision.getX(), org.firstinspires.ftc.teamcode.apollo.constant.readyVisionX);
                    double yDrive = robotUtil.p(org.firstinspires.ftc.teamcode.apollo.constant.Kp_visionXY, vision.getY(), org.firstinspires.ftc.teamcode.apollo.constant.readyVisionY);
                    constant.driveVector =  new vector(yDrive, gamepad1.left_stick_y, xDrive);
                }
        }
        constant.driveVector.fieldCentric(constant.offSet);
    }

    public void verticalLiftModeSelaction(DcMotorEx motor){
        switch (constant.robotMode){
            case emissionWheel:
            case travel:
            case intake:
                constant.verticalLiftMode = verticalLiftMode.close;
            case emission:
            case foundation:
            case capstone:
                constant.verticalLiftMode = verticalLiftMode.byHeight;
            case FAULT:
                constant.verticalLiftMode = verticalLiftMode.FAULT;
            default:
                constant.verticalLiftMode = constant.verticalLiftMode;
        }
        if (gamepad2.dpad_up != constant.last_dpad_up) constant.floor++;
        if (gamepad2.dpad_down != constant.last_dpad_down) constant.floor --;
        if (constant.floor > 8) constant.floor = 1;
        if (constant.floor < 1) constant.floor = 8;

        switch (constant.verticalLiftMode){
            case close:
                constant.verticalWantedPos   = constant.vl_closePosition;
            case byHeight:
                if (constant.floor == 1) constant.verticalWantedPos = constant.startingHeight;
                else {
                    constant2020.verticalWantedPos =  constant.floor * constant.cubeHeight + constant.startingHeight;
                }
            case FAULT:
                constant.verticalWantedPos +=  gamepad2.right_stick_y * constant.vl_factor;
        }
        robotUtil.setPower(motor, constant2020.verticalWantedPos, constant.vl_smallestPosition, constant.vl_largestPosition);
    }
    public void horizntoalLiftModeSelaction(DcMotorEx motor){
        switch (constant.robotMode){
            case emission:
            case capstone:
            case foundation:
                constant.horizntoalLiftMode = horizntoalLiftMode.close;
            case intake:
            case travel:
            case emissionWheel:
                constant.horizntoalLiftMode = horizntoalLiftMode.open;
            case FAULT:
                constant.horizntoalLiftMode = horizntoalLiftMode.FAULT;
            default:
                constant.horizntoalLiftMode = constant.horizntoalLiftMode;
        }

        switch (constant.horizntoalLiftMode){
            case close:
                constant.horizontalWantedPos = constant.hl_closePosition;
            case open:
                constant.horizontalWantedPos =  constant.hl_openPosition;
            case FAULT:
                constant.horizontalWantedPos += gamepad2.left_stick_y * constant.hl_factor;
        }
        robotUtil.setPower(motor, constant2020.horizontalWantedPos, constant.hl_smallestPosition, constant.hl_largestPosition);
    }
    public void capstoneModeSelaction(Servo servo){
        switch (constant.robotMode){
            case emission:
            case foundation:
            case intake:
            case travel:
            case emissionWheel:
                constant.capstoneMode = capstoneMode.close;
            case capstone:
                constant.capstoneMode = capstoneMode.open;
            case FAULT:
                constant.capstoneMode = capstoneMode.close;
        }
        if (constant.capstoneMode == capstoneMode.close) constant.capstoneWantedPos = constant.capston_close;
        if (constant.capstoneMode == capstoneMode.open)  constant.capstoneWantedPos =  constant.capston_open;
        servo.setPosition(constant.capstoneWantedPos);
    }
    public void catchFrontModeSelaction(Servo servo){
        switch (constant.robotMode){
            case travel:
            case emissionWheel:
                constant.catchFrontMode = catchFrontMode.close;
            case intake:
                constant.catchFrontMode = catchFrontMode.open;
            case emission:
            case foundation:
            case capstone:
                //TODO להוסיף שליטה לפי כפתור
            case FAULT:
                constant.catchFrontMode = catchFrontMode.close;
        }
        if (constant.catchFrontMode == catchFrontMode.close) constant.frontWantedPos = constant.front_close;
        if (constant.catchFrontMode == catchFrontMode.open)  constant.frontWantedPos = constant.front_open;
        servo.setPosition(constant.frontWantedPos);
    }
    public void catchBackModeSelaction(Servo servo){
        switch (constant.robotMode){
            case travel:
            case intake:
            case emissionWheel:
                constant.catchBackMode = catchBackMode.close;
            case emission:
            case foundation:
            case capstone:
                //TODO להוסיף שךיטה לפי כפתור
            case FAULT:
                constant.catchBackMode = catchBackMode.close;
            default:
                constant.catchBackMode = constant.catchBackMode;
        }
        if (constant.catchBackMode == catchBackMode.close) constant.backWantedPos = constant.back_close;
        if (constant.catchBackMode == catchBackMode.open)  constant.backWantedPos = constant.back_open;
        servo.setPosition(constant.backWantedPos);
    }

    public void foundationModeSelaction(Servo servo1, Servo servo2){
        switch (constant.robotMode){
            case emission:
            case capstone:
            case intake:
            case travel:
            case emissionWheel:
            case FAULT:
                constant.foundationMode = foundationMode.open;
            case foundation:
                constant.foundationMode = foundationMode.close;
            default:
                constant.foundationMode = constant.foundationMode;
        }
        if (constant.foundationMode == foundationMode.close) constant.foundationWantedPos = constant.foundation_close;
        if (constant.foundationMode == foundationMode.open)  constant.foundationWantedPos = constant.foundation_open;
        servo1.setPosition(constant.foundationWantedPos);
        servo2.setPosition(constant.foundationWantedPos);
    }

    public void intakeModeSelaction(DcMotor motor1, DcMotor motor2){
        switch (constant.robotMode){
            case emission:
            case travel:
            case foundation:
            case capstone:
                constant.intakeMode = intakeMode.stati;
            case intake:
                constant.intakeMode = intakeMode.inside;
            case emissionWheel:
                constant.intakeMode = intakeMode.outside;
            case FAULT:
                constant.intakeMode = intakeMode.stati;
        }
        switch (constant.intakeMode){
            case inside:
                motor1.setPower(1);
                motor2.setPower(-1);
            case outside:
                motor1.setPower(-1);
                motor2.setPower(1);
            case stati:
                motor1.setPower(0);
                motor2.setPower(0);
        }
    }

    public void ledSelaction(RevBlinkinLedDriver led){
        switch (constant.robotMode){
            case emission:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.YELLOW;
            case foundation:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN;
            case capstone:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.ORANGE;
            case intake:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.BLUE;
            case travel:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.GREEN;
            case emissionWheel:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.GOLD;
            case FAULT:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
            default:
                constant.ledcolor =  RevBlinkinLedDriver.BlinkinPattern.RED;
        }
        led.setPattern(constant.ledcolor);
    }


    public void TeleopModeChooser(robot2020 robot){
        robotModeSelaction();
        driveModeSelaction(robot.pixy);
        intakeModeSelaction(robot.intakeLeft, robot.intakeRight);
        horizntoalLiftModeSelaction(robot.liftHorizontal);
        verticalLiftModeSelaction(robot.liftVertical);
        catchFrontModeSelaction(robot.front);
        catchBackModeSelaction(robot.back);
        capstoneModeSelaction(robot.capstone);
        foundationModeSelaction(robot.foundationLeft, robot.foundationRight);
        ledSelaction(robot.led);

    }

    public void AutoModeChooser( DcMotor intakeLeft, DcMotor intakeRight, DcMotorEx liftHorizontal,
                                  DcMotorEx liftVertical, Servo front, Servo back, Servo capstone, Servo foundationLeft, Servo foundationRight,
                                  RevBlinkinLedDriver led){
        intakeModeSelaction(intakeLeft, intakeRight);
        horizntoalLiftModeSelaction(liftHorizontal);
        verticalLiftModeSelaction(liftVertical);
        catchFrontModeSelaction(front);
        catchBackModeSelaction(back);
        capstoneModeSelaction(capstone);
        foundationModeSelaction(foundationLeft, foundationRight);
        ledSelaction(led);

    }







    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
