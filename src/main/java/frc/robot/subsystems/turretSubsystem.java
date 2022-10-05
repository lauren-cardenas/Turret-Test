// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

import frc.robot.Limelight;
import frc.robot.commands.Align;

public class turretSubsystem extends SubsystemBase {
  /** Creates a new turretSubsystem. */
  WPI_TalonFX m_turret;

  Limelight li = new Limelight();

  public turretSubsystem() {
    m_turret = new WPI_TalonFX(MechConstants.turretPort);
  }

  @Override
  public void periodic() {
    li.getDistance();
  }

  public void turretRun(double speed){
    m_turret.set(-speed);
  }

  public void displayEncoderValues(){
    SmartDashboard.putNumber("Turret Speed", m_turret.getSelectedSensorVelocity());
  }
}
