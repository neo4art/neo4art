package org.neo4art.toberefactored.builder.mock;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.domain.Artwork;
import org.neo4art.toberefactored.domain.Marker;

/**
 * This class is a mock generator of <code>PointOfInterest</code> used in a first try version of the app.
 * 
 * @author Gianmarco Laggia
 * @since 26 ago 2015
 */
public class BuildPOIListMock {
	public static List<Marker> generatePOIs(Double lat, Double lng) {
		List<Marker> pois = new ArrayList<Marker>();
		
			Marker poi = new Marker();
			poi.setLat(lat + (Math.random() - 0.5));
			poi.setLng(lng + (Math.random() - 0.5));
			poi.setTitle("The Sunflowers");
			poi.setType("ArtWork");
			poi.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi2 = new Marker();
			poi2.setTitle("Still Life with Cabbage and Clogs");
			poi2.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi2.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi2.setType("ArtWork");
			poi2.setLat(lat + (Math.random() - 0.5));
			poi2.setLng(lng + (Math.random() - 0.5));
			poi2.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			Marker poi3 = new Marker();
			poi3.setTitle("Crouching Boy with Sickle");
			poi3.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi3.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi3.setType("ArtWork");
			poi3.setLat(lat + (Math.random() - 0.5));
			poi3.setLng(lng + (Math.random() - 0.5));
			poi3.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi4 = new Marker();
			poi4.setTitle("Old Man at the Fireside");
			poi4.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi4.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi4.setType("ArtWork");
			poi4.setLat(lat + (Math.random() - 0.5));
			poi4.setLng(lng + (Math.random() - 0.5));
			poi4.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi5 = new Marker();
			poi5.setTitle("Woman Sewing");
			poi5.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi5.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi5.setType("ArtWork");
			poi5.setLat(lat + (Math.random() - 0.5));
			poi5.setLng(lng + (Math.random() - 0.5));
			poi5.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi6 = new Marker();
			poi6.setTitle("Woman with White Shawl");
			poi6.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi6.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi6.setType("ArtWork");
			poi6.setLat(lat + (Math.random() - 0.5));
			poi6.setLng(lng + (Math.random() - 0.5));
			poi6.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi7 = new Marker();
			poi7.setTitle("Beach at Scheveningen in Calm Weather");
			poi7.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi7.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi7.setType("ArtWork");
			poi7.setLat(lat + (Math.random() - 0.5));
			poi7.setLng(lng + (Math.random() - 0.5));
			poi7.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi8 = new Marker();
			poi8.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi8.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi8.setTitle("Dunes");
			poi8.setType("ArtWork");
			poi8.setLat(lat + (Math.random() - 0.5));
			poi8.setLng(lng + (Math.random() - 0.5));
			poi8.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi9 = new Marker();
			poi9.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi9.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi9.setTitle("Dunes with Figures");
			poi9.setType("ArtWork");
			poi9.setLat(lat + (Math.random() - 0.5));
			poi9.setLng(lng + (Math.random() - 0.5));
			poi9.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi10 = new Marker();
			poi10.setTitle("Beach at Scheveningen in Stormy Weather");
			poi10.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi10.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi10.setType("ArtWork");
			poi10.setLat(lat + (Math.random() - 0.5));
			poi10.setLng(lng + (Math.random() - 0.5));
			poi10.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi11 = new Marker();
			poi11.setTitle("Fisherman on the Beach");
			poi11.setImage("http://www.vggallery.com/painting/f_0001.jpg");
			poi11.setThumbnail("http://www.vggallery.com/painting/f_0001.jpg");
			poi11.setType("ArtWork");
			poi11.setLat(lat + (Math.random() - 0.5));
			poi11.setLng(lng + (Math.random() - 0.5));
			poi11.setDescription("Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo Prova prova ecco ecco pero pero melo melo ");
			
			
			Marker poi12 = new Marker();
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
