import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 16.12.16.
 */
class Tokenizer {
    List<Token> tokenize(String expression) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int number = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number = number * 10 + expression.charAt(i) - '0';
                    i++;
                }
                i--;
                tokens.add(new NumberToken(number));
            } else if (c == '(' || c == ')') {
                tokens.add(new Brace(c));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                tokens.add(new Operation(c));
            } else if (c != ' ') {
                throw new RuntimeException("Expected digit, brace or operaton, but found " + c);
            }
        }
        return tokens;
    }
}
