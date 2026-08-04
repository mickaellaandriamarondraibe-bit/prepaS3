<?php

class AuthController
{
    public static function showRegister()
    {
        Flight::render('auth/register', [
            'values' => [],
            'errors' => [],
            'success' => false
        ]);
    }

    public static function postRegister()
    {
        $service = new UserService(new UserRepository(Flight::db()));
        $result = $service->register($_POST);

        Flight::render('auth/register', [
            'values' => $result['values'],
            'errors' => $result['errors'],
            'success' => $result['success']
        ]);
    }

    public static function validateRegisterAjax()
    {
        $service = new UserService(new UserRepository(Flight::db()));
        $result = $service->validateOnly($_POST);

        Flight::json([
            'ok' => empty($result['errors']),
            'errors' => $result['errors'],
            'values' => $result['values']
        ]);
    }
}
