package toberefactored.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.Settlement;

public class WikipediaSettlementDistrictDEAhrweilerInfoboxTest {

  private static String INFOBOX =
    "{{Infobox German district\n" + 
    "|name           = Ahrweiler\n" + 
    "|url            = [http://www.kreis.aw-online.de kreis.aw-online.de]\n" + 
    "}}";

  @Test
  public void shoudParseSettlementDistrictDE() throws MalformedURLException {

    Settlement settlement = WikipediaSettlementDistrictDEInfoboxParser.parse(INFOBOX);

    Assert.assertEquals("Ahrweiler", settlement.getName());
    Assert.assertEquals(new URL("http://www.kreis.aw-online.de"), settlement.getWebsite());
  }
}
