# Gestion de Stock

Application Java Swing pour surveiller les entrees et sorties d'articles avec une base PostgreSQL.

## Technologies

- Java
- Swing
- PostgreSQL
- JDBC

## Base de donnees

- Base : `gestion_stock`
- Utilisateur : `postgres`
- Mot de passe : `postgres`
- Port : `5432`

Le script SQL principal se trouve dans `base.sql`.

## Architecture

```text
gestion_de_stock/
├── base.sql
├── sujet.md
├── README.md
├── lib/
│   └── postgresql.jar
├── docs/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── config/
│   │   └── DatabaseConfig.java
│   ├── model/
│   │   ├── Article.java
│   │   ├── EntreeStock.java
│   │   ├── SortieStock.java
│   │   ├── MouvementStock.java
│   │   └── MethodeSortie.java
│   ├── dao/
│   │   ├── DatabaseConnection.java
│   │   ├── ArticleDao.java
│   │   ├── EntreeStockDao.java
│   │   └── SortieStockDao.java
│   ├── service/
│   │   └── StockService.java
│   └── ui/
│       ├── MainFrame.java
│       ├── ArticlePanel.java
│       ├── MouvementPanel.java
│       └── StockSummaryPanel.java
```

## Prochaines etapes

1. Implementer la connexion PostgreSQL.
2. Implementer les modeles.
3. Implementer les DAO.
4. Implementer la logique FIFO, LIFO et CUMP.
5. Construire l'interface Swing.
