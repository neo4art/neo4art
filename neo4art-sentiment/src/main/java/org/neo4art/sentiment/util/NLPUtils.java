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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.springframework.core.io.ClassPathResource;

/**
 * @author Lorenzo Speranzoni
 * @since 13 Apr 2015
 */
public class NLPUtils
{
  /**
   * Train and return an instance of a <a href="http://opennlp.apache.org/">OpenNLP</a> {@link SentenceDetectorME} 
   * 
   * @param locale
   * @return
   */
  public static SentenceDetectorME getSentenceDetectorME(Locale locale)
  {
    SentenceDetectorME sentenceDetectorME = null;

    InputStream modelInputStream = null;

    try
    {
      modelInputStream = new ClassPathResource("opennlp-models" + File.separator + locale.getLanguage() + File.separator + "en-sent.bin").getInputStream();

      sentenceDetectorME = new SentenceDetectorME(new SentenceModel(modelInputStream));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (modelInputStream != null)
      {
        try
        {
          modelInputStream.close();
        }
        catch (IOException e)
        {
        }
      }
    }

    return sentenceDetectorME;
  }

  /**
   * Train and return an instance of a <a href="http://opennlp.apache.org/">OpenNLP</a> {@link TokenizerME}
   * 
   * @param locale
   * @return
   */
  public static TokenizerME getSentenceTokenizerME(Locale locale)
  {
    TokenizerME tokenizerME = null;

    InputStream modelInputStream = null;

    try
    {
      modelInputStream = new ClassPathResource("opennlp-models" + File.separator + locale.getLanguage() + File.separator + "en-token.bin").getInputStream();

      tokenizerME = new TokenizerME(new TokenizerModel(modelInputStream));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (modelInputStream != null)
      {
        try
        {
          modelInputStream.close();
        }
        catch (IOException e)
        {
        }
      }
    }

    return tokenizerME;
  }

  /**
   * Train and return an instance of a <a href="http://opennlp.apache.org/">OpenNLP</a> {@link Parser}
   * 
   * @param locale
   * @return
   */
  public static Parser getSentenceParser(Locale locale)
  {
    Parser parser = null;

    InputStream modelInputStream = null;

    try
    {
      modelInputStream = new ClassPathResource("opennlp-models" + File.separator + locale.getLanguage() + File.separator + "en-parser-chunking.bin").getInputStream();

      ParserModel model = new ParserModel(modelInputStream);

      parser = ParserFactory.create(model);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (modelInputStream != null)
      {
        try
        {
          modelInputStream.close();
        }
        catch (IOException e)
        {
        }
      }
    }

    return parser;
  }

  /**
   * 
   * @param locale
   * @return
   */
  public static POSTaggerME getPOSTaggerME(Locale locale)
  {
    POSTaggerME posTaggerME = null;

    InputStream modelInputStream = null;

    try
    {
      modelInputStream = new ClassPathResource("opennlp-models" + File.separator + locale.getLanguage() + File.separator + "en-pos-maxent.bin").getInputStream();

      POSModel model = new POSModel(modelInputStream);

      posTaggerME = new POSTaggerME(model);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (modelInputStream != null)
      {
        try
        {
          modelInputStream.close();
        }
        catch (IOException e)
        {
        }
      }
    }

    return posTaggerME;
  }
}
