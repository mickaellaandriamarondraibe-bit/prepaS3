package app;

import dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connexion reussie a PostgreSQL !");
            System.out.println("Base de donnees : " + connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion a PostgreSQL.");
            System.out.println(e.getMessage());
        }
    }
}
