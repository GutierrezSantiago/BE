package utn.frc.backend.tutor.sac.lib.store.exceptions;

import java.lang.reflect.Field;

public class StoreableValidationException extends Exception {
    private String attributeName;

    // NO empty constructor

    public StoreableValidationException(String attributeName, String message) {
        super(message);
        this.attributeName = attributeName;
    }

    public StoreableValidationException(String attributeName, Exception e) {
        this(attributeName, e.getMessage());
    }

    public String getAttributeName() {
        return attributeName;
    }
}
