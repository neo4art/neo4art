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
package org.neo4art.importer.wikipedia.parser.religiousBuilding;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4art.domain.Coordinate;
import org.neo4art.domain.ReligiousBuilding;
import org.neo4art.domain.Settlement;
import org.neo4art.importer.wikipedia.util.WikipediaInfoboxUtils;

/**
 * Parser for <a href="http://en.wikipedia.org/wiki/Template:Infobox_religious_building">Template:Infobox_religious_building</a>
 * 
 * @author Lorenzo Speranzoni, Mattia Zaratin
 * @since 19 Mar 2015
 */
public class WikipediaReligiousBuildingInfoboxParser
{
  private static Log         logger                = LogFactory.getLog(WikipediaReligiousBuildingInfoboxParser.class);

  public static final String BUILDING_NAME         = "building_name";
  public static final String NATIVE_NAME           = "native_name";
  public static final String NATIVE_NAME_LANG      = "native_name_lang";
  public static final String IMAGE                 = "image";
  public static final String IMAGE_SIZE            = "image_size";
  public static final String ALT                   = "alt";
  public static final String CAPTION               = "caption";
  public static final String MAP_TYPE              = "map_type";
  public static final String MAP_SIZE              = "map_size";
  public static final String MAP_CAPTION           = "map_caption";
  public static final String LOCATION              = "location";
  public static final String COORDINATES           = "geo";
  public static final String LATITUDE              = "latitude";
  public static final String LONGITUDE             = "longitude";
  public static final String COORDINATES_REGION    = "coordinates_region";
  public static final String RELIGIOUS_AFFILIATION = "religious_affiliation";
  public static final String RITE                  = "rite";
  public static final String REGION                = "region";
  public static final String STATE                 = "state";
  public static final String PROVINCE              = "province";
  public static final String TERRITORY             = "territory";
  public static final String PREFECTURE            = "prefecture";
  public static final String SECTOR                = "sector";
  public static final String DISTRICT              = "district";
  public static final String CERCLE                = "cercle";
  public static final String MUNICIPALITY          = "municipality";
  public static final String CONSECRATION_YEAR     = "consecration_year";
  public static final String STATUS                = "status";
  public static final String FUNCTIONAL_STATUS     = "functional_status";
  public static final String HERITAGE_DESIGNATION  = "heritage_designation";
  public static final String LEADERSHIP            = "leadership";
  public static final String PATRON                = "patron";
  public static final String WEBSITE               = "website";
  public static final String ARCHITECTURE          = "architecture";
  public static final String ARCHITECT             = "architect";
  public static final String ARCHITECTURE_TYPE     = "architecture_type";
  public static final String ARCHITECTURE_STYLE    = "architecture_style";
  public static final String GENERAL_CONTRACTOR    = "general_contractor";
  public static final String FACADE_DIRECTION      = "facade_direction";
  public static final String GROUNDBREAKING        = "groundbreaking";
  public static final String YEAR_COMPLETED        = "year_completed";
  public static final String CONSTRUCTION_COST     = "construction_cost";
  public static final String SPECIFICATIONS        = "specifications";
  public static final String CAPACITY              = "capacity";
  public static final String LENGTH                = "length";
  public static final String WIDTH                 = "width";
  public static final String WIDTH_NAVE            = "width_nave";
  public static final String HEIGHT_MAX            = "height_max";
  public static final String DOME_QUANTITY         = "dome_quantity";
  public static final String DOME_HEIGHT_OUTER     = "dome_height_outer";
  public static final String DOME_HEIGHT_INNER     = "dome_height_inner";
  public static final String DOME_DIA_OUTER        = "dome_dia_outer";
  public static final String DOME_DIA_INNER        = "dome_dia_inner";
  public static final String MINARET_QUANTITY      = "minaret_quantity";
  public static final String MINARET_HEIGHT        = "minaret_height";
  public static final String SPIRE_QUANTITY        = "spire_quantity";
  public static final String SPIRE_HEIGHT          = "spire_height";
  public static final String MATERIALS             = "materials";
  public static final String NRHP                  = "nrhp";
  public static final String ADDED                 = "added";
  public static final String REFNUM                = "refnum";
  public static final String DESIGNATED            = "designated";
  public static final String STYLE                 = "infobox";

  public WikipediaReligiousBuildingInfoboxParser()
  {
  }

