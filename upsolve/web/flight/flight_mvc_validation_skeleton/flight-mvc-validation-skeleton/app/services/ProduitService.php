<?php
class ProduitService
{
    private $repository;

    public function __construct(ProductRepository $repository)
    {
        $this->repository = $repository;
    }

    public function selectProduit()
    {
        $products = $this->repository->getProduits();

        return [
            'values' => $products
        ];
    }
}
