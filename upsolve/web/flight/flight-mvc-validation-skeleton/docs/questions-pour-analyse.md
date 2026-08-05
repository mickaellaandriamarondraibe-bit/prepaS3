# Questions simples pour decouvrir le projet

Objectif : comprendre le chemin du code sans tout analyser d'un coup.

Consigne : lis un fichier, reponds aux questions, puis explique avec tes mots.

Si tu ne sais pas repondre, ce n'est pas grave. Copie le bout de code qui semble important, puis ecris ce que tu penses.

Exemple de reponse possible :

```text
Je ne suis pas sure, mais je vois ce code :

Flight::route('GET /register', ['AuthController', 'showRegister']);

Je pense que si on ouvre /register, ca appelle showRegister().
```

Autre exemple :

```text
Je ne comprends pas encore toute la ligne, mais je pense que ce code sert a verifier l'email.
```

## Section 1 - Le chemin general

1. Quel est le premier fichier appele quand le site demarre ?
2. Quel fichier prepare la configuration et la base de donnees ?
3. Quel fichier decide quelle methode appeler selon l'URL ?

   Exemple : si l'URL est `/register`, cherche dans ce fichier quelle fonction sera appelee.

4. Quel fichier recoit la demande de l'utilisateur ?
5. Quel fichier organise l'inscription ?
6. Quel fichier verifie les erreurs du formulaire ?
7. Quel fichier enregistre dans la base de donnees ?
8. Quel fichier affiche le formulaire ?

Resume le chemin avec des fleches :

```text
index.php -> ... -> ... -> ...
```

## Section 2 - Routes

Fichier : `app/routes.php`

1. Quelle route affiche le formulaire ?

   Exemple : quand on ouvre `/register` dans le navigateur, est-ce que c'est une route `GET` ou `POST` ?

2. Quelle route traite le formulaire envoye ?

   Exemple : quand on clique sur "S'inscrire", quelle route recoit les donnees ?

3. Quelle route sert a AJAX ?

   Exemple : dans JavaScript, quelle URL est appelee par `fetch()` ?

4. Quand on ouvre `/register`, quelle methode est appelee ?

   Exemple : selon toi, est-ce `showRegister()`, `postRegister()` ou `validateRegisterAjax()` ?

5. Quand on clique sur "S'inscrire", quelle methode est appelee ?

   Exemple : le formulaire utilise `POST`, donc cherche la route `POST /register`.

## Section 3 - Controller

Fichier : `app/controllers/AuthController.php`

1. Quelle methode affiche le formulaire vide ?

   Exemple : si l'utilisateur ouvre seulement la page, quelle methode du controller sert a afficher le HTML ?

2. Quelle methode recoit les donnees du formulaire ?

   Exemple : si `$_POST['email']` contient `test@gmail.com`, quelle methode utilise ces donnees ?

3. Quelle methode repond a AJAX ?

   Exemple : si JavaScript demande une verification sans recharger la page, quelle methode repond en JSON ?

4. Dans `postRegister()`, quelle classe est appelee pour faire le travail ?
5. Le controller fait-il directement la validation ?
6. Le controller fait-il directement le SQL ?
7. Explique le role du controller en une phrase.

## Section 4 - Service

Fichier : `app/services/UserService.php`

1. Quelle methode sert a verifier seulement les donnees ?

   Exemple : AJAX veut seulement savoir s'il y a des erreurs. Quelle methode du service est adaptee ?

2. Quelle methode sert a inscrire l'utilisateur ?

   Exemple : apres le clic final sur "S'inscrire", quelle methode fait toute l'inscription ?

3. Dans `register()`, quelle est la premiere chose verifiee ?

   Exemple : si le champ email est vide, quelle partie du code va le detecter ?

4. Que se passe-t-il s'il y a des erreurs ?

   Exemple : si le mot de passe est trop court, est-ce qu'on enregistre dans la base ?

5. Que se passe-t-il si l'email existe deja ?
6. Si tout est correct, quelle methode enregistre l'utilisateur ?
7. Explique le role du service en une phrase.

## Section 5 - Validator

Fichier : `app/services/Validator.php`

