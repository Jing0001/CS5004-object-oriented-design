package lookandsay;
import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger>{
    //variable
    private BigInteger end;
    private BigInteger sequence;
    //constructor to initialize the variable
    public LookAndSayIterator(){
        String e = "";
        for (int i = 0; i < 100; i++){
            e += "9";
        }
        this.sequence = new BigInteger("1");
        end = new BigInteger(e);
    }
    //constructor to initialize the variable
    public LookAndSayIterator(BigInteger seed){
        String e = "";
        for (int i = 0; i < 100; i++){
            e += "9";
        }
        end = new BigInteger(e);

        if (seed.compareTo(new BigInteger("0")) != 1
                || seed.compareTo(end) != -1
                || seed.toString().contains("0")){
            throw new IllegalArgumentException();
        }
        this.sequence = seed;
    }
    //constructor to initialize the variable
    public LookAndSayIterator(BigInteger seed, BigInteger end){
        if (seed.compareTo(new BigInteger("0")) != 1
                || seed.compareTo(end) != -1
                || seed.toString().contains("0")){
            throw new IllegalArgumentException();
        }
        this.sequence = seed;
        this.end = end;
    }
    //return the current number in the sequence and retreat to the previous number
    @Override
    public BigInteger prev() {
        if(!hasPrevious()){
            return sequence;
        }
        BigInteger seq = sequence;
        String newSeq = "";
        String seqStr = sequence.toString();
        int i = 0;
        int count = 0;
        int cur = 0;
        while(i < seqStr.length()){
            count = Character.getNumericValue(seqStr.charAt(i));
            cur = Character.getNumericValue(seqStr.charAt(i + 1));
            while(count > 0){
                newSeq += cur;
                count --;
            }
            i += 2;
        }
        sequence = new BigInteger(newSeq);
        return seq;
    }
    //return true if it is possible to go back one step, false otherwise.
    @Override
    public boolean hasPrevious() {
        if(this.sequence.toString().length() % 2 == 0 && this.sequence.toString() != null){
            return true;
        }
        return false;
    }
    //return true if the next number to be returned
    // is still lesser than end (specified in the constructors), false otherwise.
    @Override
    public boolean hasNext() {
        if (sequence.compareTo(end) <= 0){
            return true;
        }
        return false;
    }
    //return the current number in the sequence and advance to the next number.
    @Override
    public BigInteger next() {
        BigInteger seq = sequence;
        String newSeq = "";
        String seqStr = sequence.toString();
        int prev = Character.getNumericValue(seqStr.charAt(0));
        int count = 1;
        for (int i = 1; i < seqStr.length(); i++) {
            int cur = Character.getNumericValue(seqStr.charAt(i));
            if (cur == prev) {
                count += 1;
            } else {
                int n = count * 10 + prev;
                newSeq = newSeq + n;
                count = 1;
                prev = cur;
            }
            cur = prev;
        }
        int n = count * 10 + prev;
        newSeq = newSeq + n;
        sequence = new BigInteger(newSeq);
        return seq;
    }
}
