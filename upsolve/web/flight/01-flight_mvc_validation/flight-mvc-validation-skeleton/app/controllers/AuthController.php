<?php
class AuthController {

  public static function showRegister() {
    $values = self::emptyValues();
    $errors = self::emptyErrors();

    Flight::render('auth/register', array(
      'values' => $values,
      'errors' => $errors,
      'success' => false
    ));
  }

  public static function validateRegisterAjax() {
    header('Content-Type: application/json; charset=utf-8');

    try {
      $pdo  = Flight::db();
      $repo = new UserRepository($pdo);

      $req = Flight::request();

      $input = array(
        'nom' => $req->data->nom,
        'prenom' => $req->data->prenom,
        'email' => $req->data->email,
        'password' => $req->data->password,
        'confirm_password' => $req->data->confirm_password,
        'telephone' => $req->data->telephone
      );

      $res = Validator::validateRegister($input, $repo);

      Flight::json(array(
        'ok' => $res['ok'],
        'errors' => $res['errors'],
        'values' => $res['values']
      ));
    } catch (Throwable $e) {
      http_response_code(500);
      Flight::json(array(
        'ok' => false,
        'errors' => array('_global' => 'Erreur serveur lors de la validation.'),
        'values' => array()
      ));
    }
  }

  public static function postRegister() {
    $pdo  = Flight::db();
    $repo = new UserRepository($pdo);
    $svc  = new UserService($repo);

    $req = Flight::request();

    $input = array(
      'nom' => $req->data->nom,
      'prenom' => $req->data->prenom,
      'email' => $req->data->email,
      'password' => $req->data->password,
      'confirm_password' => $req->data->confirm_password,
      'telephone' => $req->data->telephone
    );

    $res = Validator::validateRegister($input, $repo);

    if ($res['ok']) {
      $svc->register($res['values'], (string)$input['password']);

      Flight::render('auth/register', array(
        'values' => self::emptyValues(),
        'errors' => self::emptyErrors(),
        'success' => true
      ));

      return;
    }

    Flight::render('auth/register', array(
      'values' => $res['values'],
      'errors' => $res['errors'],
      'success' => false
    ));
  }

  private static function emptyValues() {
    return array(
      'nom' => '',
      'prenom' => '',
      'email' => '',
      'telephone' => ''
    );
  }

  private static function emptyErrors() {
    return array(
      'nom' => '',
      'prenom' => '',
      'email' => '',
      'password' => '',
      'confirm_password' => '',
      'telephone' => ''
    );
  }
}
