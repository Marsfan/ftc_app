package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


/**
 * Created by Gabe on 2/6/2016.
 */

public class telemetryTest extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMoto;
    DcMotor rack;
    DcMotor winch;

    @Override
    public void init() {
        //get references to the motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMoto = hardwareMap.dcMotor.get("rightMoto");
        rack = hardwareMap.dcMotor.get("rack");
        winch = hardwareMap.dcMotor.get("winch");


        //reverse the right motor
      //  leftMotor.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop() {
        //get the values from the gamepads
        //note: pushing the stick all the way up returns -1,
        // so we need to reverse the values
        double leftY = gamepad1.left_stick_y;
        double rightY = gamepad1.right_trigger;
        double roY = rightY - 0.5;
        double rY = roY * 2;
        double left2 = leftY * leftY;
        double right2 = rY * rY;
        boolean rackPower1 = gamepad1.a;
        boolean rackPower2 = gamepad1.x;
        boolean winchPower1 = gamepad1.b;
        boolean winchPower2 = gamepad1.y;


        if(rackPower1 == true) {
            rack.setPower(1);
        } else if(rackPower2 == true) {
            rack.setPower(-1);
        } else {
            rack.setPower(0);
        }
        if(winchPower1 == true) {
            winch.setPower(1);
        } else if(winchPower2 == true) {
            winch.setPower(-1);
        } else {
            winch.setPower(0);
        }
        if(leftY < 0) {
            leftMotor.setPower(-left2);
        } else {
            leftMotor.setPower(left2);
        }
        if(rY < 0) {
            rightMoto.setPower(right2);
        } else {
            rightMoto.setPower(-right2);
        }
        telemetry.addData("LeftMotor", leftMotor.getPower());
        telemetry.addData("RightMotor", rightMoto.getPower());

    }

}