package teamcode;

/**
 * TeleOp Lesson 1 - Basics of Tank Drive / First Person Shooter Style Java Program
 *  How to program Stick Y on Gamepad
 */

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.DCMotor;

public class TeleOpLesson1 extends LinearOpMode {                   // Define the Class


    DCMotor leftMotor = null;                                  // Class Instance Variables
    DCMotor rightMotor = null;


    public void runOpMode() {                               // OpMode Runs the robot
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        leftMotor.setDirection(DCMotor.Direction.REVERSE);

        waitForStart();

        while ( opModeIsActive()) {

            leftMotor.setPower(-gamePad1.left_stick_y);
            rightMotor.setPower(-gamePad1.right_stick_y);

        }
    }


}
