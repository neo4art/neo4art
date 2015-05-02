# Neo4Art - REST Services

This component exposes business logic as REST-services.

## Catalog

<u><b>neo4art-services/api/services/search/domains.json</b></u> <br>it returns the search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Maria A'Becket"},{"text":"Hans von Aachen"},{"text":"Alvar Aalto"},...... ]
<br><br>

<u><b>neo4art-services/api/services/search/artists-domain.json</b></u> <br>it returns the Artist search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Vincent Van gogh"},{"text":"Claude Monet"},......]
<br><br>

<u><b>neo4art-services/api/services/search/artworks-domain.json</b></u> <br>it returns the Artworks search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Still Life with Cabbage and Clogs"},{"text":"Crouching Boy with Sickle"},{"text":"Old Man at the Fireside"},{"text":"Woman Sewing"},{"text":"Woman with White Shawl"},{"text":"Beach at Scheveningen in Calm Weather"},{"text":"Dunes"},......]
<br><br>

<u><b>neo4art-services/api/services/search/museums-domain.json</b></u> <br>it returns the Museum search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Museum of Modern Art of Algiers"},{"text":"Museum of Popular Arts and Traditions"},{"text":"National Museum of Fine Arts of Algiers"}, ....]
<br><br>

<u><b>neo4art-services/api/services/search/colours-domain.json</b></u> <br>it returns the Colour search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Acid Green"},{"text":"Red"},{"text":"White"},{"text":"Brown"},{"text":"Yellow"}, ....]
<br><br>

<u><b>neo4art-services/api/services/search/sentiments-domain.json</b></u> <br>it returns the Sentiment search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"abound"},{"text":"abounds"},{"text":"abundance"},{"text":"overzealous"},{"text":"overzealously"},{"text":"overzelous"},{"text":"pain"},{"text":"painful"},{"text":"painfull"}, ....]
<br><br>

<u><b>neo4art-services/api/services/search/search-results.json</b></u> <br>it returns the search result in a json format. 
<br><u> Example Request:</u> <br>
neo4art-services/api/services/search/search-results.json?searchInput=van gogh
<br><u> Example Result:</u><br> {  
   "nodeList":[  
      {  
         "id":10,
         "name":"Garden in Auvers",
         "thumbnail":"http://www.vggallery.com/painting/f_0814.jpg",
         "link": "http://en.wikipedia.org/wiki/Vincent_van_Gogh",
         "type": "Artist",
         "description": "Garden in Auvers",
         "group":0
      },.....
      ],
   "linkList":[  
      {  
         "source":11,
         "target":10,
         "value":1,
         "linkName":"has painted"
      },
,...... ]
<br><br>
<u><b>neo4art-services/api/services/search/node-explode.json</b></u> <br>it returns the node details in a json format.
<br><u> Example Request:</u> <br>
neo4art-services/api/services/search/node-explode.json?nodeId=1
<br><u> Example Result:</u><br> {  
   "nodeList":[  
      {  
         "id":10,
         "name":"Garden in Auvers",
         "thumbnail":"http://www.vggallery.com/painting/f_0814.jpg",
         "link": "http://en.wikipedia.org/wiki/Vincent_van_Gogh",
         "type": "Artist",
         "description": "Garden in Auvers",
         "group":0
      },.....
      ],
   "linkList":[  
      {  
         "source":11,
         "target":10,
         "value":1,
         "linkName":"has painted"
      },
,...... ]
<br><br>
<u><b>neo4art-services/api/services/timeline/search.json</b></u> <br>it returns the timeline search in a json format.
<br><u> Example Request:</u> <br>
neo4art-services/api/services/timeline/search.json?searchInput=van gogh
<br><u> Example Result:</u><br> [  
   {  
      "start":"12-Dec-1881 00:00",
      "thumbnail":"http://www.vggallery.com/painting/f_0001.jpg",
      "description":"Still Life with Cabbage and Clogs",
      "averageRgb":"C5D7F2",
      "emotion":"smile"
   },
   {  ....... }
   ]
}
<br><br>

     

## Bold Thanks!

**If you like our idea, please consider to become a contributor of this project!**


Lorenzo, Olimpia, Enrico, Mattia and all stuff @ LARUS Business Automation

www.neo4art.org / team@neo4art.org