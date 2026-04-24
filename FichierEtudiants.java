import java.io.*;
import java.util.ArrayList;

public class FichierEtudiants {

    private static final String FICHIER = "Repertoire_Etudiants.csv";

    // ------------------- INITIALISATION -------------------
    public static void initialiserFichier() {
        File f = new File(FICHIER);

        if (!f.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER))) {
                pw.println("Matricule,Nom,Prenom,Prog,Stat,BD_Av,BD_Mass,Struct,IA,Moyenne");
            } catch (Exception e) {
                System.out.println("Erreur création fichier : " + e.getMessage());
            }
        }
    }

    // ------------------- LECTURE -------------------
    public static ArrayList<Etudiant> lireEtudiants() {

        ArrayList<Etudiant> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER))) {

            String ligne = br.readLine(); // ignorer l'en-tête

            while ((ligne = br.readLine()) != null) {

                String[] t = ligne.split(",");

                if (t.length < 10) continue;

                Etudiant e = new Etudiant(
                        t[0], t[1], t[2],
                        Double.parseDouble(t[3]),
                        Double.parseDouble(t[4]),
                        Double.parseDouble(t[5]),
                        Double.parseDouble(t[6]),
                        Double.parseDouble(t[7]),
                        Double.parseDouble(t[8])
                );

                liste.add(e);
            }

        } catch (Exception e) {
            System.out.println("Erreur lecture fichier : " + e.getMessage());
        }

        return liste;
    }

    // ------------------- SAUVEGARDE -------------------
    public static void sauvegarderListe(ArrayList<Etudiant> liste) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER))) {

            pw.println("Matricule,Nom,Prenom,Prog,Stat,BD_Av,BD_Mass,Struct,IA,Moyenne");

            for (Etudiant e : liste) {

                pw.print(e.getMatricule() + ",");
                pw.print(e.getNom() + ",");
                pw.print(e.getPrenom() + ",");

                // 6 notes
                for (int i = 0; i < 6; i++) {
                    pw.print(e.getNote(i) + ",");
                }

                pw.println(e.getMoyenne());
            }

        } catch (Exception e) {
            System.out.println("Erreur sauvegarde fichier : " + e.getMessage());
        }
    }

    // ------------------- RECHERCHE -------------------
    public static Etudiant chercherEtudiant(String matricule, ArrayList<Etudiant> liste) {

        for (Etudiant e : liste) {
            if (e.getMatricule().equalsIgnoreCase(matricule)) {
                return e;
            }
        }

        return null;
    }
}
