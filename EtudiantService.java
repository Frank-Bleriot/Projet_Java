import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EtudiantService {

    private static final String[] MATIERES = {
            "Prog", "Stat", "BD avancée", "BD massives", "Structure", "IA"
    };

    // ------------------- OPTION 1 : CRÉER UN ÉTUDIANT -------------------
    public static void creerEtudiant(Scanner sc) {

        ArrayList<Etudiant> liste = FichierEtudiants.lireEtudiants();

        System.out.print("Matricule : ");
        String m = sc.nextLine();

        // Vérification du matricule déjà existant
        Etudiant existant = FichierEtudiants.chercherEtudiant(m, liste);
        if (existant != null) {
            System.out.println("Erreur : cet étudiant existe déjà !");
            return; // On arrête ici
        }

        System.out.print("Nom : ");
        String nom = sc.nextLine();

        System.out.print("Prénom : ");
        String prenom = sc.nextLine();

        Etudiant e = new Etudiant(m, nom, prenom);

        liste.add(e);
        FichierEtudiants.sauvegarderListe(liste);

        System.out.println("Étudiant ajouté !");
    }

    // ------------------- OPTION 2 : SAISIR LES NOTES -------------------
    public static void saisirNotes(Scanner sc) {

        ArrayList<Etudiant> liste = FichierEtudiants.lireEtudiants();

        System.out.print("Matricule de l’étudiant : ");
        String m = sc.nextLine();

        Etudiant e = FichierEtudiants.chercherEtudiant(m, liste);

        if (e == null) {
            System.out.println("Étudiant inexistant.");
            return;
        }

        // Boucle sur les 6 matières
        for (int i = 0; i < MATIERES.length; i++) {
            double note = demanderNote(sc, MATIERES[i]);
            e.setNote(i, note);
        }

        FichierEtudiants.sauvegarderListe(liste);

        System.out.println("Notes mises à jour !");
    }

    // ------------------- OPTION 3 : CLASSEMENT -------------------
    public static void genererClassement() {

        ArrayList<Etudiant> liste = FichierEtudiants.lireEtudiants();

        if (liste.isEmpty()) {
            System.out.println("Aucun étudiant à classer.");
            return;
        }

        // Tri décroissant par moyenne
        Collections.sort(liste, Comparator.comparingDouble(Etudiant::getMoyenne).reversed());

        try (PrintWriter pw = new PrintWriter(new FileWriter("Resultats_Etudiants.csv"))) {

            pw.println("Matricule,Nom,Prenom,Moyenne");

            for (Etudiant e : liste) {
                pw.println(e.getMatricule() + "," +
                        e.getNom() + "," +
                        e.getPrenom() + "," +
                        e.getMoyenne());
            }

            System.out.println("Classement généré dans Resultats_Etudiants.csv");

        } catch (Exception ex) {
            System.out.println("Erreur écriture classement : " + ex.getMessage());
        }
    }

    // ------------------- VALIDATION DES NOTES -------------------
    public static double demanderNote(Scanner sc, String matiere) {

        double note = -1;

        while (note < 0 || note > 20) {
            try {
                System.out.print("Note en " + matiere + " (0-20) : ");
                note = sc.nextDouble();
                sc.nextLine();

                if (note < 0 || note > 20) {
                    System.out.println("La note n’est pas valide.");
                }

            } catch (Exception ex) {
                System.out.println("Erreur : entrez un nombre valide.");
                sc.nextLine();
            }
        }

        return note;
    }
}
