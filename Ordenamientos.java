import java.util.ArrayList;

public class Ordenamientos {

    public static void bubbleDirecto(ArrayList<Curso> lista) {

        for (int i = 0; i < lista.size() - 1; i++) {

            for (int j = 0; j < lista.size() - 1 - i; j++) {

                if (lista.get(j).getIdCurso() >
                    lista.get(j + 1).getIdCurso()) {

                    Curso temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public static void bubbleInverso(ArrayList<Curso> lista) {

        for (int i = 0; i < lista.size() - 1; i++) {

            for (int j = 0; j < lista.size() - 1 - i; j++) {

                if (lista.get(j).getIdCurso() <
                    lista.get(j + 1).getIdCurso()) {

                    Curso temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public static void insercion(ArrayList<Curso> lista) {

        for (int i = 1; i < lista.size(); i++) {

            Curso actual = lista.get(i);
            int j = i - 1;

            while (j >= 0 &&
                   lista.get(j).getIdCurso() >
                   actual.getIdCurso()) {

                lista.set(j + 1, lista.get(j));
                j--;
            }

            lista.set(j + 1, actual);
        }
    }

    public static void seleccion(ArrayList<Curso> lista) {

        for (int i = 0; i < lista.size() - 1; i++) {

            int menor = i;

            for (int j = i + 1; j < lista.size(); j++) {

                if (lista.get(j).getIdCurso() <
                    lista.get(menor).getIdCurso()) {

                    menor = j;
                }
            }

            Curso temp = lista.get(i);
            lista.set(i, lista.get(menor));
            lista.set(menor, temp);
        }
    }
}