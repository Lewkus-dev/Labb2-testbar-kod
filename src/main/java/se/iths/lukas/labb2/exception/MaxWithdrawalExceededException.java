package se.iths.lukas.labb2.exception;

public class MaxWithdrawalExceededException extends RuntimeException {

    public MaxWithdrawalExceededException(String message) {
        super(message);
    }
}
