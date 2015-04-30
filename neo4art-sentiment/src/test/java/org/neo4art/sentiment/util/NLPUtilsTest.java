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

package org.neo4art.sentiment.util;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Lorenzo Speranzoni
 * @since 17 Apr 2015
 */
public class NLPUtilsTest
{
  @Test
  public void shouldNLP()
  {
    try
    {
      TokenizerME tokenizerME = new TokenizerME(new TokenizerModel(new ClassPathResource("opennlp-models/en/en-token.bin").getInputStream()));

      String[] tokens001 = tokenizerME.tokenize("I love Vincent Van Gogh");

      Assert.assertEquals("I"      , tokens001[0]);
      Assert.assertEquals("love"   , tokens001[1]);
      Assert.assertEquals("Vincent", tokens001[2]);
      Assert.assertEquals("Van"    , tokens001[3]);
      Assert.assertEquals("Gogh"   , tokens001[4]);
      
      String[] tokens002 = tokenizerME.tokenize("He's so amazing");
      
      Assert.assertEquals("He"     , tokens002[0]);
      Assert.assertEquals("'s"     , tokens002[1]);
      Assert.assertEquals("so"     , tokens002[2]);
      Assert.assertEquals("amazing", tokens002[3]);
    }
    catch (Exception e)
    {
      e.printStackTrace();

      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void shouldDetectPunctuationSymbols()
  {
    try
    {
      for (String punctuationSymbol : DictionaryUtils.PUNCTUATION_SYMBOLS)
      {
        Assert.assertTrue(DictionaryUtils.isPunctuation(punctuationSymbol));
      }
      
      Assert.assertFalse(DictionaryUtils.isPunctuation("Vincent"));
      Assert.assertFalse(DictionaryUtils.isPunctuation("Van"));
      Assert.assertFalse(DictionaryUtils.isPunctuation("Gogh"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      
      Assert.fail(e.getMessage());
    }
  }
}
