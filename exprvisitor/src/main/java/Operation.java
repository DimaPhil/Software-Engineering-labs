/**
 * Created by dmitry on 16.12.16.
 */
class Operation implements Token {
    private char operation;

    Operation(char operation) {
        this.operation = operation;
    }

    char getOperation() {
        return operation;
    }
}
