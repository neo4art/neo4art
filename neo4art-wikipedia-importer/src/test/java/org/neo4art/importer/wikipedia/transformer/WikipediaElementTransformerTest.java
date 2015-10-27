package org.neo4art.importer.wikipedia.transformer;

import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.importer.wikipedia.domain.WikipediaElement;

/**
 * 
 * @author Lorenzo Speranzoni
 * @since 28 Oct 2015
 */
public class WikipediaElementTransformerTest {
  
  private static final String TEXT = 
      "{{Infobox settlement\n"+
      "|settlement_type    = [[Village]]\n"+
      "|name               = Ezinge\n"+
      "|image_skyline      = 090228 Church Ezinge NL 1.jpg\n"+
      "|image_caption      = Church and its tower in 2009\n"+
      "|coordinates_region   = NL-GR\n"+
      "|subdivision_type   = Country\n"+
      "|subdivision_name   = [[Netherlands]]\n"+
      "|subdivision_type1  = Province\n"+
      "|subdivision_name1  = [[Groningen (province)|Groningen]]\n"+
      "|subdivision_type2  = Municipality\n"+
      "|subdivision_name2  = [[Winsum]]\n"+
      "|population         = 750\n"+
      "|population_as_of   = 2008\n"+
      "|area_km2           = 0.59\n"+
      "| pushpin_map             = Netherlands Groningen\n"+
      "| pushpin_label_position  = \n"+
      "| pushpin_map_alt         = \n"+
      "| pushpin_map_caption     = Location of Ezinge in the province of [[Groningen (province)|Groningen]]\n"+
      "|latNS    = N\n"+
      "|longEW   = E\n"+
      "|latd               = 53\n"+
      "|latm               = 18\n"+
      "|lats               = 33\n"+
      "|longd               = 6\n"+
      "|longm               = 26\n"+
      "|longs               = 35}}\n"+
      "\n"+
      "'''Ezinge''' ([[Gronings]]: ''Aisen'' or ''Aizing'') is a village in the [[Netherlands|Dutch]] province of [[Groningen (province)|Groningen]]. It is located in the municipality of [[Winsum]], about 15&amp;nbsp;km northwest of the city of [[Groningen (city)|Groningen]]. Ezinge is the oldest, constantly inhabited village in [[Netherlands|The Netherlands]] and is in archeological context referred to as &quot;the [[Pompeii]] of the North&quot;&lt;ref&gt;Robert Stiphout &quot;Ezinge: Geheim van de wierden&quot; [[Elsevier (magazine)|Elsevier]], 2006. [http://www.elsevier.nl/web/Artikel/168598/Ezinge-Geheim-van-de-wierden.htm]&lt;/ref&gt;\n"+
      "\n"+
      "Ezinge was a separate municipality until 1990, when it was merged with Winsum.&lt;ref&gt;Ad van der Meer and Onno Boonstra, &quot;Repertorium van Nederlandse gemeenten&quot;, [[Royal Netherlands Academy of Arts and Sciences|KNAW]], 2006. [http://www.knaw.nl/cfdata/publicaties/detail.cfm?boeken__ordernr=20061061]&lt;/ref&gt;\n"+
      "\n"+
      "==Heritage==\n"+
      "\n"+
      "The village is a legally protected heritage district within the Middag- Humsterland region which itself enjoys the status of Dutch National Landscape and can be found on the tentative list of [[World Heritage Site|UNESCO World Heritage]]. The main sight of the village is the early 13th century church which stands separated from its tower. The tower is connected to an old schoolbuilding. The three buildings are located on an artificial dwelling hill, known as a [[Artificial dwelling hill|terp]], with the village and pastures around it, covering a quarter and three quarters, respectively, of the slopes. According to the Dutch Museum of National History, [[Tacitus]]&lt;ref&gt;[http://www.xwashier.nl/plaats/ezinge/ Museum of National History]&lt;/ref&gt; has been to Ezinge. Whether true or not, both Tacitus&lt;ref&gt;[http://la.wikisource.org/wiki/De_origine_et_situ_Germanorum_%28Germania%29#XXXV  Tacitus, 98 De origine et situ Germanorum, book 35,36]&lt;/ref&gt; and [[Pliny the Elder]]&lt;ref&gt;[http://la.wikisource.org/wiki/Naturalis_Historia/Liber_XVI#II  Gaius Plinius Secundus maior, 77, Naturalis historia, book 16,2]&lt;/ref&gt; wrote elaborately about the terps and the [[Frisii]] and [[Chauci]], the people dwelling upon them.\n"+
      "\n"+
      "== History ==\n"+
      "[[File:Ezinge.jpg|thumb|right|250px|''Esinge in 't [[Westerkwartier]] van Groeningen'', aquarelle from 1772 by [[Aert Schouman]] (1710-1792)]]\n"+
      "\n"+
      "Before dikes were built, starting around the beginning of the second millennium, people in the Low Countries, especially in the north, built artificial hills to shelter themselves and their stock from the high tide. Repeatedly over the centuries these hills were reinforced and heightened, often with soil and waste. Crosssections of terps reveal many layers indicating periods of drought, wealth or poverty and even wars and conflagration.\n"+ 
      "At that time before the dikes and later the reclaiming of land, Ezinge was situated on the northern edge of the middle one of the three historical islands of [[Frisia]], now [[Groningen (province)|Groningen]]. The district name &quot;Middag&quot; (now meaning &quot;afternoon&quot; in [[Dutch language|Dutch]]) derives from Mid- Oog, literally Mid Island (compare with the name of the island [[Schiermonnikoog (island and municipality)|Schiermonnikoog]]).\n"+
      "\n"+
      "Around the end of the 1920s [[peat]] diggers found curious old objects that attracted the attention of archeologist Albert van Giffen. The following decade Ezinge was the centre of attention for archeologists and historians. Van Giffen found evidence for one of the oldest Dutch instances of constant habitation. Among the objects found were 85 farm buildings and 60 outbuildings, most of them with an almost pristine ground plan visible,&lt;ref&gt;[[University of Groningen]], 2010 [http://www.rug.nl/museum/geschiedenis/hoogleraren/giffen]&lt;/ref&gt; graves, pottery, [[Ice_skates#History|bone ice skates]], jewelry and tools.&lt;ref&gt;[[Television_in_the_Netherlands#Regional|TVNoord]], 2012 [http://www.youtube.com/watch?v=d7BcaTEDjeQ]&lt;/ref&gt;\n"+
      "Van Giffen learned that the closer he excavated towards the church, the older the objects proved to be. Eager as he was to find even older evidence, he had the church propped and tried to dig under it but soon had to abandon his attempt due to instability of the church. \n"+
      "All in all Van Giffen's endeavors revealed that Ezinge was constantly inhabited since 600 [[Common Era|BCE]] and probably earlier, making it one of the oldest towns in Europe. Roman (import-) pottery and artefacts, like a statue of [[Jupiter (mythology)|Jupiter]],&lt;ref&gt;[[Television_in_the_Netherlands#Regional|TVNoord]], 2012 [http://www.youtube.com/watch?v=d7BcaTEDjeQ]&lt;/ref&gt; were found, indicating (economic-) relations with cultures far away and a [[Wand|magic wand]], indicating [[Paganism|pagan beliefs]]. A golden [[Hilt|pommel]], closely resembling the one found in [[Sutton Hoo]] indicates a [[Anglo-Saxon settlement of Britain|close cultural connection]] to the [[Anglo-Saxons]] in [[Great Britain]] in the 6th and 7th century [[Common Era|CE]].\n"+
      "\n"+
      "== Location ==\n"+
      "[[File:090228 Allersmaborg Groningen NL.jpg|thumb|right|250px|The Allersmaborg]]\n"+
      "[[File:Ezinge, terp, ice rink.jpg|thumb|right|250px|Church of Ezinge on the terp with left excavated terp as pastures and right the natural ice rink, Winter, 2012]]\n"+
      "\n"+
      "Ezinge is located about 15&amp;nbsp;km from the city of Groningen and about 9&amp;nbsp;km from the municipal capital of [[Winsum]]. The Allersmaborg (literally ''[[Borg (castle)|Borg]]'' (castle) ''of Allersma'') was built in the 15th century on the banks of the Reitdiep river's meanders for economic and defensive purposes. Together with the nearby Aduarderzijl sluices, it acted as a strategic stronghold during battles in the [[Vetkopers and Schieringers|Frisian civil war]], the [[Eighty Years' War]] and the [[Siege of Groningen]] respectively. The borg was later extended into a manor house.\n"+ 
      "\n"+
      "The village is connected to the provincial highway by a bridge. The Village has a supermarket, a bakery, a pharmacy, a post office, a barbershop, a liquor store and 2 pubs. The &quot;Wierdenland&quot; archeological museum in Ezinge offers an insight in the area's history. Further recreation can be found at the natural ice rink in the excavated terp and the annual Ezinge festival with funfair.\n"+
      "\n"+
      "== References ==\n"+
      "{{reflist}}\n"+
      "\n"+
      "== External links ==\n"+
      "* [http://www.atlas1868.nl/gr/ezinge.html Map of the former municipality in 1868]\n"+
      "* {{Commons category-inline|Ezinge}}\n"+
      "\n"+
      "{{Coord|53|19|N|6|27|E|region:NL_type:city|display=title}}\n"+
      "{{Municipality of Winsum}}\n"+
      "\n"+
      "[[Category:Populated places in Groningen (province)]]\n"+
      "[[Category:Former municipalities of Groningen (province)]]";

  @Test
  public void shouldTranformWikiArticleEzingeIntoSettlement() throws MalformedURLException {

    try {
      
      Siteinfo siteInfo = new Siteinfo();
  
      WikiArticle wikiArticle = new WikiArticle();
      wikiArticle.setId("9191142");
      wikiArticle.setIntegerNamespace(0);
      wikiArticle.setNamespace("");
      wikiArticle.setRevisionId("606023480");
      wikiArticle.setText(TEXT);
      wikiArticle.setTimeStamp("2014-04-27T12:21:38Z");
      wikiArticle.setTitle("Ezinge", siteInfo);
      
      WikipediaElement wikipediaElement = WikipediaElementTransformer.toWikipediaElement(wikiArticle);
      
      Assert.assertNotNull(wikipediaElement);
      Assert.assertEquals(new Long(9191142L), new Long(wikipediaElement.getId()));
      
    }
    catch (Exception e) {
      
      e.printStackTrace();
      
      Assert.fail();
    }
  }
}
