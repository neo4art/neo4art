package org.neo4art.api.services;

import java.util.List;

import org.neo4art.api.builder.mock.geolocation.BuildPOIListMock;
import org.neo4art.api.domain.PointOfInterest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gianmarco Laggia
 * @since 26 August 2015
 *
 */
@Controller
@RequestMapping("/api/services/geolocation")
public class Neo4ArtGeolocationRestController {
	@RequestMapping(value = "/poi.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PointOfInterest> getColoursAnalysis(Model model, @RequestParam(value = "lat", required = true) Double lat,
			@RequestParam(value = "lng", required = true) Double lng) {

		return BuildPOIListMock.generatePOIs(lat,lng);
	}

}
