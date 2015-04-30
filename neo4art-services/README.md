# Neo4Art - REST Services

This component exposes business logic as REST-services.

## Catalog

<u><b>neo4art-services/api/services/search/domains.json</b></u> <br>it returns the search domains list in a json format. <br> <u>Example Result:</u> <br> [{"text":"Maria A'Becket"},{"text":"Hans von Aachen"},{"text":"Alvar Aalto"},...... ]
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
<u><b>neo4art-services/api/services/search/detail-node-search.json</b></u> <br>it returns the node details in a json format.
<br><u> Example Request:</u> <br>
neo4art-services/api/services/search/detail-node-search.json?nodeId=1
<br><u> Example Result:</u><br> {  
   "nodeList":[  
      {  
         "id":10,
         "name":"Garden in Auvers",
         "thumbnail":"http://www.vggallery.com/painting/f_0814.jpg",
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