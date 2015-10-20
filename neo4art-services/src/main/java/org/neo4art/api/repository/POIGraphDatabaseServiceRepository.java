package org.neo4art.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4art.api.domain.PointOfInterest;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.helpers.collection.MapUtil;

import deprecated.Neo4ArtGraphDatabaseServiceSingleton;

public class POIGraphDatabaseServiceRepository implements IPointOfInterestRepository {

	@Override
	public List<PointOfInterest> getNearestPointOfInterest(double lat, double lng, int limit, String type) {
		List<PointOfInterest> pointsOfInterest = null;

		GraphDatabaseService graphDatabaseService = Neo4ArtGraphDatabaseServiceSingleton.getGraphDatabaseService();

		String cql = "";
		if(type == null || "".equals(type)){
			cql = "MATCH node RETURN node LIMIT {limit}";
		} else {
			cql = "MATCH node:{type} RETURN node LIMIT {limit}";
		}

		Result result = graphDatabaseService.execute(cql, MapUtil.map("lat", lat, "lng", lng, "limit", limit));

		while (result.hasNext()) {
			Map<String, Object> next = result.next();

			if (pointsOfInterest == null) {
				pointsOfInterest = new ArrayList<PointOfInterest>();
			}

			/*Node artworkNode = (Node) next.get("artwork");
			Node colourAnalysisNode = (Node) next.get("colourAnalysis");
			Node averageClosestColourNode = (Node) next.get("averageClosestColour");

			Artwork artwork = new Artwork();
			// artwork.setArtist(artist);
			artwork.setTitle((String) artworkNode.getProperty("title"));
			artwork.setYear((String) artworkNode.getProperty("year"));
			artwork.setCompletionDate(new Date((Long) artworkNode.getProperty("completionDate")));

			Colour averageClosestColour = new Colour();
			averageClosestColour.setName((String) averageClosestColourNode.getProperty("name"));
			averageClosestColour.setRgb((int) averageClosestColourNode.getProperty("rgb"));

			Color minimumColour = new Color((int) colourAnalysisNode.getProperty("minimumColour"));
			Color averageColour = new Color((int) colourAnalysisNode.getProperty("averageColour"));
			Color maximumColour = new Color((int) colourAnalysisNode.getProperty("maximumColour"));

			ColourAnalysis colourAnalysis = new ColourAnalysis();
			colourAnalysis.setArtwork(artwork);
			colourAnalysis.setAverageClosestColour(averageClosestColour);
			colourAnalysis.setMinimumColour(minimumColour);
			colourAnalysis.setAverageColour(averageColour);
			colourAnalysis.setMaximumColour(maximumColour);
			colourAnalysis.setSource((String) colourAnalysisNode.getProperty("source"));*/
			
			PointOfInterest point = new PointOfInterest();
			pointsOfInterest.add(point);
		}

		return pointsOfInterest;
	}

}
