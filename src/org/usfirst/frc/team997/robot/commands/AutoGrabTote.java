package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGrabTote extends CommandGroup {
	public AutoGrabTote() {
	addSequential(new SetGatherSolenoid(true));
    addSequential(new GatherIn(), .25);
    addSequential(new zeroElevator());
    addSequential(new SetElevatorPosition(),.5);
    addSequential(new SetGatherSolenoid(false));
	}
}
