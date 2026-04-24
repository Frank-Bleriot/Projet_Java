import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FichierEtudiants.initialiserFichier();

        int choix;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Créer un nouvel étudiant");
            System.out.println("2. Saisir les notes d’un étudiant");
            System.out.println("3. Générer le classement");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {

                case 1:
                    EtudiantService.creerEtudiant(sc);
                    break;

                case 2:
                    EtudiantService.saisirNotes(sc);
                    break;

                case 3:
                    EtudiantService.genererClassement();
                    break;
            }

        } while (choix != 0);

        System.out.println("Fin du programme.");
    }
}
