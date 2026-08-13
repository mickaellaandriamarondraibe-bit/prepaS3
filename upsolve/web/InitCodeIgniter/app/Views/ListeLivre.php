<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<?= base_url('/assets/css/site.css') ?>">
</head>
<body>
<main class="app-shell d-flex justify-content-center">
    <section class="content-card glass-panel">
        <div class="page-header p-4 p-md-5 d-flex flex-column flex-md-row align-items-md-center justify-content-between gap-3">
            <div>
                <p class="muted-text text-uppercase fw-semibold mb-2">Bibliotheque</p>
                <h1 class="section-title mb-1">Liste des livres</h1>
                <p class="muted-text mb-0">Catalogue des ouvrages disponibles.</p>
            </div>
            <a href="<?= base_url('/insertion') ?>" class="btn btn-primary px-4">Ajouter un livre</a>
        </div>

        <div class="p-4 p-md-5 pt-md-4">
            <div class="table-responsive rounded border">
                <table class="table table-hover align-middle">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Auteur</th>
                            <th>Prix</th>
                            <th>Quantite</th>
                            <th class="text-end">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($livres as $livre) { ?>
                            <tr>
                                <td class="fw-semibold"><?= $livre['titre'] ?></td>
                                <td><?= $livre['auteur'] ?></td>
                                <td><?= $livre['prix'] ?></td>
                                <td><?= $livre['quantite'] ?></td>
                                <td class="text-end">
                                    <a href="<?= base_url('/modifierlivre/' . $livre['id']) ?>" class="btn btn-sm btn-outline-primary">Modifier</a>
                                    <a href="<?= base_url('/supprimerlivre/' . $livre['id']) ?>" class="btn btn-sm btn-outline-danger ms-2">Supprimer</a>
                                </td>
                            </tr>
                        <?php } ?>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
