package dao;

import model.Article;
import model.MouvementStock;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MouvementDao {
    private static final int TYPE_ENTREE_ID = 1;
    private static final int TYPE_SORTIE_ID = 2;

    public void ajouterEntree(MouvementStock mouvement) throws SQLException {
        String sql = """
                INSERT INTO mouvement
                    (id_type_mouvement, id_methode, article_id, quantite, quantite_restante, prix_unitaire, date_mouvement)
                VALUES (?, NULL, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, TYPE_ENTREE_ID);
            statement.setInt(2, mouvement.getArticle().getId());
            statement.setInt(3, mouvement.getQuantite());
            statement.setInt(4, mouvement.getQuantiteRestante());
            statement.setBigDecimal(5, mouvement.getPrixUnitaire());
            statement.setDate(6, Date.valueOf(mouvement.getDateMouvement()));
            statement.executeUpdate();
        }
    }

    public void ajouterSortie(Connection connection, MouvementStock mouvement) throws SQLException {
        String sql = """
                INSERT INTO mouvement
                    (id_type_mouvement, id_methode, article_id, quantite, quantite_restante, prix_unitaire, date_mouvement)
                VALUES (?, ?, ?, ?, 0, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, TYPE_SORTIE_ID);
            statement.setInt(2, getMethodeId(mouvement.getMethode()));
            statement.setInt(3, mouvement.getArticle().getId());
            statement.setInt(4, mouvement.getQuantite());
            statement.setBigDecimal(5, mouvement.getPrixUnitaire());
            statement.setDate(6, Date.valueOf(mouvement.getDateMouvement()));
            statement.executeUpdate();
        }
    }

    public List<MouvementStock> listerTous() throws SQLException {
        List<MouvementStock> mouvements = new ArrayList<>();
        String sql = """
                SELECT m.id, tm.nom AS type_mouvement, NULL AS methode_mouvement,
                       m.quantite, m.quantite_restante, m.prix_unitaire, m.date_mouvement,
                       a.id AS article_id, a.nom, a.date_creation
                FROM mouvement m
                JOIN type_mouvement tm ON tm.id = m.id_type_mouvement
                JOIN article a ON a.id = m.article_id
                ORDER BY m.date_mouvement DESC, m.id DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                mouvements.add(mapperMouvement(resultSet));
            }
        }

        return mouvements;
    }

    public List<MouvementStock> listerParArticle(int articleId) throws SQLException {
        List<MouvementStock> mouvements = new ArrayList<>();
        String sql = """
                SELECT m.id, tm.nom AS type_mouvement, mm.nom AS methode_mouvement,
                       m.quantite, m.quantite_restante, m.prix_unitaire, m.date_mouvement,
                       a.id AS article_id, a.nom, a.date_creation
                FROM mouvement m
                JOIN type_mouvement tm ON tm.id = m.id_type_mouvement
                LEFT JOIN methode_mouvement mm ON mm.id = m.id_methode
                JOIN article a ON a.id = m.article_id
                WHERE m.article_id = ?
                ORDER BY m.date_mouvement DESC, m.id DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mouvements.add(mapperMouvement(resultSet));
                }
            }
        }

        return mouvements;
    }

    public List<MouvementStock> listerEntreesDisponibles(Connection connection, int articleId,
                                                         boolean lifo) throws SQLException {
        List<MouvementStock> mouvements = new ArrayList<>();
        String ordre = lifo ? "DESC" : "ASC";
        String sql = """
                SELECT m.id, tm.nom AS type_mouvement, NULL AS methode_mouvement,
                       m.quantite, m.quantite_restante, m.prix_unitaire, m.date_mouvement,
                       a.id AS article_id, a.nom, a.date_creation
                FROM mouvement m
                JOIN type_mouvement tm ON tm.id = m.id_type_mouvement
                JOIN article a ON a.id = m.article_id
                WHERE m.article_id = ? AND m.id_type_mouvement = ? AND m.quantite_restante > 0
                ORDER BY m.date_mouvement %s, m.id %s
                FOR UPDATE OF m
                """.formatted(ordre, ordre);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, articleId);
            statement.setInt(2, TYPE_ENTREE_ID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mouvements.add(mapperMouvement(resultSet));
                }
            }
        }

        return mouvements;
    }

    public void modifierQuantiteRestante(Connection connection, int mouvementId, int quantiteRestante) throws SQLException {
        String sql = "UPDATE mouvement SET quantite_restante = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantiteRestante);
            statement.setInt(2, mouvementId);
            statement.executeUpdate();
        }
    }

    private int getMethodeId(String methode) {
        if ("FIFO".equals(methode)) {
            return 1;
        }
        if ("LIFO".equals(methode)) {
            return 2;
        }
        return 3;
    }

    private MouvementStock mapperMouvement(ResultSet resultSet) throws SQLException {
        Timestamp dateCreation = resultSet.getTimestamp("date_creation");
        Article article = new Article(
                resultSet.getInt("article_id"),
                resultSet.getString("nom"),
                dateCreation == null ? null : dateCreation.toLocalDateTime()
        );

        return new MouvementStock(
                resultSet.getInt("id"),
                resultSet.getString("type_mouvement"),
                resultSet.getString("methode_mouvement"),
                article,
                resultSet.getInt("quantite"),
                resultSet.getInt("quantite_restante"),
                resultSet.getBigDecimal("prix_unitaire"),
                resultSet.getDate("date_mouvement").toLocalDate()
        );
    }
}
