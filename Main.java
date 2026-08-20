import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static ArrayList<Curso> cursos = new ArrayList<>();
    static Stack<String> historial = new Stack<>();
    static ArbolCursos arbol = new ArbolCursos();
    static GrafoCursos grafo = new GrafoCursos();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {

            System.out.println("\nSISTEMA DE GESTION DE CURSOS UTC 3");
            System.out.println("1. Agregar curso");
            System.out.println("2. Mostrar cursos");
            System.out.println("3. Eliminar curso");
            System.out.println("4. Inscribir estudiante");
            System.out.println("5. Dar de baja estudiante");
            System.out.println("6. Insertar cursos en arbol binario");
            System.out.println("7. Buscar curso en arbol binario");
            System.out.println("8. Mostrar recorrido inorden");
            System.out.println("9. Crear relacion entre cursos");
            System.out.println("10. Mostrar grafo");
            System.out.println("11. Bubble Sort directo");
            System.out.println("12. Bubble Sort inverso");
            System.out.println("13. Insercion directa");
            System.out.println("14. Seleccion directa");
            System.out.println("15. Busqueda secuencial");
            System.out.println("16. Busqueda binaria");
            System.out.println("17. Historial");
            System.out.println("18. Cursos con cupo disponible");
            System.out.println("19. Salir");
            System.out.print("Opcion: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1:
                    agregar();
                    break;

                case 2:
                    mostrar();
                    break;

                case 3:
                    eliminar();
                    break;

                case 4:
                    inscribir();
                    break;

                case 5:
                    baja();
                    break;

                case 6:
                    insertarArbol();
                    break;

                case 7:
                    buscarArbol();
                    break;

                case 8:
                    System.out.println("\nRECORRIDO INORDEN ");
                    arbol.inorden();
                    break;

                case 9:
                    relacionar();
                    break;

                case 10:
                    grafo.mostrar();
                    break;

                case 11:
                    Ordenamientos.bubbleDirecto(cursos);
                    System.out.println("Ordenamiento Bubble Sort directo realizado.");
                    mostrar();
                    break;

                case 12:
                    Ordenamientos.bubbleInverso(cursos);
                    System.out.println("Ordenamiento Bubble Sort inverso realizado.");
                    mostrar();
                    break;

                case 13:
                    Ordenamientos.insercion(cursos);
                    System.out.println("Ordenamiento por insercion realizado.");
                    mostrar();
                    break;

                case 14:
                    Ordenamientos.seleccion(cursos);
                    System.out.println("Ordenamiento por seleccion realizado.");
                    mostrar();
                    break;

                case 15:
                    busquedaSecuencial();
                    break;

                case 16:
                    busquedaBinaria();
                    break;

                case 17:
                    mostrarHistorial();
                    break;

                case 18:
                    mostrarDisponibles();
                    break;

                case 19:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (op != 19);
    }

    static void agregar() {

        System.out.print("ID del curso: ");
        int id = Integer.parseInt(sc.nextLine());

        if (buscarID(id) != null) {
            System.out.println("El ID ya existe.");
            return;
        }

        System.out.print("Clave: ");
        String clave = sc.nextLine();

        if (buscarClave(clave) != null) {
            System.out.println("La clave ya existe.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Docente: ");
        String docente = sc.nextLine();

        System.out.print("Cupo maximo: ");
        int cupo = Integer.parseInt(sc.nextLine());

        Curso c = new Curso(id, clave, nombre, docente, cupo);

        cursos.add(c);
        grafo.agregarCurso(c);

        historial.push("Se agrego el curso: " + nombre);

        System.out.println("Curso agregado correctamente.");
    }

    static void mostrar() {

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        System.out.println("\n CURSOS");

        for (Curso c : cursos) {
            System.out.println(c);
        }
    }

    static void eliminar() {

        System.out.print("ID del curso: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = buscarID(id);

        if (c == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        cursos.remove(c);

        historial.push("Se elimino: " + c.getNombre());

        System.out.println("Curso eliminado.");
    }

    static void inscribir() {

        System.out.print("ID del curso: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = buscarID(id);

        if (c != null && c.inscribirEstudiante()) {

            historial.push("Inscripcion en: " + c.getNombre());

            System.out.println("Inscripcion realizada.");

        } else {

            System.out.println("No se pudo realizar la inscripcion.");
        }
    }

    static void baja() {

        System.out.print("ID del curso: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = buscarID(id);

        if (c != null && c.darDeBajaEstudiante()) {

            historial.push("Baja en: " + c.getNombre());

            System.out.println("Baja realizada.");

        } else {

            System.out.println("No se pudo realizar la baja.");
        }
    }

    static void insertarArbol() {

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos.");
            return;
        }

        for (Curso c : cursos) {
            arbol.insertar(c);
        }

        System.out.println("Cursos insertados en el arbol.");
    }

    static void buscarArbol() {

        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = arbol.buscar(id);

        if (c != null)
            System.out.println("Encontrado: " + c);
        else
            System.out.println("Curso no encontrado.");
    }

    static void relacionar() {

        System.out.print("ID del primer curso: ");
        int id1 = Integer.parseInt(sc.nextLine());

        System.out.print("ID del segundo curso: ");
        int id2 = Integer.parseInt(sc.nextLine());

        if (grafo.relacionar(id1, id2)) {

            historial.push("Relacion entre cursos " + id1 + " y " + id2);

            System.out.println("Relacion creada.");

        } else {

            System.out.println("Uno de los cursos no existe.");
        }
    }

    static void busquedaSecuencial() {

        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = Busquedas.secuencial(cursos, id);

        if (c != null)
            System.out.println("Encontrado: " + c);
        else
            System.out.println("Curso no encontrado.");
    }

    static void busquedaBinaria() {

        Ordenamientos.bubbleDirecto(cursos);

        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine());

        Curso c = Busquedas.binaria(cursos, id);

        if (c != null)
            System.out.println("Encontrado: " + c);
        else
            System.out.println("Curso no encontrado.");
    }

    static void mostrarHistorial() {

        if (historial.isEmpty()) {
            System.out.println("Historial vacio.");
            return;
        }

        System.out.println("\nHISTORIAL ");

        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println(historial.get(i));
        }
    }

    static void mostrarDisponibles() {

        System.out.println("\n CURSOS CON CUPO DISPONIBLE");

        boolean hay = false;

        for (Curso c : cursos) {

            if (c.getNumeroInscritos() < c.getCupoMaximo()) {

                System.out.println(c);
                hay = true;
            }
        }

        if (!hay)
            System.out.println("No hay cursos con cupo disponible.");
    }

    static Curso buscarID(int id) {

        for (Curso c : cursos) {

            if (c.getIdCurso() == id)
                return c;
        }

        return null;
    }

    static Curso buscarClave(String clave) {

        for (Curso c : cursos) {

            if (c.getClave().equalsIgnoreCase(clave))
                return c;
        }

        return null;
    }
}