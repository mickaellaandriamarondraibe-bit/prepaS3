create database avion;

CREATE TABLE IF NOT EXISTS simulations (
    id SERIAL PRIMARY KEY,
    statut VARCHAR(30) NOT NULL,
    vitesse_x_finale DOUBLE PRECISION,
    vitesse_y_finale DOUBLE PRECISION,
    altitude_finale DOUBLE PRECISION,
    distance_finale DOUBLE PRECISION,
    duree_secondes DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS etapes_simulation (
    id SERIAL PRIMARY KEY,
    simulation_id INTEGER NOT NULL REFERENCES simulations(id) ON DELETE CASCADE,
    temps_secondes DOUBLE PRECISION NOT NULL,
    vitesse_x DOUBLE PRECISION NOT NULL,
    vitesse_y DOUBLE PRECISION NOT NULL,
    altitude DOUBLE PRECISION NOT NULL,
    distance_piste DOUBLE PRECISION NOT NULL,
    freinage_x DOUBLE PRECISION NOT NULL,
    freinage_y DOUBLE PRECISION NOT NULL
);
