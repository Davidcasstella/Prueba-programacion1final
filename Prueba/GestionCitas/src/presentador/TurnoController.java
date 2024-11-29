package presentador;

import modelo.Medico;
import modelo.Turno;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class TurnoController {
    public TurnoController() {
        // Inicialización aquí, si es necesario
    }
    
    private List<Turno> turnos;  // Lista de turnos disponibles
    private List<Medico> medicos;  // Lista de médicos disponibles

    public TurnoController(List<Medico> medicos) {
        if (medicos == null || medicos.isEmpty()) {
            throw new IllegalArgumentException("La lista de médicos no puede ser nula ni vacía.");
        }
        this.turnos = new ArrayList<>();
        this.medicos = medicos;
    }

    /**
     * Obtiene la lista de todos los turnos.
     * @return Lista de todos los turnos.
     */
    public List<Turno> obtenerTurnos() {
        return turnos;
    }

    /**
     * Obtiene la lista de turnos disponibles para un médico específico.
     * @param medico El médico para el cual se obtienen los turnos.
     * @return Lista de turnos disponibles para ese médico.
     */
    public List<Turno> obtenerTurnosDisponibles(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        List<Turno> turnosDisponibles = new ArrayList<>();
        for (Turno turno : turnos) {
            if (turno.getMedico().equals(medico) && turno.getDisponible()) {
                turnosDisponibles.add(turno);
            }
        }
        return turnosDisponibles;
    }

    /**
     * Obtiene la lista de todos los médicos.
     * @return Lista de médicos.
     */
    public List<Medico> obtenerMedicos() {
        return medicos;
    }

    /**
     * Reserva un turno si es posible.
     * @param medico El médico con el que se desea reservar el turno.
     * @param fecha La fecha del turno.
     * @param hora La hora del turno.
     * @return true si se reserva con éxito, false si no se pudo reservar.
     */
    public boolean reservarTurno(Medico medico, LocalDate fecha, String hora) {
        if (medico == null || fecha == null || hora == null || hora.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros del turno no pueden ser nulos o vacíos.");
        }
        for (Turno turno : turnos) {
            if (turno.getMedico().equals(medico) && turno.getFecha().equals(fecha) && turno.getHora().equals(hora)) {
                if (turno.getDisponible()) {
                    turno.setDisponible(false);  // Marca el turno como reservado
                    System.out.println("Turno reservado con éxito: " + medico.getNombre() + " - Fecha: " + fecha + " - Hora: " + hora);
                    return true;
                } else {
                    System.out.println("El turno ya ha sido reservado o no está disponible.");
                    return false;
                }
            }
        }
        System.out.println("No se encontró un turno disponible para la fecha y hora solicitadas.");
        return false;  // Si no se encuentra el turno, no se puede reservar
    }

    /**
     * Agrega un turno a la lista de turnos.
     * @param turno El turno que se desea agregar.
     */
    public void agregarTurno(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("El turno no puede ser nulo.");
        }
        turnos.add(turno);
        System.out.println("Turno agregado: " + turno.getMedico().getNombre() + " - Fecha: " + turno.getFecha() + " - Hora: " + turno.getHora());
    }

    /**
     * Obtiene el siguiente turno en la lista (el primero disponible).
     * @return El siguiente turno (el primero de la lista) o null si no hay turnos disponibles.
     */
    public Turno siguienteTurno() {
        if (turnos.isEmpty()) {
            return null;
        }
        for (Turno turno : turnos) {
            if (turno.getDisponible()) {
                return turno;  // Devuelve el primer turno disponible
            }
        }
        return null;  // Si no hay turnos disponibles, retorna null
    }
}
