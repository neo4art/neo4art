# Neo4Art - Wikipedia Importer

This library imports [Wikimedia Dump Files](https://dumps.wikimedia.org) into [Neo4j](http://www.neo4j.com).

It manages uncompresses xml dump files which adhere to this XSD schema: http://www.mediawiki.org/xml/export-0.10.xsd as well as compressed (.gz and .bz2) files.

## Dependencies

We strongly use [Bliki](https://bitbucket.org/axelclk/info.bliki.wiki/wiki/Home) library for parsing xml dumps, then we persist nodes through [Spring-Boot Neo4j JDBC](https://github.com/neo4j-contrib/developer-resources/tree/gh-pages/language-guides/java/spring-boot-jdbc),
even if our plan is to adopt to the new [Spring Data Neo4j version 4](https://github.com/spring-projects/spring-data-neo4j/tree/4.0).

## Main class

The starting point for this library is the core service [WikipediaDumpImporter.java](https://github.com/neo4art/neo4art/blob/master/neo4art-wikipedia-importer/src/main/java/org/neo4art/importer/wikipedia/core/WikipediaDumpImporter.java).

## Testing locally

Start your local Neo4j Server version 2.2 ([Learn more](http://neo4j.com/blog/neo4j-2-2-milestone-1-release/)).

Then open the [Neo4j Browser](http://localhost:7474) and setup your username to `neo4j` and password to `neo4art`.

Finally you can run the unit test [WikipediaServiceTest.java](https://github.com/neo4art/neo4art/blob/master/neo4art-wikipedia-importer/src/test/java/org/neo4art/importer/wikipedia/service/WikipediaServiceTest.java)
to import the sample dump file [src/test/resources/enwiki-20150112-pages-articles-multistream-test.xml](https://github.com/neo4art/neo4art/blob/master/neo4art-wikipedia-importer/src/test/resources/enwiki-20150112-pages-articles-multistream-test.xml).

## Run locally

TODO...
 
## Resulting Neo4j database

>(page:Wikipedia:WikipediaPage)-[:REFERS]->(otherPage:Wikipedia:WikipediaPage)

>(page:Wikipedia:WikipediaPage)-[:BELONGS_TO]->(category:Wikipedia:WikipediaCategory)<-[:BELONGS_TO]-(subcategory:Wikipedia:WikipediaCategory)

Other kinds of Wikipedia articles (Templates, Projects, Books, Files, etc) are currently persisted as `generic` wikipedia nodes:

>(page:Wikipedia:WikipediaPage)-[:REFERS]->(otherTypologies:Wikipedia:WikipediaGeneric)

## Example (with 300K pages imported =~ 15K pages/categories)

![alt tag](https://github.com/neo4art/neo4art/blob/master/neo4art-journal/2015-02-25/19th-century-French-painters.png)



 
