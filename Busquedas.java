import java.util.ArrayList;

public class Busquedas {

    public static Curso secuencial(
            ArrayList<Curso> lista, int id) {

        for (Curso c : lista) {

            if (c.getIdCurso() == id) {
                return c;
            }
        }

        return null;
    }

    public static Curso binaria(
            ArrayList<Curso> lista, int id) {

        int inicio = 0;
        int fin = lista.size() - 1;

        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;

            int valor = lista.get(medio).getIdCurso();

            if (valor == id) {
                return lista.get(medio);
            }

            if (id < valor) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }

        return null;
    }
}