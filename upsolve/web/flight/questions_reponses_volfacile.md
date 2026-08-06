# Questions / Réponses - Projet VolFacile

## 1. Comment utiliser une foreign key dans mes tables ?

Une `FOREIGN KEY` sert à relier une colonne d'une table à la clé primaire d'une autre table.

Dans ton projet :

- `reservation.id_vol` référence `vol.id`
- `reservation.id_passager` référence `passager.id`

Exemple :

```sql
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

CREATE TABLE passager (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom_passager VARCHAR(100) NOT NULL,
  mail VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE reservation (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_vol INT NOT NULL,
  id_passager INT NOT NULL,
  nombre_place INT NOT NULL,

  FOREIGN KEY (id_vol) REFERENCES vol(id),
  FOREIGN KEY (id_passager) REFERENCES passager(id)
);
```

Il faut insérer d'abord les données dans `vol` et `passager`, puis ensuite dans `reservation`.

## 2. Donner 3 exemples pour chaque table

Exemples pour la table `vol` :

```sql
INSERT INTO vol (ville_depart, ville_arrivee, heure_vol, date_vol, prix)
VALUES
('Antananarivo', 'Paris', '08:30:00', '2026-08-10', 1200),
('Paris', 'Chine', '14:00:00', '2026-08-12', 950),
('Antananarivo', 'Maurice', '09:15:00', '2026-08-15', 350);
```

Exemples pour la table `passager` :

```sql
INSERT INTO passager (nom_passager, mail)
VALUES
('Jean Rakoto', 'jean.rakoto@mail.com'),
('Marie Dupont', 'marie.dupont@mail.com'),
('Li Wang', 'li.wang@mail.com');
```

Exemples pour la table `reservation` :

```sql
INSERT INTO reservation (id_vol, id_passager, nombre_place)
VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 3);
```

## 3. Donner un texte de bienvenue sur le vol

Bienvenue sur notre service de réservation de vols.

Découvrez facilement les vols disponibles, choisissez votre destination, consultez les horaires et réservez votre place en quelques instants. Que vous voyagiez pour les vacances, le travail ou une nouvelle aventure, nous vous accompagnons pour rendre votre voyage simple, rapide et agréable.

## 4. Que signifie une erreur PHP dans `routes.php` ligne 2 ?

Si l'erreur indique un problème sur cette ligne :

```php
require_once __DIR__ . '/services/ProduitService.php';
```

cela signifie souvent que PHP ne trouve pas le fichier demandé, ou que le chemin n'est pas correct.

Dans le projet VolFacile, les fichiers doivent plutôt correspondre aux noms du projet :

```php
require_once __DIR__ . '/services/VolService.php';
require_once __DIR__ . '/repositories/VolRepository.php';
require_once __DIR__ . '/controllers/VolController.php';
```

## 5. Pourquoi `/listeVol.php` donne une erreur 404 ?

Avec FlightPHP, il ne faut pas ouvrir directement une vue comme :

```text
http://localhost:8000/listeVol.php
```

Le fichier `listeVol.php` est dans `app/views`, donc il doit être appelé par une route.

La bonne route est :

```php
Flight::route('GET /listeVol', ['VolController', 'showVol']);
```

Il faut donc ouvrir :

```text
http://localhost:8000/listeVol
```

Et dans `Accueil.php`, le lien doit être :

```php
<a href="/listeVol">Voir liste des vols</a>
```

## 6. Pourquoi `/listeVol` donne une erreur 500 ?

Une erreur `500 Internal Server Error` signifie que la route existe, mais qu'une erreur PHP arrive pendant l'exécution.

Dans ton cas, le problème venait probablement de la base de données :

```php
define('DB_NAME', 'tp_validation');
```

alors que ton fichier SQL créait :

```sql
CREATE DATABASE vol;
```

La correction :

```php
define('DB_NAME', 'vol');
```

Et dans `schema.sql`, il faut ajouter :

```sql
USE vol;
```

## 7. Comment passer l'id avec le bouton "Voir détail" ?

Il faut mettre l'id dans l'URL.

Dans `listeVol.php` :

```php
<td><a href="/DetailVol/<?= $data['id'] ?>">Voir Détail</a></td>
```

Dans `routes.php` :

```php
Flight::route('GET /DetailVol/@id', ['VolController', 'showDetailVol']);
```

Dans le contrôleur :

```php
public static function showDetailVol($id)
{
    $service = new VolService(new VolRepository(Flight::db()));
    $result = $service->selectDetailVol($id);

    Flight::render('DetailVol', [
        'detailVol' => $result['values']
    ]);
}
```

