package mg.avion.model;

public class EtatSimulation {
    private final Avion avion;
    private StatutSimulation status;
    private double elapsedSeconds;

    public EtatSimulation() {
        this.avion = new Avion();
        this.status = StatutSimulation.PRET;
        this.elapsedSeconds = 0.0;
    }

    public void reinitialiser() {
        avion.reinitialiser();
        status = StatutSimulation.PRET;
        elapsedSeconds = 0.0;
    }

    public Avion getAvion() {
        return avion;
    }

    public StatutSimulation getStatut() {
        return status;
    }

    public void setStatut(StatutSimulation status) {
        this.status = status;
    }

    public double getTempsEcouleSecondes() {
        return elapsedSeconds;
    }

    public void ajouterTempsEcoule(double seconds) {
        this.elapsedSeconds += seconds;
    }
}
