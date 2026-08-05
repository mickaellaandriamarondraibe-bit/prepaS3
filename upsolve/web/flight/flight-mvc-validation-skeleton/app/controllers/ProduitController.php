<?php

class ProduitController 
{
    public static function showProduct()
    {
        $service = new ProduitService(new ProductRepository(Flight::db()));
        $result = $service->selectProduit();

        Flight::render('auth/produit', [
            'products' => $result['values']
        ]);
    }

}
