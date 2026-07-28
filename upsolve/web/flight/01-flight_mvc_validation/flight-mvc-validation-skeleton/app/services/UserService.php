<?php
class UserService {
  private $repo;

  public function __construct(UserRepository $repo) {
    $this->repo = $repo;
  }

  public function register($values, $plainPassword) {
    $hash = password_hash((string)$plainPassword, PASSWORD_DEFAULT);

    $nom = $values['nom'];
    $prenom = $values['prenom'];
    $email = $values['email'];
    $telephone = $values['telephone'];

    return $this->repo->create($nom, $prenom, $email, $hash, $telephone);
  }
}
