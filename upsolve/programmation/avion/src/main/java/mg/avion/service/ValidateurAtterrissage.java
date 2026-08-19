package mg.avion.service;

import mg.avion.config.ConfigurationSimulation;
import mg.avion.model.Avion;

public class ValidateurAtterrissage {
    public boolean estEnDecrochage(Avion avion) {
        return avion.getAltitude() > 0.0 && avion.getVitesseX() < ConfigurationSimulation.VITESSE_DECROCHAGE;
    }

    public boolean aToucheLaPiste(Avion avion) {
        return avion.getAltitude() <= 0.0 && avion.getDistancePiste() >= 0.0;
    }

    public boolean aCracheAvantLaPiste(Avion avion) {
        return avion.getAltitude() <= 0.0 && avion.getDistancePiste() < 0.0;
    }

    public boolean estArrete(Avion avion) {
        return avion.getVitesseX() <= 0.0;
    }
}
