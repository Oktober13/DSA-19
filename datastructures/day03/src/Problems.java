import java.lang.reflect.Array;
import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        if (A.length < k) {
            throw new IndexOutOfBoundsException();
        } else if (k == 0) {
            List list = Arrays.asList(A);
            return (List<Integer>) list;
        }

//        Stack<Integer> stack = new Stack<>();
//        Stack<Integer> reverse = new Stack<>();
//
//        for (int iter=1; iter<(A.length); iter++) {
//            if ((iter - stack.size()) == k) { // Too small
//                if
//                stack.add(A[iter]);
//            } else if ((A[iter] <= A[iter + 1]) && (stack.size() == k)) { // Better option, but stack is full
//            }
//        }

        List<Integer> kept = new LinkedList<>();
        kept.add(A[0]);
        int curr = 0;

        for (int iter=1; iter<(A.length); iter++) {
            if (kept.size() == (A.length - k)) { // If our desired return array has been filled
                int maxnum = 0;
                for (int i=0; i < kept.size()-1; i++) { // Begin iteration from larger places
                    if ((kept.get(i) - kept.get(i+1)) > 0){ // Prev num is larger
                        kept.remove(i);
                        kept.add(A[iter]);
                        break;
                    }
                    if (kept.get(i+1) > kept.get(maxnum)) {
                        maxnum = i+1;
                    }
                    if ((i == (kept.size() - 2)) && (kept.get(maxnum) > A[iter])) {
                        kept.remove(maxnum);
                        kept.add(A[iter]);
                        break;
                    }
                }

            } else {

                if ((iter - kept.size()) == k) { // If you have no choice
                    kept.add(A[iter]);
                } else if (kept.get(curr) <= A[iter]) { // If current number is smaller than or equal to next
                    kept.add(A[iter]);
                    curr++;
                } else { // There's a better option- replace current index with new smaller number
                    kept.set(curr, A[iter]);
                }

            }
        }
//        System.out.println("Hi");
        return kept;
    }

    public static boolean isPalindrome(Node n) {
        Node curr = n;
        Stack<Node> stack = new Stack<>();

        if (curr == null) {
            return true;
        }
        if (curr.next == null) {
            return true;
        }

        while (curr != null) {
            stack.push(curr);
            if (curr.next != null) {
                curr = curr.next;
            } else {
                curr = n;
                while (stack.size() > 0) {
                    if (stack.pop().val != curr.val) {
                        return false;
                    }
                    curr = curr.next;
                }
            }
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i <= (s.length() - 1); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                output.append(c);
                output.append(" ");
            }
            if ((c == '(') || (c == ')') || (c == '*') || (c == '/') || (c == '-') || (c == '+')) { // Is an operand
                if (c == '(') { // Add to stack on top
                    stack.push(c);
                } else if (c == ')') {
                    while ((stack.size() > 0) && (stack.peek() != '(')) { // Search for matching left parentheses
                        output.append(stack.pop()); // Add operators in stack to string
                        output.append(" ");
                    }
                    if ((stack.size() > 0) && (stack.peek() == '(')) { // Protects from popping a null if parenthesis are not balanced.
                        stack.pop();
                    }
                } else { // Other operator seen
                    while (stack.size() > 0 && moreImportant(stack.peek(), c)) {
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            output.append(stack.peek());
                            output.append(" ");
                            stack.pop();
                        }
                    }
                    stack.push(c);
                }
            }
        }

        if (stack.size() > 0) {
            while (stack.size() > 0) {
                output.append(stack.pop());
                output.append(" ");
            }
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    private static Boolean moreImportant(Character s, Character c) {
        Map<Character, Integer> importance = new HashMap<>() {{
            put("(".charAt(0), 5);
            put("-".charAt(0), 4);
            put("+".charAt(0), 3);
            put("/".charAt(0), 2);
            put("*".charAt(0), 1);
            //etc
        }};
        return (importance.get(s) > importance.get(c));
    }

}
