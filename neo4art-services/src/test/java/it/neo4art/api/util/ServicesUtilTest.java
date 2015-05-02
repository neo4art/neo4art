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
package it.neo4art.api.util;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.neo4art.api.util.ServicesUtil;
import org.neo4art.domain.Artwork;

/**
 * @author Enrico De Benetti
 * @since 02 Mag 2015
 *
 */
public class ServicesUtilTest {

  @Test
  public void testDateFormatCompletionDate(){
	  
	Calendar calendar = Calendar.getInstance();  
	calendar.set(Calendar.YEAR, 1883);  
	calendar.set(Calendar.MONTH, 8);  
	calendar.set(Calendar.DAY_OF_MONTH, 14);  
	calendar.set(Calendar.HOUR,12);
	calendar.set(Calendar.MINUTE,0);
	
	ServicesUtil serviceUtil = ServicesUtil.getInstance();
	Artwork artwork = new Artwork();
	artwork.setCompletionDate(calendar.getTime());
	String verifyArtworkDate = serviceUtil.verifyArtworkDate(artwork);
	
	Assert.assertEquals("15-Sep-1883 00:00", verifyArtworkDate);
  }
	
}
