CREATE DATABASE gestion_stock;

\c gestion_stock;

CREATE TABLE IF NOT EXISTS article (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150) NOT NULL UNIQUE,
    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS type_mouvement (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS methode_mouvement (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS mouvement (
    id SERIAL PRIMARY KEY,
    id_type_mouvement INTEGER NOT NULL REFERENCES type_mouvement(id),
    id_methode INTEGER DEFAULT NULL REFERENCES methode_mouvement(id),
    article_id INTEGER NOT NULL REFERENCES article(id) ON DELETE CASCADE ,
    quantite INTEGER NOT NULL CHECK (quantite > 0),
    quantite_restante INTEGER NOT NULL DEFAULT 0 CHECK (quantite_restante >= 0),
    prix_unitaire NUMERIC(12, 2) NOT NULL CHECK (prix_unitaire >= 0),
    date_mouvement DATE NOT NULL
);

INSERT INTO type_mouvement (nom)
VALUES ('ENTREE'), ('SORTIE')
ON CONFLICT (nom) DO NOTHING;

INSERT INTO methode_mouvement (nom)
VALUES ('FIFO'), ('LIFO'), ('CUMP')
ON CONFLICT (nom) DO NOTHING;
