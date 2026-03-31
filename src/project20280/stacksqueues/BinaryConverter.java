package project20280.stacksqueues;

import project20280.interfaces.Stack;

public class BinaryConverter {

    static String convertToBinary(long dec_num) {
        Stack<Integer> s = new ArrayStack<>();
        int remainder;

        while (dec_num != 0) {
            remainder = (int) (dec_num % 2);
            s.push(remainder);
            dec_num = dec_num / 2;
        }

        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToBinary(23));
    }
}
