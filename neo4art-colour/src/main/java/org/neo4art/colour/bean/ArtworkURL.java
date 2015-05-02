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

package org.neo4art.colour.bean;

import java.net.URL;

import org.neo4art.domain.Artwork;

/**
 * @author Lorenzo Speranzoni
 * @since 2 May 2015
 */
public class ArtworkURL
{
  private Artwork artwork;
  
  private URL url;

  public ArtworkURL()
  {
  }
  
  public ArtworkURL(Artwork artwork, URL url)
  {
    this.artwork = artwork;
    this.url = url;
  }

  public Artwork getArtwork()
  {
    return artwork;
  }

  public void setArtwork(Artwork artwork)
  {
    this.artwork = artwork;
  }

  public URL getUrl()
  {
    return url;
  }

  public void setUrl(URL url)
  {
    this.url = url;
  }
  
  
}
