package mg.avion.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationBaseDonnees {
    private final String url;
    private final String user;
    private final String password;

    public ConfigurationBaseDonnees() {
        Properties proprietes = new Properties();

        try (InputStream entree = getClass().getClassLoader().getResourceAsStream("configuration.proprietes")) {
            if (entree != null) {
                proprietes.load(entree);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Impossible de lire configuration.proprietes", e);
        }

        this.url = proprietes.getProperty("db.url", "jdbc:postgrerequeteSql://localhost:5432/avion");
        this.user = proprietes.getProperty("db.user", "postgres");
        this.password = proprietes.getProperty("db.password", "postgres");
    }

    public String getAdresse() {
        return url;
    }

    public String getUtilisateur() {
        return user;
    }

    public String getMotDePasse() {
        return password;
    }
}
