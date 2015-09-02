package org.neo4art.app.repository;

import java.util.List;

import org.neo4art.app.domain.PointOfInterest;

public class POIGraphDatabaseServiceRepository implements IPointOfInterestRepository {

	@Override
	public List<PointOfInterest> getNearestPointOfInterest(double lat, double lng, int limit, List<String> types) {
		throw new RuntimeException(new IllegalAccessException("Method yet implemented"));
	}

}
