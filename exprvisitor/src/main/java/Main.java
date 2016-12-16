import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by dmitry on 16.12.16.
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();
            List<Token> tokens = new Tokenizer().tokenize(expression);
            new PrintVisitor().print(tokens);
            List<Token> polish = new ParseVisitor().getPolish(tokens);
            new PrintVisitor().print(polish);
            System.out.println(new CalcVisitor().calculate(polish));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
