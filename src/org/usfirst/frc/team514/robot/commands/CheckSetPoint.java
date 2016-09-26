package org.usfirst.frc.team514.robot.commands;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckSetPoint extends Command {
	boolean done, overshot, direction, action, once;
	double distance, leftValue, rightValue;
	
    public CheckSetPoint(boolean direction, boolean action, double SetPoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveUtil);
    	this.distance = SetPoint;
    	this.action = action;
    	this.direction = direction;
    	done = false;
    	overshot = false;
    	once = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setOvershot();
    	if(overshot){
    		if(once){
    			//do this only once
    			this.direction = !this.direction;
    			done = false;
    			once = false;
    		}
        	if(this.action){
        		//Do Straight Logic
        		getMotorSpeedStraight();
        		
        	}else{
        		//Do Pivot Logic
        		getMotorSpeedPivot();
        	}
    	}else{
    		leftValue = 0.0;
    		rightValue = 0.0;
    		done = true;
    	}
		Robot.driveUtil.drive(leftValue, rightValue);      
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
  	        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void setOvershot(){
    	
    	if(Robot.driveUtil.leftIsDone(this.distance, this.action) ||
           Robot.driveUtil.rightIsDone(this.distance, this.action)){
    			overshot = false;
    		}else{
	    		overshot = true;
    	    }    		
    }
    
    public void getMotorSpeedStraight(){
        double adjust;

        leftValue = RobotMap.auto_LeftMotor;
        rightValue = RobotMap.auto_RightMotor;

        /*
        if(this.direction){
            adjust = Robot.driveUtil.coerce2Range(Robot.driveUtil.getEncoderDelta());        	
        }else{
        	adjust = 0.0;
        }
        */
        
        adjust = Robot.driveUtil.coerce2Range(Robot.driveUtil.getEncoderDelta());
        
        if(!this.direction){
    		rightValue = rightValue + -adjust;
    		leftValue = leftValue + adjust;        	        	        	
        }else{
    		rightValue = rightValue + adjust;
    		leftValue = leftValue + -adjust;        	        	        	
        }
        /*
        //Reduce Motor Speed if within Window 1
            if(Robot.driveUtil.leftInWindow1(this.distance, true) ||
               Robot.driveUtil.rightInWindow1(this.distance, true)){
            	rightValue = (rightValue - (rightValue * RobotMap.Window_S_Speed_Pct));
            	leftValue = (leftValue - (leftValue * RobotMap.Window_S_Speed_Pct));
        //Reduce Motor Speed MORE if within Window 2
            	if(Robot.driveUtil.leftInWindow2(this.distance, true) ||
            	   Robot.driveUtil.rightInWindow2(this.distance, true)){
                	rightValue = (rightValue - (rightValue * RobotMap.Window_S_Speed_Pct));
                	leftValue = (leftValue - (leftValue * RobotMap.Window_S_Speed_Pct));        		
            	}
            }
    	*/	
    	if(!this.direction){
        	//reverse
            rightValue = -(rightValue + (rightValue * RobotMap.Speed_Adjust));
            leftValue = -(leftValue - (leftValue * RobotMap.Speed_Adjust));
    		//rightValue = -rightValue;
    		//leftValue = -leftValue;
        }        
    }

    
    public void getMotorSpeedPivot(){
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
