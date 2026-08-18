package dao;

import model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public void ajouter(Article article) throws SQLException {
        String sql = "INSERT INTO article (nom) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article.getNom());
            statement.executeUpdate();
        }
    }

    public List<Article> lister() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT id, nom, date_creation FROM article ORDER BY nom";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                articles.add(mapperArticle(resultSet));
            }
        }

        return articles;
    }

    public Article trouverParId(int id) throws SQLException {
        String sql = "SELECT id, nom, date_creation FROM article WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapperArticle(resultSet);
                }
            }
        }

        return null;
    }

    public boolean nomExiste(String nom) throws SQLException {
        String sql = "SELECT COUNT(*) FROM article WHERE LOWER(nom) = LOWER(?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nom);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    public boolean nomExistePourUnAutreArticle(int id, String nom) throws SQLException {
        String sql = "SELECT COUNT(*) FROM article WHERE LOWER(nom) = LOWER(?) AND id <> ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nom);
            statement.setInt(2, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }

    public void modifier(Article article) throws SQLException {
        String sql = "UPDATE article SET nom = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article.getNom());
            statement.setInt(2, article.getId());
            statement.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM article WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Article mapperArticle(ResultSet resultSet) throws SQLException {
        Timestamp dateCreation = resultSet.getTimestamp("date_creation");

        return new Article(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                dateCreation == null ? null : dateCreation.toLocalDateTime()
        );
    }
}
