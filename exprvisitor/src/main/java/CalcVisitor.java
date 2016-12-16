import java.util.List;
import java.util.Stack;

/**
 * Created by dmitry on 16.12.16.
 */
class CalcVisitor implements TokenVisitor {
    private Stack<Token> stack;

    CalcVisitor() {
        stack = new Stack<>();
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(token);
    }

    @Override
    public void visit(Brace token) {
    }

    @Override
    public void visit(Operation token) {
        NumberToken x = (NumberToken) stack.peek();
        stack.pop();
        NumberToken y = (NumberToken) stack.peek();
        stack.pop();
        char c = token.getOperation();
        switch (c) {
            case '+':   stack.push(new NumberToken(x.getNumber() + y.getNumber()));
                        break;
            case '-':   stack.push(new NumberToken(y.getNumber() - x.getNumber()));
                        break;
            case '*':   stack.push(new NumberToken(x.getNumber() * y.getNumber()));
                        break;
            case '/':   stack.push(new NumberToken(y.getNumber() / x.getNumber()));
                        break;
        }
    }

    private void visit(Token token) {
        if (token instanceof Operation) {
            visit((Operation) token);
        }
        if (token instanceof Brace) {
            visit((Brace) token);
        }
        if (token instanceof NumberToken) {
            visit((NumberToken) token);
        }
    }

    int calculate(List<Token> tokens) {
        for (Token token : tokens) {
            visit(token);
        }
        return ((NumberToken) stack.peek()).getNumber();
    }
}
