
package org.usfirst.frc.team514.robot;

import org.usfirst.frc.team514.robot.commands.AutoCan;
import org.usfirst.frc.team514.robot.subsystems.DriveUtil;
import org.usfirst.frc.team514.robot.subsystems.ElevatorUtil;
import org.usfirst.frc.team514.robot.subsystems.GripUtil;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveUtil driveUtil = new DriveUtil();
	public static final GripUtil gripUtil = new GripUtil();
	public static final ElevatorUtil elevatorUtil = new ElevatorUtil();
	public static OI oi;
	
	//REMEMBER TO ADD BACK THE processCam() Method!!!
	//int session;
	//Image frame;

    //CameraServer server;
	
    Command autonomousCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        
		SmartDashboard.putData(driveUtil);
		SmartDashboard.putData(gripUtil);
		SmartDashboard.putData(elevatorUtil);
		/*
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		session = NIVision.IMAQdxOpenCamera("cam1",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
		*/
        //CameraServer.getInstance().startAutomaticCapture();

		//server = CameraServer.getInstance();
    	//server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam1");
        
        
     // instantiate the command used for the autonomous period
        
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Set", new AutoCan());
        //autoChooser.addObject("Stack", new AutoStack());
        SmartDashboard.putData("Auto Chooser", autoChooser);
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		//processCam();
        updateStatus();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();    
    	}

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //processCam();
        updateStatus();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //processCam();
        updateStatus();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    /*
    private void processCam(){
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);    	
    }
    */
    private void updateStatus(){
    	driveUtil.updateStatus();
    	elevatorUtil.updateStatus();
    	gripUtil.updateStatus();
    }
}
