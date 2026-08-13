<?php
namespace App\Controllers;
use App\Models\LivreModel;

class LivreController extends BaseController{

    public function __construct(){
        $this->Livremod =new LivreModel();
    }

    public function getInsertLivre(){
    $data = [
        'titre' =>$this -> request ->getPost('titre'),
        'auteur'=> $this -> request -> getPost('auteur'),
        'prix'=> $this -> request -> getPost('prix'),
        'quantite'=> $this -> request -> getPost('quantite'),

    ];
    $this->Livremod->addLivre($data);
       return redirect()->to('/ListeLivre');
    }
    public function afficherforme(){
        return view('insertion');
    }
    public function getLivre(){
        $tablelivre = $this -> Livremod->getalllivre();
        return view('ListeLivre',
        [
            'livres' => $tablelivre
        ]);
    }
    public function formulaireModifierLivre($id){
        $livre = $this -> Livremod->getLivreId($id);
        return view('ModifierLivre',[
            'livres' => $livre
        ]);
    }
    public function ModifierLivre($id){
         $data = [
        'titre'    => $this->request->getPost('titre'),
        'auteur'   => $this->request->getPost('auteur'),
        'prix'     => $this->request->getPost('prix'),
        'quantite' => $this->request->getPost('quantite'),
    ];

    $this->Livremod->updateLivre($id, $data);

    return redirect()->to('/ListeLivre');
    }
    public function SupprimerLivre($id)
{
    $this->Livremod->deleteLivre($id);

    return redirect()->to('/ListeLivre');
}

}