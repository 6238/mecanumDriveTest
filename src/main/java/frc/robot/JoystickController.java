package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author ishanmadan
 */

public class JoystickController {

    public Joystick controller;
    public int port;

    public JoystickController(int port) {
        this.port = port;
        this.controller = new Joystick(port);
        // initialize the Joystick object w/ given port
    }

    /**
     * Sensitivity buffer for joystick (recommended at least 0.05), prevents
     * excessive movement from slight touch to joystick
     */
    double DEAD_ZONE = 0.08;

    /**
     * @param value input value to check
     * @return input value if outside dead spot (0 if inside)
     */
    public double correctDeadSpot(double value) {
        if (Math.abs(value) < DEAD_ZONE) {
            return 0;
        }
        return value;
    }
    /**
     * @param buttonNumber (1 - 12)
     * @return value of given button
     */
    public boolean getButton(int buttonNumber) {
        return controller.getRawButton(buttonNumber);
    }
    
    /**
     * @param axisNumber (0 - 2)
     * @return value of joystick on given axis
     */
    public double getAxis(int axisNumber) {
        return controller.getRawAxis(axisNumber);
    }

    /**
     * @param povNumber (0)
     * @return value of the DPad in degrees (returns 0 if at center position)
     */
    public int getPOV(int povNumber) {
        return controller.getPOV(povNumber);
    }

    /**
     * @return value of the joystick's slider
     */
    public double getThrottle() {
        return controller.getThrottle();
    }

    // Joystick

    /**
     * @return joystick's x value
     */
    public double getJoystickX() {
        // return correctDeadSpot(getAxis(0));
        return correctDeadSpot(-controller.getX());
    }

    /**
     * @return joystick's y value
     */
    public double getJoystickY() {
        // return correctDeadSpot(getAxis(1));
        return correctDeadSpot(controller.getY());
    }

    /**
     * @return joystick's z value
     */
    public double getJoystickZ() {
        // return correctDeadSpot(getAxis(2));
        return correctDeadSpot(controller.getZ());
    }

    // Numeral Buttons
    /**
     * @return joystick's button 1 pressed
     */
    public boolean getButtonOne() {
        return getButton(1);
    }

    /**
     * @return joystick's button 2 pressed
     */
    public boolean getButtonTwo() {
        return getButton(2);
    }

    /**
     * @return joystick's button 3 pressed
     */
    public boolean getButtonThree() {
        return getButton(3);
    }

    /**
     * @return joystick's button 4 pressed
     */
    public boolean getButtonFour() {
        return getButton(4);
    }

    /**
     * @return joystick's button 5 pressed
     */
    public boolean getButtonFive() {
        return getButton(5);
    }

    /**
     * @return joystick's button 6 pressed
     */
    public boolean getButtonSix() {
        return getButton(6);
    }

    /**
     * @return joystick's button 7 pressed
     */
    public boolean getButtonSeven() {
        return getButton(7);
    }

    /**
     * @return joystick's button 8 pressed
     */
    public boolean getButtonEight() {
        return getButton(8);
    }

    /**
     * @return joystick's button 9 pressed
     */
    public boolean getButtonNine() {
        return getButton(9);
    }

    /**
     * @return joystick's button 10 pressed
     */
    public boolean getButtonTen() {
        return getButton(10);
    }

    /**
     * @return joystick's button 11 pressed
     */
    public boolean getButtonEleven() {
        return getButton(11);
    }

    /**
     * @return joystick's button 12 pressed
     */
    public boolean getButtonTwelve() {
        return getButton(12);
    }

    // Slider

    public double getSlider() {
        return -1 * getThrottle();
    }

    // DPad
    // The DPad is unique in that it works with a 0-360 degrees POV
    
    /**
     * @return joystick's dpad in up or center position
     */
    public boolean getDPadUp() {
        int degree = getPOV(0);
        return (degree >= 337 || degree <= 22);
    }

    /**
     * @return joystick's dpad in down position
     */
    public boolean getDPadDown() {
        int degree = getPOV(0);
        return (degree <= 202 && degree >= 157);
    }

    /**
     * @return joystick's dpad in left position
     */
    public boolean getDPadLeft() {
        int degree = getPOV(0);
        return (degree <= 292 && degree >= 247);
    }

    /**
     * @return joystick's dpad in right position
     */
    public boolean getDPadRight() {
        int degree = getPOV(0);
        return (degree <= 112 && degree >= 67);
    }

    /**
     * @return joystick's dpad in up right position
     */
    public boolean getDPadUpRight() {
        int degree = getPOV(0);
        return (degree <= 67 && degree >= 22);
    }

    /**
     * @return joystick's dpad in up left position
     */
    public boolean getDPadUpLeft() {
        int degree = getPOV(0);
        return (degree <= 337 && degree >= 292);
    }

    /**
     * @return joystick's dpad in down right position
     */
    public boolean getDPadDownRight() {
        int degree = getPOV(0);
        return (degree <= 157 && degree >= 112);
    }

    /**
     * @return joystick's dpad in down left position
     */
    public boolean getDPadDownLeft() {
        int degree = getPOV(0);
        return (degree <= 247 && degree >= 207);
    }
}