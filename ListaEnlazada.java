public class ListaEnlazada {

    Nodo cabeza;

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean existeClave(String clave) {

        Nodo actual = cabeza;

        while (actual != null) {

            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                return true;
            }

            actual = actual.siguienteNodo;
        }

        return false;
    }


    public boolean agregarCurso(Curso curso) {

       
        if (existeClave(curso.getClave())) {
            return false;
        }

        Nodo nuevo = new Nodo(curso);

        
        if (cabeza == null) {
            cabeza = nuevo;
            return true;
        }

       
        Nodo actual = cabeza;

        while (actual.siguienteNodo != null) {
            actual = actual.siguienteNodo;
        }

        actual.siguienteNodo = nuevo;

        return true;
    }

   
    public void mostrarCursos() {

        if (cabeza == null) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        Nodo actual = cabeza;

        while (actual != null) {

            System.out.println(actual.curso);

            actual = actual.siguienteNodo;
        }
    }

   
    public Curso buscarCurso(String clave) {

        Nodo actual = cabeza;

        while (actual != null) {

            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                return actual.curso;
            }

            actual = actual.siguienteNodo;
        }

        return null;
    }

   
    public boolean eliminarCurso(String clave) {

        
        if (cabeza == null) {
            return false;
        }

        
        if (cabeza.curso.getClave().equalsIgnoreCase(clave)) {

            cabeza = cabeza.siguienteNodo;

            return true;
        }

        
        Nodo actual = cabeza;

        while (actual.siguienteNodo != null) {

            if (actual.siguienteNodo.curso
                    .getClave()
                    .equalsIgnoreCase(clave)) {

                actual.siguienteNodo =
                        actual.siguienteNodo.siguienteNodo;

                return true;
            }

            actual = actual.siguienteNodo;
        }

        return false;
    }

    
    public int contarRecursivo() {
        return contarDesdeNodo(cabeza);
    }

    private int contarDesdeNodo(Nodo nodo) {

        
        if (nodo == null) {
            return 0;
        }

        
        return 1 + contarDesdeNodo(nodo.siguienteNodo);
    }

    
    public Curso buscarRecursivo(String clave) {
        return buscarDesdeNodo(cabeza, clave);
    }

    private Curso buscarDesdeNodo(Nodo nodo, String clave) {

      
        if (nodo == null) {
            return null;
        }

      
        if (nodo.curso.getClave().equalsIgnoreCase(clave)) {
            return nodo.curso;
        }

        
        return buscarDesdeNodo(nodo.siguienteNodo, clave);
    }
}