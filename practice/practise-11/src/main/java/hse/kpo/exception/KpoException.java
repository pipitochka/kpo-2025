package hse.kpo.exception;

import lombok.Getter;

/**
 * class of exceptions.
 */
@Getter
public class KpoException extends RuntimeException {
    private final int code;

    public KpoException(int code, String message) {
        super(message);
        this.code = code;
    }
}
