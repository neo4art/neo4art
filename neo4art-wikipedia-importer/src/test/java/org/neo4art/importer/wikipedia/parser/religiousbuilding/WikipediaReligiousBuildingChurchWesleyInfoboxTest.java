package org.neo4art.importer.wikipedia.parser.religiousbuilding;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.importer.wikipedia.parser.religiousBuilding.WikipediaReligiousBuildingChurchInfobox;

public class WikipediaReligiousBuildingChurchWesleyInfoboxTest {

		private static String INFOBOX = "{{Infobox church\n"+
			"| name              = Wesley Methodist Church\n"+
			"| native_name       = name in local language. If more than one, separate using {{tl|Plain list}}\n"+
			"| native_name_lang  =\n"+
			"| image             = WesleyMethodistChurch-sepiapostcard.jpg\n"+
			"| landscape         = yes\n"+
			"| caption           = Wesley Methodist Church, from an old [[Photographic print toning#Sepia toning|sepia-toned]] postcard.\n"+
			"| osgridref         = {{gbmappingsmall|TQ 414 104}}\n"+
			"| osgraw            = TQ 414 104<!-- NB. duplicated for this example only!! -->\n"+
			"| country           = [[Singapore]]\n"+
			"| website           = [http://www.wesleymc.org/ www.wesleymc.org]\n"+
			"| former name       = Methodist Episcopal Church\n"+
			"| founded date      = {{Start date|1885|2|6|df=yes}}\n"+
			"| founder           = Rev. Dr. and Mrs. [[James Mills Thoburn]], Rev. [[William Fitzjames Oldham]], Mrs. Marie Oldham, Miss Julia Battie\n"+
			"| dedicated date    = {{Start date|1909|02|04|df=y}}\n"+
			"| denomination      = [[Methodism|Methodist]]\n"+
			"| attendance        = 4,346 (as on 20–21 December 2008)\n"+
			"| status            = [[Church (building)|Church]]\n"+
			"| functional status = Active\n"+
			"| style             = [[Gothic architecture|Gothic]]\n"+
			"| groundbreaking    = December 1907\n"+
			"| completed date    = End 1908\n"+
			"| materials         = [[Brick]]\n"+
			"| division          = Trinity Annual Conference (TRAC)\n"+
			"| seniorpastor      = Rev. Melvin Hwang (Pastor in Charge)\n"+
			"| pastor            = Rev. Alvin Chan, Rev. Lilian Ang, Rev. Philip Lim, Rev. Daniel Tan, Rev. Michael Tan, Rev. Wendy Watson, Rev. Khoo Kay Huat\n"+
			"| musicgroup        = Dawnbreakers, John Wesley Choir, Wesley Chorale, Wesley Singers, Wesley Heralds\n"+
			"}}";
		
		@Test
		public void shouldParseMuseumInfobox() throws MalformedURLException {
			ReligiousBuilding church = WikipediaReligiousBuildingChurchInfobox.parse(INFOBOX);
			
			URL url = new URL("http://en.wikipedia.org/wiki/File:WesleyMethodistChurch-sepiapostcard.jpg");
		
			Assert.assertEquals("Wesley Methodist Church", church.getBuildingName());
			Assert.assertEquals(url , church.getImage());
			Assert.assertEquals("church" , church.getType());
			
		}
}
