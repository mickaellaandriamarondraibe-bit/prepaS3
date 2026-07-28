<?php
require_once __DIR__ . '/controllers/AuthController.php';
require_once __DIR__ . '/services/Validator.php';
require_once __DIR__ . '/services/UserService.php';
require_once __DIR__ . '/repositories/UserRepository.php';

Flight::route('GET /register', array('AuthController', 'showRegister'));
Flight::route('POST /register', array('AuthController', 'postRegister'));
Flight::route('POST /api/validate/register', array('AuthController', 'validateRegisterAjax'));
