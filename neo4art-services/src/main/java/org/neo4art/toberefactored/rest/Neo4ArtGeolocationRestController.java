package org.neo4art.toberefactored.rest;

import java.util.List;

import org.neo4art.toberefactored.builder.mock.BuildPOIListMock;
import org.neo4art.toberefactored.domain.Marker;
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
@RequestMapping("/api/geolocation")
public class Neo4ArtGeolocationRestController {
	@RequestMapping(value = "/poi.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Marker> getColoursAnalysis(Model model, @RequestParam(value = "lat", required = true) Double lat,
			@RequestParam(value = "lng", required = true) Double lng) {

		return BuildPOIListMock.generatePOIs(lat,lng);
	}

}
