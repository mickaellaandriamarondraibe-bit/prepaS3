<?php
require_once __DIR__ . '/controllers/VolController.php';
require_once __DIR__ . '/controllers/ReservationController.php';
require_once __DIR__ . '/services/VolService.php';
require_once __DIR__ . '/services/ReservationService.php';
require_once __DIR__ . '/repositories/VolRepository.php';
require_once __DIR__ . '/repositories/PassagerRepository.php';
require_once __DIR__ . '/repositories/ReservationRepository.php';


Flight::route('GET /Acceuil', ['VolController', 'Accueil']);
Flight::route('GET /Accueil', ['VolController', 'Accueil']);
Flight::route('GET /listeVol', ['VolController', 'showVol']);
Flight::route('GET /DetailVol/@id', ['VolController', 'showDetailVol']);
Flight::route('GET /FormulaireReservation', ['ReservationController', 'redirectFormulaireReservation']);
Flight::route('GET /FormulaireReservation/@id', ['ReservationController', 'showFormulaireReservation']);
Flight::route('POST /FormulaireReservation', ['ReservationController', 'saveReservation']);
Flight::route('GET /ReservationConfirmer', ['ReservationController', 'showReservationConfirmer']);
