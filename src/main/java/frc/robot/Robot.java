/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX rearLeft;
  private WPI_TalonSRX rearRight;

  public MecanumDrive robotDrive;

  private WPI_TalonSRX intakeLeft;
  private WPI_TalonSRX intakeRight;
  public SpeedControllerGroup intake;

  public Compressor compressor;

  public DoubleSolenoid elevator;

  public JoystickController joystick;

  private boolean elevatorUp;
  private boolean elevatorDown;

  private boolean intakeIn;
  private boolean intakeOut;
  private double intakeSpeed;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    frontLeft = new WPI_TalonSRX(1);
    frontRight = new WPI_TalonSRX(2);
    rearLeft = new WPI_TalonSRX(3);
    rearRight = new WPI_TalonSRX(4);

    robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    intakeLeft = new WPI_TalonSRX(7);
    intakeRight = new WPI_TalonSRX(8);

    intakeLeft.setInverted(false);
    intakeRight.setInverted(true);
    intake = new SpeedControllerGroup(intakeLeft, intakeRight);
    intakeSpeed = 1;

    compressor = new Compressor();
    compressor.setClosedLoopControl(true);

    elevator = new DoubleSolenoid(2, 3);

    joystick = new JoystickController(0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    robotDrive.driveCartesian(joystick.getJoystickY(), joystick.getJoystickX(), -1 * joystick.getJoystickZ());

    elevatorUp = joystick.getButtonSix();
    elevatorDown = joystick.getButtonFour();

    if (elevatorUp) {
      elevator.set(DoubleSolenoid.Value.kForward);
    } else if (elevatorDown) {
      elevator.set(DoubleSolenoid.Value.kReverse);
    } else {
      elevator.set(DoubleSolenoid.Value.kOff);
    }

    intakeIn = joystick.getButtonThree();
    intakeOut = joystick.getButtonFive();

    if (intakeIn) {
      intake.set(intakeSpeed);
    } else if (intakeOut) {
      intake.set(-1 * intakeSpeed);
    } else {
      intake.set(0);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
