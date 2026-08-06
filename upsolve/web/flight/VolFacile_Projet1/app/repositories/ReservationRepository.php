<?php class ReservationRepository 
{
    private $db;

    public function __construct(PDO $db)
    {
        $this->db = $db;
    }
    public function insertReservation($id_vol, $id_passager, $nombre_place)
    {
        $sqlReservation = 'INSERT INTO reservation (id_vol, id_passager, nombe_place)
                           VALUES (:id_vol, :id_passager, :nombe_place)';
        $stmt = $this->db->prepare($sqlReservation);
        $stmt->execute([
            'id_vol' => $id_vol,
            'id_passager' => $id_passager,
            'nombe_place' => $nombre_place
        ]);
}

    public function joinPassagerReservation()
    {
        $sql = 'SELECT
                    passager.nom_passager,
                    passager.mail,
                    reservation.id_vol AS vol,
                    reservation.nombe_place AS nombre_de_place,
                    (vol.prix*reservation.nombe_place) AS prix
                FROM passager
                JOIN reservation ON passager.id = reservation.id_passager
                JOIN vol ON reservation.id_vol = vol.id
                ORDER BY reservation.id_vol ';

        $stmt = $this->db->prepare($sql);
        $stmt->execute();

        return $stmt->fetchAll();
    }


}
?>
