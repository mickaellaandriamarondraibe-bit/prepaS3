package config;

public final class DatabaseConfig {
    public static final String URL = "jdbc:postgresql://localhost:5432/gestion_stock";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    private DatabaseConfig() {
    }
}
