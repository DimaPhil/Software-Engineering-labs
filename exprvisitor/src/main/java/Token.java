/**
 * Created by dmitry on 16.12.16.
 */
interface Token {
    void accept(TokenVisitor visitor);
}
