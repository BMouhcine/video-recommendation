# video-recommendation-spideo -- GUIDE:

Ce projet est déployé sur le Cloud, et en particulier Heroku pour plus de simplicité.  
Ci-après le lien de récupération des Films (par exemple) de l'API deployée: https://video-recommendation-spideo.herokuapp.com/api/video/getFilms  
:warning: Il est recommandé d'exécuter le projet en local: Quand l'application déployée n'est pas souvent utilisée (timeout), Heroku met l'instance en mode "hibernate". Donc après ce timeout, et quand l'instance/l'app est en mode "hibernate", en accédant au lien ci-dessus, le résultat de retour va être probablement lent (pendant que Heroku essaye de redémarrer l'application).
------
Pour exécuter ce projet en local, il faut:
- Installer le projet via la commande: `./mvnw install`.
- Lancer la commande: `java -jar target/video-recom-0.0.1-SNAPSHOT`. (Java 8).
- Ouvrir ***Postman*** (ou un outil équivalent):
> Accéder à l'api via: HTTP - POST: `http://localhost:8080/api/video/addVideo`.  avec un body de contenu:
```
{
    "id": "videoId-200200200",
    "titre": "dummy-titre-test",
    "labels": [
        "label0",
        "label1",
        "label2",
        "label6",
        "label5"
    ]
}
```

> Le résultat attendu et un retour **Json** brute de l'objet vidéo ajouté. Pour ajouter un Film ou une Série, il suffit de substituer, au niveau de l'url, "`addVideo`" par "`addFilm`" ou "`addSerie`", et adapter le contenu du body de la requête (ajouter les champs spécifique à Film ou Série). 
----------
> Accéder, par exemple, via HTTP - GET, à: `http://localhost:8080/api/video/getVideo?id={STRING_ID}` pour avoir en retour uniquement la vidéo ayant le ***ID*** spécifié.

> Accéder, via HTTP - GET, à: `http://localhost:8080/api/video/getVideos?titre={VIDEO_TITRE_INJECTION}` pour avoir en retour toutes les vidéos .

**Remarque**: *les résultats en retour sont formatés en json brut exprès. La raison derrière est de laisser l'API compatible avec tout autre utilisation et avec n'importe quel système externe intérrogeant l'API, en lui donnant un retour standard pour pouvoir faire, sans complications, l'analyse(parsing) des données, etc.*

------

### Autres endpoints et exemples: 
> Pour pouvoir tester l'api rapidement, à la racine du dossier, il y a un fichier ***JSON*** `[Spideo.postman_collection.json]` qui représente la collection Postman Ready-to-go que j'ai exporté. Il suffit d'ouvrir Postman et importer la collection et lancer toutes les endpoints pour tester les endpoints de l'api.
------
Sinon, pour plus de détails, ci-dessous le guide: 
* Pour ajouter un Film, HTTP/POST à `http://localhost:8080/api/video/addFilm` avec un body de contenu suivant: 
```
  {
    "id": "videoId-200200201",
    "titre": "dummy-titre-film",
    "director": "Film director",
    "release_date": "1982-03-18T12:00:00Z",
    "labels": [
        "label4",
        "label5"
    ]
}
```  
* Pour ajouter une Série, HTTP/POST à `http://localhost:8080/api/video/addSerie` avec un body de contenu suivant:
```
{
    "id": "videoId-200200202",
    "titre": "dummy-titre-serie",
    "number_of_episodes": 22,
    "labels": [
        "testlabel3",
        "labelFilm",
        "anotherlabel",
    ]
}
```  
* Pour ajouter une Série, HTTP/POST à `http://localhost:8080/api/video/addSerie` avec un body de contenu suivant:
```
{
    "id": "videoId-200200202",
    "titre": "dummy-titre-serie",
    "number_of_episodes": 22,
    "labels": [
        "testlabel3",
        "labelFilm",
        "anotherlabel",
    ]
}
```  
* Pour supprimer une Vidéo(y compris Film et Série), HTTP/DELETE à `http://localhost:8080/api/video/deleteVideo` avec un body de contenu suivant:
```
{
    "id": "videoId-200200202",
    "titre": "dummy-titre-serie",
    "labels": [
        "testlabel3",
        "labelFilm",
        "anotherlabel",
    ]
}
```

* Pour récupérer les ***IDs*** des vidéos supprimées, HTTP/GET à `http://localhost:8080/api/video/getDeletedVideos`.  

* Pour récupérer tous les films, HTTP/GET à `http://localhost:8080/api/video/getFilms`.  

* Pour récupérer tous les séries, HTTP/GET à `http://localhost:8080/api/video/getSeries`.

* Pour récupérer tous les vidéos similaires à une vidéo (critère de labels) avec un minimum de match result, HTTP/GET à `http://localhost:8080/api/video/getSimilarVideos?minMatchNumber={MIN_INPUT_INT}` avec un body de contenu: 
```
{
    "id": "videoId-200200201",
    "titre": "dummy-titre-film",
    "labels": [
        "label4",
        "label5",
        "label3"
    ]
}
```
