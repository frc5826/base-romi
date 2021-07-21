// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{

    private final Joystick joystick = new Joystick(0);
    private final RomiDrivetrain drivetrain = new RomiDrivetrain();

    @Override
    public void autonomousInit(){
        drivetrain.resetEncoders();
        drivetrain.resetAngle();
    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic()
    {
        //The number of inches the right wheel has moved
        double right = drivetrain.getRightDistanceInch();

        //The number of inches the left wheel has moved
        double left = drivetrain.getLeftDistanceInch();

        //The angle (z-axis) our robot has rotated
        double angle = drivetrain.getAngle();

        //How to drive the robot
        //drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

    }
}
