package presentador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Medico;

/**
 * Clase que gestiona los médicos, sus disponibilidades y permite realizar varias operaciones sobre ellos.
 */
public class MedicoController {
    private List<Medico> medicos;  // Lista de médicos gestionados por el controlador

    /**
     * Constructor que inicializa la lista de médicos.
     */
    public MedicoController() {
        this.medicos = new ArrayList<>();
    }

    /**
     * Agrega un médico a la lista de médicos gestionados.
     * 
     * @param medico El médico que se desea agregar a la lista.
     * @throws IllegalArgumentException si el médico es nulo.
     */
    public void agregarMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        medicos.add(medico);
        System.out.println("Médico agregado: " + medico.getNombre());
    }

    /**
     * Busca un médico por su nombre en la lista de médicos.
     * 
     * @param nombre El nombre del médico que se desea buscar.
     * @return El médico encontrado si existe, o null si no se encuentra.
     * @throws IllegalArgumentException si el nombre es nulo o vacío.
     */
    public Medico buscarMedico(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del médico no puede ser nulo o vacío.");
        }
        return medicos.stream()
            .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }

    /**
     * Obtiene la lista completa de médicos gestionados.
     * 
     * @return La lista de médicos.
     */
    public List<Medico> obtenerMedicos() {
        return new ArrayList<>(medicos); // Devolver una copia para evitar modificaciones externas
    }

    /**
     * Verifica si un médico está disponible en una fecha y hora específicas.
     * 
     * @param nombre El nombre del médico.
     * @param fecha La fecha de la disponibilidad.
     * @param hora La hora de la disponibilidad.
     * @return true si el médico está disponible, false si no lo está o si no existe.
     * @throws IllegalArgumentException si los parámetros de entrada son nulos o inválidos.
     */
    public boolean obtenerDisponibilidad(String nombre, LocalDate fecha, String hora) {
        if (nombre == null || nombre.isEmpty() || fecha == null || hora == null || hora.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros de disponibilidad no pueden ser nulos o vacíos.");
        }

        Medico medico = buscarMedico(nombre);
        if (medico != null) {
            return medico.isDisponible(fecha, hora);
        }
        System.out.println("Médico no encontrado: " + nombre);
        return false;
    }

    /**
     * Genera un listado de médicos con su nombre, especialidad y sus disponibilidades.
     * 
     * @return Un String con el listado completo de médicos y su disponibilidad.
     */
    public String listarMedicos() {
        StringBuilder listado = new StringBuilder("Lista de Médicos:\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Iterar sobre cada médico en la lista
        for (Medico medico : medicos) {
            listado.append("Nombre: ").append(medico.getNombre())
                .append("\nEspecialidad: ").append(medico.getEspecialidad())
                .append("\nDisponibilidad:\n");

            // Mostrar disponibilidad específica de cada médico (fecha + hora)
            medico.getDisponibilidades().forEach((fecha, horas) -> horas.forEach((hora, disponible) -> 
                listado.append(" - ").append(fecha.format(formatter))
                    .append(" a las ").append(hora)
                    .append(": ").append(disponible ? "Disponible" : "No disponible").append("\n")
            ));

            listado.append("\n");  // Separar cada médico por una línea
        }
        return listado.toString();
    }

    /**
     * Actualiza la disponibilidad de un médico para una fecha y hora específicas.
     * 
     * @param nombre El nombre del médico cuya disponibilidad se desea actualizar.
     * @param fechaDisponibilidad La fecha en la que se desea actualizar la disponibilidad.
     * @param hora La hora en la que se desea actualizar la disponibilidad.
     * @param disponible El estado de disponibilidad: true si está disponible, false si no.
     * @throws IllegalArgumentException si los parámetros son inválidos.
     */
    public void actualizarDisponibilidad(String nombre, LocalDate fechaDisponibilidad, String hora, boolean disponible) {
        if (nombre == null || nombre.isEmpty() || fechaDisponibilidad == null || hora == null || hora.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros de disponibilidad no pueden ser nulos o vacíos.");
        }

        Medico medico = buscarMedico(nombre);
        if (medico != null) {
            medico.agregarDisponibilidad(fechaDisponibilidad, hora, disponible);
            System.out.println("Disponibilidad actualizada para " + medico.getNombre() + " en " 
                + fechaDisponibilidad.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " a las " + hora);
        } else {
            System.out.println("Médico no encontrado: " + nombre);
        }
    }
}
