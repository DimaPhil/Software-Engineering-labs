/**
 * Created by dmitry on 16.12.16.
 */
interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}