  public static ReligiousBuilding parse(String text)
  {
    Map<String, String> map = WikipediaInfoboxUtils.asMap(text);

    ReligiousBuilding religiousBuilding = new ReligiousBuilding();
    Coordinate coordinate = new Coordinate();
    Settlement settlement = new Settlement();

    for (String key : map.keySet())
    {
      switch (key)
      {
        case BUILDING_NAME:
          religiousBuilding.setBuildingName(infoboxBuildingName(map.get(key)));
          break;
        case STYLE:
          religiousBuilding.setType(map.get(key));
          break;
        case NATIVE_NAME:
          religiousBuilding.setNativeName(map.get(key));
          break;
        case NATIVE_NAME_LANG:
          religiousBuilding.setNativeNameLang(map.get(key));
          break;
        case IMAGE:
          religiousBuilding.setImage(WikipediaInfoboxUtils.infoboxImageUrl(map.get(key)));
          break;
        case IMAGE_SIZE:
          religiousBuilding.setImageSize(map.get(key));
          break;
        case ALT:
          religiousBuilding.setAlt(map.get(key));
          break;
        case CAPTION:
          religiousBuilding.setCaption(infoboxCaption(map.get(key)));
          break;
        case MAP_TYPE:
          religiousBuilding.setMapType(map.get(key));
          break;
        case MAP_SIZE:
          religiousBuilding.setMapSize(map.get(key));
          break;
        case MAP_CAPTION:
          religiousBuilding.setMapCaption(map.get(key));
          break;
        case LOCATION:
          settlement.setName(infoboxLocation(map.get(key)));
          religiousBuilding.setLocation(settlement);
          break;
        case COORDINATES:
          String[] c = infoboxRestingPlaceCoordinates(map.get(key));
          // coordinate.setLatD(Double.parseDouble(c[1]));
          // coordinate.setLatM(Double.parseDouble(c[2]));
          // coordinate.setLatS(Double.parseDouble(c[3]));
          // coordinate.setLatNS(c[4]);
          // coordinate.setLongD(Double.parseDouble(c[5]));
          // coordinate.setLongM(Double.parseDouble(c[6]));
          // coordinate.setLongS(Double.parseDouble(c[7]));
          coordinate.setLongEW(c[8]);
          religiousBuilding.setCoordinates(coordinate);
          break;
        case LATITUDE:
          religiousBuilding.setLatitude(map.get(key));
          break;
        case LONGITUDE:
          religiousBuilding.setLongitude(map.get(key));
          break;
        case COORDINATES_REGION:
          religiousBuilding.setCoordinatesRegion(map.get(key));
          break;
        case RELIGIOUS_AFFILIATION:
          religiousBuilding.setReligiousAffiliation(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case RITE:
          religiousBuilding.setRite(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case REGION:
          religiousBuilding.setRegion(map.get(key));
          break;
        case STATE:
          religiousBuilding.setState(map.get(key));
          break;
        case PROVINCE:
          religiousBuilding.setProvince(infoboxProvince(map.get(key)));
          break;
        case TERRITORY:
          religiousBuilding.setTerritory(map.get(key));
          break;
        case PREFECTURE:
          religiousBuilding.setPrefecture(map.get(key));
          break;
        case SECTOR:
          religiousBuilding.setSector(map.get(key));
          break;
        case DISTRICT:
          religiousBuilding.setDistrict(map.get(key));
          break;
        case CERCLE:
          religiousBuilding.setCercle(map.get(key));
          break;
        case MUNICIPALITY:
          religiousBuilding.setMunicipality(map.get(key));
          break;
        case CONSECRATION_YEAR:
          religiousBuilding.setConsecrationYear(map.get(key));
          break;
        case STATUS:
          religiousBuilding.setStatus(infoboxStatus(map.get(key)));
          break;
        case FUNCTIONAL_STATUS:
          religiousBuilding.setFunctionalStatus(map.get(key));
          break;
        case HERITAGE_DESIGNATION:
          religiousBuilding.setHeritageDesignation(map.get(key));
          break;
        case LEADERSHIP:
          religiousBuilding.setLeadership(infoboxLeadership(map.get(key)));
          break;
        case PATRON:
          religiousBuilding.setPatron(map.get(key));
          break;
        case WEBSITE:
          religiousBuilding.setWebsite(infoboxWebsite(map.get(key)));
          break;
        case ARCHITECTURE:
          religiousBuilding.setArchitecture(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ARCHITECT:
          religiousBuilding.setArchitect(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ARCHITECTURE_TYPE:
          religiousBuilding.setArchitectureType(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case ARCHITECTURE_STYLE:
          religiousBuilding.setArchitectureStyle(infoboxProvince(map.get(key)));
          break;
        case GENERAL_CONTRACTOR:
          religiousBuilding.setGeneralContractor(map.get(key));
          break;
        case FACADE_DIRECTION:
          religiousBuilding.setFacadeDirection(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case GROUNDBREAKING:
          religiousBuilding.setGroundbreaking(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case YEAR_COMPLETED:
          religiousBuilding.setYearCompleted(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case CONSTRUCTION_COST:
          religiousBuilding.setConstructionCost(map.get(key));
          break;
        case SPECIFICATIONS:
          religiousBuilding.setSpecifications(map.get(key));
          break;
        case CAPACITY:
          religiousBuilding.setCapacity(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case LENGTH:
          religiousBuilding.setLength(infoboxMisure(map.get(key)));
          break;
        case WIDTH:
          religiousBuilding.setWidth(infoboxMisure(map.get(key)));
          break;
        case WIDTH_NAVE:
          religiousBuilding.setWidthNave(infoboxMisure(map.get(key)));
          break;
        case HEIGHT_MAX:
          religiousBuilding.setHeightMax(infoboxMisure(map.get(key)));
          break;
        case DOME_QUANTITY:
          religiousBuilding.setDomeQuantity(map.get(key));
          break;
        case DOME_HEIGHT_OUTER:
          religiousBuilding.setDomeHeightOuter(infoboxMisure(map.get(key)));
          break;
        case DOME_HEIGHT_INNER:
          religiousBuilding.setDomeHeightInner(map.get(key));
          break;
        case DOME_DIA_OUTER:
          religiousBuilding.setDomeDiaOuter(map.get(key));
          break;
        case DOME_DIA_INNER:
          religiousBuilding.setDomeDiaInner(map.get(key));
          break;
        case MINARET_QUANTITY:
          religiousBuilding.setMinaretQuantity(map.get(key));
          break;
        case MINARET_HEIGHT:
          religiousBuilding.setMinaretHeight(map.get(key));
          break;
        case SPIRE_QUANTITY:
          religiousBuilding.setSpireQuantity(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case SPIRE_HEIGHT:
          religiousBuilding.setSpireHeight(infoboxMisure(map.get(key)));
          break;
        case MATERIALS:
          religiousBuilding.setMaterials(WikipediaInfoboxUtils.removeAllParenthesis(map.get(key)));
          break;
        case NRHP:
          religiousBuilding.setNrhp(map.get(key));
          break;
        case ADDED:
          religiousBuilding.setAdded(map.get(key));
          break;
        case REFNUM:
          religiousBuilding.setRefnum(map.get(key));
          break;
        case DESIGNATED:
          religiousBuilding.setDesignated(map.get(key));
          break;
      }
    }

    return religiousBuilding;
  }

  public static String infoboxBuildingName(String name)
  {
    try
    {
      String[] n = StringUtils.split(name, "<");
      name = n[0];
      name = name.replace("&nbsp;", " ");
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxCaption(String name)
  {
    try
    {
      if (name.contains("|") || name.contains("["))
      {
        String[] n = StringUtils.split(name, "[");
        String[] n1 = StringUtils.split(n[1], "|");
        name = n1[0];
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing Artist infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxLocation(String name)
  {
    try
    {
      if (name.contains("]"))
      {
        String[] n1 = StringUtils.split(name, "]");
        name = n1[0];
        name = WikipediaInfoboxUtils.removeAllParenthesis(name);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxWebsite(String name)
  {
    try
    {
      if (name.contains(" "))
      {
        String[] n1 = StringUtils.split(name, " ");
        name = WikipediaInfoboxUtils.removeAllParenthesis(n1[0]);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxStatus(String name)
  {
    try
    {
      if (name.contains("#"))
      {
        String[] n1 = StringUtils.split(name, "#");
        name = WikipediaInfoboxUtils.removeAllParenthesis(n1[0]);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxLeadership(String name)
  {
    try
    {
      if (name.contains("["))
      {
        String[] n1 = StringUtils.split(name, "[");
        name = WikipediaInfoboxUtils.removeAllParenthesis(n1[1]);
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());
    }
    
    return null;
  }

  public static String infoboxProvince(String name)
  {
    try
    {
      String[] n1 = StringUtils.split(name, "|");
  
      name = n1[0].replace("[", "");
      name = name.replace("]", "");
  
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());      
    }
    
    return null;
  }

  public static String infoboxMisure(String name)
  {
    try
    {
      name = name.replace("\n", "");
  
      if (name.contains("|"))
      {
        String[] n1 = StringUtils.split(name, "|");
        name = n1[1].replace(" ", "");
      }
      
      return name;
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());      
    }
    
    return null;
  }

  public static String[] infoboxRestingPlaceCoordinates(String coor)
  {
    try
    {
      return StringUtils.split(coor, "|");
    }
    catch (Exception e)
    {
      logger.error("Error parsing ReligiousBuilding infobox: " + e.getMessage());      
    }
    
    return null;
  }
}
