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
        return [
            'values' => $data,
            'errors' => Validator::register($data)
        ];
    }

    public function register(array $data)
    {
        $errors = Validator::register($data);

        if (!empty($errors)) {
            return [
                'values' => $data,
                'errors' => $errors,
                'success' => false
            ];
        }

        if ($this->repository->emailExists($data['email'])) {
            return [
                'values' => $data,
                'errors' => ['email' => 'Cet email existe deja.'],
                'success' => false
            ];
        }

        $this->repository->create($data);

        return [
            'values' => [],
            'errors' => [],
            'success' => true
        ];
    }
}
