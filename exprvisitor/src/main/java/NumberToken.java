/**
 * Created by dmitry on 16.12.16.
 */
class NumberToken implements Token {
    private int number;

    NumberToken(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
