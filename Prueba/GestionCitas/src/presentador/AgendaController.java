package presentador;

import modelo.Agenda;
import modelo.Turno;
import java.util.List;

public class AgendaController {
    private Agenda agenda;

    public AgendaController() {
        this.agenda = new Agenda();
    }

    public boolean reservarTurno(Turno turno) {
        return agenda.agregarTurno(turno);
    }

    public List<Turno> obtenerTurnos() {
        return agenda.obtenerTurnos();
    }
}
