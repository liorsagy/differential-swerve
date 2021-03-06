package org.firstinspires.ftc.teamcode.apollo.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.apollo.swerve.Enums.side;
import org.firstinspires.ftc.teamcode.apollo.constant;

public class moduleControl {
    private DcMotor motor1;
    private DcMotor motor2;
    private side side;
    org.firstinspires.ftc.teamcode.apollo.robotUtil robotUtil;

    public moduleControl(DcMotor motor1, DcMotor motor2, side side) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        this.side = side;
    }

    public double getModuleAngle() {
        return (motor2.getCurrentPosition() - motor1.getCurrentPosition()) * constant.tickPerDegree;
    }

    public double setModuleAngle(double targetAngle, double Kp) {
        double p = (targetAngle - getModuleAngle()) * Kp;
        return Range.clip(p, -1, 1);
    }

    public void moduleSetPower(double vectorAngle, double vectorLength, double z) {
        if (side == org.firstinspires.ftc.teamcode.apollo.swerve.Enums.side.left) {
            motor1.setPower(Range.clip(vectorLength - z, -1, 1));
            motor2.setPower(Range.clip(-vectorLength + z + (setModuleAngle(vectorAngle, constant.Kp_moduleRotation)), -1, 1));
        } else{
            motor1.setPower(Range.clip(-vectorLength + z, -1, 1));
            motor2.setPower(Range.clip(vectorLength - z + (setModuleAngle(vectorAngle, constant.Kp_moduleRotation)), -1, 1));
        }
    }
}