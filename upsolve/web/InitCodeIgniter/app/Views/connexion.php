<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<?= base_url('/assets/css/site.css') ?>">
</head>
<body>
    <main class="app-shell d-flex align-items-center justify-content-center">
        <section class="login-card glass-panel p-4 p-md-5">
            <div class="d-flex align-items-center gap-3 mb-4">
                <div class="brand-mark">LB</div>
                <div>
                    <h1 class="section-title h3 mb-1">Connexion</h1>
                    <p class="muted-text mb-0">Accedez a votre espace de gestion.</p>
                </div>
            </div>

            <?php if (session()->getFlashdata('error')) { ?>
                <div class="alert alert-danger" role="alert">
                    <?= session()->getFlashdata('error') ?>
                </div>
            <?php } ?>

            <form action="<?= base_url('/login') ?>" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Nom</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        class="form-control"
                        placeholder="Votre nom"
                        required
                    >
                </div>

                <div class="mb-4">
                    <label for="email" class="form-label">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        class="form-control"
                        placeholder="nom@exemple.com"
                        required
                    >
                </div>

                <button type="submit" class="btn btn-primary w-100 py-2">Se connecter</button>
            </form>
        </section>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
