package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.apollo.vector;

public class robotModeControl extends OpMode {

    constant2020 constant;
    robot2020 robot;

    public void robotModeSelaction(){
        if (gamepad1.x) constant.robotMode = robotMode.travel;
        else if (gamepad1.a) constant.robotMode =  robotMode.capstone;
        else if (gamepad1.b) constant.robotMode =  robotMode.emission;
        else if (gamepad1.y) constant.robotMode =  robotMode.emissionWheel;
        else if (gamepad1.left_bumper) constant.robotMode = robotMode.intake;
        else if (gamepad1.right_bumper) constant.robotMode = robotMode.foundation;
        else if (false) constant.robotMode = robotMode.FAULT;
        else   constant.robotMode = constant.robotMode;

    }

    public void driveModeSelaction(){
        switch (constant.robotMode){
            case emission:
            case foundation:
            case capstone:
                constant.driveMode = driveMode.driverAndVision;
            case intake:
                constant.driveMode = driveMode.vision;
            case travel:
            case emissionWheel:
                constant.driveMode = driveMode.driver1;
            case FAULT:
                constant.driveMode = driveMode.driver2;
            default:
                constant.driveMode = constant.driveMode;
        }

    }
    public void verticalLiftModeSelaction(){
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
    }
    public void horizntoalLiftModeSelaction(){
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
    }
    public void capstoneModeSelaction(){
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
            default:
                constant.capstoneMode = constant.capstoneMode;
        }
    }
    public void catchFrontModeSelaction(){
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
            default:
                constant.catchFrontMode = constant.catchFrontMode;
        }
    }
    public void catchBackModeSelaction(){
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
    }
    public void foundationModeSelaction(){
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
    }
    public void intakeModeSelaction(){
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
            default:
               constant.intakeMode =  constant.intakeMode;
        }
    }
    public void ledSelaction(){
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
    }








    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
