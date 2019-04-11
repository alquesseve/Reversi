# Dossier explicatif : Application Reversi

L’application utilise Spring Boot MVC. Le moteur de template utilisé est thymeleaf.


Ce projet n’est pas dans une version utilisable et reste inachevé.


## Principe de l’application :

Le principe est relativement simple: un utilisateur se connecte sur la page d'accueil, il est ensuite redirigé vers une page listant les membres connectés. Dès qu'il se retrouve connecté, il apparaît alors comme prêt à jouer sur l'interface des autres joueurs. La liste qui regroupe tous ces joueurs est rafraîchie périodiquement via un appel réseau AJAX.


Lors du clic sur un membre de la liste, une partie se crée entre les deux joueurs. Lors de la création, la grille de jeu est créée en base de données. Le premier joueur choisi alors sa première une case, le système vérifie alors si le coup est permis par les règles du Reversi. Si c’est le cas, le système réalise le coup et retourne les pièces concernées. Sinon, le joueur reçoit un message d’erreur lui demandant de choisir une autre case. Les modifications liées à la validation d’un coup sont enregistrées en base de données. La grille est rechargée de manière cyclique via AJAX.


Une fois que le joueur a joué son tour, il doit attendre que le deuxième joueur se synchronise (récupère la grille mise à jour) et joue.


## Technique :

Nous avons utilisé le système de base de données H2. 


Un utilisateur user1 se connecte à l'application. Au moment de l'établissement de la session, son statut est mis à jour en base de données pour signifier qu'il est en attente de partie. Il doit alors attendre qu'un autre utilisateur se connecte. Il atterit sur la page listant les membres connectés. Cette page fait un appel réseau via AJAX toutes les 10 secondes pour récupérer la liste des utilisateurs mise à jour. Lors du clic sur un utilisateur de cette liste (user2), une partie est créée. La partie est stockée en base de données dès sa création. L'utilisateur user1 sera  le joueur qui va commencer à jouer (choix arbitraire, on aurait très bien pû utiliser un système aléatoire). Le joueur user1 va donc cliquer sur une case et déclencher un événement javascript. Cet événement va réaliser un appel réseau pour vérifier que le coup est possible côté serveur. Si c'est le cas, le coup est alors pris en compte. Du côté du joueur user2, la grille est rechargée par appel réseau via AJAX toutes les 10 secondes. Une fois que le coup du joueur user1 est validé, c'est à son tour de jouer. 


Comme annoncé en préambule, ce projet est dans un état inachevé. La logique de jeu est terminée, cependant la jonction entre le front end et cette logique est en chantier. En effet, les fonctions et événements javascript ne sont pas développés (ils sont en état d'ébauche par manque de temps, cf points de blocage). De même la logique côté serveur n'est pas terminée.


## Points de blocage :

Le premier point de blocage est l’utilisation de la technologie. Bien que nous ayons eu des TPs, des soucis de configuration matérielle nous ont ralenti et nous n’avons que trop peu eu l’occasion de pratiquer le framework Spring boot. Cette difficulté de prise en main de la technologie est renforcée par la prise en main de thymeleaf qui, même si la finalité est de simplifier la vie aux développeurs, est resté pour nous assez complexe à manipuler dans certains de nos traitements de construction de pages.


Le travail de compréhension de la structure et du fonctionnement du système d’authentification de Spring security nous a également beaucoup occupé (nous avions beaucoup de mal à comprendre l’enchaînement logique des différents contrôleurs). Bien que nous ayons pu commencer ce projet durant les cours, il nous a manqué du temps. En effet, bien que la date de rendu soit éloignée, nous avons manqué de disponibilités pour travailler sur ce projet. Nous avions en effet de nombreuses obligations, que ce soit le rendu de notre projet d’études, les partiels, la recherche de logement, la recherche de stage ou encore le stage en lui-même dernièrement. Ces contraintes de temps expliquent le caractère inachevé de ce projet.
