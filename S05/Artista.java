public class Artista implements Comparable<Artista> {

    private String nombre;
    private String genero;
    private int dia;
    private String escenario;
    private int duracion;
    private int popularidad;

    /** Constructor completo */
    public Artista(String nombre, String genero, int dia, String escenario, int duracion, int popularidad) {
        this.nombre = nombre;
        this.genero = genero;
        this.dia = dia;
        this.escenario = escenario;
        this.duracion = duracion;
        this.popularidad = popularidad;
    }

    /** Getters */
    public String getNombre() {
        return nombre; 
    }
    public String getGenero() {
        return genero; 
    }
    public int getDia() { 
        return dia; 
    }
    public String getEscenario() { 
        return escenario; 
    }
    public int getDuracion() { 
        return duracion; 
    }
    public int getPopularidad() { 
        return popularidad; 
    }

    /** Setters */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setGenero(String genero) { 
        this.genero = genero; 
    }
    public void setDia(int dia) { 
        this.dia = dia; 
    }
    public void setEscenario(String escenario) { 
        this.escenario = escenario; 
    }
    public void setDuracion(int duracion) { 
        this.duracion = duracion; 
    }
    public void setPopularidad(int popularidad) { 
        this.popularidad = popularidad; 
    }
    
    public int compareTo(Artista otro) {
        if (this.popularidad != otro.popularidad)
            return Integer.compare(this.popularidad, otro.popularidad);
        if (this.duracion != otro.duracion)
            return Integer.compare(this.duracion, otro.duracion);
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    public String toString() {
        return String.format("%s (%s) - Dia %d, %s, %d min, Popularidad %d",
                nombre, genero, dia, escenario, duracion, popularidad);
    }
}