1. Quel tableau contient les erreurs ?

   Exemple : si l'email est mauvais, dans quelle case du tableau met-on le message ?

2. Quels champs sont obligatoires ?
3. Quelle condition verifie l'email ?

   Exemple : si l'email vaut `abc`, est-ce valide ?

4. Quelle condition verifie le mot de passe ?

   Exemple : si le mot de passe vaut `12`, est-ce valide ?

5. Quelle condition verifie la confirmation du mot de passe ?

   Exemple : si `password = 1234` et `confirm_password = 0000`, que doit retourner le validator ?

6. Que retourne le validator si tout est correct ?
7. Explique le role du validator en une phrase.

## Section 6 - Repository

Fichier : `app/repositories/UserRepository.php`

1. Quelle methode verifie si l'email existe deja ?

   Exemple : avant d'ajouter `test@gmail.com`, quelle methode cherche si cet email existe deja ?

2. Quelle methode ajoute l'utilisateur dans la base ?

   Exemple : si tout est correct, quelle methode fait le `INSERT INTO users` ?

3. Pourquoi utilise-t-on `prepare()` et `execute()` ?
4. Pourquoi utilise-t-on `password_hash()` ?

   Exemple : est-ce qu'on enregistre `1234` directement dans la base ?

5. Le repository affiche-t-il du HTML ?
6. Explique le role du repository en une phrase.

## Section 7 - Vue

Fichier : `app/views/auth/register.php`

1. Quels champs vois-tu dans le formulaire ?
2. Pourquoi chaque input a un attribut `name` ?

   Exemple : si un input a `name="email"`, comment PHP retrouve cette valeur dans `$_POST` ?

3. Ou sont affichees les erreurs ?

   Exemple : si `$errors['email']` existe, ou le message apparait-il dans la page ?

4. Ou est affiche le message de succes ?
5. Quel fichier JavaScript est charge ?
6. Explique le role de la vue en une phrase.

## Section 8 - AJAX

Fichier : `public/js/validation-ajax.js`

1. Quelle route AJAX appelle-t-il ?

   Exemple : cherche l'URL utilisee dans `fetch()`.

2. Que fait JavaScript quand il recoit des erreurs ?

   Exemple : si le serveur renvoie une erreur pour `email`, que devient le champ email ?

3. Que fait JavaScript quand tout est correct ?
4. AJAX enregistre-t-il vraiment l'utilisateur ?
5. Pourquoi PHP doit quand meme verifier a la fin ?

## Section 9 - Base de donnees

Fichier : `database/schema.sql`

1. Quel est le nom de la table ?
2. Quels champs contient la table ?
3. Pourquoi `email` est `UNIQUE` ?

   Exemple : peut-on avoir deux utilisateurs avec le meme email ?

4. Pourquoi le mot de passe est stocke dans `password_hash` ?

## Section 10 - Explication finale

Reponds sans regarder le code :

1. Que se passe-t-il quand on ouvre `/register` ?

   Exemple attendu : `/register` en GET va vers quelle methode, puis quelle vue ?

2. Que se passe-t-il quand on clique sur "S'inscrire" ?

   Exemple attendu : `/register` en POST va vers quelle methode, puis quel service ?

3. Que se passe-t-il si le formulaire contient une erreur ?

   Exemple : email vide. Quels fichiers travaillent avant d'afficher l'erreur ?

4. Que se passe-t-il si tout est correct ?

   Exemple : email valide et nouveau. Quels fichiers travaillent avant l'enregistrement ?

5. Explique le projet en moins de 1 minute.

## Section 11 - Si je bloque

Quand tu ne sais pas quoi repondre, utilise ce format :

```text
Question :

Bout de code que j'ai trouve :

Ce que je pense :

Ce que je ne comprends pas :
```

Exemple :

```text
Question : Quelle methode affiche le formulaire ?

Bout de code que j'ai trouve :
Flight::route('GET /register', ['AuthController', 'showRegister']);

Ce que je pense :
Je pense que quand on ouvre /register, ca appelle showRegister().

Ce que je ne comprends pas :
Je ne comprends pas encore pourquoi il y a GET.
```
