import java.time.LocalDate;

public class Prestec {
    private Usuari usuari;
    private Llibre llibre;
    private LocalDate dataPrestec;
    private LocalDate dataRetorn;
    private boolean retornat;

    private static final int DIES_PRESTEC = 14;

    public Prestec(Usuari usuari, Llibre llibre) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.dataPrestec = LocalDate.now();
        this.dataRetorn = dataPrestec.plusDays(DIES_PRESTEC);
        this.retornat = false;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public LocalDate getDataPrestec() {
        return dataPrestec;
    }

    public LocalDate getDataRetorn() {
        return dataRetorn;
    }

    public boolean isRetornat() {
        return retornat;
    }

    public void retornar() {
        if (!retornat) {
            llibre.retornar();
            usuari.retornarLlibre(llibre);
            retornat = true;
            System.out.println("Llibre retornat correctament.");
        } else {
            System.out.println("Aquest préstec ja està tancat.");
        }
    }

    public boolean estaEndarrerit() {
        return LocalDate.now().isAfter(dataRetorn) && !retornat;
    }

    @Override
    public String toString() {
        return llibre.getTitol() + " prestat a " + usuari.getNom() +
                " fins a " + dataRetorn +
                (retornat ? " (Retornat)" : (estaEndarrerit() ? " (Endarrerit)" : ""));
    }
}