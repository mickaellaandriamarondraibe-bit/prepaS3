<?php
namespace App\Models;
use CodeIgniter\Model;

class LivreModel extends Model{
    protected $table ='livres';
    protected $primaryKey='id';
    protected $allowedFields =[
    'titre',
    'auteur',
    'prix',
    'quantite'
    ];

    public function addLivre($data){
    $this->insert($data);
    }
    public function getalllivre(){
        return $this ->findAll();
    }
    public function getLivreId($id){
        return $this->where('id', $id)->first();
    }
    public function updateLivre($id , $data){
        return $this->update($id, $data);
    }
    public function deleteLivre($id){
        return $this->delete($id);
    }

}
?>