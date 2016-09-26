package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestArm extends Command {
	int action;

    public TestArm(int i) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gripUtil);
    	action = i;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gripUtil.initGripCMD();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(action == 0){
    		Robot.gripUtil.testArmsOut();
    	}
    	if(action == 1){
    		Robot.gripUtil.testArmsBack();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
