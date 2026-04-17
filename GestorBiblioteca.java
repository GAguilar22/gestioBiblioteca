import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {
    private List<Prestec> prestecs;
    private List<Llibre> inventari; // Per controlar l'estoc

    public GestorBiblioteca() {
        this.prestecs = new ArrayList<>();
        this.inventari = new ArrayList<>();
    }

    // --- GESTIÓ D'ESTOC (CRUD) ---

    public void crearLlibre(String titol, String autor) {
        Llibre nou = new Llibre(titol, autor);
        inventari.add(nou);
        System.out.println("Llibre registrat a l'inventari.");
    }

    public void modificarLlibre(String titolOriginal, String nouTitol, String nouAutor) {
        for (Llibre l : inventari) {
            if (l.getTitol().equalsIgnoreCase(titolOriginal)) {
                l.setTitol(nouTitol);
                l.setAutor(nouAutor);
                System.out.println("Dades del llibre actualitzades.");
                return;
            }
        }
        System.out.println("Llibre no trobat.");
    }

    public void eliminarLlibre(String titol) {
        boolean eliminat = inventari.removeIf(l -> l.getTitol().equalsIgnoreCase(titol));
        if (eliminat) {
            System.out.println("Llibre eliminat de la biblioteca.");
        } else {
            System.out.println("No s'ha trobat el llibre per eliminar.");
        }
    }

    // --- LÒGICA DE PRÉSTEC ---

    public void prestarLlibre(Usuari usuari, Llibre llibre) {
        if (inventari.contains(llibre) && !llibre.esPrestat()) {
            llibre.prestar();
            
            Prestec prestec = new Prestec(usuari, llibre, LocalDate.now());
            prestecs.add(prestec);
            usuari.afegirLlibre(llibre);
            System.out.println(usuari.getNom() + " ha agafat el llibre: " + llibre.getTitol());
        } else {
            System.out.println("No es pot prestar: llibre no disponible o no existeix.");
        }
    }
    
    public int controlarEstoc() {
        return inventari.size();
    }
}