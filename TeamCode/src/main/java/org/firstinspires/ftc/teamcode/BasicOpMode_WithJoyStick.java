package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode With JoyStick", group="Linear Opmode")
//@Disabled
public class BasicOpMode_WithJoyStick extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    /*
        private DcMotor leftDrive = null;
        private DcMotor rightDrive = null;
    */

    private DcMotor leftDrive2 = null;
    private DcMotor rightDrive2 = null;

    private DcMotor []leftDrive;
    private DcMotor []rightDrive;

    private int driveMotorAmount = 2;

    //ColorSensor color_sensor;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).


        /*leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");*/

        this.leftDrive = new DcMotor[10];
        this.rightDrive = new DcMotor[10];

        //Init Color Sensor
        /*color_sensor = hardwareMap.colorSensor.get("color");
        color_sensor.enableLed(true);*/

        InitMotorLoop:
        for(int n = 0; n < driveMotorAmount; n++){
            try{
                leftDrive[n] = hardwareMap.get(DcMotor.class, "left_driver_" + n);
                leftDrive[n].setDirection(DcMotor.Direction.FORWARD);


                rightDrive[n] = hardwareMap.get(DcMotor.class, "right_driver_" + n);
                rightDrive[n].setDirection(DcMotor.Direction.REVERSE);
            }catch(Exception e){
                telemetry.addData("Status", "Init Motor Error");
                telemetry.update();

                break InitMotorLoop;
            }
        }

        telemetry.addData("driveMotorAmount", leftDrive.length);
        telemetry.update();

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels

            for(int n = 0; n < driveMotorAmount; n++){
                leftDrive[n].setPower(leftPower);
                rightDrive[n].setPower(rightPower);
            }

            /*leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);*/

            // Show the elapsed game time and wheel power.
            /*telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addLine()
                    .addData("a", "%s", color_sensor.alpha())
                    .addData("r", "%s", color_sensor.red())
                    .addData("g", "%s", color_sensor.green())
                    .addData("b", "%s", color_sensor.blue());
            telemetry.update();*/
        }
    }
}
