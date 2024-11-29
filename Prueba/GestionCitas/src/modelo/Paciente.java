package modelo;

/**
 * Clase abstracta que representa un paciente en un sistema de atención médica.
 * La clase almacena información básica sobre el paciente, como su nombre, cédula
 * y nivel de urgencia, además de proporcionar un método abstracto para obtener el tipo
 * de paciente.
 */
public abstract class Paciente {

    // Atributos
    private String nombre; // Nombre del paciente
    private String cedula; // Cédula del paciente
    private int nivelUrgencia; // Nivel de urgencia del paciente (1 = Alta, 2 = Media, 3 = Baja)

    /**
     * Constructor de la clase Paciente.
     * 
     * @param nombre El nombre del paciente.
     * @param cedula La cédula del paciente.
     * @param nivelUrgencia El nivel de urgencia del paciente (1 = Alta, 2 = Media, 3 = Baja).
     * @throws IllegalArgumentException Si el nivel de urgencia no está entre 1 y 3.
     * @throws IllegalArgumentException Si la cédula tiene una longitud incorrecta.
     */
    public Paciente(String nombre, String cedula, int nivelUrgencia) {
        // Validación de la cédula (por ejemplo, longitud de la cédula)
        if (cedula == null || cedula.length() != 10) {
            throw new IllegalArgumentException("La cédula debe tener 10 dígitos.");
        }
        // Validación del nivel de urgencia
        if (nivelUrgencia < 1 || nivelUrgencia > 3) {
            throw new IllegalArgumentException("El nivel de urgencia debe ser entre 1 (alta) y 3 (baja).");
        }
        
        this.nombre = nombre;
        this.cedula = cedula;
        this.nivelUrgencia = nivelUrgencia;
    }

    /**
     * Getter para el nombre del paciente.
     * 
     * @return El nombre del paciente.
     */
    public String getNombre() { 
        return nombre; 
    }

    /**
     * Getter para la cédula del paciente.
     * 
     * @return La cédula del paciente.
     */
    public String getCedula() { 
        return cedula; 
    }

    /**
     * Getter para el nivel de urgencia del paciente.
     * 
     * @return El nivel de urgencia del paciente (1 = Alta, 2 = Media, 3 = Baja).
     */
    public int getNivelUrgencia() { 
        return nivelUrgencia; 
    }

    /**
     * Método abstracto que debe ser implementado por las clases hijas.
     * El tipo de paciente puede ser diferente dependiendo de la implementación de la clase hija.
     * 
     * @return El tipo de paciente.
     */
    public abstract String getTipo();

    /**
     * Método toString que devuelve una representación en cadena del paciente.
     * 
     * @return Una cadena con la información básica del paciente.
     */
    @Override
    public String toString() {
        return "Paciente [nombre=" + nombre + ", cedula=" + cedula + ", nivelUrgencia=" + nivelUrgencia + "]";
    }

    /**
     * Método para comparar el nivel de urgencia entre dos pacientes.
     * 
     * @param otroPaciente El otro paciente a comparar.
     * @return Un valor negativo si el nivel de urgencia del paciente actual es menor,
     *         cero si son iguales, y un valor positivo si el nivel de urgencia es mayor.
     */
    public int compararUrgencia(Paciente otroPaciente) {
        return Integer.compare(this.nivelUrgencia, otroPaciente.getNivelUrgencia());
    }
}
