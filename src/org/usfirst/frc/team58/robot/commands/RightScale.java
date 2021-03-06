package org.usfirst.frc.team58.robot.commands;

import org.usfirst.frc.team58.robot.Dashboard;
import org.usfirst.frc.team58.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;

public class RightScale extends CommandGroup {
	public RightScale() {
		addSequential(new PIDdrive(221, 0.9));
		addSequential(new ResetWait(250));
		addSequential(new PIDRotate(0.03,0.006,0.07, -20));
		addSequential(new GoToScale(1400));
		addSequential(new PIDElevate(0, 1500));
		addSequential(new PIDRotate(0.03,0.006,0.07, -50));
		addSequential(new ResetWait(250));
		/*addSequential(new PIDdrive(56, 0.75));
		addSequential(new PullCube()); 
		//addParallel(new ForwardNSpit());*/
		
	}
}
