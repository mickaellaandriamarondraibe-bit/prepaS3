<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des vols</title>
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
                <a class="nav-link active" href="/listeVol">Vols</a>
                <a class="nav-link" href="/ReservationConfirmer">Réservations</a>
            </div>
        </div>
    </nav>

    <main class="container app-shell">
        <div class="page-header">
            <div>
                <div class="page-kicker">Catalogue</div>
                <h1 class="page-title">Vols disponibles</h1>
                <p class="page-subtitle">Comparez les trajets, les horaires et les prix dans un tableau pensé pour une lecture rapide.</p>
            </div>
            <a class="btn btn-primary" href="/Accueil">Accueil</a>
        </div>

        <div class="content-panel">
            <div class="table-responsive">
            <table class="table table-hover table-pro">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Trajet</th>
                        <th>Date départ</th>
                        <th>Heure départ</th>
                        <th>Statut</th>
                        <th>Prix</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($vols as $data){?>
                        <tr>
                            <td><span class="id-pill">#<?= $data['id'] ?></span></td>
                            <td>
                                <div class="route-cell">
                                <img
                                    src="https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=160&q=80"
                                    alt="Destination"
                                    class="route-thumb"
                                >
                                <div>
                                <div class="route-city"><?= $data['ville_depart']?> → <?= $data['ville_arrivee']?></div>
                                <div class="route-muted">Vol direct</div>
                                </div>
                                </div>
                            </td>
                            <td><?= $data['date_vol']?></td>
                            <td><?= $data['heure_vol']?></td>
                            <td><span class="status-pill">Disponible</span></td>
                            <td class="price"><?= $data['prix']?> €</td>
                            <td><a class="action-button" href="/DetailVol/<?= $data['id']?>">Voir détail</a></td>
                        </tr>
                       <?php } ?>
                </tbody>
            </table>
            </div>
        </div>
    </main>
</body>
</html>
