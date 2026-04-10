public class Main {
public static void main(String[] args) {
Biblioteca biblioteca = new Biblioteca();
Llibre llibre1 = new Llibre("1984", "George Orwell");
Llibre llibre2 = new Llibre("El petit príncep", "Antoine de Saint-Exupéry");
biblioteca.afegirLlibre(llibre1);
biblioteca.afegirLlibre(llibre2);
Usuari usuari = new Usuari("Carla");
GestorBiblioteca gestor = new GestorBiblioteca();
gestor.prestarLlibre(usuari, llibre1);
}
}