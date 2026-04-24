import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EtudiantService {

    // ------------------- OPTION 1 : CRÉER UN ÉTUDIANT -------------------
    public static void creerEtudiant(Scanner sc) {

        ArrayList<Etudiant> liste = FichierEtudiants.lireEtudiants();

        System.out.print("Matricule : ");
        String m = sc.nextLine();

        System.out.print("Nom : ");
        String nom = sc.nextLine();

        System.out.print("Prénom : ");
        String prenom = sc.nextLine();

        // Notes par défaut à 0
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

        String[] matieres = {"Prog", "Stat", "BD avancée", "BD massives", "Structure", "IA"};

        e.prog = demanderNote(sc, matieres[0]);
        e.stat = demanderNote(sc, matieres[1]);
        e.bdAv = demanderNote(sc, matieres[2]);
        e.bdMass = demanderNote(sc, matieres[3]);
        e.struct = demanderNote(sc, matieres[4]);
        e.ia = demanderNote(sc, matieres[5]);

        e.calculerMoyenne();

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

        Collections.sort(liste, Comparator.comparingDouble(e -> -e.moyenne));

        try (PrintWriter pw = new PrintWriter(new FileWriter("Resultats_Etudiants.csv"))) {

            pw.println("Matricule,Nom,Prenom,Moyenne");

            for (Etudiant e : liste) {
                pw.println(e.matricule + "," + e.nom + "," + e.prenom + "," + e.moyenne);
            }

            System.out.println("Classement généré dans Resultats_Etudiants.csv");

        } catch (Exception e) {
            System.out.println("Erreur écriture classement : " + e.getMessage());
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
