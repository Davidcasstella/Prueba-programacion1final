package modelo;

import java.util.List;
import java.util.ArrayList;

public class HistorialPaciente {
    private Paciente paciente;
    private List<Turno> turnos;

    public HistorialPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    public List<Turno> obtenerHistorial() {
        return turnos;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
