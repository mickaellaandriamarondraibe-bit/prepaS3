<?
select :
 
function selectMembre($mail, $pwd) {
        $conn = connection();
        if (!$conn) return null;

        $sql = "SELECT id,email,Nom FROM membre WHERE email = ? AND pwd = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ss", $mail, $pwd);
        $stmt->execute();
        $res = $stmt->get_result();

        if ($row = $res->fetch_assoc()) {
            return [$row['Nom'], $row['id']];
        }
        return null;
    }