package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoStack extends CommandGroup {
    
    public  AutoStack() {
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
    	
    	
    	//Unlikely Situation Where We Do a Stack
    	
    	
    	//Lower arms
    	addSequential(new OperateGrip(RobotMap.kArmOut));
    	//drive forward (2 feet)
    	addSequential(new Straight(true, RobotMap.driveTwoFeet));
    	//close arms
    	addSequential(new OperateGrip(RobotMap.kGripTote));
    	//Raise arms
    	addSequential(new OperateLift(true, RobotMap.liftStackTote));
    	//wait
    	addSequential(new WaitCommand(1.0));
    	//turn clockwise 45 degrees
    	addSequential(new Pivot(true, RobotMap.ENCODER_45));
    	//drive forward
    	addSequential(new Straight(true, RobotMap.driveToNextTote));
    	//open arms
    	addSequential(new OperateGrip(RobotMap.kGripHome));
    	//lower arms
    	addSequential(new OperateLift(true, RobotMap.kLiftHome));
    	//close arms
    	addSequential(new OperateGrip(RobotMap.kGripCan));
    	//raise arms
    	addSequential(new OperateLift(true, RobotMap.liftStackTote));
    	//drive forward
    	addSequential(new Straight(true, RobotMap.driveToNextTote));
    	//open arms
    	addSequential(new OperateGrip(RobotMap.kGripHome));
    	//lower arms
    	addSequential(new OperateLift(true, RobotMap.kLiftHome));
    	//close arms
    	addSequential(new OperateGrip(RobotMap.kGripCan));
    	//raise arms
    	addSequential(new OperateLift(true, RobotMap.liftStackTote));
    	//turn clockwise 90 degrees
    	addSequential(new Pivot(true,RobotMap.ENCODER_90));
    	//Drive forward to autozone
    	addSequential(new Straight(true, RobotMap.driveIntoAutoZone));
    	//lower arms
    	addSequential(new OperateLift(true, RobotMap.kLiftHome));
    	//open arms
    	addSequential(new OperateGrip(RobotMap.kGripHome));
    	//back up 5"
    	addSequential(new Straight(false, RobotMap.driveFiveInches));
    }
}
