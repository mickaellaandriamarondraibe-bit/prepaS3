<?php 
class VolController 

{
    public static function Accueil()
    {
        Flight::render('Accueil');
    }
    public static function showVol()
    {
        $service = new VolService(new VolRepository(Flight::db()));
        $result = $service->selectVol();

        Flight::render('listeVol', [
            'vols' => $result['values']
        ]);
    }
     public static function showDetailVol($id)
    {
        $service = new VolService(new VolRepository(Flight::db()));
        $result = $service->selectDetailVol($id);

        Flight::render('DetailVol', [
            'detailVol' => $result['values']
        ]);
    }


   
}


?>
