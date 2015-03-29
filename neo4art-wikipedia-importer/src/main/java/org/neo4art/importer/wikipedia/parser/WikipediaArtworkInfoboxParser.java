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
package org.neo4art.importer.wikipedia.parser;

import java.util.Map;

import org.neo4art.domain.Artwork;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_artwork">Template:Infobox_artwork</a>
 * 
 * @author Lorenzo Speranzoni
 * @since 19 Mar 2015
 */
public class WikipediaArtworkInfoboxParser {

  public static final String TITLE              = "title";              
  public static final String IMAGE_FILE         = "image_file";         
  public static final String IMAGE_SIZE         = "image_size";         
  public static final String ALT                = "alt";                
  public static final String CAPTION            = "caption";            
  public static final String PAINTING_ALIGNMENT = "painting_alignment"; 
  public static final String OTHER_LANGUAGE_1   = "other_language_1";   
  public static final String OTHER_TITLE_1      = "other_title_1";      
  public static final String OTHER_LANGUAGE_2   = "other_language_2";   
  public static final String OTHER_TITLE_2      = "other_title_2";      
  public static final String ARTIST             = "artist";             
  public static final String YEAR               = "year";               
  public static final String COMPLETION_DATE    = "completion_date";    
  public static final String CATALOGUE          = "catalogue";          
  public static final String TYPE               = "type";               
  public static final String MATERIAL           = "material";           
  public static final String SUBJECT            = "subject";            
  public static final String HEIGHT_METRIC      = "height_metric";      
  public static final String WIDTH_METRIC       = "width_metric";       
  public static final String LENGTH_METRIC      = "length_metric";      
  public static final String HEIGHT_IMPERIAL    = "height_imperial";    
  public static final String HEIGHT_INCH        = "height_inch";        
  public static final String WIDTH_IMPERIAL     = "width_imperial";     
  public static final String WIDTH_INCH         = "width_inch";         
  public static final String LENGTH_IMPERIAL    = "length_imperial";    
  public static final String LENGTH_INCH        = "length_inch";        
  public static final String DIAMETER_METRIC    = "diameter_metric";    
  public static final String DIAMETER_IMPERIAL  = "diameter_imperial";  
  public static final String METRIC_UNIT        = "metric_unit";        
  public static final String IMPERIAL_UNIT      = "imperial_unit";      
  public static final String DIMENSIONS         = "dimensions";         
  public static final String DIMENSIONS_REF     = "dimensions_ref";     
  public static final String WEIGHT             = "weight";             
  public static final String CONDITION          = "condition";          
  public static final String OWNER              = "owner";              
  public static final String ACCESSION          = "accession";          
  public static final String MUSEUM             = "museum";             
  public static final String CITY               = "city";               
  public static final String COORDINATES        = "coordinates";        
  public static final String URL                = "url";                
  
  public WikipediaArtworkInfoboxParser() {
  }

  public static Artwork parse(String text) {

    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);
    
    Artwork artwork = new Artwork();

    for (String key : map.keySet()) {
      
      switch (key) {
        case TITLE              :
          artwork.setTitle(map.get(key));
          break;
        case IMAGE_FILE         :
          artwork.setImageFile(map.get(key));
          break;
        case IMAGE_SIZE         :
          artwork.setImageSize(map.get(key));
          break;
        case ALT                :
          artwork.setAlt(map.get(key));
          break;
        case CAPTION            :
          artwork.setCaption(map.get(key));
          break;
        case PAINTING_ALIGNMENT :
          artwork.setPaintingAlignment(map.get(key));
          break;
        case OTHER_LANGUAGE_1   :
          artwork.setOtherLanguage1(map.get(key));
          break;
        case OTHER_TITLE_1      :
          artwork.setOtherTitle1(map.get(key));
          break;
        case OTHER_LANGUAGE_2   :
          artwork.setOtherLanguage2(map.get(key));
          break;
        case OTHER_TITLE_2      :
          artwork.setOtherTitle2(map.get(key));
          break;
        case ARTIST             :
          artwork.setArtist(map.get(key));
          break;
        case YEAR               :
          artwork.setYear(map.get(key));
          break;
        case COMPLETION_DATE    :
          artwork.setCompletionDate(map.get(key));
          break;
        case CATALOGUE          :
          artwork.setCatalogue(map.get(key));
          break;
        case TYPE               :
          artwork.setType(map.get(key));
          break;
        case MATERIAL           :
          artwork.setMaterial(map.get(key));
          break;
        case SUBJECT            :
          artwork.setSubject(map.get(key));
          break;
        case HEIGHT_METRIC      :
          artwork.setHeightMetric(map.get(key));
          break;
        case WIDTH_METRIC       :
          artwork.setWidthMetric(map.get(key));
          break;
        case LENGTH_METRIC      :
          artwork.setLengthMetric(map.get(key));
          break;
        case HEIGHT_IMPERIAL    :
          artwork.setHeightImperial(map.get(key));
          break;
        case HEIGHT_INCH        :
          artwork.setHeightInch(map.get(key));
          break;
        case WIDTH_IMPERIAL     :
          artwork.setWidthImperial(map.get(key));
          break;
        case WIDTH_INCH         :
          artwork.setWidthInch(map.get(key));
          break;
        case LENGTH_IMPERIAL    :
          artwork.setLengthImperial(map.get(key));
          break;
        case LENGTH_INCH        :
          artwork.setLengthInch(map.get(key));
          break;
        case DIAMETER_METRIC    :
          artwork.setDiameterMetric(map.get(key));
          break;
        case DIAMETER_IMPERIAL  :
          artwork.setDiameterImperial(map.get(key));
          break;
        case METRIC_UNIT        :
          artwork.setMetricUnit(map.get(key));
          break;
        case IMPERIAL_UNIT      :
          artwork.setImperialUnit(map.get(key));
          break;
        case DIMENSIONS         :
          artwork.setDimensions(map.get(key));
          break;
        case DIMENSIONS_REF     :
          artwork.setDimensionsRef(map.get(key));
          break;
        case WEIGHT             :
          artwork.setWeight(map.get(key));
          break;
        case CONDITION          :
          artwork.setCondition(map.get(key));
          break;
        case OWNER              :
          artwork.setOwner(map.get(key));
          break;
        case ACCESSION          :
          artwork.setAccession(map.get(key));
          break;
        case MUSEUM             :
          artwork.setMuseum(map.get(key));
          break;
        case CITY               :
          artwork.setCity(map.get(key));
          break;
        case COORDINATES        :
          artwork.setCoordinates(map.get(key));
          break;
        case URL                :
          artwork.setUrl(map.get(key));
          break;
      }
    }

    return artwork;
  }
}
