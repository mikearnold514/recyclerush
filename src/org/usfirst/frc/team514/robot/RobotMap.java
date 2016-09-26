package org.usfirst.frc.team514.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// ENCODER Parameters!!
	//250 Tick Encoder
	//One recolution = app. 19 inches 
	//13.26 tic/in

	//Encoder Distance Parameters
    public static final double ENCODER_DISTANCE = 2292.0;  //Ten Feet
    public static final double ENCODER_90 = 345.0;         //Right Encoder 90 Degree Turn
    public static final double ENCODER_180 = 690.0;	   //Right Encoder 180 Degree Turn
    public static final double ENCODER_45 = 173.0;
    public static final double ENCODER_135 = 518.0;
    
    //Encoder Window Ranges to step down robot speed to improve accuracy in Autonomous
         //Pivot Action, Dead Zone windowing constant
    public static final double ENCODER_P_Window1 = 150.0;   
    public static final double ENCODER_P_Window2 = 75.0;
    
        //Straight Action, Dead Zone windowing constant
    public static final double ENCODER_S_Window1 = 500.00;  
    public static final double ENCODER_S_Window2 = 200.00;

        //Speed Rate of Reduction when in the Window
    public static final double Window_S_Speed_Pct = 0.2;
    public static final double Window_P_Speed_Pct = 0.1;
    
    //Encoder window - 5 Ticks (2.5 +/-) where we consider the robot on target
    public static final double ENCODER_CONSTANT = 2.5;
    
    //Autonomous Motor Starting Values
    	//Straight (Reverse) and Pivot (Clockwise) Speed Adjustment Factor
    public static final double Speed_Adjust = 0.05;
    //public static final double Speed_Adjust_f = 0.1;
    	
    	//Straight
    public static final double auto_LeftMotor = -0.55;
    public static final double auto_RightMotor = -0.55;
    
    	//Pivot
    public static final double auto_LeftPivot = -0.60;
    public static final double auto_RightPivot = -0.60;
    
    //Coerce to Range control variables
    public static final double C2R_inputMin = -100.0;
    public static final double C2R_inputMax = 100.0;
    public static final double C2R_outputMin = -0.25;
    public static final double C2R_outputMax = 0.25;
    
    //LogiTech F310 Button Mapping X/D Switch = D, DirectInput
    //Axis
    public static final int kLeftXAxisNum = 0;
    public static final int kLeftYAxisNum = 1;
    public static final int kRightXAxisNum = 2;
    public static final int kRightYAxisNum = 3;
    //For DPad, use controller.getPOV();
    //public static final int kDPadXAxisNum = 5;
    //public static final int kDPadYAxisNum = 6;
    //Buttons
    public static final int kXButtonNum = 1;
    public static final int kAButtonNum = 2;
    public static final int kBButtonNum = 3;
    public static final int kYButtonNum = 4;
    public static final int kLeftBumperNum = 5;
    public static final int kRightBumperNum = 6;
    public static final int kLeftTriggerNum = 7;
    public static final int kRightTriggerNum = 8;
    public static final int kBackButtonNum = 9;
    public static final int kStartButtonNum = 10;
    public static final int kLeftStickButtonNum = 11;
    public static final int kRightStickButtonNum = 12;
    //controller rumble value
    public static final float kRumbleOn = (float) .5;
    public static final float kRumbleOff = (float) 0.0;
    public static final int kArmOut = 4;
    public static final int kArmBack = 5;
    public static final int kGripScroll = 0;
    public static final int kGripHome = 1;
    public static final int kGripTote = 2;
    public static final int kGripCan = 3;
    public static final int kLiftScroll = 0;
    public static final int kLiftHome = 1;
    public static final int kLiftCUp = 2;
    public static final int kLiftCDown = 3;
    public static final int kLiftUp = 4;
    public static final int kLiftDown = 5;
    
    
    //Pneumatics
    public static final int armOut = 4;
    public static final int armBack = 5;
    
    //Motors
    public static final int gripMotor = 3;
    public static final int leftLiftMotor = 5;
    public static final int RightLiftMotor = 4;
    public static final int LeftTalon = 0;
    public static final int RightTalon = 1;
    
    //Analog Inputs
    //public static final int pot = 0;
    
    //Digital Inputs
    public static final int gripSwitch = 8;
    public static final int leftEncoder1 = 2;
    public static final int leftEncoder2 = 3;
    public static final int rightEncoder1 = 0;
    public static final int rightEncoder2 = 1;
    public static final int liftEncoder1 = 4;
    public static final int liftEncoder2 = 5;
    public static final int liftSwitch = 9;
    public static final int gripEncoder1 = 7;
    public static final int gripEncoder2 = 6;
    
    //Constants
    public static final double gripCan = 4440.0;
    public static final double gripTote = 8660.0;
    public static final double gripSpeed = 0.65;
    public static final double gripHome = 0.0;
    public static final double liftBottom = 0.0;
    public static final double liftOneClear = 1500.0;
    public static final double liftOneRelease = 1035.0;
    public static final double liftMax = 3700.0;
    public static final double liftCan = 2700.0;
    /*
    public static final double liftFirst = 1000.0;
    public static final double liftSecond = 2175.0;
    public static final double liftThird = 3350.0;
    public static final double liftFourth = 4525.0;
    public static final double liftFifth = 5700.0;
    public static final double liftSixth = 6875.0;
    */
    public static final double liftSpeed = 0.65;
  
    //Auto Constants
    public static final double driveOneFoot = 229.0;  //drives 1 foot
    public static final double driveTwoFeet = 458.0; //drives 2 feet
    public static final double driveIntoAutoZone = 1374.0; //drives into auto zone from game pieces
    public static final double driveFiveInches = 95.0; //drives out of the way
    public static final double driveToCan = 2750.0; //drives to can (12 feet)
    public static final double driveToNextTote = 1547.0; //drives to next tote in Auto Stack
    //public static final int liftFiveInches = 500; //lifts elevator 5 inches
    public static final int liftComb = 500; //lifts comb 5 inches
    public static final int liftStackTote = 1250; //lifts tote high enough to stack onto another tote

}
