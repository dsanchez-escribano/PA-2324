package es.udc.paproject.backend.model.exceptions;

public class CardDoesntMatchException extends Throwable{
    public CardDoesntMatchException(String message) {
        super(message);
    }
}