Dans le repository :

```php
public function getDetailVol($id)
{
    $sql = 'SELECT * FROM vol WHERE id = :id';
    $stmt = $this->db->prepare($sql);
    $stmt->execute([
        'id' => $id
    ]);

    return $stmt->fetch();
}
```

## 8. Comment faire un formulaire de réservation ?

Il faut :

- une route `GET` pour afficher le formulaire
- une route `POST` pour enregistrer
- une vue avec le formulaire
- une validation côté serveur
- une insertion dans `passager`, puis dans `reservation`

Routes :

```php
Flight::route('GET /FormulaireReservation/@id', ['ReservationController', 'showFormulaireReservation']);
Flight::route('POST /FormulaireReservation', ['ReservationController', 'saveReservation']);
```

Lien depuis le détail du vol :

```php
<a href="/FormulaireReservation/<?= $detailVol['id'] ?>">Réserver ce vol</a>
```

## 9. Validation du formulaire de réservation

Le formulaire doit demander :

- le nom du passager
- l'adresse e-mail
- le nombre de places

Règles :

- le nom est obligatoire
- l'e-mail est obligatoire
- l'e-mail doit avoir un format valide
- le nombre de places est obligatoire
- le nombre de places doit être supérieur ou égal à 1
- la validation doit se faire uniquement côté serveur avec PHP
- les erreurs doivent être affichées sous le formulaire

Exemple de validation :

```php
$errors = [];

if ($nom_passager === '') {
    $errors[] = 'Le nom du passager est obligatoire.';
}

if ($email === '') {
    $errors[] = 'L’adresse e-mail est obligatoire.';
} elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $errors[] = 'L’adresse e-mail doit avoir un format valide.';
}

if ($nombre_place === '') {
    $errors[] = 'Le nombre de places est obligatoire.';
} elseif (!ctype_digit($nombre_place) || (int) $nombre_place < 1) {
    $errors[] = 'Le nombre de places doit être supérieur ou égal à 1.';
}
```

## 10. Explication de la fonction `insertReservation`

La fonction `insertReservation` sert à enregistrer une réservation dans la base.

Elle ajoute d'abord la réservation dans la table `reservation` :

```php
public function insertReservation($id_vol, $id_passager, $nombre_place)
{
    $sqlReservation = 'INSERT INTO reservation (id_vol, id_passager, nombe_place)
                       VALUES (:id_vol, :id_passager, :nombe_place)';

    $stmt = $this->db->prepare($sqlReservation);
    $stmt->execute([
        'id_vol' => $id_vol,
        'id_passager' => $id_passager,
        'nombe_place' => $nombre_place
    ]);
}
```

`id_vol` indique le vol réservé.

`id_passager` indique le passager qui réserve.

`nombe_place` indique le nombre de places réservées.

Attention : dans ta base, la colonne s'appelle `nombe_place`. Si tu la renommes en `nombre_place`, il faut aussi modifier le code PHP.

## 11. Erreur `Cannot declare class ReservationRepository, because the name is already in use`

Cette erreur signifie que PHP trouve deux classes avec le même nom.

Dans ton projet, le problème était dans `PassagerRepository.php` :

```php
<?php class ReservationRepository
```

Alors que ça devait être :

```php
<?php class PassagerRepository
```

Il fallait aussi corriger le constructeur du service :

```php
public function __construct(ReservationRepository $reservationRepository, PassagerRepository $passagerRepository)
```

et non :

```php
public function construct(...)
```

## 12. Qu'est-ce qui est incorrect dans la requête SQL avec `JOIN` ?

Requête incorrecte :

```sql
SELECT nom_passager, mail, id_vol, nombre_place, prix
FROM passager
JOIN reservation WHERE passager.id = reservation.id
JOIN vol WHERE reservation.id = vol.id;
```

Problèmes :

- avec `JOIN`, il faut utiliser `ON`, pas `WHERE`
- les clés utilisées ne sont pas bonnes
- `reservation.id_passager` référence `passager.id`
- `reservation.id_vol` référence `vol.id`

Bonne requête :

```sql
SELECT 
  passager.nom_passager,
  passager.mail,
  reservation.id_vol,
  reservation.nombe_place,
  vol.prix
FROM passager
JOIN reservation ON passager.id = reservation.id_passager
JOIN vol ON reservation.id_vol = vol.id;
```

