# Simulation Avion

Application Java Swing qui simule l'atterrissage d'un avion avec vitesse, altitude, distance de piste et freinage.

## Technologies

- Java 17
- Swing
- PostgreSQL
- JDBC

## Lancement

```bash
./compiler.sh
./lancer.sh
```

## Configuration Base De Donnees

Modifier `src/main/resources/configuration.properties` avec les identifiants PostgreSQL.

Creer les tables avec :

```bash
psql -d avion -f database/structure.sql
```

## Driver PostgreSQL

Pour utiliser PostgreSQL, placer le fichier `.jar` du driver JDBC PostgreSQL dans un dossier `lib/`.

Exemple :

```txt
lib/postgresql-42.7.4.jar
```
