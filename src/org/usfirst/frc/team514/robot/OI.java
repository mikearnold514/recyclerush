package org.usfirst.frc.team514.robot;

import org.usfirst.frc.team514.robot.commands.AutoPivot;
import org.usfirst.frc.team514.robot.commands.DriveArcade;
import org.usfirst.frc.team514.robot.commands.DriveTank;
import org.usfirst.frc.team514.robot.commands.OperateGrip;
import org.usfirst.frc.team514.robot.commands.OperateLift;
import org.usfirst.frc.team514.robot.commands.ResetEncoders;
import org.usfirst.frc.team514.robot.commands.Straight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	Joystick left, right, controller;
	Button Tank, Arcade,
			armOut, armBack,
			GripHome, GripCan, GripTote,
			scrollLift, scrollGrip,
			liftHome, liftUp, liftDown, liftToggleUp, liftToggleDown;
	
	//Testing Buttons
	Button resetEncoders, 
	       testStraight, testReverse,
	       testPivot90, testPivot180,
	       testPivot90counter, testPivot180counter,
	       resetLift, resetGrip,
	       testGrip, testLift, testArmOut, testArmBack,
	       testLiftUp, testLiftDown;
	
	public OI(){
		left = new Joystick(1);
		right = new Joystick(2);
		controller = new Joystick(3);
		
		Tank = new JoystickButton(right, 2);
		Arcade = new JoystickButton(right, 3);
		//joystick buttons
		Tank.whenPressed(new DriveTank());
		Arcade.whenPressed(new DriveArcade());
		
		//controller buttons
		armOut = new JoystickButton(controller, RobotMap.kYButtonNum);
		armBack = new JoystickButton(controller, RobotMap.kAButtonNum);
		GripHome = new JoystickButton(controller, RobotMap.kRightBumperNum);
		GripTote = new JoystickButton(controller, RobotMap.kXButtonNum);
		GripCan = new JoystickButton(controller, RobotMap.kBButtonNum);
		scrollLift = new JoystickButton(controller, RobotMap.kLeftStickButtonNum);
		scrollGrip = new JoystickButton(controller, RobotMap.kRightStickButtonNum);
		liftHome = new JoystickButton(controller, RobotMap.kRightTriggerNum);
		liftUp = new JoystickButton(controller, RobotMap.kLeftBumperNum);
		liftDown = new JoystickButton(controller, RobotMap.kLeftTriggerNum);
		/*
		liftToggleUp = new JoystickButton(controller, RobotMap.kLeftBumperNum);
		liftToggleDown = new JoystickButton(controller, RobotMap.kLeftTriggerNum);
		*/
		armOut.whenPressed(new OperateGrip(RobotMap.kArmOut));
		armBack.whenPressed(new OperateGrip(RobotMap.kArmBack));
		GripHome.whenPressed(new OperateGrip(RobotMap.kGripHome));
		GripTote.whenPressed(new OperateGrip(RobotMap.kGripTote));
		GripCan.whenPressed(new OperateGrip(RobotMap.kGripCan));
		scrollGrip.whenPressed(new OperateGrip(RobotMap.kGripScroll));
		scrollLift.whenPressed(new OperateLift(false, RobotMap.kLiftScroll));
		liftHome.whenPressed(new OperateLift(false, RobotMap.kLiftHome));
		liftUp.whenPressed(new OperateLift(false, RobotMap.kLiftUp));
		liftDown.whenPressed(new OperateLift(false, RobotMap.kLiftDown));
		/*
		liftToggleUp.whenPressed(new OperateLift(RobotMap.kLiftTUp));
		liftToggleDown.whenPressed(new OperateLift(RobotMap.kLiftTDown));
		*/
		
		//TO DO - Testing Objects, remove when done.
		
		resetEncoders = new JoystickButton(left, 2);
		testStraight = new JoystickButton(left, 9);
		testReverse = new JoystickButton(left, 11);
		testPivot90 = new JoystickButton(left, 6);
		testPivot180 = new JoystickButton(left, 4);
		testPivot90counter = new JoystickButton(left, 5);
		testPivot180counter = new JoystickButton(left, 3);
		/*
		resetLift = new JoystickButton(controller, RobotMap.kBButtonNum);
		testGrip = new JoystickButton(controller, RobotMap.kRightStickButtonNum);
		testLift = new JoystickButton(controller, RobotMap.kLeftStickButtonNum);
		testArmOut = new JoystickButton(controller, RobotMap.kStartButtonNum);
		testArmBack = new JoystickButton(controller, RobotMap.kBackButtonNum);
		testLiftUp = new JoystickButton(left, 3);
		testLiftDown = new JoystickButton(left, 4);
		resetGrip = new JoystickButton(left, 5);
		*/
		resetEncoders.whileHeld(new ResetEncoders());		
		testStraight.whenPressed(new Straight(true, (RobotMap.ENCODER_DISTANCE)));
		testReverse.whenPressed(new Straight(false, (RobotMap.ENCODER_DISTANCE)));
		testPivot90.whenPressed(new AutoPivot(true, RobotMap.ENCODER_90));
		testPivot180.whenPressed(new AutoPivot(true, RobotMap.ENCODER_180));
		testPivot90counter.whenPressed(new AutoPivot(false, RobotMap.ENCODER_90));
		testPivot180counter.whenPressed(new AutoPivot(false, RobotMap.ENCODER_180));
		/*
		resetLift.whileHeld(new ResetLift());
		testGrip.whenPressed(new TestGrip());
		testLift.whenPressed(new TestLift());
		testArmOut.whenPressed(new TestArm(0));
		testArmBack.whenPressed(new TestArm(1));
		testLiftUp.whenPressed(new OperateLift(RobotMap.kLiftUp));
		testLiftDown.whenPressed(new OperateLift(RobotMap.kLiftDown));
		resetGrip.whenPressed(new ResetGrip());
		*/
	}
	
	public Joystick getLeftStick(){
		return left;
	}
	
	public double getRightX(){
		return controller.getRawAxis(RobotMap.kRightXAxisNum);
	
	}
	
	public double getLeftY(){
		return controller.getRawAxis(RobotMap.kLeftYAxisNum);
	}

	public Joystick getRightStick(){
		return right;
	}
	
	//public double getLeftValue(){
	//	return left.getY();
	//}
	
	public void setRumble(float f){
		controller.setRumble(RumbleType.kLeftRumble, f);
		controller.setRumble(RumbleType.kRightRumble, f);
		
	}
}

