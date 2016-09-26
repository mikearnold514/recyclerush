package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateLift extends Command {
	int operate;
	double startPos;
	boolean auto;
    public OperateLift(boolean auto, int i) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorUtil);
    	this.operate = i;
    	this.auto = auto;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorUtil.initLiftCMD();
    	startPos = Robot.elevatorUtil.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(operate){
    	case RobotMap.kLiftScroll:
    		Robot.elevatorUtil.scroll();
    		break;
    	case RobotMap.kLiftHome:
    		Robot.elevatorUtil.goHome();
    		break;
    	case RobotMap.kLiftCUp:
    		Robot.elevatorUtil.liftIt(auto, RobotMap.liftCan);
    		break;
    	case RobotMap.kLiftCDown:
    		//Robot.elevatorUtil.dropIt(RobotMap.DropCan);
    		break;
    	case RobotMap.kLiftUp:
    		Robot.elevatorUtil.liftIt(auto, RobotMap.liftOneClear);
    		break;
    	case RobotMap.kLiftDown:
    		Robot.elevatorUtil.dropIt(RobotMap.liftOneRelease);
    		break;
    		default:
    			Robot.elevatorUtil.goHome();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevatorUtil.isDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
