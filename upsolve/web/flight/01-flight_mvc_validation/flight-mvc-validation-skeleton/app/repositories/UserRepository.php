<?php
class UserRepository {
  private $pdo;

  public function __construct(PDO $pdo) {
    $this->pdo = $pdo;
  }

  public function emailExists($email) {
    $st = $this->pdo->prepare("SELECT 1 FROM users WHERE email=? LIMIT 1");
    $st->execute(array((string)$email));

    $result = $st->fetchColumn();

    if ($result) {
      return true;
    }

    return false;
  }

  public function create($nom, $prenom, $email, $hash, $telephone) {
    $st = $this->pdo->prepare("
      INSERT INTO users(nom, prenom, email, password_hash, telephone)
      VALUES(?,?,?,?,?)
    ");

    $params = array(
      (string)$nom,
      (string)$prenom,
      (string)$email,
      (string)$hash,
      (string)$telephone
    );

    $st->execute($params);

    return $this->pdo->lastInsertId();
  }
}
