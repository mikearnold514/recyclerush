package org.usfirst.frc.team514.robot.subsystems;

import org.usfirst.frc.team514.robot.Robot;
import org.usfirst.frc.team514.robot.RobotMap;
import org.usfirst.frc.team514.robot.commands.OperateGrip;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GripUtil extends Subsystem {
	SpeedController gripmotor;
	//AnalogPotentiometer pot;
	Encoder gripencoder;
	DigitalInput homeswitch;
	DoubleSolenoid armcontrol;
	double motorspeed;
	
	boolean done;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GripUtil(){
		gripmotor = new Victor(RobotMap.gripMotor);
		//pot = new AnalogPotentiometer(RobotMap.pot);
		gripencoder = new Encoder(RobotMap.gripEncoder1, RobotMap.gripEncoder2, false, CounterBase.EncodingType.k4X);
		homeswitch = new DigitalInput(RobotMap.gripSwitch);
		done = false;
		armcontrol = new DoubleSolenoid(RobotMap.armOut, RobotMap.armBack);
		motorspeed = 0.0;
	}
	
	public void initGripCMD(){
		setDone(false);
	}
	
	private void setDone(boolean d){
		this.done = d;
	}
	
	public boolean isDone(){
		return this.done;
	}
	
	public void resetEncoder(){
		gripencoder.reset();
	}
	
	private void getScaleSpeed(){
		this.motorspeed = 0.0;
		double d = -Robot.oi.getRightX();
		if(Math.abs(d) > 0.25){
			if(d > 0.0){
				this.motorspeed = d - 0.25;
			}else{
				this.motorspeed = d + 0.25;
			}
		}	
	}
	
	public void scroll(){
		//TODO:  For Scroll function we want to set a deadband around the stick
		//The deadband should be between -0.25 and +0.25.  Then it should 
		//Graduate from 0.00 to a max of either -0.75 or +0.75
		
		//TODO:  We need a safety check to make sure if we exceed the Grab Tote setting on Scroll
		//Mode that we stop the drives!!
		getScaleSpeed();
		
		if(homeswitch.get()){
			gripencoder.reset();
			if(this.motorspeed > 0.0){
				gripmotor.set(this.motorspeed);
			}else{
				gripmotor.set(0.0);
				Robot.oi.setRumble(RobotMap.kRumbleOn);
				setDone(true);				
			}
		}else if(gripencoder.getDistance() > RobotMap.gripTote){
			if(this.motorspeed < 0.0){
				gripmotor.set(this.motorspeed);
			}else{
				gripmotor.set(0.0);
				Robot.oi.setRumble(RobotMap.kRumbleOn);
				setDone(true);								
			}
		}else{
			gripmotor.set(this.motorspeed);
		}
	}
	
	public void operateGrip(){
		//Test Method, only use for Calibration testing!!!
		getScaleSpeed();
		gripmotor.set(this.motorspeed);
	}
	
	public void goHome(){
		if(homeswitch.get()){
			gripmotor.set(0.0);
			gripencoder.reset();
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
			gripencoder.reset();
		}else{
			gripmotor.set(-RobotMap.gripSpeed);
		}
		
	}
	
	public void gripCan(){
		if(gripencoder.getDistance() < RobotMap.gripCan - 50.0){
			gripmotor.set(RobotMap.gripSpeed);
		}else if(gripencoder.getDistance() > RobotMap.gripCan + 50.0){
			gripmotor.set(-RobotMap.gripSpeed);
		}else{	
			gripmotor.set(0.0);
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
		}
	}

	public void gripTote(){
		if(gripencoder.getDistance() < RobotMap.gripTote){
			gripmotor.set(RobotMap.gripSpeed);
		}else{
			gripmotor.set(0.0);
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
		}
	}
	
	public void armsOut(){
		if(gripencoder.getDistance() < RobotMap.gripCan){
			armcontrol.set(DoubleSolenoid.Value.kForward);
		}else{
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
		}
	}
	
	public void armsBack(){
		if(gripencoder.getDistance() < RobotMap.gripCan){
			armcontrol.set(DoubleSolenoid.Value.kReverse);
		}else{
			Robot.oi.setRumble(RobotMap.kRumbleOn);
			setDone(true);
		}
	}
	
	public void armsOff(){
		armcontrol.set(DoubleSolenoid.Value.kOff);
		this.done = true;
	}
	public void testArmsOut(){
		armcontrol.set(DoubleSolenoid.Value.kForward);
		this.done = true;
	}
	public void testArmsBack(){
		armcontrol.set(DoubleSolenoid.Value.kReverse);
		this.done = true;
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("Grip Speed = ", this.motorspeed);
		SmartDashboard.putNumber("Grip Encoder : ", gripencoder.getDistance());
		SmartDashboard.putBoolean("Grip Home", homeswitch.get());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperateGrip(RobotMap.kGripScroll));
    }    
}

