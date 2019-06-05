package teamcode;

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.DCMotor;
import virtual_robot.util.time.ElapsedTime;

public abstract class DriveControl extends LinearOpMode {

    DCMotor leftMotor = null;
    DCMotor rightMotor = null;

    public ElapsedTime runTime = new ElapsedTime();

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

    public void sleep(double time) {
        while (runTime.milliseconds() < time) { }
    }

}
