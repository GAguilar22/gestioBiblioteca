import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {
    private List<Prestec> prestecs;

    public GestorBiblioteca() {
        this.prestecs = new ArrayList<>();
    }

    public void prestarLlibre(Usuari usuari, Llibre llibre) {
        if (!llibre.esPrestat()) {
            llibre.prestar();
            
            Prestec prestec = new Prestec(usuari, llibre, LocalDate.now());
            prestecs.add(prestec);
            usuari.afegirLlibre(llibre);
            System.out.println(usuari.getNom() + " ha agafat el llibre: " + llibre.getTitol());
        } else {
            System.out.println("Aquest llibre ja està prestat.");
        }
    }

    public boolean controlarEstoc(Biblioteca biblioteca, String titol) {
        int count = 0;
        for (Llibre llibre : biblioteca.getLlibres()) {
            if (llibre.getTitol().equalsIgnoreCase(titol) && !llibre.esPrestat()) {
                count++;
            }
        }
        if (count > 1) {
            System.out.println("L'estoc és positiu. Tenim " + count + " còpies de " + titol + " disponibles.");
            return true;
        } else {
            System.out.println("No hi ha estoc suficient (més d'un llibre) per: " + titol);
            return false;
        }
    }

    public void retornarLlibre(Usuari usuari, Llibre llibre) {
        if (llibre.esPrestat()) {
            llibre.retornar();
            usuari.retornarLlibre(llibre);
            
            Prestec prestecACancelar = null;
            for (Prestec p : prestecs) {
                if (p.getLlibre().equals(llibre) && p.getUsuari().equals(usuari)) {
                    prestecACancelar = p;
                    break;
                }
            }
            if (prestecACancelar != null) {
                prestecs.remove(prestecACancelar);
            }
            
            System.out.println(usuari.getNom() + " ha retornat el llibre: " + llibre.getTitol());
        } else {
            System.out.println("Aquest llibre no estava prestat.");
        }
    }
}