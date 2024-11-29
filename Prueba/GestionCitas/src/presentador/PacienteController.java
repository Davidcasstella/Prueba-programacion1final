package presentador;

import java.util.ArrayList;
import java.util.List;

import modelo.Paciente;

/**
 * Clase que gestiona los pacientes, permitiendo agregar, buscar, eliminar y obtener la lista de pacientes.
 */
public class PacienteController {
    private List<Paciente> pacientes;  // Lista de pacientes gestionados por el controlador

    /**
     * Constructor que inicializa la lista de pacientes.
     */
    public PacienteController() {
        this.pacientes = new ArrayList<>();
    }

    /**
     * Agrega un paciente a la lista de pacientes gestionados.
     * 
     * @param paciente El paciente que se desea agregar a la lista.
     */
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    /**
     * Busca un paciente por su cédula en la lista de pacientes.
     * 
     * @param cedula La cédula del paciente que se desea buscar.
     * @return El paciente encontrado si existe, o null si no se encuentra.
     */
    public Paciente buscarPaciente(String cedula) {
        for (Paciente p : pacientes) {
            if (p.getCedula().equals(cedula)) {
                return p;  // Paciente encontrado
            }
        }
        return null;  // Paciente no encontrado
    }

    /**
     * Elimina un paciente de la lista de pacientes mediante su cédula.
     * 
     * @param cedula La cédula del paciente que se desea eliminar.
     */
    public void eliminarPaciente(String cedula) {
        Paciente paciente = buscarPaciente(cedula);
        if (paciente != null) {
            pacientes.remove(paciente);  // Eliminar el paciente de la lista
        }
    }

    /**
     * Obtiene la lista completa de pacientes gestionados.
     * 
     * @return La lista de pacientes.
     */
    public List<Paciente> obtenerPacientes() {
        return pacientes;
    }
}
