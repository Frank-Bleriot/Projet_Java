public class Etudiant {

    private String matricule;
    private String nom;
    private String prenom;

    // Tableau des notes : 6 matières
    private double[] notes = new double[6];

    private double moyenne;

    // Constructeur avec notes à 0
    public Etudiant(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;

        // Notes déjà initialisées à 0
        calculerMoyenne();
    }

    // Constructeur utilisé lors de la lecture du CSV
    public Etudiant(String matricule, String nom, String prenom,
                    double prog, double stat, double bdAv,
                    double bdMass, double struct, double ia) {

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;

        this.notes[0] = prog;
        this.notes[1] = stat;
        this.notes[2] = bdAv;
        this.notes[3] = bdMass;
        this.notes[4] = struct;
        this.notes[5] = ia;

        calculerMoyenne();
    }

    // Calcul automatique de la moyenne
    public void calculerMoyenne() {
        double somme = 0;
        for (double n : notes) {
            somme += n;
        }
        this.moyenne = somme / notes.length;
    }

    // Setter pour modifier une note
    public void setNote(int index, double valeur) {
        if (index >= 0 && index < notes.length) {
            notes[index] = valeur;
            calculerMoyenne();
        }
    }

    // Getter pour récupérer une note
    public double getNote(int index) {
        return (index >= 0 && index < notes.length) ? notes[index] : -1;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return matricule + " - " + nom + " " + prenom + " | Moyenne : " + moyenne;
    }
}
