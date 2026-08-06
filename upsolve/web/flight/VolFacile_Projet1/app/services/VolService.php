<?php
class VolService
{
    private $repository;

    public function __construct(VolRepository $repository)
    {
        $this->repository = $repository;
    }

    public function selectVol()
    {
        $vols = $this->repository->getVol();

        return [
            'values' => $vols
        ];
    }
    public function selectDetailVol($id)
    {
        $detailVol =$this->repository->getDetailVol($id);
        return [
            'values' => $detailVol
        ];
    }


}
