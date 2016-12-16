import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 16.12.16.
 */
class PrintVisitor implements TokenVisitor {
    private List<String> output;

    PrintVisitor() {
        output = new ArrayList<>();
    }

    @Override
    public void visit(NumberToken token) {
        output.add("NUMBER(" + String.valueOf(token.getNumber()) + ")");
    }

    @Override
    public void visit(Brace token) {
        output.add(String.valueOf(token.getBrace()));
    }

    @Override
    public void visit(Operation token) {
        switch (token.getOperation()) {
            case '+': output.add("PLUS");
                      break;
            case '-': output.add("MINUS");
                      break;
            case '*': output.add("MULTIPLY");
                      break;
            case '/': output.add("DIVISION");
                      break;
            default: throw new RuntimeException("Incorrect operation: " + token.getOperation());
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

    void print(List<Token> tokens) {
        for (Token token : tokens) {
            visit(token);
        }
        System.out.println(String.join(" ", output));
    }
}
