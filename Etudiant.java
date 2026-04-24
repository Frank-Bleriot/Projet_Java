// Classe représentant un étudiant
public class Etudiant {

    String matricule;
    String nom;
    String prenom;

    // Notes
    double prog;
    double stat;
    double bdAv;
    double bdMass;
    double struct;
    double ia;

    double moyenne;

    // Constructeur avec notes par défaut à 0
    public Etudiant(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;

        // Notes initialisées à 0
        this.prog = 0;
        this.stat = 0;
        this.bdAv = 0;
        this.bdMass = 0;
        this.struct = 0;
        this.ia = 0;

        calculerMoyenne();
    }

    // Constructeur utilisé lors de la lecture du fichier CSV
    public Etudiant(String matricule, String nom, String prenom,
                    double prog, double stat, double bdAv,
                    double bdMass, double struct, double ia) {

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;

        this.prog = prog;
        this.stat = stat;
        this.bdAv = bdAv;
        this.bdMass = bdMass;
        this.struct = struct;
        this.ia = ia;

        calculerMoyenne();
    }

    // Calcul de la moyenne
    public void calculerMoyenne() {
        this.moyenne = (prog + stat + bdAv + bdMass + struct + ia) / 6.0;
    }
}
