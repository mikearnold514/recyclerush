package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateGrip extends Command {
	int GripAction;
    public OperateGrip(int i) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gripUtil);
    	GripAction = i;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gripUtil.initGripCMD();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (GripAction){
    	case RobotMap.kGripScroll:   
    		Robot.gripUtil.scroll();
    		break;
    	case RobotMap.kGripHome:   
    		Robot.gripUtil.goHome();
    		break;
    	case RobotMap.kGripTote:   
    		Robot.gripUtil.gripTote();
    		break;
    	case RobotMap.kGripCan:   
    		Robot.gripUtil.gripCan();
    		break;
    	case RobotMap.kArmOut: 
    		Robot.gripUtil.armsOut();
    		break;
    	case RobotMap.kArmBack:
    		Robot.gripUtil.armsBack();
    		
    		break;
    	default:    
    		Robot.gripUtil.goHome();
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gripUtil.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.setRumble(RobotMap.kRumbleOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
