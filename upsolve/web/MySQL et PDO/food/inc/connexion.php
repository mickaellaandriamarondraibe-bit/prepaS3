<?php

function connection(){
    echo "coucou";
$host = 'localhost';
$dbname = 'TpMagasin';
$username = 'root';
$password = '';


try {
    $conn = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "reussi";
    
    return $conn;
} catch (PDOException $e) {
    echo "Erreur de connexion: " . $e->getMessage();
}
}


?>