package modelo;

import java.util.List;
import java.util.ArrayList;

public class Agenda {
    private List<Turno> turnos;

    public Agenda() {
        this.turnos = new ArrayList<>();
    }

    public boolean agregarTurno(Turno turno) {
        // Lógica para agregar un turno, evitando conflictos de disponibilidad
        turnos.add(turno);
        return true;
    }

    public List<Turno> obtenerTurnos() {
        return turnos; // Devuelve la lista de turnos agendados
    }
}
