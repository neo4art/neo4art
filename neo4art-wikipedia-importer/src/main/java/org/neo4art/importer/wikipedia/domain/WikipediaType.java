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
package org.neo4art.importer.wikipedia.domain;

/**
 * Different kind of Wikipedia Pages
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public enum WikipediaType
{
  PAGE, CATEGORY, FILE, PROJECT, TEMPLATE, GENERIC,

  ARTIST_PAGE, ARTWORK_PAGE, MUSEUM_PAGE, ART_MOVEMENT_PAGE, MONUMENT_PAGE, RELIGIOUS_BUILDING_PAGE,

  SETTLEMENT_PAGE, COUNTRY_PAGE,
}
