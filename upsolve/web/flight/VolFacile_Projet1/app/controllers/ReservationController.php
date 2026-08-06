<?php

class ReservationController
{
    public static function redirectFormulaireReservation()
    {
        Flight::redirect('/listeVol');
    }

    public static function showFormulaireReservation($id)
    {
        Flight::render('FormulaireReservation', [
            'id_vol' => $id,
            'errors' => [],
            'data' => [
                'nom_passager' => '',
                'email' => '',
                'nombre_place' => ''
            ]
        ]);
    }

    public static function saveReservation()
    {
        $request = Flight::request()->data;

        $id_vol = trim($request->id_vol ?? '');
        $nom_passager = trim($request->nom_passager ?? '');
        $email = trim($request->email ?? '');
        $nombre_place = trim($request->nombre_place ?? '');

        $errors = [];
        $passagerRepository = new PassagerRepository(Flight::db());

        if ($nom_passager === '' ) {
            $errors[] = 'Le nom du passager est obligatoire.';
        }

        if ($email === '') {
            $errors[] = 'L’adresse e-mail est obligatoire.';
        } 
        elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errors[] = 'L’adresse e-mail doit avoir un format valide.';
        }
        elseif ($passagerRepository->verifierPassager($email)) {
            $errors[] = 'L’adresse e-mail existe deja';
        }

        if ($nombre_place === '') {
            $errors[] = 'Le nombre de places est obligatoire.';
        } elseif (!ctype_digit($nombre_place) || (int) $nombre_place < 1) {
            $errors[] = 'Le nombre de places doit être supérieur ou égal à 1.';
        }

        if (!empty($errors)) {
            Flight::render('FormulaireReservation', [
                'id_vol' => $id_vol,
                'errors' => $errors,
                'data' => [
                    'nom_passager' => $nom_passager,
                    'email' => $email,
                    'nombre_place' => $nombre_place
                ]
            ]);
            return;
        }

        $service = new ReservationService(
            new ReservationRepository(Flight::db()),
            $passagerRepository
        );

        $service->reserverVol($id_vol, $nom_passager, $email, (int) $nombre_place);

        Flight::redirect('/ReservationConfirmer');
    }

    public static function showReservationConfirmer()
    {
        $service = new ReservationService(
            new ReservationRepository(Flight::db()),
            new PassagerRepository(Flight::db())
        );

        $reservations = $service->listeReservations();

        Flight::render('ReservationConfirmer', [
            'reservations' => $reservations
        ]);
    }
}


?>
