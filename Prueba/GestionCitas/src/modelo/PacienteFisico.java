package modelo;

/**
 * Clase que representa un tipo de paciente específico: un paciente físico.
 * Esta clase extiende de la clase abstracta Paciente y proporciona una implementación 
 * del método abstracto `getTipo()` para devolver el tipo de paciente como "Físico".
 */
public class PacienteFisico extends Paciente {

    /**
     * Constructor de la clase PacienteFisico.
     * 
     * @param nombre El nombre del paciente.
     * @param cedula La cédula del paciente.
     * @param nivelUrgencia El nivel de urgencia del paciente (1 = Alta, 2 = Media, 3 = Baja).
     */
    public PacienteFisico(String nombre, String cedula, int nivelUrgencia) {
        super(nombre, cedula, nivelUrgencia); // Llama al constructor de la clase base (Paciente)
    }

    /**
     * Implementación del método abstracto `getTipo()` de la clase base Paciente.
     * Este método devuelve el tipo de paciente, que en este caso es "Físico".
     * 
     * @return El tipo de paciente, en este caso "Físico".
     */
    @Override
    public String getTipo() {
        return "Físico"; // Devuelve el tipo de paciente como "Físico"
    }
}
