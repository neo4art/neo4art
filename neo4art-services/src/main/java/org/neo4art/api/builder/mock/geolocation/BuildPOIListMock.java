package org.neo4art.api.builder.mock.geolocation;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.domain.PointOfInterest;
import org.neo4art.domain.Artwork;

/**
 * This class is a mock generator of <code>PointOfInterest</code> used in a first try version of the app.
 * 
 * @author Gianmarco Laggia
 * @since 26 ago 2015
 */
public class BuildPOIListMock {
	public static List<PointOfInterest> generatePOIs(Double lat, Double lng) {
		List<PointOfInterest> pois = new ArrayList<PointOfInterest>();
		
			PointOfInterest poi = new PointOfInterest();
			poi.setLat(lat + (Math.random() - 0.5));
			poi.setLng(lng + (Math.random() - 0.5));
			poi.setTitle("The Sunflowers");
			poi.setType("ArtWork");
			poi.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi2 = new PointOfInterest();
			poi2.setTitle("Still Life with Cabbage and Clogs");
			poi2.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi2.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi2.setType("ArtWork");
			poi2.setLat(lat + (Math.random() - 0.5));
			poi2.setLng(lng + (Math.random() - 0.5));
			poi2.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			PointOfInterest poi3 = new PointOfInterest();
			poi3.setTitle("Crouching Boy with Sickle");
			poi3.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi3.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi3.setType("ArtWork");
			poi3.setLat(lat + (Math.random() - 0.5));
			poi3.setLng(lng + (Math.random() - 0.5));
			poi3.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi4 = new PointOfInterest();
			poi4.setTitle("Old Man at the Fireside");
			poi4.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi4.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi4.setType("ArtWork");
			poi4.setLat(lat + (Math.random() - 0.5));
			poi4.setLng(lng + (Math.random() - 0.5));
			poi4.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi5 = new PointOfInterest();
			poi5.setTitle("Woman Sewing");
			poi5.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi5.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi5.setType("ArtWork");
			poi5.setLat(lat + (Math.random() - 0.5));
			poi5.setLng(lng + (Math.random() - 0.5));
			poi5.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi6 = new PointOfInterest();
			poi6.setTitle("Woman with White Shawl");
			poi6.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi6.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi6.setType("ArtWork");
			poi6.setLat(lat + (Math.random() - 0.5));
			poi6.setLng(lng + (Math.random() - 0.5));
			poi6.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi7 = new PointOfInterest();
			poi7.setTitle("Beach at Scheveningen in Calm Weather");
			poi7.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi7.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi7.setType("ArtWork");
			poi7.setLat(lat + (Math.random() - 0.5));
			poi7.setLng(lng + (Math.random() - 0.5));
			poi7.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi8 = new PointOfInterest();
			poi8.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi8.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi8.setTitle("Dunes");
			poi8.setType("ArtWork");
			poi8.setLat(lat + (Math.random() - 0.5));
			poi8.setLng(lng + (Math.random() - 0.5));
			poi8.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi9 = new PointOfInterest();
			poi9.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi9.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi9.setTitle("Dunes with Figures");
			poi9.setType("ArtWork");
			poi9.setLat(lat + (Math.random() - 0.5));
			poi9.setLng(lng + (Math.random() - 0.5));
			poi9.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi10 = new PointOfInterest();
			poi10.setTitle("Beach at Scheveningen in Stormy Weather");
			poi10.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi10.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi10.setType("ArtWork");
			poi10.setLat(lat + (Math.random() - 0.5));
			poi10.setLng(lng + (Math.random() - 0.5));
			poi10.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi11 = new PointOfInterest();
			poi11.setTitle("Fisherman on the Beach");
			poi11.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi11.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi11.setType("ArtWork");
			poi11.setLat(lat + (Math.random() - 0.5));
			poi11.setLng(lng + (Math.random() - 0.5));
			poi11.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			PointOfInterest poi12 = new PointOfInterest();
			poi12.setTitle("Starry Night Over the Rhone");
			poi12.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi12.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi12.setType("ArtWork");
			poi12.setLat(lat + (Math.random() - 0.5));
			poi12.setLng(lng + (Math.random() - 0.5));
			poi12.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			pois.add(poi);
			pois.add(poi2);
			pois.add(poi3);
			pois.add(poi4);
			pois.add(poi5);
			pois.add(poi6);
			pois.add(poi7);
			pois.add(poi8);
			pois.add(poi9);
			pois.add(poi10);
			pois.add(poi11);
			pois.add(poi12);
		return pois;
	}
}
