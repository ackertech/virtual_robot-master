package teamcode;

/**
 * This is a template to learn Java programming of a virtual FTC robot on a virtual competition field
 *  How to create additional methods to simplify the main program
 */

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.DCMotor;
import virtual_robot.hardware.GyroSensor;

public class TeleOpLesson3 extends LinearOpMode {                   // Define the Class


    DCMotor leftMotor = null;                                  // Class Instance Variables
    DCMotor rightMotor = null;
    boolean tankDrive;                                         // Tank Drive State
    boolean arcadeMode;                                        // Arcade Mode State


    public void runOpMode() {                               // OpMode Runs the robot
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        leftMotor.setDirection(DCMotor.Direction.REVERSE);

        waitForStart();

        while ( opModeIsActive()) {

            // Two If Statements to set drive state variables to true or false

            if (gamePad1.left_trigger > 0.0) {
                tankDrive = true;
                arcadeMode = false;

            }

            if (gamePad1.right_trigger > 0.0) {
                tankDrive = false;
                arcadeMode = true;
            }


            if (tankDrive) {

                leftMotor.setPower(-gamePad1.left_stick_y);
                rightMotor.setPower(-gamePad1.right_stick_y);

            }

            else if (arcadeMode) {


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
