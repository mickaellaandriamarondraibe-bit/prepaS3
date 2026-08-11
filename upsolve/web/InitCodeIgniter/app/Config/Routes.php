<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'UserController::loginPage');
$routes->get('/user', 'UserController::getUser');
$routes->get('/user/(:num)', 'UserController::getIdUser/$1');
