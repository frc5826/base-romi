// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.text.MessageFormat;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RomiGyro;

public class RomiDrivetrain {
    private static final double SURFACE_FACTOR = .75;
    private static final double kCountsPerRevolution = 1440.0;
    private static final double kWheelDiameterInch = 2.75591; // 70 mm

    // The Romi has the left and right motors set to
    // PWM channels 0 and 1 respectively
    private final Spark m_leftMotor = new Spark(0);
    private final Spark m_rightMotor = new Spark(1);

    // The Romi has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private final Encoder m_leftEncoder = new Encoder(4, 5);
    private final Encoder m_rightEncoder = new Encoder(6, 7);

    private final RomiGyro gyro = new RomiGyro();

    // Set up the differential drive controller
    private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);

    /** Creates a new RomiDrivetrain. */
    public RomiDrivetrain() {
        // Use inches as unit for encoder distances
        m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / (kCountsPerRevolution * SURFACE_FACTOR));
        m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / (kCountsPerRevolution * SURFACE_FACTOR));
        resetEncoders();
        gyro.reset();
    }

    public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
        m_diffDrive.arcadeDrive(xaxisSpeed*-1, zaxisRotate);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_diffDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public double getLeftDistanceInch() {
        return m_leftEncoder.getDistance();
    }

    public double getRightDistanceInch() {
        return m_rightEncoder.getDistance();
    }

    public double getAngle() {
        return gyro.getAngleZ();
    }

    public void resetAngle() {
        gyro.reset();
    }

    public void printGyroOffsets() {
        System.out.println(MessageFormat.format("Gyro data \t time: {0} \t aX:{1} \t aY:{2} \t aZ:{3} \t rX: {4} \t rY: {5} \t rZ: {6}", Timer.getFPGATimestamp(), gyro.getAngleX()/Timer.getFPGATimestamp(),  gyro.getAngleY()/Timer.getFPGATimestamp(),  gyro.getAngleZ()/Timer.getFPGATimestamp(), gyro.getRateX(), gyro.getRateX(), gyro.getRateY(), gyro.getRateZ()));
    }
}