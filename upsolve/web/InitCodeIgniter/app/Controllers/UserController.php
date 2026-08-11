<?php
namespace App\Controllers;
use App\Models\UserModel;

class UserController extends BaseController{

    public function __construct(){
        $this->usermod =new UserModel();
    }
    public function loginPage(){
            return view ('connexion');
    }

    public function getUser(){
        $liste = $this->usermod->getAllUser();
        return view ('pageuser',
         ['table' => $liste] 
        );
    }
    public function getIdUser($id){
        $liste = $this->usermod->getid($id);
        return view ('pageuser',
         ['table' => [$liste] ]
        );
    }
}


?>