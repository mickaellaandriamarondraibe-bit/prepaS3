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
    public function login()
{
    $username = $this->request->getPost('username');
    $email = $this->request->getPost('email');

    $user = $this->usermod->getUserByUsernameAndEmail($username, $email);

    if ($user) {
        session()->set([
            'id' => $user['id'],
            'username' => $user['username'],
            'email' => $user['email'],
            'connecte' => true
        ]);

        return redirect()->to('/ListeLivre');
    }

    return redirect()->to('/')->with('error', 'Nom ou email incorrect');
}
}


?>