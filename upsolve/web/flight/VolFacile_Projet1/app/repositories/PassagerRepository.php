<?php class PassagerRepository
{
    private $db;

    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function insertPassager($nom_passager, $email)
    {
        $sqlPassager = 'INSERT INTO passager (nom_passager, mail) VALUES (:nom_passager, :mail)';
        $stmt = $this->db->prepare($sqlPassager);
        $stmt->execute([
            'nom_passager' => $nom_passager,
            'mail' => $email
        ]);

        return $this->db->lastInsertId();
    }
    public function verifierPassager($mail)
    {
        $sql = 'SELECT mail FROM passager WHERE mail = :mail';
        $stmt = $this->db->prepare($sql);
        $stmt->execute([
            'mail' => $mail
        ]);

        return $stmt->fetch();
    }
}
?>
