package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {
	double leftValue = 0.0, rightValue = 0.0, distance = 0.0;
	boolean done, direction;
	
    public Pivot(boolean direction, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.direction = direction;
    	this.distance = distance;
    	requires(Robot.driveUtil);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		getMotorSpeed();
    	Robot.driveUtil.drive(leftValue, rightValue);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(Robot.driveUtil.leftIsDone(this.distance, false) ||
           Robot.driveUtil.rightIsDone(this.distance, false)){
    		done = true;
    		Robot.driveUtil.drive(0.0, 0.0);
    	}else{
    		done = false;
    	}
    	
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void getMotorSpeed(){
        //double adjust;

        leftValue = RobotMap.auto_LeftPivot;
        rightValue = RobotMap.auto_RightPivot;
        
        /*
        adjust = Robot.driveUtil.coerce2Range(Robot.driveUtil.getEncoderDelta());
        
    		rightValue = rightValue + adjust;
    		leftValue = leftValue + -adjust;        	        	
    	*/
        /*
        //Reduce Motor Speed if within Window 1
        if(Robot.driveUtil.leftInWindow1(this.distance, false) ||
           Robot.driveUtil.rightInWindow1(this.distance, false)){
        	rightValue = (rightValue - (rightValue * RobotMap.Window_P_Speed_Pct));
        	leftValue = (leftValue - (leftValue * RobotMap.Window_P_Speed_Pct));
        //Reduce Motor Speed MORE  if within Window 2
        	if(Robot.driveUtil.leftInWindow2(this.distance, false) ||
        	   Robot.driveUtil.rightInWindow2(this.distance, false)){
            	rightValue = (rightValue - (rightValue * RobotMap.Window_P_Speed_Pct));
            	leftValue = (leftValue - (leftValue * RobotMap.Window_P_Speed_Pct));        		
        	}
        }
        */
    	//Invert Left or Right Motor input to perform proper rotation direction
    	if(!this.direction){
        	//Counter Clockwise
            leftValue = -leftValue;
        }else{
        	//Clockwise;
            rightValue = -(rightValue + (rightValue * RobotMap.Speed_Adjust));
            leftValue = (leftValue + (leftValue * RobotMap.Speed_Adjust));
        }
        
    }
    
}
