import java.util.*;

public class Mon_Gestionnaire_Etudiants {

    // Nom du fichier principal
    private static final String FICHIER = "Repertoire_Etudiants.csv";

    // Création d'un nouvel étudiant
    public static void creerEtudiant() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Matricule : ");
        String matricule = sc.nextLine();

        System.out.print("Nom : ");
        String nom = sc.nextLine();

        System.out.print("Prénom : ");
        String prenom = sc.nextLine();

        // Création de l'objet étudiant
        Etudiant e = new Etudiant(matricule, nom, prenom);

        // Lecture du fichier existant
        ArrayList<String[]> data = Fichier_CSV.lireCSV(FICHIER);
        ArrayList<String> lignes = new ArrayList<>();

        // On réécrit les anciennes lignes
        for (String[] d : data) lignes.add(String.join(",", d));

        // On ajoute le nouvel étudiant
        lignes.add(e.toCSV());

        // Sauvegarde
        Fichier_CSV.ecrireCSV(FICHIER, lignes);

        System.out.println("Étudiant ajouté avec succès !");
    }

    // Saisie des notes d'un étudiant
    public static void saisirNotes() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Matricule de l'étudiant : ");
        String matricule = sc.nextLine();

        ArrayList<String[]> data = Fichier_CSV.lireCSV(FICHIER);
        ArrayList<Etudiant> liste = new ArrayList<>();

        boolean trouve = false;

        // On parcourt tous les étudiants
        for (String[] d : data) {
            Etudiant e = new Etudiant(d);

            // Si on trouve le bon étudiant
            if (e.getMatricule().equals(matricule)) {
                trouve = true;

                // Saisie des notes
                double p = demanderNote("Programmation avancée");
                double s = demanderNote("Statistique");
                double b1 = demanderNote("BD avancée");
                double b2 = demanderNote("BD massives");
                double st = demanderNote("Structure des données");
                double i = demanderNote("Intelligence Artificielle");

                // Mise à jour des notes
                e.setNotes(p, s, b1, b2, st, i);
            }

            liste.add(e);
        }

        if (!trouve) {
            System.out.println("Étudiant inexistant !");
            return;
        }

        // Réécriture du fichier
        ArrayList<String> lignes = new ArrayList<>();
        for (Etudiant e : liste) lignes.add(e.toCSV());

        Fichier_CSV.ecrireCSV(FICHIER, lignes);

        System.out.println("Notes enregistrées !");
    }

    // Méthode pour demander une note valide
    private static double demanderNote(String matiere) {
        Scanner sc = new Scanner(System.in);
        double note;

        do {
            System.out.print("Note " + matiere + " (0-20) : ");
            note = sc.nextDouble();
        } while (note < 0 || note > 20); // contrôle de validité

        return note;
    }

    // Génération du classement
    public static void genererClassement() {

        // Lecture du fichier
        ArrayList<String[]> data = Fichier_CSV.lireCSV(FICHIER);
        ArrayList<Etudiant> liste = new ArrayList<>();

        // Conversion en objets
        for (String[] d : data) liste.add(new Etudiant(d));

        // Tri par moyenne décroissante
        liste.sort((a, b) -> Double.compare(b.getMoyenne(), a.getMoyenne()));

        // Conversion en lignes CSV
        ArrayList<String> lignes = new ArrayList<>();
        for (Etudiant e : liste) lignes.add(e.toCSV());

        // Sauvegarde dans un autre fichier
        Fichier_CSV.ecrireCSV("Resultats_Etudiants.csv", lignes);

        System.out.println("Classement généré !");
    }
}
