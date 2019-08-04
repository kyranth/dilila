package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Basic: Linear Arm Controller", group="Linear Opmode")
public class ArmController extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor Arm1 = null;
    private DcMotor Arm2 = null;
    private DcMotor Arm3 = null;

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

        this.Arm1 = hardwareMap.get(DcMotor.class, "Arm1");
        this.Arm1.setDirection(DcMotor.Direction.FORWARD);
        this.Arm2 = hardwareMap.get(DcMotor.class, "Arm2");
        this.Arm2.setDirection(DcMotor.Direction.REVERSE);

        this.Arm3 = hardwareMap.get(DcMotor.class, "Arm3");
        this.Arm3.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double armPower;
            double arm3Power;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double power = -gamepad1.left_stick_y / 2;
            double power2 = -gamepad1.right_stick_y / 2;
            armPower    = Range.clip(power, -0.5, 0.5);
            arm3Power    = Range.clip(power2, -0.5, 0.5);

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels

            this.Arm1.setPower(armPower);
            this.Arm2.setPower(armPower);
            this.Arm3.setPower(arm3Power);

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
