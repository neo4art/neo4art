# Neo4Art - Wikipedia Importer

This library imports [Wikimedia Dump Files](https://dumps.wikimedia.org).

It manages uncompresses xml dump files which adhere to this XSD schema: http://www.mediawiki.org/xml/export-0.10.xsd as well as compressed (.gz and .bz2) files.

## Dependencies

We strongly use [bliki](https://bitbucket.org/axelclk/info.bliki.wiki/wiki/Home) library for parsing xml dumps, then we persist nodes through Spring-Boot JDBC.

## Main class

The core service is [WikipediaDumpImporter](https://github.com/neo4art/neo4art/blob/master/neo4art-wikipedia-importer/src/main/java/org/neo4art/importer/wikipedia/core/WikipediaDumpImporter.java)
 
## Resulting Neo4j database

>(page:Wikipedia:WikipediaPage)-[:REFERS]->(otherPage:Wikipedia:WikipediaPage)

>(page:Wikipedia:WikipediaPage)-[:BELONGS_TO]->(category:Wikipedia:WikipediaCategory)<-[:BELONGS_TO]-(subcategory:Wikipedia:WikipediaCategory)

Other kinds of Wikipedia articles (Templates, Projects, Books, Files, etc) are currently persisted as `generic` wikipedia nodes:

>(page:Wikipedia:WikipediaPage)-[:REFERS]->(otherTypologies:Wikipedia:WikipediaGeneric)




 
