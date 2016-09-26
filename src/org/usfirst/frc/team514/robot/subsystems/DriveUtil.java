package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.DriveArcade;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveUtil extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SpeedController leftDrive, rightDrive;
    RobotDrive drive;
    Encoder leftDriveEncoder, rightDriveEncoder;

    //double output = 0.0; 
    double degrees = 0.0, input = 0.0, speed = 0.0;
    //double adjLeft = 0.0, d = 0.0, 
    double delta = 0.0;
    double leftSpeed = 0.0, rightSpeed = 0.0;
    //int state = 0;
    
    public DriveUtil() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	leftDrive = new Talon(RobotMap.LeftTalon);
    	rightDrive = new Talon(RobotMap.RightTalon);
    	drive = new RobotDrive(leftDrive, rightDrive);
    	leftDriveEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2, true, CounterBase.EncodingType.k4X);
    	rightDriveEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2, true, CounterBase.EncodingType.k4X);

    	drive.setInvertedMotor(MotorType.kRearLeft, true);
    	drive.setInvertedMotor(MotorType.kRearRight, true);
    	
    }
    public void drive(double left, double right){
    	this.leftSpeed = left;
    	this.rightSpeed = right;
    	drive.tankDrive(left, right);
    }
    
    public void driveArcade(Joystick right){
    	drive.arcadeDrive(right);
    	this.leftSpeed = right.getX();
    	this.rightSpeed = right.getY();
    }
    
    public void driveTank(Joystick left, Joystick right){
    	drive.tankDrive(left, right);
    	this.leftSpeed = left.getX();
    	this.rightSpeed = right.getX();
    }

    public double getLeftEncoder(){
    	return leftDriveEncoder.getDistance();
    }
    
    public double getRightEncoder(){
    	return rightDriveEncoder.getDistance();
    }
    
    public void resetEncoders(){
    	leftDriveEncoder.reset();
    	rightDriveEncoder.reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveArcade());
    }
    public boolean leftInWindow1(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_S_Window1)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_S_Window1))){
        	    	  done = true;
        	 }else{
        	    	  done = false;
        	 }    	
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_P_Window1)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_P_Window1))){
        	    	  done = true;
        	}else{
        	    	  done = false;
        	}    	
    	}
    	return done;
    }
    
    public boolean rightInWindow1(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_S_Window1)) &&
            	(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_S_Window1))){
      	    	   		done = true;
       	    }else{
      	    	   		done = false;
       	    }    	    		
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_P_Window1)) &&
        	    (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_P_Window1))){
  	    	    		done = true;
   	    	}else{
  	    	    		done = false;
   	    	}    	
    		
    	}
    	return done;
    }
    
    public boolean leftInWindow2(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_S_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_S_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_P_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_P_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    		
    	}
    	return done;
    }
    
    public boolean rightInWindow2(double distance, boolean action){
    	boolean done;
    	if(action){
    		//Straight
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_S_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_S_Window2))){
        	   		done = true;
        	}else{
        	    	done = false;
        	}    		
    	}else{
    		//Pivot
        	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_P_Window2)) &&
        	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_P_Window2))){
        	   		done = true;
        	}else{
        	   		done = false;
        	}
    	}
    	return done;
    }

    public boolean leftIsDone(double distance, boolean action){
    	boolean done;
    	if((Math.abs(Robot.driveUtil.getLeftEncoder()) <= (distance + RobotMap.ENCODER_CONSTANT)) &&
    	   (Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance - RobotMap.ENCODER_CONSTANT))){
    		done = true;
    	}else if(action){ 
    		//Straight
    		if(Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance + RobotMap.ENCODER_S_Window2)){
    			done = true;
    		}else{
    			done = false;
    		}
    	}else{
    		//Pivot
    		if(Math.abs(Robot.driveUtil.getLeftEncoder()) >= (distance + RobotMap.ENCODER_P_Window2)){
    			done = true;
    		}else{
    			done = false;
    		}
    	}
    	return done;
    }
    
    public boolean rightIsDone(double distance, boolean action){
    	boolean done;
    	if((Math.abs(Robot.driveUtil.getRightEncoder()) <= (distance + RobotMap.ENCODER_CONSTANT)) &&
    	   (Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance - RobotMap.ENCODER_CONSTANT))){
   	    		done = true;
    	}else if(action){ 
    		//Straight
    		if(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance + RobotMap.ENCODER_S_Window2)){
   	    			done = true;
    		}else{
   	    			done = false;
    		}
    	}else{
    		//Pivot
    		if(Math.abs(Robot.driveUtil.getRightEncoder()) >= (distance + RobotMap.ENCODER_P_Window2)){
   	    			done = true;
    		}else{
   	    			done = false;
    		}
    	}
    	return done;
    }

    public double coerce2Range(double input){
        
        double inputMin, inputMax, inputCenter;
        double outputMin, outputMax, outputCenter;
        double scale, result;
        //double output;
        
        inputMin = RobotMap.C2R_inputMin; 
        inputMax = RobotMap.C2R_inputMax;     
        
        outputMin = RobotMap.C2R_outputMin;
        outputMax = RobotMap.C2R_outputMax;
        
        //14 Encode ticks per inch...
        this.input = input;
                
            /* Determine the center of the input range and output range */
            inputCenter = Math.abs(inputMax - inputMin) / 2 + inputMin;
            outputCenter = Math.abs(outputMax - outputMin) / 2 + outputMin;

            /* Scale the input range to the output range */
            scale = (outputMax - outputMin) / (inputMax - inputMin);

            /* Apply the transformation */
            result = (input + -inputCenter) * scale + outputCenter;

            /* Constrain to the output range */
            speed = Math.max(Math.min(result, outputMax), outputMin);

       return speed;
       
    }
    
    public double getEncoderDelta(){
    	delta = (Robot.driveUtil.getRightEncoder() - Robot.driveUtil.getLeftEncoder());
    	return delta;
    }
        
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Left Encoder = ", leftDriveEncoder.getDistance());
    	SmartDashboard.putNumber("Right Encoder = ", rightDriveEncoder.getDistance());
    	//SmartDashboard.putNumber("PID Output = ", this.output);
    	//SmartDashboard.putNumber("Auto Mode = ", state);
    	SmartDashboard.putNumber("Speed Adjust = ", speed);
    	SmartDashboard.putNumber("Left Speed = ", leftSpeed);
    	SmartDashboard.putNumber("Right Speed = ", rightSpeed);
    	//SmartDashboard.putNumber("% Delta = ", d);
    	//SmartDashboard.putNumber("Adjusted Left Encoder = ", adjLeft);
    	SmartDashboard.putNumber("Encoder Delta = ", delta);
    }
}



