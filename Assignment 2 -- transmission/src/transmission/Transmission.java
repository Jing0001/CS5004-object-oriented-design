package transmission;
public interface Transmission {
    //variable
    int getSpeed();
    int getGear();
    String toString();
    AutomaticTransmission increaseSpeed();
    AutomaticTransmission decreaseSpeed();
}
