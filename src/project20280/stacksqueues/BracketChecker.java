package project20280.stacksqueues;
import project20280.interfaces.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        Stack<Character> s = new ArrayStack<>();
        char c;
        char popped;
        int len = this.input.length();
        String message = "correct";

        for (int i = 0; i < len; i++){
            c = this.input.charAt(i);

            if (c == '{' || c == '[' || c == '(') {
                s.push(c);
            }
            else if (c == '}' || c == ']' || c == ')') {
                if(s.isEmpty()) {
                    message = "not correct; nothing matches the " + c;
                    break;
                }
                popped = s.pop();

                if (!((popped == '{' && c == '}') ||
                      (popped == '[' && c == ']') ||
                      (popped == '(' && c == ')')))
                {
                    message = "not correct; the '" + c + "' after the " + this.input.charAt(i - 1) + " doesn't match the '" + popped + " after";
                    break;
                }
            }

            if(!s.isEmpty() && i + 1 == len) {
                message = "not correct; nothing matches the " + s.pop();
            }
        }

        System.out.println(message);
    }

    public static void main(String[] args) {
            String[] inputs = {
                    "[]]()()", // not correct
                    "c[d]", // correct\n" +
                    "a{b[c]d}e", // correct\n" +
                    "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                    "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                    "a{b(c) ", // // not correct; Nothing matches opening {
            };

                for (String input:inputs) {
                BracketChecker checker = new BracketChecker(input);
                System.out.println("checking: " + input);
                checker.check();
            }
        }
}