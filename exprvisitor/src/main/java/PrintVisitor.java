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

    void print(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        System.out.println(String.join(" ", output));
    }
}
