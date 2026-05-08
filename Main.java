import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner teclat = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();
    static GestorBiblioteca gestor = new GestorBiblioteca();
    static List<Usuari> usuaris = new ArrayList<>();

    public static void main(String[] args) {
        int opcio;
        do {
            System.out.println("\n=== GESTIÓ BIBLIOTECA ===");
            System.out.println("1. Llibres");
            System.out.println("2. Usuaris");
            System.out.println("3. Préstecs");
            System.out.println("0. Sortir");
            System.out.print("Opció: ");
            opcio = teclat.nextInt();
            teclat.nextLine();

            switch (opcio) {
                case 1: menuLlibres(); break;
                case 2: menuUsuaris(); break;
                case 3: menuPrestecs(); break;
                case 0: System.out.println("Fins aviat!"); break;
                default: System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    // ===== LLIBRES =====

    static void menuLlibres() {
        int opcio;
        do {
            System.out.println("\n--- LLIBRES ---");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("5. Buscar");
            System.out.println("6. Buscar sense accents");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = teclat.nextInt();
            teclat.nextLine();

            switch (opcio) {
                case 1:
                    System.out.print("Títol: ");
                    String titol = teclat.nextLine();
                    System.out.print("Autor: ");
                    String autor = teclat.nextLine();
                    biblioteca.afegirLlibre(new Llibre(titol, autor));
                    System.out.println("Llibre afegit.");
                    break;
                case 2:
                    System.out.print("Títol a modificar: ");
                    Llibre lMod = biblioteca.buscarLlibre(teclat.nextLine());
                    if (lMod == null) { System.out.println("No trobat."); break; }
                    System.out.print("Nou títol (buit=no canviar): ");
                    String nTitol = teclat.nextLine();
                    System.out.print("Nou autor (buit=no canviar): ");
                    String nAutor = teclat.nextLine();
                    if (!nTitol.isEmpty()) lMod.setTitol(nTitol);
                    if (!nAutor.isEmpty()) lMod.setAutor(nAutor);
                    System.out.println("Llibre modificat.");
                    break;
                case 3:
                    System.out.print("Títol a eliminar: ");
                    Llibre lEl = biblioteca.buscarLlibre(teclat.nextLine());
                    if (lEl == null) { System.out.println("No trobat."); break; }
                    if (lEl.esPrestat()) { System.out.println("Està prestat, no es pot eliminar."); break; }
                    biblioteca.getLlibres().remove(lEl);
                    System.out.println("Llibre eliminat.");
                    break;
                case 4:
                    biblioteca.llistarLlibres();
                    break;
                case 5:
                    System.out.print("Títol a buscar: ");
                    Llibre lBus = biblioteca.buscarLlibre(teclat.nextLine());
                    System.out.println(lBus != null ? "Trobat: " + lBus : "No trobat.");
                    break;
                case 6:
                    System.out.print("Títol (sense accents): ");
                    Llibre lAcc = biblioteca.buscarLlibreSenseAccents(teclat.nextLine());
                    System.out.println(lAcc != null ? "Trobat: " + lAcc : "No trobat.");
                    break;
                case 0: break;
                default: System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    // ===== USUARIS =====

    static void menuUsuaris() {
        int opcio;
        do {
            System.out.println("\n--- USUARIS ---");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("5. Cercar");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = teclat.nextInt();
            teclat.nextLine();

            switch (opcio) {
                case 1:
                    System.out.print("Nom: ");
                    usuaris.add(new Usuari(teclat.nextLine()));
                    System.out.println("Usuari afegit.");
                    break;
                case 2:
                    System.out.print("Nom a modificar: ");
                    Usuari uMod = buscarUsuari(teclat.nextLine());
                    if (uMod == null) { System.out.println("No trobat."); break; }
                    System.out.print("Nou nom: ");
                    uMod.setNom(teclat.nextLine());
                    System.out.println("Usuari modificat.");
                    break;
                case 3:
                    System.out.print("Nom a eliminar: ");
                    Usuari uEl = buscarUsuari(teclat.nextLine());
                    if (uEl == null) { System.out.println("No trobat."); break; }
                    if (!uEl.getLlibresPrestats().isEmpty()) { System.out.println("Té llibres prestats, no es pot eliminar."); break; }
                    usuaris.remove(uEl);
                    System.out.println("Usuari eliminat.");
                    break;
                case 4:
                    if (usuaris.isEmpty()) { System.out.println("No hi ha usuaris."); break; }
                    for (Usuari u : usuaris) System.out.println("- " + u);
                    break;
                case 5:
                    System.out.print("Nom a cercar: ");
                    Usuari uCer = buscarUsuari(teclat.nextLine());
                    if (uCer != null) { System.out.println("Trobat: " + uCer); uCer.mostrarLlibres(); }
                    else System.out.println("No trobat.");
                    break;
                case 0: break;
                default: System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    // ===== PRÉSTECS =====

    static void menuPrestecs() {
        int opcio;
        do {
            System.out.println("\n--- PRÉSTECS ---");
            System.out.println("1. Prestar llibre");
            System.out.println("2. Retornar llibre");
            System.out.println("3. Controlar estoc");
            System.out.println("0. Tornar");
            System.out.print("Opció: ");
            opcio = teclat.nextInt();
            teclat.nextLine();

            switch (opcio) {
                case 1:
                    System.out.print("Nom usuari: ");
                    Usuari uPres = buscarUsuari(teclat.nextLine());
                    if (uPres == null) { System.out.println("Usuari no trobat."); break; }
                    if (!Prestec.potDemanar(uPres)) { System.out.println("Màxim de llibres assolit."); break; }
                    System.out.print("Títol del llibre: ");
                    Llibre lPres = biblioteca.buscarLlibre(teclat.nextLine());
                    if (lPres == null) { System.out.println("Llibre no trobat."); break; }
                    gestor.prestarLlibre(uPres, lPres);
                    break;
                case 2:
                    System.out.print("Nom usuari: ");
                    Usuari uRet = buscarUsuari(teclat.nextLine());
                    if (uRet == null) { System.out.println("Usuari no trobat."); break; }
                    System.out.print("Títol del llibre: ");
                    Llibre lRet = biblioteca.buscarLlibre(teclat.nextLine());
                    if (lRet == null) { System.out.println("Llibre no trobat."); break; }
                    uRet.retornarLlibre(lRet);
                    lRet.retornar();
                    System.out.println("Llibre retornat.");
                    break;
                case 3:
                    System.out.print("Títol a comprovar: ");
                    gestor.controlarEstoc(biblioteca, teclat.nextLine());
                    break;
                case 0: break;
                default: System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }


}