package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoToteAuto extends CommandGroup {

    public TwoToteAuto() {
    	addSequential(new SetGatherSolenoid(false));
        addSequential(new GatherIn(), 1);
        addSequential(new zeroElevator());
        addSequential(new SetElevatorPosition(), .5);
        addSequential(new SetGatherSolenoid(true));
        addSequential(new RotateClockwise(180, true));
        addSequential(new Wait(), 1);
        addSequential(new SetGatherSolenoid(false));
        addSequential(new GatherIn(), 1);
        addSequential(new zeroElevator());
        addSequential(new SetElevatorPosition(), .5);
        addSequential(new RotateClockwise(180, false));
        addSequential(new DriveStraight());
    }
}
