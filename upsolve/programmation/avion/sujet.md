ce sujet est une simulation d'un avion qui vole avec une vitesse pour une atterissage

parametre pour faire fonctionner l'avion :
- vitesse x : 900 km/h
- vitesse y : 0 
- altitude : 10 000 m
- distance par rapport au piste : -7000 km (plus la distance approche de 0 , plus l'avion s'approche  de la piste )
- vitesse de décrochage pour que l'avion vole : 200 km/h (condition si la vitesse décrochage n'est pas atteind l'avion crache)
- Décceleration ou freinage : x/y par m/s^-2 :
    - x : bouton + et - : quand on clique sur moins ou plus cela marche par taille de freinage qui est -10m/s^-2
    - y : bouton + et - : quand on clique sur moins ou plus cela marche par taille de freinage qui est -2m/s^-2

- gamma x (c'est la capacité de freinage maximum  en suivant le repère x) : -40 (quand le capacité de freinage de x atteind -40 l'avion ne peut plus descendre )
- gamma y (c'est la capacité de freinage maximum en suivant le repere y  ) : -6 
- distance du piste et l'avion c'est 1km


application : JAVA
Interface graphique java
base de donnee : postgre

affichage :
- coté droite : 
 - tableau de bord :
    - vitesse x
    - vitesse y
    - altitude 
    - distance piste : -7000 m (qui change lorque l'avion vole , quand on clique sur le bouton jouer)
    - timer( qui commence lorque l'avion decole)
    - bouton jouer
    - bouton pause : pour qui met en pause tous les simulations pour voir si la distance dans le tableau de bord marche vraiment / l'altitude 
- coté gauche : l'avion 
- en bas : la piste d'arrivée
- l'avion contient 3 vues :
    - vue de profil droite qui voit les paysages fond paysage 
    - vue de profil gauche qui voit le soleil 
    - vue de derrière qui vois les montagnes
 condition : si la vitesse de decrochage n'est pas atteint , l'avion crache .Tant que le pneu de l'avion ne touche pas la piste , la vitesse de décrochage doit etre toujours respecter sinon l'avion crache


 