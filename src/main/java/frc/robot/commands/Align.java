// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/*
The purpose of this test code is to create a system in which when a button press or any other input is detected, then this command would run. 
Some use cases for this code could be to constantly keep your eye on the target, however wrap around code 
(limiting the degrees to 180, -180) would have to be put in place in order to ensure that the the turret does not extend past bounds that could
harm wires. For now, the driver has to put itself in a general location that would be viable for the turret, then press the align button.
*/





package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.turretSubsystem;
import frc.robot.RobotContainer;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Align extends CommandBase {
 
  Limelight li;
  private double x;
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    li = new Limelight();
    x =  li.getX();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    li.update();
    // The x variable does not print the values that it is supposed to  (only prints 0.0 instead of the proper Shuffle board values)
    System.out.println(x);
    x = li.getX();
   // this is to turn right
    //x is greater than 257 times limelight distance to the power of -.915, all subtracted by 2
    if(x>((257*Math.pow(li.getDistance(), -.915))-2)){
      RobotContainer.returnTurret().turretRun(-1);

    }
    //this is to turn left
    //x is less than 257 times limelight distance to the power of -.915, all added by 2
    if(x<((257*Math.pow(li.getDistance(), -.915))+2)){
      RobotContainer.returnTurret().turretRun(1);
    }

    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.returnTurret().turretRun(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    // when x is greater than -5 and less than positive 5, stop turret
    // this is potentially where we can tighten the bounds
      // original working numbers (x>-5&&x<5)
      if(x>-1&&x<1){
        RobotContainer.returnTurret().turretRun(0);
        return true;
      }
    
      return false;
    
    
  }
}
