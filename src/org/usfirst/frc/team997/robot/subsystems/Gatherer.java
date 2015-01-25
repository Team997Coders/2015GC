package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *this is the gatherer. it spins to intake totes and spins in reverse to spit them back out
 */
public class Gatherer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static Talon gatherMotor = new Talon(RobotMap.gatherer);
	
	public Gatherer(){
		
		
		
	}

	// i think Robot is yelling at me for not having a default, so i'm making it default to ExampleCommand
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //gathers in. the '1' sets the motor to full speed. may change later
    public static void in(){
    	
    	gatherMotor.set(1);
    }
    
    //spits out. the '-1' sets the motor to full reverse. may change later.
    public static void out(){
    	
    	gatherMotor.set(-1);
    	
    }
}

