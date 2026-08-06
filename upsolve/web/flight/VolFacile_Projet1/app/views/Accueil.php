<?php 
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VolFacile</title>
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

    <main class="hero-section">
        <div class="container">
            <div class="hero-layout">
            <div class="hero-content">
            <span class="eyebrow">Plateforme de réservation</span>
            <h1 class="hero-title">L’expérience de réservation pensée pour les voyageurs internationaux</h1>
            <p class="hero-text mt-3">
                VolFacile centralise les vols, les tarifs, les détails de trajet et les confirmations
                dans une interface élégante, rapide et lisible.
            </p>
            <div class="hero-actions">
                <a href="/listeVol" class="btn btn-primary btn-lg">Voir les vols</a>
                <a href="/ReservationConfirmer" class="btn btn-outline-light btn-lg">Réservations</a>
            </div>
            </div>

            <div class="hero-card">
                <img
                    src="https://images.unsplash.com/photo-1436491865332-7a61a109cc05?auto=format&fit=crop&w=900&q=85"
                    alt="Avion au décollage"
                    class="hero-card-img"
                >
                <h2 class="hero-card-title">Voyage maîtrisé, présentation premium</h2>
                <p class="hero-card-text">
                    Consultez les disponibilités, ouvrez le détail d’un vol et confirmez votre réservation
                    sans perdre le contexte du trajet.
                </p>
            </div>
            </div>

            <div class="hero-stats">
                <div class="stat-box">
                    <span class="stat-value">3</span>
                    <span class="stat-label">destinations principales</span>
                </div>
                <div class="stat-box">
                    <span class="stat-value">100%</span>
                    <span class="stat-label">validation serveur PHP</span>
                </div>
                <div class="stat-box">
                    <span class="stat-value">Pro</span>
                    <span class="stat-label">interface responsive</span>
                </div>
            </div>
        </div>
    </main>

    <section class="feature-section">
        <div class="container">
            <div class="page-header">
                <div>
                    <div class="page-kicker">Expérience</div>
                    <h1 class="page-title">Une interface claire pour réserver vite</h1>
                    <p class="page-subtitle">Chaque écran guide l’utilisateur vers l’action importante : consulter, comparer, réserver, confirmer.</p>
                </div>
            </div>

            <div class="feature-grid">
                <div class="feature-card">
                    <span class="feature-icon">01</span>
                    <h2>Vols lisibles</h2>
                    <p>Trajet, date, heure et prix sont organisés pour être compris en quelques secondes.</p>
                </div>
                <div class="feature-card">
                    <span class="feature-icon">02</span>
                    <h2>Réservation guidée</h2>
                    <p>Le formulaire reste simple et sérieux, avec une validation uniquement côté serveur.</p>
                </div>
                <div class="feature-card">
                    <span class="feature-icon">03</span>
                    <h2>Confirmation propre</h2>
                    <p>Les réservations sont affichées dans un tableau professionnel prêt pour une démonstration.</p>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
