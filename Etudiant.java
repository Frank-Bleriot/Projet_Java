// Classe représentant un étudiant et ses notes
public class Etudiant {

    // Informations personnelles
    private String matricule;
    private String nom;
    private String prenom;

    // Notes des différentes matières
    private double progAv;
    private double statistique;
    private double bdAv;
    private double bdMassive;
    private double structure;
    private double ia;

    // Moyenne générale
    private double moyenne;

    // Constructeur utilisé lors de la création d'un nouvel étudiant
    public Etudiant(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = 0; // moyenne initiale
    }

    // Constructeur utilisé lors de la lecture depuis le fichier CSV
    public Etudiant(String[] data) {
        this.matricule = data[0];
        this.nom = data[1];
        this.prenom = data[2];
        this.progAv = Double.parseDouble(data[3]);
        this.statistique = Double.parseDouble(data[4]);
        this.bdAv = Double.parseDouble(data[5]);
        this.bdMassive = Double.parseDouble(data[6]);
        this.structure = Double.parseDouble(data[7]);
        this.ia = Double.parseDouble(data[8]);
        this.moyenne = Double.parseDouble(data[9]);
    }

    // Calcul automatique de la moyenne
    public void calculerMoyenne() {
        this.moyenne = (progAv + statistique + bdAv + bdMassive + structure + ia) / 6;
    }

    // Convertit l'étudiant en ligne CSV
    public String toCSV() {
        return matricule + "," + nom + "," + prenom + "," +
                progAv + "," + statistique + "," + bdAv + "," +
                bdMassive + "," + structure + "," + ia + "," + moyenne;
    }

    public String getMatricule() { return matricule; }
    public double getMoyenne() { return moyenne; }

    // Affecte les notes et recalcule la moyenne
    public void setNotes(double p, double s, double b1, double b2, double st, double i) {
        this.progAv = p;
        this.statistique = s;
        this.bdAv = b1;
        this.bdMassive = b2;
        this.structure = st;
        this.ia = i;
        calculerMoyenne();
    }
}
