package bignumber;

import java.util.prefs.NodeChangeListener;

public class BigNumberImpl implements BigNumber {
    //member variable
    private Node dummy;

    //constructor with no parameters that creates the number 0
    public BigNumberImpl() {
        dummy = new Node(null, 0, null);
        dummy.next = new Node(dummy, 0, dummy);
        dummy.prev = dummy.next;
    }

    //getter for dummy node
    public Node getDummy() {
        return dummy;
    }

    // overloading constructor:
    public BigNumberImpl(String number) {
        dummy = new Node(null, 0, null);
        dummy.next = dummy;
        dummy.prev = dummy;
        //string passed to it does not represent a valid number
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < number.length(); i++) {
            int num = Character.getNumericValue(number.charAt(i));
            appTail(num);
        }
    }

    //append the tail of the double linked list
    public void appTail(int val) {
        Node curLast = dummy.prev;
        Node newN = new Node(null, val, null);
        dummy.prev = newN;
        curLast.next = newN;
        newN.next = dummy;
        newN.prev = curLast;
    }

    //append the head of the double linked list
    public void appHead(int val) {
        Node curFirst = dummy.next;
        Node newN = new Node(null, val, null);
        dummy.next = newN;
        curFirst.prev = newN;
        newN.next = curFirst;
        newN.prev = dummy;
    }

    //calculate the length of double linked list
    @Override
    public int length() {
        Node traversePtr = dummy.next;
        int counter = 0;
        while (traversePtr.next != dummy) {
            counter = counter + 1;
            // move to the next node
            traversePtr = traversePtr.next;
        }
        // when reaching here, traversePtr is pointing to null,
        // meaning that we've reached the end of the linked list
        return (counter + 1);
    }

    //takes the number of shifts as an argument and shifts this number to the left by that number
    @Override
    public void shiftLeft(int k) {
        if (dummy.next.val == 0 && this.length() == 1) {
            return;
        }
        if (k == 0) {
            return;
        }
        if (k < 0) {
            shiftRight(-k);
            return;
        }
        Node cur = dummy.next;
        for (int count = 0; count < k; count++) {
            appTail(0);
        }
    }

    //takes the number of shifts as an argument and shifts this number to the right by that number
    @Override
    public void shiftRight(int n) {
        if (dummy.next.val == 0 && this.length() == 1) {
            return;
        }
        if (n == 0) {
            return;
        }
        if (n < 0) {
            shiftLeft(-n);
            return;
        }

        for (int count = 0; count < n; count++) {
            Node curLast = dummy.prev;
            if (curLast.prev != dummy) {
                Node last = curLast.prev;
                dummy.prev = last;
                last.next = dummy;
            } else {
                curLast.val = 0;
            }
        }
    }

    //takes a single digit as an argument and adds it to this number
    @Override
    public void addDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException();
        }
        Node curLast = dummy.prev;
        int total = curLast.val + digit;
        curLast.val = total % 10;
        int carry = total / 10;
        System.out.println(carry);
        while (carry > 0 && curLast.prev != dummy) {
            curLast = curLast.prev;
            total = curLast.val + carry;
            curLast.val = total % 10;
            carry = total / 10;
        }
        if (carry > 0) {
            appHead(carry);
        }
    }

    //takes a position as an argument and returns the digit at that position
    @Override
    public int getDigitAt(int position) {
        if (position < 0 || (position > (length() - 1))) {
            throw new IllegalArgumentException();
        }
        Node traversePtr = dummy.next;
        int myPosition = length() - position - 1;
        int count = 0;


        while (count < myPosition && traversePtr != dummy) {
            // move to the next node
            traversePtr = traversePtr.next;
            count++;
        }
        // when reaching here, traversePtr is pointing to the last node
        // of the linked list
        return traversePtr.val;
    }

    //returns an identical and independent copy of this number
    @Override
    public BigNumber copy() {
        String num = "";
        Node traversePtr = dummy.next;

        while (traversePtr != dummy) {
            // move to the next node
            num += traversePtr.val;
            traversePtr = traversePtr.next;
        }
        BigNumberImpl copy = new BigNumberImpl(num);
        return copy;
    }

    //returns the sum of these two numbers
    @Override
    public BigNumber add(BigNumber num) {
        BigNumberImpl result = new BigNumberImpl();
        Node curLast0 = this.dummy.prev;
        Node curLast1 = num.getDummy().prev;
        Node curLast = result.dummy.prev;
        curLast.val = (curLast0.val + curLast1.val) % 10;
        int carry = (curLast0.val + curLast1.val) / 10;

        while (curLast0.prev != dummy && curLast1.prev != num.getDummy()) {
            curLast0 = curLast0.prev;
            curLast1 = curLast1.prev;
            int val = (curLast0.val + curLast1.val + carry) % 10;
            carry = (curLast0.val + curLast1.val + carry) / 10;
            result.appHead(val);
        }

        Node curLastRem;
        Node remainDummy;
        if (curLast0.prev != dummy) {
            curLastRem = curLast0.prev;
            remainDummy = dummy;
        } else {
            curLastRem = curLast1.prev;
            remainDummy = num.getDummy();
        }

        while (curLastRem != remainDummy) {
            int val = (curLastRem.val + carry) % 10;
            carry = (curLastRem.val + carry) / 10;
            result.appHead(val);
            curLastRem = curLastRem.prev;
        }

        if (carry > 0) {
            result.appHead(carry);
        }

        return result;
    }

    @Override
    public String toString() {
        String res = "";
        Node traversePtr = dummy.next;
        while (traversePtr != dummy) {
            res = res + Integer.toString(traversePtr.val);
            // move to the next node
            traversePtr = traversePtr.next;
        }

        // when reaching here, traversePtr is pointing to null,
        // meaning that we've reached the end of the linked list
        return res;
    }

    @Override
    public int compareTo(BigNumber n) {
        if (this.length() > n.length()) {
            return 1;
        }
        if (this.length() < n.length()) {
            return -1;
        }
        int l = this.length();
        Node h0 = this.dummy.next;
        Node h1 = n.getDummy().next;
        while (l > 0) {
            if (h0.val > h1.val) {
                return 1;
            }
            if (h0.val < h1.val) {
                return -1;
            }
            h0 = h0.next;
            h1 = h1.next;
            l--;
        }
        return 0;
    }
}