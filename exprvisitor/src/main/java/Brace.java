/**
 * Created by dmitry on 16.12.16.
 */
class Brace implements Token {
    private char brace;

    Brace(char brace) {
        this.brace = brace;
    }

    char getBrace() {
        return brace;
    }
}
