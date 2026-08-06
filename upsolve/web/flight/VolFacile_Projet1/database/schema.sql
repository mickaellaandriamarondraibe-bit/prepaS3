
CREATE DATABASE vol;
USE vol;

CREATE TABLE vol (
  id INT AUTO_INCREMENT PRIMARY KEY,
  ville_depart VARCHAR(150) NOT NULL,
  ville_arrivee VARCHAR(150) NOT NULL,
  heure_vol TIME,
  date_vol DATE,
  prix DOUBLE
);
CREATE TABLE passager 
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom_passager VARCHAR(100) NOT NULL,
  mail VARCHAR (150) NOT NULL
);
CREATE TABLE reservation
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_vol INT NOT NULL,
  id_passager INT NOT NULL,
  nombe_place INT NOT NULL,

  FOREIGN KEY (id_vol) REFERENCES vol(id),
  FOREIGN KEY (id_passager) REFERENCES passager(id)

);

INSERT INTO vol (ville_depart, ville_arrivee, heure_vol, date_vol, prix)
VALUES
('Antananarivo', 'Paris', '08:30:00', '2026-08-10', 1200),
('Paris', 'Chine', '14:00:00', '2026-08-12', 950),
('Antananarivo', 'Maurice', '09:15:00', '2026-08-15', 350);

INSERT INTO passager (nom_passager, mail)
VALUES
('HAINGOTIANA Stephanie', 'stephanie.rakoto@mail.com'),
('Marie Dupont', 'marie.dupont@mail.com'),
('Li Wang', 'li.wang@mail.com');
