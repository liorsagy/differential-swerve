package org.firstinspires.ftc.teamcode.apollo.subSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.apollo.constant;

enum side{left,right;}

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
        return (motor1.getCurrentPosition() + motor2.getCurrentPosition()) * constant.DEGREE_PER_TICK;
    }

    public double setModuleAngle(double targetAngle, double Kp) {
        double p = (targetAngle - getModuleAngle()) * Kp;
        return Range.clip(p, -1, 1);
    }

    public void moduleSetPower(double vectorAngle, double vectorLength, double z) {
        if (side == org.firstinspires.ftc.teamcode.apollo.subSystems.side.left) {
            motor1.setPower(Range.clip(vectorLength - z, -1, 1));
            motor2.setPower(Range.clip(-vectorLength + z + (setModuleAngle(vectorAngle, constant.Kp_moduleRotation)), -1, 1));
        } else{
            motor1.setPower(Range.clip(-vectorLength + z, -1, 1));
            motor2.setPower(Range.clip(vectorLength - z + (setModuleAngle(vectorAngle, constant.Kp_moduleRotation)), -1, 1));
        }
    }
}