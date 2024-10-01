import java.util.ArrayList;
import java.util.List;

abstract class Personaje {
    protected String nombre;
    protected int energia;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.energia = 0;
    }

    public abstract void comerSemilla();
}

class Terricola extends Personaje {
    public Terricola(String nombre) {
        super(nombre);
    }

    @Override
    public void comerSemilla() {
        energia += 200;
        System.out.println(nombre + " (Terrícola) ha comido una Semilla del Ermitaño y ahora tiene " + energia + " de energía.");
    }
}

class Extraterrestre extends Personaje {
    protected int aliados;

    public Extraterrestre(String nombre, int aliados) {
        super(nombre);
        this.aliados = aliados;
    }

    @Override
    public void comerSemilla() {
        energia += 100 * aliados;
        System.out.println(nombre + " (Extraterrestre) ha comido una Semilla del Ermitaño y ahora tiene " + energia + " de energía.");
    }
}

class Namekiano extends Extraterrestre {
    public Namekiano(String nombre, int aliados) {
        super(nombre, aliados);
    }

    @Override
    public void comerSemilla() {
        super.comerSemilla();
        energia += 10;
        System.out.println(nombre + " (Namekiano) ha comido una Semilla del Ermitaño y ahora tiene " + energia + " de energía.");
    }
}

class Saiyajin extends Extraterrestre {
    public Saiyajin(String nombre, int aliados) {
        super(nombre, aliados);
    }

    @Override
    public void comerSemilla() {
        super.comerSemilla();
        energia *= 2;
        System.out.println(nombre + " (Saiyajin) ha comido una Semilla del Ermitaño y ahora tiene " + energia + " de energía.");
    }
}

class Demonio extends Personaje {
    private int victimas;

    public Demonio(String nombre, int victimas) {
        super(nombre);
        this.victimas = victimas;
    }

    @Override
    public void comerSemilla() {
        energia += 200 + 10 * victimas;
        System.out.println(nombre + " (Demonio) ha comido una Semilla del Ermitaño y ahora tiene " + energia + " de energía.");
    }
}

class Banquete {
    private List<Personaje> personajes;

    public Banquete() {
        personajes = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public void iniciarBanquete() {
        for (Personaje personaje : personajes) {
            personaje.comerSemilla();
        }
    }

    public double calcularPorcentajeDemonios() {
        int totalPersonajes = personajes.size();
        int totalDemonios = 0;
        for (Personaje p : personajes) {
            if (p instanceof Demonio) {
                totalDemonios++;
            }
        }
        if (totalPersonajes > 0) {
            return (double) totalDemonios / totalPersonajes * 100;
        }
        return 0;
    }
}

//aca ponemos un ejempko
public class Main {
    public static void main(String[] args) {
        Banquete banquete = new Banquete();
        banquete.agregarPersonaje(new Terricola("Goku"));
        banquete.agregarPersonaje(new Namekiano("Piccolo", 5));
        banquete.agregarPersonaje(new Saiyajin("Vegeta", 3));
        banquete.agregarPersonaje(new Demonio("Demon King", 10));
        banquete.agregarPersonaje(new Demonio("Demon Prince", 5));

        System.out.println("Iniciando el banquete...");
        banquete.iniciarBanquete();

        double porcentajeDemonios = banquete.calcularPorcentajeDemonios();
        System.out.printf("Porcentaje de demonios sobre el total de invitados: %.2f%%\n", porcentajeDemonios);
    }
}
