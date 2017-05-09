/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.camera.AxisCamera;

public class TheReaper extends IterativeRobot 
    {
    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick shootStick;
    private SpeedController m_Left;
    private SpeedController m_Right;
    private RobotDrive drive;
    private DriverStationLCD lcd = DriverStationLCD.getInstance();
    private AxisCamera camera;
    private Servo servo1;
    private Servo servo2;
    
    boolean OpenClose = false;
    boolean Extend = true;

    public void robotInit() 
        {
        joystickInit(); 
        talonInit(); 
        robotDriveInit();
        servoInit();
        
        getWatchdog().setExpiration(0.1); 
        lcd.println(DriverStationLCD.Line.kUser2, 1, "Enabled!");
        lcd.updateLCD();
        }
    
    public void joystickInit() 
        {
        leftStick = new Joystick(1);
        rightStick = new Joystick(2); 
        shootStick = new Joystick(3);
        }
    
    public void talonInit() 
        {
        m_Left = new Talon(1);
        m_Right = new Talon(2);
        }
    
    public void robotDriveInit() 
        {
        drive = new RobotDrive(m_Left, m_Right);
        }
    
    
    
    public void servoInit() {
        servo1 = new Servo(7);
        servo2 = new Servo(8);
    }
    
    public void disabledInit() 
        {
        
        lcd.println(DriverStationLCD.Line.kUser2, 1, "Disabled!");
        lcd.updateLCD();

        }
    
    public void disabledContinuous() 
        {
        
        }
    
    public void disabledPeriodic() 
        {
        
        }
    
    public void autonomousInit() 
        {
        getWatchdog().setEnabled(false);
        lcd.println(DriverStationLCD.Line.kUser2, 1, "Autonomous!");
        lcd.updateLCD();
        }
    
    public void autonomousContinuous() {
        while(isAutonomous() && isEnabled()) 
        {
            
        }
        
        }
    
    public void autonomousPeriodic() 
        {

        }
    
    public void teleopInit() 
        {
        getWatchdog().setEnabled(true);
        lcd.println(DriverStationLCD.Line.kUser2, 1, "Teleop!");
        lcd.updateLCD();
        }

    /**
     * This function is called periodically during operator control
     */
    long x = 1;
    public void teleopContinuous() 
        {
        
        }
    
    public void teleopPeriodic() 
        {
            AxisCamera.getInstance().writeCompression(0);
            AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k320x240);
            AxisCamera.getInstance().writeBrightness(10);
        
        drive.tankDrive(-leftStick.getY(), -rightStick.getY());
        
        if(shootStick.getRawButton(8)) 
        {
            servo1.setAngle(0);
            servo2.setAngle(0);        
        }
        else
        {
            servo1.setAngle(180);
            servo2.setAngle(180);
        }    
        
        
        }
}