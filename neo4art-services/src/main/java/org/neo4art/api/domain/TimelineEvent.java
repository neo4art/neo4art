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
package org.neo4art.api.domain;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class TimelineEvent {
	
	private String start;
	
	private String thumbnail;
	
	private String original;
	
	private String description;
	
	private String averageRgb;
	
	private String closestAverageColorName;
	
	private String emotion;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAverageRgb() {
		return averageRgb;
	}

	public void setAverageRgb(String averageRgb) {
		this.averageRgb = averageRgb;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	public String getClosestAverageColorName() {
		return closestAverageColorName;
	}

	public void setClosestAverageColorName(String closestAverageColorName) {
		this.closestAverageColorName = closestAverageColorName;
	}

  public String getOriginal()
  {
    return original;
  }

  public void setOriginal(String original)
  {
    this.original = original;
  }
	
}
