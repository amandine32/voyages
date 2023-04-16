# Projet AOO Voyage



Pour notre projet nous avons choisis le thème du voyage. Nous avons dû mettre en place différentes fonctionnalités sur les voyages, les activités et les clients. Dans notre application, il y'a tout d'abord un formulaire de connexion/ Inscription. Après s'être inscrit, on se connecte à l'application et on peut ajouter, modifier, supprimer un voyage, un client et une activité.

Nous avons choisi d'utiliser une architecture Modele Vue Contrôleur. Dans le modele, nous avons créé un fichier par entité de la base de données dans lesquels on trouve les get et set. Nous avons également créé un dossier DAO qui permet d'accéder aux données. On y trouve donc des fichiers avec le CRUD pour les entités activités clients et voyages. Dans le dossier vue, nous avons créé une vue pour chaque fonctionnalité. Il y'a par exemple panelVoyage, VueConnexion , PanelEnregistrerClients, PanelActivites... On y trouve dans ces fichiers des méthodes permettant de modifier la vue comme par exemple vider les champs du formulaire une fois qu'il a été rempli. 
Nous avons utilisé les principes SOLID. Nous avons séparé les différentes fonctionnalités dans des classes différentes et nous avons séparé les vues des entités. Nous avons essayé de ne pas créer des classes trop lourdes et de n'y mettre que les méthodes nécessaires au fonctionnement de la classe. Nous avons essayé de rendre le code simple et compréhensible.


