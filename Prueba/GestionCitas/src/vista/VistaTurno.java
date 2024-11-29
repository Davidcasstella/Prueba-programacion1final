package vista;

import java.util.List;
import presentador.TurnoController;
import modelo.Turno;
import modelo.Medico;
import java.time.LocalDate;

public class VistaTurno {
    private TurnoController turnoController;

    public VistaTurno(TurnoController turnoController) {
        this.turnoController = turnoController;
    }

    /**
     * Muestra los turnos disponibles para todos los médicos y sus horarios.
     */
    public void mostrarTurnosDisponibles() {
        // Obtiene los médicos desde el controlador
        List<Medico> medicos = turnoController.obtenerMedicos();
        
        for (Medico medico : medicos) {
            System.out.println("Turnos disponibles para " + medico.getNombre() + " (" + medico.getEspecialidad() + "):");
            // Itera sobre las disponibilidades de cada médico
            for (LocalDate fecha : medico.getDisponibilidades().keySet()) {
                // Muestra las horas disponibles para cada fecha
                for (String hora : medico.getDisponibilidades().get(fecha).keySet()) {
                    boolean disponible = medico.isDisponible(fecha, hora);
                    if (disponible) {
                        System.out.println("  " + fecha + " - " + hora);
                    }
                }
            }
        }
    }

    /**
     * Permite reservar un turno con el médico seleccionado, en la fecha y hora proporcionadas.
     * @param medico El médico con el que se desea reservar el turno.
     * @param fecha La fecha del turno.
     * @param hora La hora del turno.
     */
    public void reservarTurno(Medico medico, LocalDate fecha, String hora) {
        boolean exito = turnoController.reservarTurno(medico, fecha, hora);
        if (exito) {
            System.out.println("Turno reservado con éxito.");
        } else {
            System.out.println("No se pudo reservar el turno. El médico no está disponible.");
        }
    }
}
