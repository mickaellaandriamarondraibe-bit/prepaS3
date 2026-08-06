<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de réservation</title>
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
        <div class="form-panel">
        <div class="page-header">
            <div>
                <div class="page-kicker">Réservation</div>
                <h1 class="page-title">Informations du passager</h1>
                <p class="page-subtitle">Complétez les informations nécessaires pour confirmer la réservation.</p>
            </div>
        </div>

        <div class="form-card">
        <div class="form-media">
            <span class="eyebrow">Vol #<?= htmlspecialchars($id_vol) ?></span>
            <h2 class="mt-4">Réservation sécurisée</h2>
            <p>Les champs sont vérifiés côté serveur avec PHP, conformément aux règles du projet.</p>
        </div>

        <div class="form-area">

        <form method="POST" action="/FormulaireReservation">
            <input type="hidden" name="id_vol" value="<?= htmlspecialchars($id_vol) ?>">

            <div class="mb-3">
                <label for="nom_passager" class="form-label">Nom du passager</label>
                <input
                    type="text"
                    id="nom_passager"
                    name="nom_passager"
                    class="form-control"
                    value="<?= htmlspecialchars($data['nom_passager']) ?>"
                >
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Adresse e-mail</label>
                <input
                    type="text"
                    id="email"
                    name="email"
                    class="form-control"
                    value="<?= htmlspecialchars($data['email']) ?>"
                >
            </div>

            <div class="mb-3">
                <label for="nombre_place" class="form-label">Nombre de places</label>
                <input
                    type="text"
                    id="nombre_place"
                    name="nombre_place"
                    class="form-control"
                    value="<?= htmlspecialchars($data['nombre_place']) ?>"
                >
            </div>

            <div class="reservation-total mb-4">
                <strong>Vol sélectionné :</strong> #<?= htmlspecialchars($id_vol) ?><br>
                <span class="text-muted">Le nombre de places doit être supérieur ou égal à 1.</span>
            </div>

            <div class="actions">
                <button type="submit" class="btn btn-primary">Réserver</button>
                <a href="/listeVol" class="btn btn-outline-secondary">Annuler</a>
            </div>
        </form>

        <?php if (!empty($errors)) { ?>
            <div class="alert alert-danger mt-3">
                <ul class="mb-0">
                    <?php foreach ($errors as $error) { ?>
                        <li><?= htmlspecialchars($error) ?></li>
                    <?php } ?>
                </ul>
            </div>
        <?php } ?>
        </div>
        </div>
        </div>
    </main>
</body>
</html>
