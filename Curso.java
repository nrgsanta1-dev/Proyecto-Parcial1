public class Curso {

    private int idCurso;
    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int numeroInscritos;

    public Curso(int idCurso, String clave, String nombre, String docente, int cupoMaximo) {
        this.idCurso = idCurso;
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.numeroInscritos = 0;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocente() {
        return docente;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public int getNumeroInscritos() {
        return numeroInscritos;
    }

    public boolean inscribirEstudiante() {
        if (numeroInscritos < cupoMaximo) {
            numeroInscritos++;
            return true;
        }
        return false;
    }

    public boolean darDeBajaEstudiante() {
        if (numeroInscritos > 0) {
            numeroInscritos--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + idCurso +
               " | Clave: " + clave +
               " | Curso: " + nombre +
               " | Docente: " + docente +
               " | Inscritos: " +
               numeroInscritos + "/" + cupoMaximo;
    }
}