## 13. Comment faire une méthode `joinPassagerReservation` ?

La méthode sert à afficher les réservations avec les informations du passager et du vol.

```php
public function joinPassagerReservation()
{
    $sql = 'SELECT
                passager.nom_passager,
                passager.mail,
                reservation.id_vol AS vol,
                reservation.nombe_place AS nombre_de_place,
                vol.prix AS prix
            FROM passager
            JOIN reservation ON passager.id = reservation.id_passager
            JOIN vol ON reservation.id_vol = vol.id
            ORDER BY reservation.id_vol';

    $stmt = $this->db->prepare($sql);
    $stmt->execute();

    return $stmt->fetchAll();
}
```

## 14. Comment rediriger après le bouton "Réserver" ?

Un bouton `submit` n'utilise pas `href`.

Incorrect :

```php
<button type="submit" href="/ReservationConfirmer">Réserver</button>
```

Correct :

```php
<button type="submit" class="btn btn-primary">Réserver</button>
```

La redirection se fait dans le contrôleur, après l'enregistrement :

```php
Flight::redirect('/ReservationConfirmer');
```

Le fonctionnement est :

```text
bouton submit -> POST /FormulaireReservation -> saveReservation() -> redirect /ReservationConfirmer
```

## 15. Que prend `<form method="POST" action="/FormulaireReservation">` ?

Cette ligne signifie que le formulaire envoie ses données en `POST` vers la route `/FormulaireReservation`.

```html
<form method="POST" action="/FormulaireReservation">
```

Elle correspond à cette route :

```php
Flight::route('POST /FormulaireReservation', ['ReservationController', 'saveReservation']);
```

Quand on clique sur le bouton :

```html
<button type="submit">Réserver</button>
```

FlightPHP appelle :

```php
ReservationController::saveReservation()
```

Les champs envoyés sont ceux qui ont un attribut `name` :

```html
<input type="hidden" name="id_vol">
<input type="text" name="nom_passager">
<input type="text" name="email">
<input type="text" name="nombre_place">
```

## 16. Ajouter Bootstrap et CSS au projet

Les vues doivent charger Bootstrap et le CSS personnalisé :

```html
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
```

Les pages principales à styliser sont :

- `Accueil.php`
- `listeVol.php`
- `DetailVol.php`
- `FormulaireReservation.php`
- `ReservationConfirmer.php`

## 17. Améliorer le design professionnellement

Pour avoir un design plus professionnel, il faut travailler :

- la navigation
- les espacements
- les boutons
- les tableaux
- les formulaires
- les images
- la hiérarchie typographique
- le responsive

Exemples d'éléments ajoutés :

- header sombre
- logo plus propre
- grande section d'accueil avec image
- boutons alignés et cohérents
- tableaux avec badges et images
- formulaire responsive
- page détail avec image et bloc de réservation

## 18. Vérifier les boutons, le logo et le responsive

Les problèmes observés :

- header mal aligné
- logo trop simple
- boutons mal espacés ou peu professionnels
- certains éléments collés au bord
- formulaire mal aligné
- tableau qui peut déborder sur mobile

Corrections appliquées :

- création d'un vrai `.container`
- logo en CSS avec symbole d'avion stylisé
- boutons avec `inline-flex`
- boutons avec tailles constantes
- bouton `Voir détail` avec classe dédiée `action-button`
- inputs en pleine largeur
- labels au-dessus des champs
- tableau avec scroll horizontal sur mobile
- vérification avec captures desktop et mobile

## 19. Commande utile pour tester après modification

Après modification du CSS, il faut souvent vider le cache du navigateur :

```text
Ctrl + F5
```

Puis ouvrir :

```text
http://localhost:8000/Accueil
```

ou :

```text
http://localhost:8000/listeVol
```

## 20. Pourquoi le premier design Bootstrap était trop simple ?

Le premier design utilisait surtout les classes Bootstrap de base :

```html
class="container mt-5"
class="table table-bordered table-striped"
class="btn btn-primary"
```

Ces classes rendent la page correcte, mais pas forcément professionnelle.

Pour un projet plus sérieux, il faut ajouter un vrai style personnalisé :

- une palette de couleurs cohérente
- une navigation bien alignée
- une page d'accueil avec une image forte
- des boutons avec taille et style constants
- des tableaux lisibles
- des espacements réguliers
- un responsive propre sur mobile

## 21. Comment rendre le header plus professionnel ?

Le header doit être aligné avec `flex`.

Exemple CSS :

