<?php
function e($v){ return htmlspecialchars($v ?? '', ENT_QUOTES, 'UTF-8'); }
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produits</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="mb-4">Liste des produits</h1>

        <?php if (empty($products)): ?>
            <div class="alert alert-info">Aucun produit trouve.</div>
        <?php else: ?>
            <table class="table table-bordered table-striped bg-white">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($products as $product): ?>
                        <tr>
                            <td><?= e($product['id']) ?></td>
                            <td><?= e($product['nom']) ?></td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        <?php endif; ?>
    </div>
</body>
</html>
