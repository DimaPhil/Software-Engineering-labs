import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by dmitry on 16.12.16.
 */
class ParseVisitor implements TokenVisitor {
    private List<Token> polish;
    private Stack<Token> stack;

    ParseVisitor() {
        polish = new ArrayList<>();
        stack = new Stack<>();
    }

    @Override
    public void visit(NumberToken token) {
        polish.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getBrace() == '(') {
            stack.push(token);
        } else {
            while (true) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Incorrect exception\n");
                }
                Token peek = stack.peek();
                stack.pop();
                if (!(peek instanceof Brace)) {
                    polish.add(peek);
                } else {
                   break;
                }
            }
        }
    }

    private int priority(Operation token) {
        if (token.getOperation() == '+' || token.getOperation() == '-') {
            return 0;
        }
        if (token.getOperation() == '*' || token.getOperation() == '/') {
            return 1;
        }
        return -1;
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            Token op = stack.peek();
            if (!(op instanceof Operation)) {
                break;
            }
            if (priority((Operation) op) >= priority(token)) {
                polish.add(op);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }

    private void finish() {
        while (!stack.isEmpty()) {
            Token token = stack.peek();
            stack.pop();
            if (token instanceof Brace) {
                throw new RuntimeException("There is a problem with parenthesis in expression");
            }
            polish.add(token);
        }
    }

    List<Token> getPolish(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        finish();
        return polish;
    }
}
