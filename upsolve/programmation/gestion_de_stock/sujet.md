projet : gestion de stock : surveiller les entrées et sorties d'article

application : JAVA
Interface graphique java
base de donnee : postgre

je veux un projet qui permet de gerer les stocks d'article 
-Enregister l'entrée/sortie 
: l'utilisateur enregistre les articles en choisissant le type de mouvement : C'est a dire entré ou sortie : et quel type de mouvement il veut proceder : FIFO , LIFO , CUMP 
-Gerer les stocks d'entrée et sortie 
: Connaitre l'entré et sortie des articles (Combien d'articles sont entrés, et combien sont sorties)

mouvement d'entrée/sortie : 
-premier mouvement : FIFO : L'article qui est entrée en stock en premier sort en stock en premier
-deuxieme mouvement: LIFO : l'article qui est entrée en stock en dernier sort en stock en premier (c'est a dire vendu en premier)
-troisieme mouvement: CUMP 


affichage : -liste d'artcile 
            -quand on clique sur un article, cela affiche les mouvements en entrée et sortie : quels articles sont entrés dans le stock et quels articles sont sorties 
            -quantité disponible en stock (entré - sortie): c'est a dire la valeur de stock : combien en total les restes d'article en stock (condition : si il n'y a pas d'entré d'article donc il n'y a pas de valeur en stock car le stock est vide )


Questions et reponses du projet
================================

1. Quelle interface graphique utiliser ?

Reponse :
L'application utilise Java Swing.

2. Quelle structure de projet utiliser ?

Reponse :
Le projet utilise des fichiers Java simples avec extension `.java`, sans Maven et sans Gradle.

3. Quelle base de donnees utiliser ?

Reponse :
La base de donnees utilisee est PostgreSQL.

Configuration :
- base : gestion_stock
- utilisateur : postgres
- mot de passe : postgres
- port : 5432

4. Quelles informations enregistrer pour un article ?

Reponse :
Un article contient seulement :
- id
- nom
- date de creation

Il n'y a pas de prix unitaire dans la fiche article.

5. Ou enregistrer le prix unitaire ?

Reponse :
Le prix unitaire est enregistre dans le mouvement de stock.

Pour une entree, il correspond au prix d'achat unitaire.
Pour une sortie, il correspond au prix de vente unitaire.

6. Que doit contenir une entree de stock ?

Reponse :
Une entree contient :
- article
- quantite
- quantite restante
- prix unitaire
- date du mouvement

Pour une entree, la quantite restante est egale a la quantite entree au debut.

7. Que doit contenir une sortie de stock ?

Reponse :
Une sortie contient :
- article
- quantite sortie
- prix unitaire
- date du mouvement
- methode de sortie : FIFO, LIFO ou CUMP

Pour une sortie, la quantite restante vaut 0.

8. Quand choisir la methode FIFO, LIFO ou CUMP ?

Reponse :
La methode est choisie pour chaque mouvement de sortie.

Une entree n'a pas besoin de methode, donc `id_methode` peut etre NULL.

9. Comment gerer une sortie si la quantite demandee est superieure au stock disponible ?

Reponse :
L'utilisateur peut faire une sortie partielle.

Exemple :
Si l'utilisateur demande 4 articles, mais qu'il n'y en a que 2 en stock, l'application sort seulement 2 articles.

10. Que faire si le stock est vide ?

Reponse :
Si aucun stock n'est disponible, aucune sortie n'est enregistree.

Message affiche :
Stock vide pour cet article. Aucune sortie n'a ete enregistree.

11. Comment organiser les mouvements dans la base ?

Reponse :
Les anciennes tables `entree_stock` et `sortie_stock` ont ete remplacees par une seule table :

`mouvement`

Colonnes principales :
- id
- id_type_mouvement
- id_methode
- article_id
- quantite
- quantite_restante
- prix_unitaire
- date_mouvement

12. Pourquoi avoir une table `type_mouvement` ?

Reponse :
Pour identifier si un mouvement est une entree ou une sortie.

Valeurs :
- ENTREE
- SORTIE

13. Pourquoi avoir une table `methode_mouvement` ?

Reponse :
Pour identifier la methode utilisee lors d'une sortie.

Valeurs :
- FIFO
- LIFO
- CUMP

14. Comment afficher les mouvements d'un article ?

Reponse :
Dans l'onglet Articles, quand l'utilisateur clique sur une ligne d'article, l'application affiche seulement les mouvements de cet article.

Les details affiches sont :
- id du mouvement
- type de mouvement
- methode
- quantite
- quantite restante
- prix
- date

15. Quels onglets contient l'application ?

Reponse :
L'application contient :
- Articles
- Mouvements

16. Quel est le role de l'onglet Articles ?

Reponse :
L'onglet Articles permet :
- d'ajouter un article
- de modifier un article
- de supprimer un article
- de lister les articles
- d'afficher les mouvements d'un article selectionne

17. Quel est le role de l'onglet Mouvements ?

Reponse :
L'onglet Mouvements permet :
- d'enregistrer une entree
- d'enregistrer une sortie
- de choisir FIFO, LIFO ou CUMP pour une sortie
- de lister les mouvements de stock

18. Comment calculer la quantite disponible ?

Reponse :
La quantite disponible correspond a la somme des `quantite_restante` des mouvements d'entree d'un article.

19. Comment calculer la valeur du stock ?

Reponse :
La valeur du stock correspond a :

quantite_restante * prix_unitaire

pour chaque entree restante.

La valeur totale est la somme de ces valeurs.

20. Comment fonctionne FIFO ?

Reponse :
FIFO signifie que le premier article entre en stock est le premier a sortir.

L'application utilise les entrees disponibles les plus anciennes en premier.

21. Comment fonctionne LIFO ?

Reponse :
LIFO signifie que le dernier article entre en stock est le premier a sortir.

L'application utilise les entrees disponibles les plus recentes en premier.

22. Comment fonctionne CUMP ?

Reponse :
CUMP signifie cout unitaire moyen pondere.

Formule :

CUMP = valeur totale du stock / quantite totale disponible

Dans ce projet, CUMP est une methode de sortie selectionnable par l'utilisateur.



