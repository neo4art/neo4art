package org.neo4art.api.repository;

import java.util.List;

import org.neo4art.api.domain.PointOfInterest;

/**
 * 
 * @author Gianmarco Laggia
 * @since 02 September 2015
 *
 */
public interface IPointOfInterestRepository {
	
	/**
	 * This method ask the database for nearby <code>PointOfInterest</code> that will be of one of the chosen types.
	 * @param lat User current Latitude
	 * @param lng User current Longitude
	 * @param limit Number of point to be returned
	 * @param types The list of types each POIs have to be chosen from
	 * @return
	 */
	public List<PointOfInterest> getNearestPointOfInterest(double lat, double lng, int limit, String type);
}
