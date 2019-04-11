# Dossier explicatif : Application Reversi

L’application utilise Spring Boot MVC. Le moteur de template utilise est thymeleaf.


Ce projet n’est pas dans une version utilisable et reste inachevé.


## Principe de l’application :

Le principe est relativement simple: un utilisateur se connecte sur la page d'accueil, il est ensuite redirigé vers une page listant les membres connectés. Dès qu'il se retrouve connecté, il apparaît alors comme prêt à jouer sur l'interface des autres joueurs. La liste qui regroupe tous ces joueurs est rafraîchie périodiquement via un appel réseau AJAX.


Lors du clic sur un membre de la liste, une partie se crée entre les deux joueurs. Lors de la création, la grille de jeu est créée en base de données. Le premier joueur choisi alors sa première une case, le système vérifie alors si le coup est permis par les règles du Reversi. Si c’est le cas, le système réalise le coup et retourne les pièces concernées. Sinon, le joueur reçoit un message d’erreur lui demandant de choisir une autre case. Les modifications liées à la validation d’un coup sont enregistrées en base de données. La grille est rechargée de manière cyclique via AJAX.


Une fois que le joueur a joué son tour, il doit attendre que le deuxième joueur se synchronise (récupère la grille mise à jour) et joue.


## Points de blocage :

Le premier point de blocage est l’utilisation de la technologie. Bien que nous ayons eu des TPs, des soucis de configuration matérielle nous ont ralenti et nous n’avons que trop peu eu l’occasion de pratiquer le framework Spring boot. Cette difficulté de prise en main de la technologie est renforcée par la prise en main de thymeleaf qui, même si la finalité est de simplifier la vie aux développeurs, est resté pour nous assez complexe à manipuler dans certains de nos traitements de construction de pages.


Le travail de compréhension de la structure et du fonctionnement du système d’authentification de Spring security nous a également beaucoup occupé (nous avions beaucoup de mal à comprendre l’enchaînement logique des différents contrôleurs). Bien que nous ayons pu commencer ce projet durant les cours, il nous a manqué du temps. En effet, bien que la date de rendu soit éloignée, nous avons manqué de disponibilités pour travailler sur ce projet. Nous avions en effet de nombreuses obligations, que ce soit le rendu de notre projet d’études, les partiels, la recherche de logement, la recherche de stage ou encore le stage en lui-même dernièrement. Ces contraintes de temps expliquent le caractère inachevé de ce projet.
