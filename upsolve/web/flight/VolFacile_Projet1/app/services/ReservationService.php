<?php
class ReservationService
{
    private $reservationRepository;
    private $passagerRepository;

    public function __construct(ReservationRepository $reservationRepository, PassagerRepository $passagerRepository)
    {
        $this->reservationRepository = $reservationRepository;
        $this->passagerRepository = $passagerRepository;

    }
    public function reserverVol($id_vol, $nom_passager, $email, $nombre_place)
    {
         $id_passager = $this->passagerRepository->insertPassager($nom_passager, $email);
         $this->reservationRepository->insertReservation($id_vol, $id_passager, $nombre_place);

    }
    public function listeReservations()
{
    return $this->reservationRepository->joinPassagerReservation();
}
}
?>
