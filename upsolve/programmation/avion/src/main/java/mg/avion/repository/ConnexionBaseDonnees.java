package mg.avion.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mg.avion.config.ConfigurationBaseDonnees;

public class ConnexionBaseDonnees {
    private final ConfigurationBaseDonnees configuration;

    public ConnexionBaseDonnees() {
        this.configuration = new ConfigurationBaseDonnees();
    }

    public Connection ouvrirConnexion() throws SQLException {
        return DriverManager.getConnection(configuration.getAdresse(), configuration.getUtilisateur(), configuration.getMotDePasse());
    }
}
