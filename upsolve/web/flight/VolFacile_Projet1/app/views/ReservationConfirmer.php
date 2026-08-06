<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réservations confirmées</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg app-nav">
        <div class="container">
            <a class="navbar-brand" href="/Accueil">
                <span class="brand-mark"><span class="brand-wing"></span></span>
                <span class="brand-text">VolFacile</span>
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="/listeVol">Vols</a>
                <a class="nav-link active" href="/ReservationConfirmer">Réservations</a>
            </div>
        </div>
    </nav>

    <main class="container app-shell">
        <div class="page-header">
            <div>
                <div class="page-kicker">Historique</div>
                <h1 class="page-title">Réservations confirmées</h1>
                <p class="page-subtitle">Liste des passagers enregistrés avec leur vol, nombre de places et prix associé.</p>
            </div>
            <a class="btn btn-primary" href="/listeVol">Nouvelle réservation</a>
        </div>

        <div class="content-panel">

        <div class="table-responsive">
        <table class="table table-hover table-pro">
            <thead>
                <tr>
                    <th>Nom passager</th>
                    <th>Email</th>
                    <th>Vol</th>
                    <th>Nombre de places</th>
                    <th>Prix</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($reservations as $reservation) { ?>
                    <tr>
                        <td><strong><?= htmlspecialchars($reservation['nom_passager']) ?></strong></td>
                        <td><?= htmlspecialchars($reservation['mail']) ?></td>
                        <td><span class="id-pill">#<?= htmlspecialchars($reservation['vol']) ?></span></td>
                        <td><span class="status-pill"><?= htmlspecialchars($reservation['nombre_de_place']) ?> place(s)</span></td>
                        <td class="price"><?= htmlspecialchars($reservation['prix']) ?> €</td>
                    </tr>
                <?php } ?>
            </tbody>
        </table>
        </div>

        </div>
    </main>
</body>
</html>
