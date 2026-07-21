public class ListaDobleEnlazada {

    NodoDoble cabeza;
    NodoDoble cola;

   
    public void agregarFinal(Curso curso) {

        NodoDoble nuevo = new NodoDoble(curso);

        
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            return;
        }

        
        cola.siguiente = nuevo;
        nuevo.anterior = cola;

        cola = nuevo;
    }

    
    public void mostrarInicioFin() {

        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoDoble actual = cabeza;

        System.out.println("\n===== CURSOS DE INICIO A FIN =====");

        while (actual != null) {

            System.out.println(actual.curso);

            actual = actual.siguiente;
        }
    }

    
    public void mostrarFinInicio() {

        if (cola == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoDoble actual = cola;

        System.out.println("\n===== CURSOS DE FIN A INICIO =====");

        while (actual != null) {

            System.out.println(actual.curso);

            actual = actual.anterior;
        }
    }

    
    public Curso buscar(String clave) {

        NodoDoble actual = cabeza;

        while (actual != null) {

            if (actual.curso.getClave()
                    .equalsIgnoreCase(clave)) {

                return actual.curso;
            }

            actual = actual.siguiente;
        }

        return null;
    }

   
    public NodoDoble getCabeza() {
        return cabeza;
    }

    
    public NodoDoble getCola() {
        return cola;
    }

    
    public boolean estaVacia() {
        return cabeza == null;
    }
}