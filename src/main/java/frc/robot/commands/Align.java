// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.subsystems.turretSubsystem;
import frc.robot.RobotContainer;



public class Align extends CommandBase {
 

  Limelight li;
  private double x;
  private int v;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    li = new Limelight();
    x = li.getX();
    v = li.getV();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (v == 1){
      if(x < -2){
        RobotContainer.returnTurret().turretRun(-1);
      }
      if(x > 2){
        RobotContainer.returnTurret().turretRun(1);
      }
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
    
    if (v == 1){
      if(x == 0){
        RobotContainer.returnTurret().turretRun(0);
        return true;
      }
    } 
      return false;
    
    
  }
}
