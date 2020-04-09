package org.firstinspires.ftc.teamcode.apollo.subSystems;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;


import static org.firstinspires.ftc.teamcode.skystone.constant2020.cameraOnePercentArea;

public class imageProcessing {
    private double x;
    private double y;
    private double width;
    private double height;

    //must run in loop
    public void setValue(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getZ(){
        double area = width * height;
        return (area / cameraOnePercentArea)*100;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }


    //0 Number of blocks that match the specified signature.
    //1 X value of center of largest detected block, ranging between 0 and 255. An x value of 255 is the far right­side of Pixy’s image.
    //2 Y value of center of largest detected block, ranging between 0 and 199. A value of 199 is the far bottom­side of Pixy’s image.
    //3 Width of largest block, ranging between 1 and 255. A width of 255 is the full width of Pixy’s image.
    //4 Height of largest block, ranging between 1 and 200. A height of 200 is the full height of Pixy’s image.
    public void pixyContorl(I2cDeviceSynch pixy){
        pixy.engage();
        byte[] byteData = pixy.read(0x51,5);
        double[] doubleData = byteArraytoDoubleArray(byteData);
        x = doubleData[1];
        y = doubleData[2];
        width = doubleData[3];
        height = doubleData[4];
    }

    public static double[] byteArraytoDoubleArray(byte[] byteArr){
        double[] arr=new double[byteArr.length];
        for (int i=0;i<arr.length;i++){
            arr[i]=byteArr[i];
        }
        return arr;
    }


}
