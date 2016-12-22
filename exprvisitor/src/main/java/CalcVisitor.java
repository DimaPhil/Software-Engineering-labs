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

    int calculate(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        return ((NumberToken) stack.peek()).getNumber();
    }
}
