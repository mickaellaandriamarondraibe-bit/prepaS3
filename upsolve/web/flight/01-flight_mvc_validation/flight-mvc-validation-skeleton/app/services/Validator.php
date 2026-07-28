<?php
class Validator {

  public static function normalizeTelephone($tel) {
    $telephone = trim((string)$tel);
    $telephone = preg_replace('/\s+/', '', $telephone);

    return $telephone;
  }

  public static function validateRegister($input, UserRepository $repo = null) {
    $errors = array(
      'nom' => '',
      'prenom' => '',
      'email' => '',
      'password' => '',
      'confirm_password' => '',
      'telephone' => ''
    );

    $nom = self::readInput($input, 'nom');
    $prenom = self::readInput($input, 'prenom');
    $email = self::readInput($input, 'email');
    $password = self::readInput($input, 'password');
    $confirm = self::readInput($input, 'confirm_password');
    $telephone = self::readInput($input, 'telephone');

    $values = array(
      'nom' => trim($nom),
      'prenom' => trim($prenom),
      'email' => trim($email),
      'telephone' => self::normalizeTelephone($telephone)
    );

    if (mb_strlen($values['nom']) < 2) {
      $errors['nom'] = "Le nom doit contenir au moins 2 caractères.";
    }

    if (mb_strlen($values['prenom']) < 2) {
      $errors['prenom'] = "Le prénom doit contenir au moins 2 caractères.";
    }

    if ($values['email'] === '') {
      $errors['email'] = "L'email est obligatoire.";
    } else if (!filter_var($values['email'], FILTER_VALIDATE_EMAIL)) {
      $errors['email'] = "L'email n'est pas valide (ex: nom@domaine.com).";
    }

    if (strlen($password) < 8) {
      $errors['password'] = "Le mot de passe doit contenir au moins 8 caractères.";
    }

    if (strlen($confirm) < 8) {
      $errors['confirm_password'] = "Veuillez confirmer le mot de passe (min 8 caractères).";
    } else if ($password !== $confirm) {
      $errors['confirm_password'] = "Les mots de passe ne correspondent pas.";

      if ($errors['password'] === '') {
        $errors['password'] = "Vérifiez le mot de passe et sa confirmation.";
      }
    }

    $tel = $values['telephone'];

    if (strlen($tel) < 8 || strlen($tel) > 15) {
      $errors['telephone'] = "Le téléphone doit contenir entre 8 et 15 chiffres.";
    } else if (!preg_match('/^[0-9]+$/', $tel)) {
      $errors['telephone'] = "Le téléphone ne doit contenir que des chiffres.";
    }

    if ($repo != null && $errors['email'] === '' && $repo->emailExists($values['email'])) {
      $errors['email'] = "Cet email est déjà utilisé.";
    }

    $ok = true;

    foreach ($errors as $message) {
      if ($message !== '') {
        $ok = false;
        break;
      }
    }

    return array(
      'ok' => $ok,
      'errors' => $errors,
      'values' => $values
    );
  }

  private static function readInput($input, $name) {
    if (isset($input[$name])) {
      return (string)$input[$name];
    }

    return '';
  }
}
