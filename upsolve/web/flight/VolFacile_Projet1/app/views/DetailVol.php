<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détail du vol</title>
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
                <a class="nav-link" href="/ReservationConfirmer">Réservations</a>
            </div>
        </div>
    </nav>

    <main class="container app-shell">
        <div class="page-header">
            <div>
                <div class="page-kicker">Détail du vol</div>
                <h1 class="page-title"><?= $detailVol['ville_depart']?> → <?= $detailVol['ville_arrivee']?></h1>
                <p class="page-subtitle">Toutes les informations essentielles du vol numéro <?= $detailVol['id'] ?> avant confirmation.</p>
            </div>
        </div>

        <div class="flight-summary">
        <div class="content-panel">
            <div class="panel-body-pro">
                <div class="detail-grid">
                    <div class="detail-item">
                        <span class="detail-label">Numéro</span>
                        <span class="detail-value">#<?= $detailVol['id'] ?></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Prix</span>
                        <span class="detail-value price"><?= $detailVol['prix']?> €</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Départ</span>
                        <span class="detail-value"><?= $detailVol['ville_depart']?></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Arrivée</span>
                        <span class="detail-value"><?= $detailVol['ville_arrivee']?></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Date</span>
                        <span class="detail-value"><?= $detailVol['date_vol']?></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Heure</span>
                        <span class="detail-value"><?= $detailVol['heure_vol']?></span>
                    </div>
                </div>

            <div class="actions mt-4">
                <a class="btn btn-primary" href="/FormulaireReservation/<?= $detailVol['id'] ?>">Réserver ce vol</a>
                <a class="btn btn-outline-secondary" href="/listeVol">Retour</a>
            </div>
            </div>
        </div>

        <aside class="booking-aside">
            <div class="detail-visual mb-4">
                <img
                    src="https://images.unsplash.com/photo-1436491865332-7a61a109cc05?auto=format&fit=crop&w=900&q=85"
                    alt="Vue depuis un avion"
                >
            </div>
            <h2>Prêt à confirmer ?</h2>
            <p>Réservez ce trajet maintenant et retrouvez ensuite votre confirmation dans le tableau des réservations.</p>
            <a class="btn btn-primary w-100 mt-2" href="/FormulaireReservation/<?= $detailVol['id'] ?>">Continuer</a>
        </aside>
        </div>
    </main>
</body>
</html>
