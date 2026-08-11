<?php
namespace App\Models;
use CodeIgniter\Model;

class UserModel extends Model
{
    protected $table = 'user';
    protected $primarykey = 'id';
    protected $allowdFields = 
    [
    'username',
    'email'
    ];
    

public function getAllUser()
{
return $this-> findAll();
}
public function getid($id)
{
    return $this-> where( 'id', $id) -> first();
   
}
}

?>