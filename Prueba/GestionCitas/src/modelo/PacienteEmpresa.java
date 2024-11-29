package modelo;

/**
 * Clase que representa un tipo de paciente específico: un paciente perteneciente a una empresa.
 * Esta clase extiende de la clase abstracta Paciente y agrega un atributo específico para 
 * almacenar el nombre de la empresa a la que pertenece el paciente.
 */
public class PacienteEmpresa extends Paciente {

    // Atributo específico de PacienteEmpresa
    private String empresa; // Nombre de la empresa a la que pertenece el paciente

    /**
     * Constructor de la clase PacienteEmpresa.
     * 
     * @param nombre El nombre del paciente.
     * @param cedula La cédula del paciente.
     * @param nivelUrgencia El nivel de urgencia del paciente (1 = Alta, 2 = Media, 3 = Baja).
     * @param empresa El nombre de la empresa a la que pertenece el paciente.
     */
    public PacienteEmpresa(String nombre, String cedula, int nivelUrgencia, String empresa) {
        super(nombre, cedula, nivelUrgencia); // Llamada al constructor de la clase base (Paciente)
        this.empresa = empresa; // Asigna el nombre de la empresa al atributo empresa
    }

    /**
     * Getter para el nombre de la empresa a la que pertenece el paciente.
     * 
     * @return El nombre de la empresa.
     */
    public String getEmpresa() { 
        return empresa; 
    }

    /**
     * Implementación del método abstracto getTipo() de la clase base Paciente.
     * Este método devuelve el tipo de paciente en formato de texto, indicando que 
     * el paciente pertenece a una empresa.
     * 
     * @return El tipo de paciente, que en este caso incluye el nombre de la empresa.
     */
    @Override
    public String getTipo() {
        return "Empresa: " + empresa; // Devuelve el tipo de paciente concatenado con el nombre de la empresa
    }
}
