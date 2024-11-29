package presentador;

import modelo.HistorialPaciente;
import modelo.Turno;
import java.util.List;

public class HistorialController {
    private HistorialPaciente historial;

    public HistorialController(HistorialPaciente historial) {
        this.historial = historial;
    }

    public void agregarTurno(Turno turno) {
        historial.agregarTurno(turno);
    }

    public List<Turno> obtenerHistorial() {
        return historial.obtenerHistorial();
    }
}
