import java.util.ArrayList;

public class GrafoCursos {

    private ArrayList<Curso> cursos = new ArrayList<>();
    private int[][] matriz = new int[20][20];

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public boolean relacionar(int id1, int id2) {

        int a = posicion(id1);
        int b = posicion(id2);

        if (a == -1 || b == -1) {
            return false;
        }

        matriz[a][b] = 1;
        return true;
    }

    private int posicion(int id) {

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getIdCurso() == id) {
                return i;
            }
        }

        return -1;
    }

    public void mostrar() {

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos en el grafo.");
            return;
        }

        System.out.println("\nMATRIZ DE ADYACENCIA ");

        System.out.print("     ");

        for (Curso c : cursos) {
            System.out.print(c.getIdCurso() + " ");
        }

        System.out.println();

        for (int i = 0; i < cursos.size(); i++) {

            System.out.print(cursos.get(i).getIdCurso() + "    ");

            for (int j = 0; j < cursos.size(); j++) {
                System.out.print(matriz[i][j] + " ");
            }

            System.out.println();
        }
    }
}