package modelo;

/**
 * Clase que representa un turno para un paciente con un médico en una fecha y hora determinadas.
 * Esta clase implementa la interfaz Comparable para permitir la comparación de turnos según su prioridad.
 */
public class Turno implements Comparable<Turno> {

    private Paciente paciente;    // El paciente asociado con el turno
    private Medico medico;        // El médico asignado al turno
    private String fecha;         // La fecha del turno en formato "YYYY-MM-DD"
    private String hora;          // La hora del turno
    private int prioridad;        // La prioridad del turno (basada en el nivel de urgencia del paciente)
    private boolean disponible;   // Indica si el turno está disponible (true si está disponible, false si no)

    /**
     * Constructor de la clase Turno.
     * Inicializa los atributos del turno y asigna la prioridad basada en el nivel de urgencia del paciente.
     * Además, el turno es marcado como disponible por defecto.
     * 
     * @param paciente El paciente que requiere el turno.
     * @param medico El médico asignado al turno.
     * @param fecha La fecha del turno (formato: "YYYY-MM-DD").
     * @param hora La hora del turno.
     */
    public Turno(Paciente paciente, Medico medico, String fecha, String hora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.prioridad = paciente.getNivelUrgencia(); // Asigna la prioridad según el nivel de urgencia del paciente
        this.disponible = true; // El turno es inicialmente disponible
    }

    /**
     * Obtiene el paciente asociado con el turno.
     * 
     * @return El paciente del turno.
     */
    public Paciente getPaciente() { 
        return paciente; 
    }

    /**
     * Obtiene el médico asignado al turno.
     * 
     * @return El médico del turno.
     */
    public Medico getMedico() { 
        return medico; 
    }

    /**
     * Obtiene la fecha del turno.
     * 
     * @return La fecha del turno en formato "YYYY-MM-DD".
     */
    public String getFecha() { 
        return fecha; 
    }

    /**
     * Obtiene la hora del turno.
     * 
     * @return La hora del turno.
     */
    public String getHora() { 
        return hora; 
    }

    /**
     * Obtiene la prioridad del turno, que está basada en el nivel de urgencia del paciente.
     * 
     * @return La prioridad del turno.
     */
    public int getPrioridad() { 
        return prioridad; 
    }

    /**
     * Obtiene la disponibilidad del turno.
     * 
     * @return true si el turno está disponible, false si no está disponible.
     */
    public boolean getDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del turno.
     * 
     * @param disponible true para marcar el turno como disponible, false para marcarlo como no disponible.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Método que compara este turno con otro turno basado en la prioridad.
     * La comparación se realiza utilizando el nivel de urgencia del paciente: 
     * un turno con un paciente de mayor urgencia (menor número) tendrá mayor prioridad.
     * 
     * @param o El otro turno con el cual se desea comparar.
     * @return Un valor negativo si este turno tiene mayor prioridad que el otro, 
     *         un valor positivo si tiene menor prioridad, o 0 si tienen la misma prioridad.
     */
    @Override
    public int compareTo(Turno o) {
        return Integer.compare(this.prioridad, o.prioridad); 
    }
}
