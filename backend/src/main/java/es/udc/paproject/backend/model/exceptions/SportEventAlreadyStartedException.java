package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class SportEventAlreadyStartedException extends Exception {
    public SportEventAlreadyStartedException(String message) {
        super(message);
    }
}

