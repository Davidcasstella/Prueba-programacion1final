package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import presentador.MedicoController;

/**
 * Clase que representa la vista para interactuar con el sistema de médicos.
 */
public class VistaMedico {
    private MedicoController medicoController;

    /**
     * Constructor de la clase VistaMedico.
     * 
     * @param medicoController El controlador de médicos que gestiona la lógica de negocio.
     */
    public VistaMedico(MedicoController medicoController) {
        this.medicoController = medicoController;
    }

    /**
     * Método para mostrar la disponibilidad de un médico en una fecha y hora específicas.
     * 
     * @param nombreMedico El nombre del médico que se desea consultar.
     * @param fecha La fecha para consultar la disponibilidad.
     * @param hora La hora para consultar la disponibilidad.
     */
    public void mostrarDisponibilidad(String nombreMedico, LocalDate fecha, String hora) {
        Boolean disponibilidad = medicoController.obtenerDisponibilidad(nombreMedico, fecha, hora); // Consulta disponibilidad

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formato de fecha para imprimir

        if (disponibilidad == null) {
            System.out.println("No se encontraron registros de disponibilidad para el médico " + nombreMedico + 
                " en la fecha " + fecha.format(formatter) + " a las " + hora + ".");
        } else if (disponibilidad) {
            System.out.println("El médico " + nombreMedico + " está disponible en la fecha " + 
                fecha.format(formatter) + " a las " + hora + ".");
        } else {
            System.out.println("El médico " + nombreMedico + " no está disponible en la fecha " + 
                fecha.format(formatter) + " a las " + hora + ".");
        }
    }

    /**
     * Método para mostrar el listado de médicos y sus disponibilidades.
     */
    public void mostrarListadoMedicos() {
        String listado = medicoController.listarMedicos(); // Genera el listado de médicos desde el controlador
        System.out.println(listado); // Muestra el listado en consola
    }

    /**
     * Método para actualizar la disponibilidad de un médico.
     * 
     * @param nombreMedico El nombre del médico a actualizar.
     * @param fecha La fecha de la disponibilidad a actualizar.
     * @param hora La hora de la disponibilidad a actualizar.
     * @param disponible El nuevo estado de disponibilidad (true para disponible, false para no disponible).
     */
    public void actualizarDisponibilidad(String nombreMedico, LocalDate fecha, String hora, boolean disponible) {
        medicoController.actualizarDisponibilidad(nombreMedico, fecha, hora, disponible); // Actualiza disponibilidad en el controlador
        System.out.println("Se ha actualizado la disponibilidad del médico " + nombreMedico + 
            " para la fecha " + fecha + " a las " + hora + ".");
    }
}
