package org.usfirst.frc.team58.robot.commands;

import org.usfirst.frc.team58.robot.Dashboard;
import org.usfirst.frc.team58.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftSwitch extends CommandGroup {
	public LeftSwitch() {
		if (Robot.gameData != null) {
		if(Robot.gameData.charAt(0) == 'L') {
			addSequential(new PIDdrive(3,3,0,168));
			addSequential(new ResetWait(500)); // Wait 1000ms
			addSequential(new PIDRotate(0.03,0.006,0.07,90));
			//addSequential(new PIDElevate(Dashboard.SwitchHeight));
			addSequential(new PIDdrive(3,0,0,12));
			addSequential(new SpitCube());
		}
		if(Robot.gameData.charAt(0) == 'R') {
			addSequential(new PIDdrive(3,0,0,192));
			addSequential(new ResetWait(500)); // Wait 1000ms
			addSequential(new PIDRotate(0.03,0.006,0.07,-90));
			addSequential(new ResetWait(500));
		}
	}
	}
}
