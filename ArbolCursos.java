public class ArbolCursos {

    private NodoArbolCurso raiz;

    public void insertar(Curso curso) {
        raiz = insertarRec(raiz, curso);
    }

    private NodoArbolCurso insertarRec(
            NodoArbolCurso nodo, Curso curso) {

        if (nodo == null) {
            return new NodoArbolCurso(curso);
        }

        if (curso.getIdCurso() < nodo.curso.getIdCurso()) {
            nodo.izquierda = insertarRec(nodo.izquierda, curso);
        } else if (curso.getIdCurso() > nodo.curso.getIdCurso()) {
            nodo.derecha = insertarRec(nodo.derecha, curso);
        }

        return nodo;
    }

    public Curso buscar(int id) {
        return buscarRec(raiz, id);
    }

    private Curso buscarRec(NodoArbolCurso nodo, int id) {

        if (nodo == null) {
            return null;
        }

        if (id == nodo.curso.getIdCurso()) {
            return nodo.curso;
        }

        if (id < nodo.curso.getIdCurso()) {
            return buscarRec(nodo.izquierda, id);
        }

        return buscarRec(nodo.derecha, id);
    }

    public void inorden() {
        inordenRec(raiz);
    }

    private void inordenRec(NodoArbolCurso nodo) {

        if (nodo != null) {
            inordenRec(nodo.izquierda);
            System.out.println(nodo.curso);
            inordenRec(nodo.derecha);
        }
    }
}