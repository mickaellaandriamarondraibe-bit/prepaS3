<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Modifier livre</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<?= base_url('/assets/css/site.css') ?>">
</head>
<body>
<main class="app-shell d-flex align-items-center justify-content-center">
    <section class="form-card glass-panel p-4 p-md-5">
        <div class="mb-4">
            <p class="muted-text text-uppercase fw-semibold mb-2">Catalogue</p>
            <h1 class="section-title h2 mb-1">Modifier livre</h1>
            <p class="muted-text mb-0">Mettez a jour les informations de l'ouvrage.</p>
        </div>

        <form action="<?= base_url('/modifierlivre/' . $livres['id']) ?>" method="post">
            <div class="mb-3">
                <label class="form-label">Titre</label>
                <input type="text" name="titre" value="<?= $livres['titre'] ?>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Auteur</label>
                <input type="text" name="auteur" value="<?= $livres['auteur'] ?>" class="form-control" required>
            </div>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Prix</label>
                    <input type="text" name="prix" value="<?= $livres['prix'] ?>" class="form-control" required>
                </div>
                <div class="col-md-6 mb-4">
                    <label class="form-label">Quantite</label>
                    <input type="text" name="quantite" value="<?= $livres['quantite'] ?>" class="form-control" required>
                </div>
            </div>

            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary px-4">Modifier</button>
                <a href="<?= base_url('/ListeLivre') ?>" class="btn btn-outline-secondary px-4">Annuler</a>
            </div>
        </form>
    </section>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
