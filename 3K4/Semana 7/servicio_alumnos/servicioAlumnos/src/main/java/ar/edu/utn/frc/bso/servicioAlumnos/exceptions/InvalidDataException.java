package ar.edu.utn.frc.bso.servicioAlumnos.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
