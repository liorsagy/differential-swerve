package org.firstinspires.ftc.teamcode.skystone;

public enum robotMode {
    travel,
    intake,
    emission,
    emissionWheel,
    foundation,
    capstone,
    FAULT;
}

enum driveMode{
    driver1,
    driver2,
    vision,
    driverAndVision;
}
enum verticalLiftMode{
    close,
    byHeight,
    FAULT;
}
enum horizntoalLiftMode{
    open,
    close,
    FAULT;
}
enum capstoneMode{
    open,
    close;
}
enum catchFrontMode{
    open,
    close;

}
enum catchBackMode{
    open,
    close;
}
enum foundationMode{
    open,
    close;
}
enum intakeMode{
    stati,
    inside,
    outside;
}