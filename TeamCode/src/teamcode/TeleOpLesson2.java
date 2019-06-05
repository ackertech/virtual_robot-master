package teamcode;

/**
 * TeleOp Lesson 2 - Basics of Arcade Mode Drive / First Person Shooter Style Java Program
 * How to program buttons on GamePad
 */

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.DCMotor;

public class TeleOpLesson2 extends LinearOpMode {                   // Define the Class


    DCMotor leftMotor = null;                                  // Class Instance Variables
    DCMotor rightMotor = null;


    public void runOpMode() {       // OpMode Runs the robot


        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        leftMotor.setDirection(DCMotor.Direction.REVERSE);


        waitForStart();

        while ( opModeIsActive()) {

               if (gamePad1.a) {
                   reverse(1);
               }
               else if (gamePad1.y) {
                   forward(1);
               }
               else if (gamePad1.b) {
                   turnRight(1);
               }
               else if (gamePad1.x) {
                   turnLeft(1);
               }
               else {
                   stopMotors();
               }



        }
    }

    public void stopMotors () {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void forward (double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void turnRight (double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(0);
    }

    public void turnLeft (double power) {
        leftMotor.setPower(0);
        rightMotor.setPower(power);
    }

    public void reverse (double power) {
        leftMotor.setPower(-power);
        rightMotor.setPower(-power);
    }


}
