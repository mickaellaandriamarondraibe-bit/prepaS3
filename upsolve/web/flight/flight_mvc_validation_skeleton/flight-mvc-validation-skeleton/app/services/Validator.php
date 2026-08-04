<?php

class Validator
{
    public static function register(array $data)
    {
        $errors = [];

        if (trim($data['nom'] ?? '') === '') {
            $errors['nom'] = 'Le nom est obligatoire.';
        }

        if (trim($data['prenom'] ?? '') === '') {
            $errors['prenom'] = 'Le prenom est obligatoire.';
        }

        if (!filter_var($data['email'] ?? '', FILTER_VALIDATE_EMAIL)) {
            $errors['email'] = 'Email invalide.';
        }

        if (strlen($data['password'] ?? '') < 4) {
            $errors['password'] = 'Minimum 4 caracteres.';
        }

        if (($data['password'] ?? '') !== ($data['confirm_password'] ?? '')) {
            $errors['confirm_password'] = 'Les mots de passe sont differents.';
        }

        if (trim($data['telephone'] ?? '') === '') {
            $errors['telephone'] = 'Le telephone est obligatoire.';
        }

        return $errors;
    }
}
