<?php
include("connexion.php");
function getAllcategorie(){
    $conn = connection();
        if (!$conn) return null;

    $sql="SELECT * FROM categorie";
    $statement=$conn=prepare($sql);
    $statement -> execute();
    if($row = $res-> fetch_assoc()){
        echo $row['nom'];
    }

}
