package teamcode;

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.ColorSensor;
import virtual_robot.hardware.DCMotor;
import virtual_robot.hardware.GyroSensor;
import virtual_robot.hardware.DistanceSensor;
import virtual_robot.util.time.ElapsedTime;


public class AutoTest extends LinearOpMode {

    DCMotor leftMotor = null;
    DCMotor rightMotor = null;
    public ElapsedTime runTime = new ElapsedTime();



    // Encoder Count Targets

    int pass1 = 1000;    // Pass 1
    int turn1 = 2000;    // Turn 1
    int pass2 = 7000;    // Pass 2
    int turn2 = 8100;    // Turn 2
    int pass3 = 9700;    // Pass 3


    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("left_motor");
        leftMotor.setDirection(DCMotor.Direction.REVERSE);
        leftMotor.setMode(DCMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DCMotor.RunMode.RUN_USING_ENCODER);

        rightMotor = hardwareMap.dcMotor.get("right_motor");
        rightMotor.setMode(DCMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DCMotor.RunMode.RUN_USING_ENCODER);

        // ColorSensor colorSensor = hardwareMap.colorSensor.get("color_sensor");
        //GyroSensor gyro = hardwareMap.gyroSensor.get("gyro_sensor");

        //DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        //DistanceSensor leftDistance = hardwareMap.get(DistanceSensor.class, "left_distance");
        //DistanceSensor backDistance = hardwareMap.get(DistanceSensor.class, "back_distance");
        //DistanceSensor rightDistance = hardwareMap.get(DistanceSensor.class, "right_distance");


        waitForStart();

        while(opModeIsActive()) {

            while (leftMotor.getCurrentPosition() < pass1) {
                forward(1);
                telemetryStatus();
            }
            stopMotors ();
            sleep(3000);

            while (leftMotor.getCurrentPosition() < turn1) {
                turnRight(1);
                telemetryStatus();
            }
            stopMotors ();
            sleep(3000);

            while (leftMotor.getCurrentPosition() < pass2) {
                forward(1);
                telemetryStatus();
            }
            stopMotors ();

            while (leftMotor.getCurrentPosition() < turn2) {
                turnRight(1);
                telemetryStatus();
            }
            stopMotors ();

            while (leftMotor.getCurrentPosition() < pass3) {
                forward(1);
                telemetryStatus();
            }
            stopMotors ();

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

    public void telemetryStatus () {
        telemetry.addData("Encoders","Left %d  Right %d", leftMotor.getCurrentPosition(), rightMotor.getCurrentPosition());
        telemetry.addData("Elapsed Time", runTime);
        telemetry.update();
    }

    public void sleep(double time) {
        while (runTime.milliseconds() < time) { }
    }
}
