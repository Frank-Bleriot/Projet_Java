import java.io.*;
import java.util.ArrayList;

// Classe utilitaire pour lire et écrire des fichiers CSV
public class Fichier_CSV {

    // Lecture d'un fichier CSV et renvoi d'une liste de lignes
    public static ArrayList<String[]> lireCSV(String fichier) {
        ArrayList<String[]> data = new ArrayList<>();
        File f = new File(fichier);

        // Si le fichier n'existe pas, on retourne une liste vide
        if (!f.exists()) return data;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;

            // Lecture ligne par ligne
            while ((ligne = br.readLine()) != null) {
                data.add(ligne.split(",")); // découpe CSV
            }

        } catch (Exception e) {
            System.out.println("Erreur lecture CSV : " + e.getMessage());
        }

        return data;
    }

    // Écriture d'une liste de lignes dans un fichier CSV
    public static void ecrireCSV(String fichier, ArrayList<String> lignes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichier))) {

            // Écriture de chaque ligne
            for (String l : lignes) pw.println(l);

        } catch (Exception e) {
            System.out.println("Erreur écriture CSV : " + e.getMessage());
        }
    }
}
