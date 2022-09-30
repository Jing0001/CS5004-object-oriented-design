package transmission;
public class AutomaticTransmission implements Transmission{
    //variable
    private int speed1;
    private int speed2;
    private int speed3;
    private int speed4;
    private int speed5;
    private int currentSpeed;
    private int gear;

    //constructor to initialize the variable
    public AutomaticTransmission(int speed1, int speed2, int speed3, int speed4, int speed5) {
        if (speed1 <= 0 || speed2 <= 0 || speed3 <= 0 || speed4 <= 0 || speed5 <= 0) {
            throw new IllegalArgumentException("Cars cannot have negative speed");
        }
        this.speed1 = speed1;
        this.speed2 = speed2;
        this.speed3 = speed3;
        this.speed4 = speed4;
        this.speed5 = speed5;
    }

    //getter method for currentSpeed
    public int getSpeed() {
        return currentSpeed;
    }

    //getter method for gear
    public int getGear() {
        return gear;
    }

    //increaseSpeed method to return a Transmission object with speed increased by 2 and the appropriate gear.
    public AutomaticTransmission increaseSpeed() {
        currentSpeed += 2;
        if (currentSpeed < speed1) {
            gear = 1;
        }
        else if (currentSpeed < speed2) {
            gear = 2;
        }
        else if (currentSpeed < speed3) {
            gear = 3;
        }
        else if (currentSpeed < speed4) {
            gear = 4;
        }
        else if (currentSpeed < speed5) {
            gear = 5;
        }
        else {
            gear = 6;
        }
        return this;
    }

    //decreaseSpeed method to return a Transmission object with speed increased by 2 and the appropriate gear.
    public AutomaticTransmission decreaseSpeed() {
        currentSpeed -= 2;
        if (currentSpeed < 0) {
            throw new IllegalStateException("Speed become invalid");
        }
        if (currentSpeed < speed1) {
            gear = 1;
        }
        else if (currentSpeed < speed2) {
            gear = 2;
        }
        else if (currentSpeed < speed3) {
            gear = 3;
        }
        else if (currentSpeed < speed4) {
            gear = 4;
        }
        else if (currentSpeed < speed5) {
            gear = 5;
        }
        else {
            gear = 6;
        }
        return this;
    }

    //toString method to get the current state of the transmission (speed and gear)
    @Override
    public String toString() {
        String message = "Transmission (speed = " + getSpeed() + ", gear = " + getGear() + ")";
        return message;
    }
}
