package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.OperateLift;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorUtil extends Subsystem {
	SpeedController leftLiftMotor, rightLiftMotor;
	Encoder liftEncoder;
	DigitalInput homeswitch;
	double motorspeed, spValue, prevEncoder;
	boolean done;
	int spIndex, direction, counter;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorUtil(){
		leftLiftMotor = new Victor(RobotMap.leftLiftMotor);
		rightLiftMotor = new Victor(RobotMap.RightLiftMotor);
		liftEncoder = new Encoder(RobotMap.liftEncoder1, RobotMap.liftEncoder2, false, CounterBase.EncodingType.k4X);
		homeswitch = new DigitalInput(RobotMap.liftSwitch);
		motorspeed = 0.0;
		done = false; 
		spIndex = 0;
		spValue = 0.0;
		prevEncoder = 0.0;
		counter = 0;
	}
	
	public void initLiftCMD(){
		setDone(false);
	}
	
	private void setDone(boolean b){
		this.done = b;
	}
	
	public boolean isDone(){
		return this.done;
	}
	
	public void operateLift() {
		//Test method, only use for calibration testing!!!
		getScaleSpeed();
		driveMotors(this.motorspeed);
	}
	
	public void resetEncoder(){
		liftEncoder.reset();
	}
	
	private void getScaleSpeed(){
		this.motorspeed = 0.0;
		double d = -Robot.oi.getLeftY();
		if(Math.abs(d) > 0.25){
			if(d > 0.0){
				this.motorspeed = d - 0.25;
			}else{
				this.motorspeed = d + 0.25;
			}
		}	
	}
	
	private void driveMotors(double d){
		leftLiftMotor.set(d);
		rightLiftMotor.set(d);
	}
	
	public void scroll(){
		getScaleSpeed();
		
		if(homeswitch.get()){
			resetEncoder();
			if(this.motorspeed > 0.0){
				driveMotors(this.motorspeed);				
			}else{
				driveMotors(0.0);
				Robot.oi.setRumble(RobotMap.kRumbleOn);
				setDone(true);
			}
		}else if(liftEncoder.getDistance() > RobotMap.liftMax){
			if(this.motorspeed < 0.0){
				driveMotors(this.motorspeed);
			}else{
				driveMotors(0.0);
				Robot.oi.setRumble(RobotMap.kRumbleOn);
				setDone(true);				
			}
		}else{
			driveMotors(this.motorspeed);			
		}
	}
	
	public void goHome(){
		if(homeswitch.get()){
			driveMotors(0.0);
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
			liftEncoder.reset();
		}else{
			driveMotors(-RobotMap.liftSpeed);
		}
	}
	
	public void moveLift(int i){
		/*
		setParameters(i);
		driveLift();
		*/
	}
	
	@SuppressWarnings("unused")
	private void driveLift(){
		double d  = liftEncoder.getDistance();
		if(d==spValue){
			//Stop Motor
			driveMotors(0.0);
			setDone(true);
		}else if(d<spValue){
			driveMotors(RobotMap.liftSpeed);
		}else{
			driveMotors(-RobotMap.liftSpeed);
		}
	}
	/*
	private void setParameters(int i){
		double d = liftEncoder.getDistance();
		if(i>0){
			if(d > RobotMap.liftSixth){
				spValue = RobotMap.liftSixth;
				spIndex = 6;
				//Stop Motors
			}else if(d > RobotMap.liftFifth){
				spValue = RobotMap.liftSixth;
				spIndex = 5;
			}else if(d > RobotMap.liftFourth){
				spValue = RobotMap.liftFifth;
				spIndex = 4;
			}else if(d > RobotMap.liftThird){
				spValue = RobotMap.liftFourth;
				spIndex = 3;
			}else if(d > RobotMap.liftSecond){
				spValue = RobotMap.liftThird;
				spIndex = 2;
			}else if(d > RobotMap.liftFirst){
				spValue = RobotMap.liftSecond;
				spIndex = 1;
			}else{
				spValue = RobotMap.liftFirst;
				spIndex = 0;
			}
		}else{
		if(d > RobotMap.liftSixth){
			spValue = RobotMap.liftSixth;
			spIndex = 5;
			//Stop Motors
		}else if(d > RobotMap.liftFifth){
			spValue = RobotMap.liftFifth;
			spIndex = 4;
		}else if(d > RobotMap.liftFourth){
			spValue = RobotMap.liftFourth;
			spIndex = 3;
		}else if(d > RobotMap.liftThird){
			spValue = RobotMap.liftThird;
			spIndex = 2;
		}else if(d > RobotMap.liftSecond){
			spValue = RobotMap.liftSecond;
			spIndex = 1;
		}else if(d > RobotMap.liftFirst){
			spValue = RobotMap.liftFirst;
			spIndex = 0;
		}
		}
	}
	*/
	public void liftIt(boolean auto, double d){
		if(liftEncoder.getDistance() <= d){
			driveMotors(RobotMap.liftSpeed);
		}else{
			driveMotors(0.0);
			setDone(true);
		}
		if(auto){
			counter++;
			if(counter >= 5){
				if(liftEncoder.getDistance() != prevEncoder){
					prevEncoder = liftEncoder.getDistance();
					counter = 0;
				}else{
					driveMotors(0.0);
					setDone(true);
					counter = 0;
				}
			}
		}
	}
	public void dropIt(double d){
		if(liftEncoder.getDistance() >= d){
			driveMotors(-RobotMap.liftSpeed);
		}else{
			driveMotors(0.0);
			setDone(true);
		}
	}
	public double getPosition(){
		return liftEncoder.getDistance();
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("lift Encoder : ", liftEncoder.getDistance());
		SmartDashboard.putBoolean("Lift Home", homeswitch.get());
		SmartDashboard.putNumber("Lift Setpoint Index = ", spIndex);
		SmartDashboard.putNumber("Lift Speed = ", this.motorspeed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperateLift(false, RobotMap.kLiftScroll));
    }
}

