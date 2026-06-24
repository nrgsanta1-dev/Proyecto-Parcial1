import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SistemaGestion {
    private static ArrayList<Curso> listaCursos = new ArrayList<>();
    private static Stack<String> historialAcciones = new Stack<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE CURSOS UTC ---");
            System.out.println("1. Agregar curso");
            System.out.println("2. Mostrar cursos");
            System.out.println("3. Buscar curso por clave");
            System.out.println("4. Inscribir estudiante");
            System.out.println("5. Dar de baja estudiante");
            System.out.println("6. Eliminar curso");
            System.out.println("7. Mostrar historial de acciones");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarCurso();
                case 2 -> mostrarCursos();
                case 3 -> buscarCurso();
                case 4 -> inscribirEstudiante();
                case 5 -> darDeBajaEstudiante();
                case 6 -> eliminarCurso();
                case 7 -> mostrarHistorial();
                case 8 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
    }

    private static void agregarCurso() {
        System.out.print("Ingrese la clave del curso: ");
        String clave = scanner.nextLine();
        System.out.print("Ingrese el nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nombre del docente: ");
        String docente = scanner.nextLine();
        System.out.print("Ingrese el cupo máximo: ");
        
        int cupo = 30;
        try {
            cupo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cupo no válido, asignando 30 por defecto.");
        }

        Curso nuevoCurso = new Curso(clave, nombre, docente, cupo);
        listaCursos.add(nuevoCurso);
        historialAcciones.push("Se agregó el curso: " + nombre);
        
        System.out.println("!!! CURSO GUARDADO CON ÉXITO !!!");
        System.out.println("Cursos en memoria actualmente: " + listaCursos.size());
    }

    private static void mostrarCursos() {
        if (listaCursos.isEmpty()) {
            System.out.println("ALERTA: El ArrayList está vacío. No hay cursos.");
            return;
        }
        System.out.println("\n--- LISTA DE CURSOS EN EL ARRAYLIST ---");
        for (Curso c : listaCursos) {
            System.out.println(c.toString());
        }
    }

    private static void buscarCurso() {
        System.out.print("Ingrese la clave a buscar: ");
        String clave = scanner.nextLine();
        Curso curso = encontrarCursoPorClave(clave);
        if (curso != null) System.out.println("Encontrado: " + curso);
        else System.out.println("Curso no encontrado.");
    }

    private static void inscribirEstudiante() {
        System.out.print("Ingrese la clave del curso: ");
        String clave = scanner.nextLine();
        Curso curso = encontrarCursoPorClave(clave);
        if (curso != null && curso.inscribirEstudiante()) {
            historialAcciones.push("Se inscribió un estudiante en " + curso.getNombre());
            System.out.println("Inscrito con éxito.");
        } else {
            System.out.println("No se pudo inscribir (Curso lleno o no existe).");
        }
    }

    private static void darDeBajaEstudiante() {
        System.out.print("Ingrese la clave del curso: ");
        String clave = scanner.nextLine();
        Curso curso = encontrarCursoPorClave(clave);
        if (curso != null && curso.darDeBajaEstudiante()) {
            historialAcciones.push("Se dio de baja un estudiante en " + curso.getNombre());
            System.out.println("Baja realizada.");
        } else {
            System.out.println("No se pudo dar de baja.");
        }
    }

    private static void eliminarCurso() {
        System.out.print("Ingrese la clave del curso a eliminar: ");
        String clave = scanner.nextLine();
        Curso curso = encontrarCursoPorClave(clave);
        if (curso != null) {
            listaCursos.remove(curso);
            historialAcciones.push("Se eliminó el curso: " + curso.getNombre());
            System.out.println("Curso eliminado.");
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void mostrarHistorial() {
        if (historialAcciones.isEmpty()) {
            System.out.println("Historial vacío.");
            return;
        }
        System.out.println("\n--- HISTORIAL ---");
        @SuppressWarnings("unchecked")
        Stack<String> tempStack = (Stack<String>) historialAcciones.clone();
        while (!tempStack.isEmpty()) {
            System.out.println("- " + tempStack.pop());
        }
    }

    private static Curso encontrarCursoPorClave(String clave) {
        for (Curso c : listaCursos) {
            if (c.getClave().equalsIgnoreCase(clave)) return c;
        }
        return null;
    }
}