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

package other;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Lorenzo Speranzoni
 * @since 1 Nov 2015
 */
public class WikipediaImageURLDecoderTest {

  @Test
  public void shouldComputeWikipediaImageURL() {

    try {

      String imageName = "Spelterini_Blüemlisalp.jpg";
      
      Assert.assertEquals("ae1a26d34d6a674d4400c8a1e6fe73f8", encodeAsMD5(imageName));

      String actual = toWikipediaImageURL(imageName);
      
      String expected = "http://upload.wikimedia.org/wikipedia/commons/a/ae/Spelterini_Bl%C3%BCemlisalp.jpg";
      
      Assert.assertEquals(expected, actual); 
    }
    catch (Exception e) {

      e.printStackTrace();

      Assert.fail();
    }
  }

  private String toWikipediaImageURL(String imageName) throws Exception {
    
    String encodedAsMD5 = encodeAsMD5(imageName);
    String encodeAsURL = URLEncoder.encode(imageName, "UTF-8");
    
    return "http://upload.wikimedia.org/wikipedia/commons/" + encodedAsMD5.charAt(0) + "/" + encodedAsMD5.charAt(0) + encodedAsMD5.charAt(1) + "/" + encodeAsURL;
  }

  private String encodeAsMD5(String imageName) throws NoSuchAlgorithmException {
    
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    byte[] digest = messageDigest.digest(imageName.getBytes());
    BigInteger bigInt = new BigInteger(1, digest);
    String hashtext = bigInt.toString(16);
    while (hashtext.length() < 32) {
      hashtext = "0" + hashtext;
    }
    return hashtext;
  }
}
