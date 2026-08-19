package mg.avion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mg.avion.model.Avion;
import mg.avion.model.EtatSimulation;

public class DepotSimulationPostgres implements DepotSimulation {
    private final ConnexionBaseDonnees connexionBaseDonnees;

    public DepotSimulationPostgres() {
        this.connexionBaseDonnees = new ConnexionBaseDonnees();
    }

    @Override
    public int creerSimulation(EtatSimulation etat) {
        String requeteSql = "INSERT INTO simulations (status) VALUES (?)";

        try (Connection connexion = connexionBaseDonnees.ouvrirConnexion();
             PreparedStatement requete = connexion.prepareStatement(requeteSql, Statement.RETURN_GENERATED_KEYS)) {
            requete.setString(1, etat.getStatut().name());
            requete.executeUpdate();

            try (ResultSet cles = requete.getGeneratedKeys()) {
                if (cles.next()) {
                    return cles.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Impossible de creer la simulation", e);
        }

        throw new IllegalStateException("Aucun identifiant de simulation genere");
    }

    @Override
    public void sauvegarderEtape(int idSimulation, EtatSimulation etat) {
        String requeteSql = """
                INSERT INTO simulation_steps (
                    simulation_id, time_seconds, speed_x, speed_y, altitude,
                    distance_to_runway, braking_x, braking_y
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        Avion avion = etat.getAvion();

        try (Connection connexion = connexionBaseDonnees.ouvrirConnexion();
             PreparedStatement requete = connexion.prepareStatement(requeteSql)) {
            requete.setInt(1, idSimulation);
            requete.setDouble(2, etat.getTempsEcouleSecondes());
            requete.setDouble(3, avion.getVitesseX());
            requete.setDouble(4, avion.getVitesseY());
            requete.setDouble(5, avion.getAltitude());
            requete.setDouble(6, avion.getDistancePiste());
            requete.setDouble(7, avion.getFreinageX());
            requete.setDouble(8, avion.getFreinageY());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Impossible de sauvegarder l'etape", e);
        }
    }

    @Override
    public void terminerSimulation(int idSimulation, EtatSimulation etat) {
        String requeteSql = """
                UPDATE simulations
                SET ended_at = CURRENT_TIMESTAMP,
                    status = ?,
                    final_speed_x = ?,
                    final_speed_y = ?,
                    final_altitude = ?,
                    final_distance = ?,
                    duration_seconds = ?
                WHERE id = ?
                """;
        Avion avion = etat.getAvion();

        try (Connection connexion = connexionBaseDonnees.ouvrirConnexion();
             PreparedStatement requete = connexion.prepareStatement(requeteSql)) {
            requete.setString(1, etat.getStatut().name());
            requete.setDouble(2, avion.getVitesseX());
            requete.setDouble(3, avion.getVitesseY());
            requete.setDouble(4, avion.getAltitude());
            requete.setDouble(5, avion.getDistancePiste());
            requete.setDouble(6, etat.getTempsEcouleSecondes());
            requete.setInt(7, idSimulation);
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Impossible de terminer la simulation", e);
        }
    }
}