```css
.app-nav .container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 28px;
  min-height: 76px;
}

.app-nav .navbar-nav {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 8px;
  margin-left: auto;
}
```

Cela permet d'avoir :

- le logo à gauche
- les liens à droite
- un alignement propre
- un header stable sur desktop

## 22. Pourquoi le logo avec seulement "V" n'était pas professionnel ?

Un simple carré avec la lettre `V` faisait trop basique.

La correction a été de créer une marque visuelle en CSS :

```html
<a class="navbar-brand" href="/Accueil">
    <span class="brand-mark"><span class="brand-wing"></span></span>
    <span class="brand-text">VolFacile</span>
</a>
```

Le CSS crée un petit symbole d'avion stylisé :

```css
.brand-mark {
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 42px;
  height: 42px;
  margin-right: 12px;
  background: linear-gradient(135deg, #eaf7ff 0%, #ffffff 100%);
  border-radius: 8px;
}
```

## 23. Comment corriger les boutons mal alignés ?

Le problème venait du fait que les boutons n'avaient pas tous la même hauteur, le même alignement et le même comportement.

La correction :

```css
.btn,
a.btn,
button.btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-height: 44px;
  padding: 0 20px;
  border-radius: 8px;
  border: 1px solid transparent;
  font-weight: 900;
  line-height: 1;
  text-align: center;
  white-space: nowrap;
}
```

Avec `inline-flex`, le texte du bouton est bien centré verticalement et horizontalement.

## 24. Pourquoi le bouton "Voir détail" était moche dans le tableau ?

Le bouton `Voir détail` ne ressortait pas bien avec les classes Bootstrap.

La solution a été de créer une classe spéciale :

```php
<a class="action-button" href="/DetailVol/<?= $data['id']?>">Voir détail</a>
```

CSS :

```css
.action-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-height: 38px;
  padding: 0 15px;
  color: #0b5cab;
  background: #ffffff;
  border: 1px solid rgba(11, 92, 171, 0.28);
  border-radius: 8px;
  font-size: 13px;
  font-weight: 900;
  white-space: nowrap;
}
```

## 25. Comment rendre la page flexible selon les écrans ?

Il faut utiliser des media queries.

Exemple :

```css
@media (max-width: 992px) {
  .hero-layout,
  .flight-summary,
  .form-card {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 520px) {
  .hero-actions .btn,
  .actions .btn {
    width: 100%;
  }
}
```

Cela permet :

- aux grilles de passer en une seule colonne
- aux boutons de prendre toute la largeur sur mobile
- au formulaire de rester lisible
- au tableau de ne pas casser la page

## 26. Pourquoi ajouter un vrai `.container` en CSS ?

Sur certaines pages, les éléments collaient trop aux bords de l'écran.

La correction :

```css
.container {
  width: min(100% - 48px, 1280px);
  max-width: 1280px;
  margin-right: auto;
  margin-left: auto;
  padding-right: 0;
  padding-left: 0;
}
```

Cela ajoute des marges propres à gauche et à droite, tout en limitant la largeur maximale du contenu.

## 27. Comment rendre le formulaire plus professionnel ?

Le formulaire doit avoir :

- des labels au-dessus des champs
- des inputs pleine largeur
- des espacements réguliers
- une carte ou un bloc visuel
- des boutons bien alignés

CSS important :

```css
.form-label {
  display: block;
  margin-bottom: 8px;
  font-weight: 900;
}

.form-control {
  display: block;
  width: 100%;
  min-height: 48px;
  padding: 10px 13px;
  border: 1px solid #d0d5dd;
  border-radius: 8px;
}
```

## 28. Comment corriger un tableau qui déborde sur mobile ?

Il faut entourer le tableau avec :

```html
<div class="table-responsive">
    <table class="table table-hover table-pro">
        ...
    </table>
</div>
```

Et ajouter :

```css
.table-responsive {
  width: 100%;
  overflow-x: auto;
}

.table {
  width: 100%;
  border-collapse: collapse;
}
```

Sur petit écran, le tableau peut défiler horizontalement sans casser toute la page.

## 29. Comment vérifier le design correctement ?

Il ne suffit pas de regarder le code.

Il faut tester plusieurs pages :

```text
/Accueil
/listeVol
/DetailVol/1
/FormulaireReservation/1
/ReservationConfirmer
```

Et vérifier plusieurs tailles :

- desktop
- tablette
- mobile

Dans cette discussion, le design a été vérifié avec des captures Chrome headless sur plusieurs largeurs d'écran.
