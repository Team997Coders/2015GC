package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleContainer extends CommandGroup {
    
    public  SingleContainer() {
    	 
         addSequential(new zeroElevator());
         addSequential(new SetElevatorPosition(), .5);
         addSequential(new RotateCounterClockwise(90, true));
         addSequential(new Wait(), 1);
         addSequential(new DriveStraight(), 4);
         addSequential(new zeroElevator());
         addSequential(new SetGatherSolenoid(true));
         addSequential(new GatherOut(),1);
        
    }
}
