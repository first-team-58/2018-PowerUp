/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team58.robot.subsystems.*;
import org.usfirst.frc.team58.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrain m_DriveTrain
			= new DriveTrain();
	public static final Elevator m_Elevator
			= new Elevator();
	public static final Grabber m_Grabber
			= new Grabber();
	public static final Climber m_Climber
			= new Climber();
	public static final Lifter m_Lifter
			= new Lifter();
	public static OI m_oi;
	public static String gameData = null;


	Command autoCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();

		// Start the SmartDashboard. - Tyler 01/13/18
		Dashboard.initDashboard();
		
		// This line creates a USB camera (integer 0) and starts streaming to the SmartDashboard.
		// We can create more with different integers as needed. - Tyler 01/13/18
		UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(0);
		UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(1);
		//cam1.setResolution(320, 240);
		//cam2.setResolution(320, 240);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.m_DriveTrain.zeroEncoders();
		
		// Get the game data string. (Ex: "LRL") - Tyler 01/10/18
		gameData = DriverStation.getInstance().getGameSpecificMessage(); 
		SmartDashboard.putString("Game Data", gameData);
		
		// Set the autonomous command to the one selected in the dashboard. - Tyler 01/10/18
		autoCommand = Dashboard.getAutoProgram();
		
		// If an autonomous program is selected (not null), start the selected program. - Tyler 01/10/18
		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if (m_DriveTrain.isNavxCal()) {
			Scheduler.getInstance().run();
		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		if (autoCommand != null) {
			autoCommand.cancel();
		}
		
		m_Elevator.returnToVariableControl();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	
	public void testPeriodic() {
			//LiveWindow.run(); This was in last year's code, but throws a weird warning when included. - Tyler 01/10/18
	}
	
}
