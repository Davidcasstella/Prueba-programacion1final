package ejecucion;

import java.time.LocalDate;
import javax.swing.*;
import modelo.Medico;
import modelo.Paciente;
import modelo.PacienteEmpresa;
import modelo.PacienteFisico;
import modelo.Turno;
import presentador.MedicoController;
import presentador.PacienteController;
import presentador.TurnoController;
import vista.TurnoException;

public class Run {
    
    // Controladores para manejar las operaciones relacionadas con pacientes, médicos y turnos
    private static PacienteController pacienteController = new PacienteController();
    private static MedicoController medicoController = new MedicoController();
    private static TurnoController turnoController = new TurnoController();
    

    /**
     * Método principal que inicia el sistema y muestra un menú para gestionar pacientes, médicos y turnos.
     * El bucle principal permite al usuario interactuar con el sistema hasta que decida salir.
     * 
     * @param args Argumentos de línea de comando (no utilizados en este caso).
     */
    public static void main(String[] args) {
        System.out.println("david lopez castellanos");
        
        // Bucle principal del programa, que permite gestionar las diferentes secciones
        while (true) {
            // Opciones que el usuario puede seleccionar desde el menú principal
            String[] opciones = { "Gestionar Pacientes", "Gestionar Médicos", "Gestionar Turnos", "Salir" };
            // Muestra el menú con las opciones disponibles
            int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Sistema de Turnos Médicos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            // Ejecuta las operaciones según la opción seleccionada
            switch (eleccion) {
            case 0 -> gestionarPacientes();  // Gestionar pacientes
            case 1 -> gestionarMedicos();    // Gestionar médicos
            case 2 -> gestionarTurnos();    // Gestionar turnos
            case 3 -> System.exit(0);       // Salir del programa
            default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    /**
     * Método para gestionar las operaciones relacionadas con los pacientes.
     * Permite agregar, eliminar, listar pacientes o regresar al menú principal.
     */
    private static void gestionarPacientes() {
        String[] opciones = { "Agregar Paciente", "Eliminar Paciente", "Listar Pacientes", "Regresar" };
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Pacientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
    
        switch (eleccion) {
        case 0 -> {
            try {
                // Solicita los datos básicos para todos los pacientes
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
                if (nombre == null || nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío.");
    
                String cedula = solicitarCedula();
    
                // Solicita el nivel de urgencia enumerado
                String urgenciaMensaje = """
                        Ingrese el nivel de urgencia:
                        1. Alta
                        2. Media
                        3. Baja
                        """;
                int urgencia = Integer.parseInt(JOptionPane.showInputDialog(urgenciaMensaje));
                if (urgencia < 1 || urgencia > 3) throw new Exception("El nivel de urgencia debe ser 1, 2 o 3.");
    
                // Solicita el tipo de paciente enumerado
                String tipoMensaje = """
                        Ingrese el tipo de paciente:
                        1. Físico
                        2. Empresa
                        """;
                int tipo = Integer.parseInt(JOptionPane.showInputDialog(tipoMensaje));
                if (tipo < 1 || tipo > 2) throw new Exception("El tipo de paciente debe ser 1 (Físico) o 2 (Empresa).");
    
                // Crea el paciente según el tipo seleccionado
                if (tipo == 1) { // Físico
                    pacienteController.agregarPaciente(new PacienteFisico(nombre, cedula, urgencia));
                    JOptionPane.showMessageDialog(null, "Paciente físico agregado exitosamente.");
                } else if (tipo == 2) { // Empresa
                    String empresa = JOptionPane.showInputDialog("Ingrese el nombre de la empresa:");
                    if (empresa == null || empresa.isEmpty())
                        throw new Exception("El nombre de la empresa no puede estar vacío.");
                    pacienteController.agregarPaciente(new PacienteEmpresa(nombre, cedula, urgencia, empresa));
                    JOptionPane.showMessageDialog(null, "Paciente de empresa agregado exitosamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        case 1 -> {
            try {
                String cedula = solicitarCedula();
                pacienteController.eliminarPaciente(cedula);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        case 2 -> {
            StringBuilder listado = new StringBuilder("Pacientes:\n");
            for (Paciente p : pacienteController.obtenerPacientes()) {
                listado.append("- ").append(p.getNombre()).append(" (").append(p.getCedula()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, listado.toString());
        }
        case 3 -> {
            // Regresar al menú principal
        }
        }
    }
    
    // Método auxiliar para validar que la cédula sea solo numérica
    private static String solicitarCedula() throws Exception {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula (solo números):");
        if (cedula == null || cedula.isEmpty()) {
            throw new Exception("La cédula no puede estar vacía.");
        }
        if (!cedula.matches("\\d+")) { // Verifica que solo contenga números
            throw new Exception("La cédula solo puede contener números, no letras ni caracteres especiales.");
        }
        return cedula;
    }
    

    
    
    
    /**
     * Método para gestionar las operaciones relacionadas con los médicos.
     * Permite agregar médicos, actualizar su disponibilidad y listar médicos.
     */
    private static void gestionarMedicos() {
        // Opciones disponibles en la gestión de médicos
        String[] opciones = { "Agregar Médico", "Actualizar Disponibilidad", "Listar Médicos", "Regresar" };
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Médicos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        // Ejecuta las operaciones según la opción seleccionada
        switch (eleccion) {
        case 0 -> {
            try {
                // Solicita los datos del nuevo médico
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del médico:");
                medicoController.agregarMedico(new Medico(nombre, especialidad));
                JOptionPane.showMessageDialog(null, "Médico agregado exitosamente.");
            } catch (Exception e) {
                // Maneja los errores al agregar el médico
                JOptionPane.showMessageDialog(null, "Error al agregar médico: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        case 1 -> {
            try {
                // Solicita los datos del médico y la fecha de disponibilidad
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                Medico medico = medicoController.buscarMedico(nombre);
                if (medico == null)
                    throw new Exception("Médico no encontrado.");

                String fecha = JOptionPane.showInputDialog("Ingrese la fecha de disponibilidad (YYYY-MM-DD):");
                boolean disponible = JOptionPane.showConfirmDialog(null, "¿Está disponible en esta fecha?",
                        "Actualizar Disponibilidad", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

                LocalDate fechaDisponibilidad = LocalDate.parse(fecha);
                medicoController.actualizarDisponibilidad(nombre, fechaDisponibilidad , disponible);

                JOptionPane.showMessageDialog(null, "Disponibilidad actualizada correctamente.");
            } catch (Exception e) {
                // Maneja los errores al actualizar la disponibilidad
                JOptionPane.showMessageDialog(null, "Error al actualizar disponibilidad: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        case 2 -> {
            try {
                // Muestra la lista de médicos registrados
                String medicos = medicoController.listarMedicos();
                JOptionPane.showMessageDialog(null, medicos, "Lista de Médicos", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                // Maneja los errores al listar médicos
                JOptionPane.showMessageDialog(null, "Error al listar médicos: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        case 3 -> {
        } // Regresa al menú anterior
        }
    }

    /**
     * Método para gestionar las operaciones relacionadas con los turnos.
     * Permite asignar turnos, listar turnos y ver el siguiente turno.
     */
    private static void gestionarTurnos() {
        // Opciones disponibles en la gestión de turnos
        String[] opciones = { "Asignar Turno", "Listar Turnos", "Siguiente Turno", "Regresar" };
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestión de Turnos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        // Ejecuta las operaciones según la opción seleccionada
        switch (eleccion) {
        case 0 -> {
            try {
                // Solicita los datos del paciente y médico para asignar un turno
                String cedulaPaciente = JOptionPane.showInputDialog("Ingrese la cédula del paciente:");
                Paciente paciente = pacienteController.buscarPaciente(cedulaPaciente);
                if (paciente == null)
                    throw new Exception("Paciente no encontrado.");

                String nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                Medico medico = medicoController.buscarMedico(nombreMedico);
                if (medico == null)
                    throw new Exception("Médico no encontrado.");

                String fecha = JOptionPane.showInputDialog("Ingrese la fecha de la cita (YYYY-MM-DD):");
                String hora = JOptionPane.showInputDialog("Ingrese la hora de la cita (HH:MM):");

                LocalDate fechaTurno = LocalDate.parse(fecha);
                if (!medico.isDisponible(fechaTurno)) {
                    throw new Exception("El médico no está disponible en la fecha especificada.");
                }

                // Asigna el turno al paciente con el médico correspondiente
                Turno turno = new Turno(paciente, medico, fecha, hora);
                turnoController.agregarTurno(turno);

                JOptionPane.showMessageDialog(null, "Turno asignado exitosamente.");
            } catch (TurnoException e) {
                // Maneja los errores específicos de turnos
                JOptionPane.showMessageDialog(null, "Error al asignar turno: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Maneja los errores generales
                JOptionPane.showMessageDialog(null, "Error al asignar turno: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        case 1 -> {
            // Muestra los turnos programados
            StringBuilder listado = new StringBuilder("Turnos programados:\n");
            for (Turno t : turnoController.obtenerTurnos()) {
                listado.append("- ").append(t.getPaciente().getNombre()).append(" con ").append(t.getMedico().getNombre())
                        .append(" en ").append(t.getFecha()).append(" a las ").append(t.getHora()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listado.toString());
        }
        case 2 -> {
			try {
                // Muestra el siguiente turno disponible
                Turno siguienteTurno = turnoController.siguienteTurno();
                if (siguienteTurno == null)
                    throw new Exception("No hay turnos asignados.");
                JOptionPane.showMessageDialog(null,
                        "Siguiente Turno: Paciente: " + siguienteTurno.getPaciente().getNombre() + ", Médico: "
                                + siguienteTurno.getMedico().getNombre() + ", Fecha: " + siguienteTurno.getFecha()
                                + ", Hora: " + siguienteTurno.getHora(),
                        "Siguiente Turno", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                // Maneja los errores al obtener el siguiente turno
                JOptionPane.showMessageDialog(null, "Error al obtener siguiente turno: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        case 3 -> {
        } // Regresa al menú anterior
        }
    }
}
