package teamcode;

import virtual_robot.controller.LinearOpMode;
import virtual_robot.hardware.ColorSensor;
import virtual_robot.hardware.DCMotor;
import virtual_robot.hardware.DistanceSensor;
import virtual_robot.hardware.GyroSensor;
import virtual_robot.util.time.ElapsedTime;


public class AutoLessonRyan extends LinearOpMode {

    DCMotor leftMotor = null;
    DCMotor rightMotor = null;
    public ElapsedTime runTime = new ElapsedTime();

    GyroSensor gyro = null;
    ColorSensor colorSensor = null;

    // Color Thresholds
    int redThreshold = 50;
    int blueThreshold = 50;
    int redMinReading = 255;
    int blueMinReading = 255;
    int greenMinReading = 255;

    DistanceSensor frontDistance;
    DistanceSensor leftDistance;
    DistanceSensor backDistance;
    DistanceSensor rightDistance;


    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("left_motor");
        leftMotor.setDirection(DCMotor.Direction.REVERSE);
        leftMotor.setMode(DCMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DCMotor.RunMode.RUN_USING_ENCODER);

        rightMotor = hardwareMap.dcMotor.get("right_motor");
        rightMotor.setMode(DCMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DCMotor.RunMode.RUN_USING_ENCODER);

        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        gyro = hardwareMap.gyroSensor.get("gyro_sensor");

        frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        leftDistance = hardwareMap.get(DistanceSensor.class, "left_distance");
        backDistance = hardwareMap.get(DistanceSensor.class, "back_distance");
        rightDistance = hardwareMap.get(DistanceSensor.class, "right_distance");


        gyro.init();

        waitForStart();

        while(opModeIsActive()) {

            while (leftMotor.getCurrentPosition() < 1000) {
                forward(1);
                telemetryStatus();
            }

            stopMotors();
            sleep(3000);

            while (leftMotor.getCurrentPosition() < 1500) {
                turnRight(1);
                telemetryStatus();
            }

            stopMotors();
            sleep(3000);


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
        telemetry.addData("Elapsed Time", runTime.seconds());
        telemetry.addData("Gyro Heading: ", gyro.getHeading());
        telemetry.addData("Color","R %d  G %d  B %d", colorSensor.red(), colorSensor.green(), colorSensor.blue());
        telemetry.addData("RED MIN: ", redMinReading);
        telemetry.addData("BLUE MIN: ", blueMinReading);
        telemetry.addData("GREEN MIN: ", greenMinReading);
  //      telemetry.addData("Distance", " Fr %.1f  Lt %.1f  Rt %.1f  Bk %.1f  ",
  //              frontDistance.getDistance(DistanceUnit.CM), leftDistance.getDistance(DistanceUnit.CM),
  //              rightDistance.getDistance(DistanceUnit.CM), backDistance.getDistance(DistanceUnit.CM)
  //     );

        telemetry.update();
    }

    public void sleep(double time) {
        while (runTime.milliseconds() < time) { }
    }
}
