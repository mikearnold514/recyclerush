package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSet extends CommandGroup {
    
    public  AutoSet() {
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
    	
    	
    	
    	//Can and Tote
    	
    	//Drop arms
    	addParallel(new OperateGrip(RobotMap.armOut));
    	//Drive forward 2 ft.
    	addSequential(new Straight(true, RobotMap.driveTwoFeet));
    	//Grip can
    	addParallel(new OperateGrip(RobotMap.kGripCan));
    	//Lift 5-10 in.
    	addParallel(new OperateLift(true, RobotMap.kLiftCUp));
    	
    	//Turn 45 degrees counter-clockwise
    	addSequential(new Pivot(false, RobotMap.ENCODER_45));
    	//Drive forward 2 ft.
    	addSequential(new Straight(true, RobotMap.driveTwoFeet));
    	//Lower Lift
    	addParallel(new OperateLift(true, RobotMap.kLiftHome));
    	//Drop Can on Tote
    	addSequential(new OperateGrip(RobotMap.kGripHome));
    	//Grip Tote
    	addSequential(new OperateGrip(RobotMap.kGripTote));
    	//Lift 5-10 in.
    	addParallel(new OperateLift(true, RobotMap.kLiftUp));
    	//Turn 180 degrees
    	addSequential(new Pivot(false, RobotMap.ENCODER_180));    	
    	//Back up to Auto Zone
    	addSequential(new Straight(true, RobotMap.driveIntoAutoZone));
    	//Lower Lift
    	addParallel(new OperateLift(true, RobotMap.kLiftHome));
    	//Drop
    	addSequential(new OperateGrip(RobotMap.kGripHome));
    	
    }
}
