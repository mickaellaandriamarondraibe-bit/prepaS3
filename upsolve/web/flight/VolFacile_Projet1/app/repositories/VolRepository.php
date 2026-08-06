<?php class VolRepository 
{
    private $db;

    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function getVol()
    {
        $sql = 'SELECT*FROM vol ORDER BY id';
        $stmt = $this->db->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll();
    }
    public function getDetailVol($id)
    {
    $sql = "SELECT*FROM vol where id = :id";
    $stmt = $this->db->prepare($sql);
    $stmt->execute( [
                    'id'=> $id
    ]
             
    );
        return $stmt->fetch();

    }

}
?>
