<?php

class UserRepository
{
    private $db;

    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function emailExists($email)
    {
        $sql = 'SELECT id FROM users WHERE email = ? LIMIT 1';
        $stmt = $this->db->prepare($sql);
        $stmt->execute([$email]);

        return $stmt->fetch() !== false;
    }

    public function create(array $user, $password)
    {
        $sql = 'INSERT INTO users (nom, prenom, email, password_hash, telephone)
                VALUES (?, ?, ?, ?, ?)';

        $stmt = $this->db->prepare($sql);
        $stmt->execute([
            $user['nom'],
            $user['prenom'],
            $user['email'],
            password_hash($password, PASSWORD_DEFAULT),
            $user['telephone']
        ]);
    }
}
