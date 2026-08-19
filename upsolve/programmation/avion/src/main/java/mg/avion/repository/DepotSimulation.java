package mg.avion.repository;

import mg.avion.model.EtatSimulation;

public interface DepotSimulation {
    int creerSimulation(EtatSimulation etat);

    void sauvegarderEtape(int idSimulation, EtatSimulation etat);

    void terminerSimulation(int idSimulation, EtatSimulation etat);
}
