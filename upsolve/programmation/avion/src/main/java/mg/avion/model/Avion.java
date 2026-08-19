package mg.avion.model;

import mg.avion.config.ConfigurationSimulation;

public class Avion {
    private double vitesseX;
    private double vitesseY;
    private double altitude;
    private double distancePiste;
    private double freinageX;
    private double freinageY;
    private VueAvion vue;

    public Avion() {
        reinitialiser();
    }

    public void reinitialiser() {
        this.vitesseX = ConfigurationSimulation.VITESSE_X_INITIALE;
        this.vitesseY = ConfigurationSimulation.VITESSE_Y_INITIALE;
        this.altitude = ConfigurationSimulation.ALTITUDE_INITIALE;
        this.distancePiste = ConfigurationSimulation.DISTANCE_INITIALE;
        this.freinageX = 0.0;
        this.freinageY = 0.0;
        this.vue = VueAvion.PROFIL_DROITE;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = Math.max(0.0, vitesseX);
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = Math.max(0.0, altitude);
    }

    public double getDistancePiste() {
        return distancePiste;
    }

    public void setDistancePiste(double distancePiste) {
        this.distancePiste = distancePiste;
    }

    public double getFreinageX() {
        return freinageX;
    }

    public void setFreinageX(double freinageX) {
        this.freinageX = Math.max(ConfigurationSimulation.FREINAGE_X_MIN, Math.min(0.0, freinageX));
    }

    public double getFreinageY() {
        return freinageY;
    }

    public void setFreinageY(double freinageY) {
        this.freinageY = Math.max(ConfigurationSimulation.FREINAGE_Y_MIN, Math.min(0.0, freinageY));
    }

    public VueAvion getVue() {
        return vue;
    }

    public void setVue(VueAvion vue) {
        this.vue = vue;
    }
}
