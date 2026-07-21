public class NodoDoble {

    Curso curso;
    NodoDoble siguiente;
    NodoDoble anterior;

    public NodoDoble(Curso curso) {
        this.curso = curso;
        this.siguiente = null;
        this.anterior = null;
    }
}