/**
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4art.api.services;

import java.util.List;

import org.neo4art.api.builder.TimeLineBuilder;
import org.neo4art.api.domain.TimelineEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */

@Controller
@RequestMapping("/api/services/timeline")
public class Neo4ArtTimelineSearchRestController {

	@RequestMapping(value = "/search.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TimelineEvent> search(Model model, 
			                                        @RequestParam(value="searchInput", required=true) String searchInput ) {

		System.out.println("Input search: "+searchInput);
		//TODO LA LISTA CHE TORNO DEVE ESSERE ORDINATA
		return TimeLineBuilder.buildTimeLineEvents();
	}
	
	
}
