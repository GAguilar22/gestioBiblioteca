import java.util.ArrayList;
import java.util.List;

public class Usuari {
    private String nom;
    private List<Llibre> llibresPrestats;
    private static final int MAX_LLIBRES = 3; // màxim llibres per usuari

    public Usuari(String nom) {
        this.nom = nom;
        this.llibresPrestats = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Llibre> getLlibresPrestats() {
        return llibresPrestats;
    }

    public boolean potPrestar() {
        return llibresPrestats.size() < MAX_LLIBRES;
    }

    public void afegirLlibre(Llibre llibre) {
        if (potPrestar()) {
            llibresPrestats.add(llibre);
        } else {
            System.out.println("L'usuari ja té el màxim de llibres prestats.");
        }
    }

    public void retornarLlibre(Llibre llibre) {
        if (llibresPrestats.contains(llibre)) {
            llibresPrestats.remove(llibre);
        } else {
            System.out.println("Aquest llibre no el té aquest usuari.");
        }
    }

    public void mostrarLlibres() {
        if (llibresPrestats.isEmpty()) {
            System.out.println(nom + " no té llibres prestats.");
        } else {
            System.out.println("Llibres de " + nom + ":");
            for (Llibre l : llibresPrestats) {
                System.out.println("- " + l);
            }
        }
    }

    @Override
    public String toString() {
        return "Usuari: " + nom + " | Llibres prestats: " + llibresPrestats.size();
    }
}