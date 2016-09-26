package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCan extends CommandGroup {
    
    public  AutoCan() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//Can Only (No Totes)
    	
    	
    	//Drop arms
    	addParallel(new OperateGrip(RobotMap.armOut));
    	//Lift arms 4 in. (changed to 5 inches)
    	//addSequential(new OperateLift(RobotMap.liftFiveInches));
    	//Drive forward 2 ft.
    	addSequential(new Straight(true, RobotMap.driveTwoFeet));
    	//Grip can
    	addParallel(new OperateGrip(RobotMap.kGripCan));
    	//Lift 5-10 in.
    	addSequential(new OperateLift(true, RobotMap.kLiftCUp));
    	//Back up to Auto Zone
    	addSequential(new Straight(false, RobotMap.driveIntoAutoZone));
    	//Lower Lift
    	//addParallel(new OperateLift(true, RobotMap.kLiftHome));
    	//Drop
    	//addSequential(new OperateGrip(RobotMap.kGripHome));
    	//Get outta the way! (drive 5 inches back)
    	//addSequential(new Straight(false, RobotMap.driveFiveInches));
    	
    }
}
