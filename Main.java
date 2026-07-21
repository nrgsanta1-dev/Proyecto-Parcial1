import java.util.Scanner;
import java.util.Stack;

public class Main {

    static ListaEnlazada cursos = new ListaEnlazada();
    static ListaDobleEnlazada doble = new ListaDobleEnlazada();
    static Stack<String> historial = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n===== SISTEMA DE GESTION DE CURSOS UTC =====");
            System.out.println("1. Agregar curso");
            System.out.println("2. Mostrar cursos");
            System.out.println("3. Buscar curso por clave");
            System.out.println("4. Eliminar curso");
            System.out.println("5. Inscribir estudiante");
            System.out.println("6. Dar de baja estudiante");
            System.out.println("7. Mostrar de inicio a fin");
            System.out.println("8. Mostrar de fin a inicio");
            System.out.println("9. Navegador de cursos");
            System.out.println("10. Contar cursos recursivamente");
            System.out.println("11. Buscar curso recursivamente");
            System.out.println("12. Mostrar historial");
            System.out.println("13. Salir");
            System.out.print("Opcion: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> agregar();
                case 2 -> cursos.mostrarCursos();
                case 3 -> buscar(false);
                case 4 -> eliminar();
                case 5 -> inscribir();
                case 6 -> baja();
                case 7 -> doble.mostrarInicioFin();
                case 8 -> doble.mostrarFinInicio();
                case 9 -> navegador();
                case 10 -> System.out.println("Total: " + cursos.contarRecursivo());
                case 11 -> buscar(true);
                case 12 -> historial.forEach(System.out::println);
                case 13 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida.");
            }

        } while (op != 13);
    }

    static void agregar() {

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        if (cursos.existeClave(clave)) {
            System.out.println("La clave ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Docente: ");
        String docente = sc.nextLine();

        System.out.print("Cupo: ");
        int cupo = Integer.parseInt(sc.nextLine());

        Curso c = new Curso(clave, nombre, docente, cupo);

        cursos.agregarCurso(c);
        doble.agregarFinal(c);
        historial.push("Se agrego: " + nombre);

        System.out.println("Curso agregado correctamente.");
    }

    static void buscar(boolean recursivo) {

        System.out.print("Clave: ");

        String clave = sc.nextLine();

        Curso c = recursivo
                ? cursos.buscarRecursivo(clave)
                : cursos.buscarCurso(clave);

        System.out.println(c != null ? c : "Curso no encontrado.");
    }

    static void eliminar() {

        System.out.print("Clave: ");

        String clave = sc.nextLine();

        Curso c = cursos.buscarCurso(clave);

        if (c != null && cursos.eliminarCurso(clave)) {
            historial.push("Se elimino: " + c.getNombre());
            System.out.println("Curso eliminado.");
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    static void inscribir() {

        System.out.print("Clave: ");

        Curso c = cursos.buscarCurso(sc.nextLine());

        if (c != null && c.inscribirEstudiante()) {
            historial.push("Inscripcion en: " + c.getNombre());
            System.out.println("Inscripcion realizada.");
        } else {
            System.out.println("No se pudo inscribir.");
        }
    }

    static void baja() {

        System.out.print("Clave: ");

        Curso c = cursos.buscarCurso(sc.nextLine());

        if (c != null && c.darDeBajaEstudiante()) {
            historial.push("Baja en: " + c.getNombre());
            System.out.println("Baja realizada.");
        } else {
            System.out.println("No se pudo realizar la baja.");
        }
    }

    static void navegador() {

        if (doble.estaVacia()) {
            System.out.println("No hay cursos.");
            return;
        }

        NodoDoble actual = doble.getCabeza();
        int op;

        do {
            System.out.println("\nCurso actual: " + actual.curso);
            System.out.println("1. Siguiente");
            System.out.println("2. Anterior");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");

            op = Integer.parseInt(sc.nextLine());

            if (op == 1 && actual.siguiente != null)
                actual = actual.siguiente;
            else if (op == 2 && actual.anterior != null)
                actual = actual.anterior;
            else if (op != 3)
                System.out.println("No puedes avanzar en esa direccion.");

        } while (op != 3);
    }
}