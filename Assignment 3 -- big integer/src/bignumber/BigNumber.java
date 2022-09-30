package bignumber;
public interface BigNumber {
    Node getDummy();
    int length();
    void shiftLeft(int number);
    void shiftRight(int number);
    void addDigit(int digit);
    int getDigitAt(int position);
    BigNumber copy();
    BigNumber add(BigNumber num);
    int compareTo(BigNumber n);
    String toString();
}
