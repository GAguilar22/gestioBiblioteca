import java.time.LocalDate;

public class Prestec {
    private Usuari usuari;
    private Llibre llibre;
    private LocalDate dataPrestec;
    private LocalDate dataRetorn;
    
    // Constant per controlar el màxim de llibres que es poden demanar (com indica l'enunciat)
    public static final int MAX_LLIBRES_PER_USUARI = 3;

    public Prestec(Usuari usuari, Llibre llibre, LocalDate dataPrestec) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.dataPrestec = dataPrestec;
        this.dataRetorn = dataPrestec.plusWeeks(2);
    }

    public Usuari getUsuari() { return usuari; }
    public Llibre getLlibre() { return llibre; }
    public LocalDate getDataPrestec() { return dataPrestec; }
    public LocalDate getDataRetorn() { return dataRetorn; }
    
    // Validació per controlar si un usuari pot demanar més llibres
    public static boolean potDemanar(Usuari usuari) {
        return usuari.getLlibresPrestats().size() < MAX_LLIBRES_PER_USUARI;
    }
}