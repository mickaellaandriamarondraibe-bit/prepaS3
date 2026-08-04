<?php

class ProductRepository 
{
    private $db;

    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function getProduits()
    {
        $sql = 'SELECT id, nom FROM products ORDER BY id';
        $stmt = $this->db->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll();
    }
   
} 
