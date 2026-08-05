# Mini squelette FlightPHP MVC – Validation AJAX (PHP 7 + Bootstrap)

## Objectif
- Formulaire d’inscription avec **validation AJAX** (endpoint JSON) + **soumission finale** (POST classique).
- **Une seule source de vérité** des règles de validation côté serveur (`app/services/Validator.php`).

## Prérequis
- PHP 7.x
- MySQL
- Apache (XAMPP/WAMP/MAMP)
- FlightPHP installé (recommandé via Composer)

## Installation Flight (option Composer)
1) Ouvrir un terminal dans le dossier du projet
2) Installer les dépendances :
```bash
composer install
```
> Si vous êtes offline, vous pouvez préparer le dossier `vendor/` une fois en ligne et le copier sur les PCs des étudiants.

## Configuration DB
1) Créer la base et la table : `database/schema.sql`
2) Configurer l’accès DB : `app/config.php`

## Lancement (XAMPP)
- Placez le projet dans `htdocs/` (ou équivalent).
- Accès : `http://localhost/flight-mvc-validation-skeleton/public/register`

## Routes
- GET  /register                 -> page formulaire
- POST /register                 -> inscription (revalide + insert)
- POST /api/validate/register     -> validation AJAX (JSON)

## Fichiers importants
- `app/controllers/AuthController.php` : recoit les requetes et choisit la reponse.
- `app/services/Validator.php` : verifie les champs du formulaire.
- `app/services/UserService.php` : organise la validation et l'inscription.
- `app/repositories/UserRepository.php` : parle avec la table `users`.
- `app/views/auth/register.php` : affiche le formulaire HTML.
- `public/js/validation-ajax.js` : demande une validation sans recharger la page.
- `database/schema.sql` : cree la base de donnees et la table.

## Notes pédagogiques
- AJAX améliore l’UX.
- La sécurité reste côté serveur : `POST /register` **revalide toujours**.
