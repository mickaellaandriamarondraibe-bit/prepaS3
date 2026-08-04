<?php
require_once __DIR__ . '/services/ProduitService.php';
require_once __DIR__ . '/repositories/ProductRepository.php';
require_once __DIR__ . '/controllers/ProduitController.php';
Flight::route('GET /produit', ['ProduitController', 'showProduct']);
