import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;
public class Biblioteca {
private List<Llibre> llibres;
public Biblioteca() {
this.llibres = new ArrayList<>();
}
public void afegirLlibre(Llibre llibre) { llibres.add(llibre); }
public Llibre buscarLlibre(String titol) {
for (Llibre llibre : llibres) {
if (llibre.getTitol().equalsIgnoreCase(titol)) {
return llibre;
}
}
return null;
}
    public List<Llibre> getLlibres() { return llibres; }

    public Llibre buscarLlibreSenseAccents(String titol) {
        String titolBuscat = treureAccents(titol).toLowerCase();
        for (Llibre llibre : llibres) {
            String titolLlibre = treureAccents(llibre.getTitol()).toLowerCase();
            if (titolLlibre.equals(titolBuscat)) {
                return llibre;
            }
        }
        return null;
    }

    private String treureAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public void llistarLlibres() {
        if (llibres.isEmpty()) {
            System.out.println("La biblioteca no té cap llibre.");
        } else {
            System.out.println("Llibres a la biblioteca:");
            for (Llibre llibre : llibres) {
                System.out.println("- " + llibre.getTitol());
            }
        }
    }
}  