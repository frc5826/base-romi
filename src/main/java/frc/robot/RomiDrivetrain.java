// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class RomiDrivetrain
{
    private static final double COUNTS_PER_REVOLUTION = 1440.0;
    private static final double WHEEL_DIAMETER_INCH = 2.75591; // 70 mm

    // The Romi has the left and right motors set to
    // PWM channels 0 and 1 respectively
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);

    // The Romi has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder leftEncoder = new Encoder(4, 5);
    private final Encoder rightEncoder = new Encoder(6, 7);

    // Set up the differential drive controller
    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotor, rightMotor);


    /** Creates a new RomiDrivetrain. */
    public RomiDrivetrain()
    {
        // Use inches as unit for encoder distances
        leftEncoder.setDistancePerPulse((Math.PI * WHEEL_DIAMETER_INCH) / COUNTS_PER_REVOLUTION);
        rightEncoder.setDistancePerPulse((Math.PI * WHEEL_DIAMETER_INCH) / COUNTS_PER_REVOLUTION);
        resetEncoders();
    }


    public void arcadeDrive(double xaxisSpeed, double zaxisRotate)
    {
        diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
    }

    public void drive(double xaxisSpeed, double zaxisRotate){
        arcadeDrive(xaxisSpeed, zaxisRotate);
    }

    public void resetEncoders()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }


    public double getLeftDistanceInch()
    {
        return leftEncoder.getDistance();
    }


    public double getRightDistanceInch()
    {
        return rightEncoder.getDistance();
    }
}
