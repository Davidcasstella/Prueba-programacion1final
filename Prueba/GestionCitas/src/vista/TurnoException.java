package vista;

// Excepción personalizada para manejar errores relacionados con los turnos
public class TurnoException extends Exception {
    
    /**
     * Constructor para la excepción TurnoException.
     * 
     * @param mensaje El mensaje que describe el error o problema con el turno.
     */
    public TurnoException(String mensaje) {
        // Llama al constructor de la clase base (Exception) con el mensaje proporcionado.
        super(mensaje);
    }
}
