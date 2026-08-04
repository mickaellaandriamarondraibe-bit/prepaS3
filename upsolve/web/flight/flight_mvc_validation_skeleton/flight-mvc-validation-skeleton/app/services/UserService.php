<?php

class UserService
{
    private $repository;

    public function __construct(UserRepository $repository)
    {
        $this->repository = $repository;
    }

    public function validateOnly(array $data)
    {
        $values = $this->cleanValues($data);

        return [
            'values' => $values,
            'errors' => Validator::register($data)
        ];
    }

    public function register(array $data)
    {
        $values = $this->cleanValues($data);
        $errors = Validator::register($data);

        if (!empty($errors)) {
            return [
                'values' => $values,
                'errors' => $errors,
                'success' => false
            ];
        }

        if ($this->repository->emailExists($values['email'])) {
            return [
                'values' => $values,
                'errors' => ['email' => 'Cet email existe deja.'],
                'success' => false
            ];
        }

        $this->repository->create($values, $data['password']);

        return [
            'values' => [],
            'errors' => [],
            'success' => true
        ];
    }

    private function cleanValues(array $data)
    {
        return [
            'nom' => trim($data['nom'] ?? ''),
            'prenom' => trim($data['prenom'] ?? ''),
            'email' => trim($data['email'] ?? ''),
            'telephone' => trim($data['telephone'] ?? '')
        ];
    }
}
