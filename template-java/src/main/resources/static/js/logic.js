function fetchUsers(){
    //récupère la liste des utilisateurs connectés toutes les 10 secondes : appel réseau à /home
    //injection de la réponse dans la page
}

function play(x, y){
   //Appel réseau AJAX sur la méthode /play en POST avec les infos x,y et id de la partie
   //injection de la réponse dans la page
   //Si le serveur renvoie true, on affiche que c'est à l'adversaire de jouer, sinon message d'erreur et demander de rejouer
}

function fetchGrid(){
    //appel réseau à /grid (méthode POST avec l'id de la partie) pour récupérer la grille, à appeller toutes les 10 secondes
    //injection de la réponse dans la page
}