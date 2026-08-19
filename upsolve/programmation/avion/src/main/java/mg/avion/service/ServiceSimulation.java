package mg.avion.service;

import mg.avion.config.ConfigurationSimulation;
import mg.avion.model.Avion;
import mg.avion.model.EtatSimulation;
import mg.avion.model.StatutSimulation;

public class ServiceSimulation {
    private final EtatSimulation etat;
    private final ValidateurAtterrissage validateur;

    public ServiceSimulation() {
        this.etat = new EtatSimulation();
        this.validateur = new ValidateurAtterrissage();
    }

    public EtatSimulation getEtat() {
        return etat;
    }

    public void jouer() {
        if (etat.getStatut() == StatutSimulation.PRET || etat.getStatut() == StatutSimulation.PAUSE) {
            etat.setStatut(StatutSimulation.EN_VOL);
        }
    }

    public void mettreEnPause() {
        if (etat.getStatut() == StatutSimulation.EN_VOL || etat.getStatut() == StatutSimulation.AU_SOL) {
            etat.setStatut(StatutSimulation.PAUSE);
        }
    }

    public void reinitialiser() {
        etat.reinitialiser();
    }

    public void augmenterFreinageX() {
        Avion avion = etat.getAvion();
        avion.setFreinageX(avion.getFreinageX() - Math.abs(ConfigurationSimulation.PAS_FREINAGE_X));
    }

    public void diminuerFreinageX() {
        Avion avion = etat.getAvion();
        avion.setFreinageX(avion.getFreinageX() + Math.abs(ConfigurationSimulation.PAS_FREINAGE_X));
    }

    public void augmenterFreinageY() {
        Avion avion = etat.getAvion();
        avion.setFreinageY(avion.getFreinageY() - Math.abs(ConfigurationSimulation.PAS_FREINAGE_Y));
    }

    public void diminuerFreinageY() {
        Avion avion = etat.getAvion();
        avion.setFreinageY(avion.getFreinageY() + Math.abs(ConfigurationSimulation.PAS_FREINAGE_Y));
    }

    public void mettreAJour(double deltaSecondes) {
        if (etat.getStatut() != StatutSimulation.EN_VOL && etat.getStatut() != StatutSimulation.AU_SOL) {
            return;
        }

        Avion avion = etat.getAvion();
        etat.ajouterTempsEcoule(deltaSecondes);

        avion.setVitesseX(avion.getVitesseX() + avion.getFreinageX() * deltaSecondes);

        if (etat.getStatut() == StatutSimulation.EN_VOL) {
            avion.setVitesseY(avion.getVitesseY() + avion.getFreinageY() * deltaSecondes);
            avion.setAltitude(avion.getAltitude() + avion.getVitesseY() * deltaSecondes);
            avion.setDistancePiste(avion.getDistancePiste() + avion.getVitesseX() * deltaSecondes);

            if (validateur.estEnDecrochage(avion) || validateur.aCracheAvantLaPiste(avion)) {
                etat.setStatut(StatutSimulation.CRASH);
            } else if (validateur.aToucheLaPiste(avion)) {
                avion.setAltitude(0.0);
                etat.setStatut(StatutSimulation.AU_SOL);
            }
        } else {
            avion.setDistancePiste(avion.getDistancePiste() + avion.getVitesseX() * deltaSecondes);

            if (validateur.estArrete(avion)) {
                etat.setStatut(StatutSimulation.REUSSI);
            }
        }
    }
}
