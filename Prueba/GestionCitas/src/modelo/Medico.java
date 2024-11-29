package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa a un médico con su información y disponibilidades.
 */
public class Medico {

    // Atributos de la clase Medico
    private String nombre; // Nombre del médico
    private String especialidad; // Especialidad del médico
    private Map<LocalDate, Map<String, Boolean>> disponibilidades; // Disponibilidades del médico

    /**
     * Constructor de la clase Medico.
     * 
     * @param nombre El nombre del médico.
     * @param especialidad La especialidad del médico.
     */
    public Medico(String nombre, String especialidad) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del médico no puede ser nulo o vacío.");
        }
        if (especialidad == null || especialidad.isEmpty()) {
            throw new IllegalArgumentException("La especialidad del médico no puede ser nula o vacía.");
        }
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponibilidades = new HashMap<>();
    }

    /**
     * Agrega o actualiza la disponibilidad del médico para una fecha y hora específicas.
     * 
     * @param fecha La fecha de la disponibilidad.
     * @param hora La hora de la disponibilidad.
     * @param disponible Indica si está disponible (true) o no (false).
     */
    public void agregarDisponibilidad(LocalDate fecha, String hora, boolean disponible) {
        if (fecha == null || hora == null || hora.isEmpty()) {
            throw new IllegalArgumentException("La fecha y la hora no pueden ser nulas o vacías.");
        }
        disponibilidades.putIfAbsent(fecha, new HashMap<>());
        disponibilidades.get(fecha).put(hora, disponible);
    }

    /**
     * Verifica si el médico está disponible en una fecha y hora específicas.
     * 
     * @param fecha La fecha a consultar.
     * @param hora La hora a consultar.
     * @return true si está disponible, false en caso contrario.
     */
    public boolean isDisponible(LocalDate fecha, String hora) {
        if (fecha == null || hora == null || hora.isEmpty()) {
            return false;
        }
        return disponibilidades.getOrDefault(fecha, new HashMap<>()).getOrDefault(hora, false);
    }

    /**
     * Verifica si el médico está disponible en cualquier horario de un día específico.
     * 
     * @param dia El día de la semana (1 = Lunes, 7 = Domingo).
     * @return true si está disponible en algún horario, false en caso contrario.
     */
    public boolean isDisponibleDiaSemana(int dia) {
        if (dia < 1 || dia > 7) {
            throw new IllegalArgumentException("El día debe estar entre 1 (Lunes) y 7 (Domingo).");
        }
        for (Map.Entry<LocalDate, Map<String, Boolean>> entry : disponibilidades.entrySet()) {
            if (entry.getKey().getDayOfWeek().getValue() == dia) {
                if (entry.getValue().containsValue(true)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Obtiene todas las disponibilidades del médico.
     * 
     * @return Un mapa con fechas, horas y su estado de disponibilidad.
     */
    public Map<LocalDate, Map<String, Boolean>> getDisponibilidades() {
        return disponibilidades;
    }

    /**
     * Obtiene el nombre del médico.
     * 
     * @return El nombre del médico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la especialidad del médico.
     * 
     * @return La especialidad del médico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Formatea una fecha a un String con formato "dd-MM-yyyy".
     * 
     * @param fecha La fecha a formatear.
     * @return La fecha formateada.
     */
    private String formatFecha(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fecha.format(formatter);
    }
}
