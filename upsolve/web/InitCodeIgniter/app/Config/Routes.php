<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'UserController::loginPage');
$routes->get('/user', 'UserController::getUser');
$routes->get('/user/(:num)', 'UserController::getIdUser/$1');
$routes->post('/insertionlivre', 'LivreController::getInsertLivre');
$routes->get('/insertion', 'LivreController::afficherforme');
$routes->get('/ListeLivre', 'LivreController::getLivre');
$routes->get('/modifierlivre/(:num)', 'LivreController::formulaireModifierLivre/$1');
$routes->post('/modifierlivre/(:num)', 'LivreController::ModifierLivre/$1');
$routes->get('/supprimerlivre/(:num)', 'LivreController::SupprimerLivre/$1');
$routes->post('/login', 'UserController::login');