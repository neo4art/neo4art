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
package org.neo4art.api.builder.mock.searchresult;

import java.util.ArrayList;
import java.util.List;

import org.neo4art.api.domain.Link;
import org.neo4art.api.domain.Node;
import org.neo4art.api.domain.SearchResult;

/**
 * @author Enrico De Benetti
 * @since 29 Apr 2015
 *
 */
public class BuildSearchResultMock {

	
	public SearchResult getSearchResult(){
		
		SearchResult searchResult = new SearchResult();
        searchResult.setLinkList(getLinkList());
        searchResult.setNodeList(getNodeList());
		
	  return searchResult;	
	}
	
    public SearchResult getDetailSearchNode(){
		
		SearchResult searchResult = new SearchResult();
        searchResult.setLinkList(getDetailLinkList());
        searchResult.setNodeList(getDetailNodeList());
		
	  return searchResult;	
	}
	
	
	public static List<Node> getNodeList(){
		
		List<Node> nodeList = new ArrayList<Node>();
		
		Node nodo1 = new Node();
		nodo1.setId(0);
		nodo1.setName("Van Gogh");
		nodo1.setThumbnail("");
		nodo1.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo2 = new Node();
		nodo2.setId(1);
		nodo2.setName("Water Mill at Gennep I");
		nodo2.setThumbnail("http://www.vggallery.com/painting/f_0046.jpg");
		nodo2.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo3 = new Node();
		nodo3.setId(2);
		nodo3.setName("Village at Sunset");
		nodo3.setThumbnail("http://www.vggallery.com/painting/f_0190.jpg");
		nodo3.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo4 = new Node();
		nodo4.setId(3);
		nodo4.setName("red");
		nodo4.setThumbnail("");
		nodo4.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo5 = new Node();
		nodo5.setId(4);
		nodo5.setName("good");
		nodo5.setThumbnail("");
		nodo5.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo5.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo6 = new Node();
		nodo6.setId(5);
		nodo6.setName("Weaver, Interior with Three Small Windows");
		nodo6.setThumbnail("http://www.vggallery.com/painting/f_0037.jpg");
		nodo6.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo6.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo7 = new Node();
		nodo7.setId(6);
		nodo7.setName("Van Gogh Museum");
		nodo7.setThumbnail("");
		nodo7.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo7.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo8 = new Node();
		nodo8.setId(7);
		nodo8.setName("Head of a Man I");
		nodo8.setThumbnail("http://www.vggallery.com/painting/f_0179r.jpg");
		nodo8.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo8.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo9 = new Node();
		nodo9.setId(8);
		nodo9.setName("The Potato Eaters I");
		nodo9.setThumbnail("http://www.vggallery.com/painting/f_0082.jpg");
		nodo9.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo9.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo10 = new Node();
		nodo10.setId(9);
		nodo10.setName("Landscape with Pollard Willows");
		nodo10.setThumbnail("http://www.vggallery.com/painting/f_0031.jpg");
		nodo10.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo10.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		nodeList.add(nodo1);
		nodeList.add(nodo2);
		nodeList.add(nodo3);
		nodeList.add(nodo4);
		nodeList.add(nodo5);
		nodeList.add(nodo6);
		nodeList.add(nodo7);
		nodeList.add(nodo8);
		nodeList.add(nodo9);
		nodeList.add(nodo10);
		 
	  return nodeList;
	}
	
	public static List<Link> getLinkList(){
		
	 List<Link> linkList = new ArrayList<Link>();
	
	 Link link1 = new Link();
	 link1.setLinkName("has painted");
	 link1.setSource(1);
	 link1.setTarget(0);
	 
	 
	 Link link2 = new Link();
	 link2.setLinkName("has painted");
	 link2.setSource(2);
	 link2.setTarget(0);
	 
	 Link link3 = new Link();
	 link3.setLinkName("has painted");
	 link3.setSource(3);
	 link3.setTarget(0);
	 
	 Link link4 = new Link();
	 link4.setLinkName("has painted");
	 link4.setSource(4);
	 link4.setTarget(0);
	 
	 Link link5 = new Link();
	 link5.setLinkName("has painted");
	 link5.setSource(5);
	 link5.setTarget(0);
	 
	 Link link6 = new Link();
	 link6.setLinkName("has painted");
	 link6.setSource(6);
	 link6.setTarget(7);
	 
	 Link link7 = new Link();
	 link7.setLinkName("has painted");
	 link7.setSource(9);
	 link7.setTarget(7);
	 
	 Link link8 = new Link();
	 link8.setLinkName("has painted");
	 link8.setSource(5);
	 link8.setTarget(9);
	 
	 Link link9 = new Link();
	 link9.setLinkName("has painted");
	 link9.setSource(4);
	 link9.setTarget(5);
	 
	 Link link10 = new Link();
	 link10.setLinkName("has painted");
	 link10.setSource(3);
	 link10.setTarget(5);
	 
	 Link link11 = new Link();
	 link11.setLinkName("has painted");
	 link11.setSource(7);
	 link11.setTarget(5);
	 
	 Link link12 = new Link();
	 link12.setLinkName("has painted");
	 link12.setSource(1);
	 link12.setTarget(3);
	 
	 Link link13 = new Link();
	 link13.setLinkName("has painted");
	 link13.setSource(2);
	 link13.setTarget(3);
	 
	 Link link14 = new Link();
	 link14.setLinkName("has painted");
	 link14.setSource(9);
	 link14.setTarget(3);
	 
	 Link link15 = new Link();
	 link15.setLinkName("has painted");
	 link15.setSource(8);
	 link15.setTarget(3);
	 
	 Link link16 = new Link();
	 link16.setLinkName("has painted");
	 link16.setSource(9);
	 link16.setTarget(0);
	 
	 Link link17 = new Link();
	 link17.setLinkName("has painted");
	 link17.setSource(9);
	 link17.setTarget(2);
	 
	 Link link18 = new Link();
	 link18.setLinkName("has painted");
	 link18.setSource(6);
	 link18.setTarget(9);
	 
	 Link link19 = new Link();
	 link19.setLinkName("has painted");
	 link19.setSource(7);
	 link19.setTarget(9);
	 
	 Link link20 = new Link();
	 link20.setLinkName("has painted");
	 link20.setSource(8);
	 link20.setTarget(9);
	 
	 linkList.add(link1);
	 linkList.add(link2);
	 linkList.add(link3);
	 linkList.add(link4);
	 linkList.add(link5);
	 linkList.add(link6);
	 linkList.add(link7);
	 linkList.add(link8);
	 linkList.add(link9);
	 linkList.add(link10);
	 linkList.add(link11);
	 linkList.add(link12);
	 linkList.add(link13);
	 linkList.add(link14);
	 linkList.add(link15);
	 linkList.add(link16);
	 linkList.add(link17);
	 linkList.add(link18);
	 linkList.add(link19);
	 linkList.add(link20);
	 
     return linkList;		 
	}
	
     public static List<Node> getDetailNodeList(){
		
		List<Node> nodeList = new ArrayList<Node>();
		
		Node nodo1 = new Node();
		nodo1.setId(10);
		nodo1.setName("Garden in Auvers");
		nodo1.setThumbnail("http://www.vggallery.com/painting/f_0814.jpg");
		nodo1.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo2 = new Node();
		nodo2.setId(11);
		nodo2.setName("Plaster Statuette of a Female Torso VIII");
		nodo2.setThumbnail("http://www.vggallery.com/painting/f_0216.jpg");
		nodo2.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo3 = new Node();
		nodo3.setId(12);
		nodo3.setName("Portrait of PÃ¨re Tanguy I");
		nodo3.setThumbnail("http://www.vggallery.com/painting/f_0364.jpg");
		nodo3.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo4 = new Node();
		nodo4.setId(13);
		nodo4.setName("Still Life with Meadow Flowers and Roses");
		nodo4.setThumbnail("http://www.vggallery.com/painting/f_0278.jpg");
		nodo4.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
				
		Node nodo5 = new Node();
		nodo5.setId(14);
		nodo5.setName("Portrait of a Man with a Skull Cap");
		nodo5.setThumbnail("http://www.vggallery.com/painting/f_0289.jpg");
		nodo5.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo5.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo6 = new Node();
		nodo6.setId(15);
		nodo6.setName("Head of a Woman VIII");
		nodo6.setThumbnail("http://www.vggallery.com/painting/f_0156.jpg");
		nodo6.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo6.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo7 = new Node();
		nodo7.setId(16);
		nodo7.setName("Thatched Cottages in Jorgus");
		nodo7.setThumbnail("http://www.vggallery.com/painting/f_0758.jpg");
		nodo7.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo7.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo8 = new Node();
		nodo8.setId(17);
		nodo8.setName("Still Life: Glass with Carnations");
		nodo8.setThumbnail("http://www.vggallery.com/painting/f_0598.jpg");
		nodo8.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo8.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo9 = new Node();
		nodo9.setId(18);
		nodo9.setName("Portrait of Adeline Ravoux II");
		nodo9.setThumbnail("http://www.vggallery.com/painting/f_0768.jpg");
		nodo9.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo9.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		Node nodo10 = new Node();
		nodo10.setId(19);
		nodo10.setName("The Fields");
		nodo10.setThumbnail("http://www.vggallery.com/painting/f_0761.jpg");
		nodo10.setLink("http://en.wikipedia.org/wiki/Vincent_van_Gogh");
		nodo10.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales malesuada enim, et eleifend justo venenatis sit amet. Nam sem libero, lacinia id lorem eget, tincidunt aliquam ligula. Duis tempor ligula et venenatis malesuada. Proin pulvinar eget sem eu sagittis. Nunc diam ipsum, vestibulum nec feugiat et, dapibus eu erat. Nulla nunc orci, iaculis eget placerat quis, scelerisque at dui. Vivamus cursus dolor pharetra dignissim consectetur. Suspendisse rhoncus mi at nisi interdum aliquam. Etiam magna ante, auctor sed imperdiet nec, cursus sit amet diam. Vivamus ante ante, bibendum et varius a, sagittis a eros. Maecenas imperdiet sed lectus lacinia vulputate. Nunc turpis eros, eleifend maximus enim at, vehicula elementum diam.");
		
		nodeList.add(nodo1);
		nodeList.add(nodo2);
		nodeList.add(nodo3);
		nodeList.add(nodo4);
		nodeList.add(nodo5);
		nodeList.add(nodo6);
		nodeList.add(nodo7);
		nodeList.add(nodo8);
		nodeList.add(nodo9);
		nodeList.add(nodo10);
		 
	  return nodeList;
	}
	
	public static List<Link> getDetailLinkList(){
		
	 List<Link> linkList = new ArrayList<Link>();
	
	 Link link1 = new Link();
	 link1.setLinkName("has painted");
	 link1.setSource(11);
	 link1.setTarget(10);
	 
	 Link link2 = new Link();
	 link2.setLinkName("has painted");
	 link2.setSource(12);
	 link2.setTarget(10);
	 
	 Link link3 = new Link();
	 link3.setLinkName("has painted");
	 link3.setSource(13);
	 link3.setTarget(10);
	 
	 Link link4 = new Link();
	 link4.setLinkName("has painted");
	 link4.setSource(14);
	 link4.setTarget(10);
	 
	 Link link5 = new Link();
	 link5.setLinkName("has painted");
	 link5.setSource(15);
	 link5.setTarget(10);
	 
	 Link link6 = new Link();
	 link6.setLinkName("has painted");
	 link6.setSource(16);
	 link6.setTarget(17);
	 
	 Link link7 = new Link();
	 link7.setLinkName("has painted");
	 link7.setSource(19);
	 link7.setTarget(17);
	 
	 Link link8 = new Link();
	 link8.setLinkName("has painted");
	 link8.setSource(15);
	 link8.setTarget(19);
	 
	 Link link9 = new Link();
	 link9.setLinkName("has painted");
	 link9.setSource(14);
	 link9.setTarget(15);
	 
	 Link link10 = new Link();
	 link10.setLinkName("has painted");
	 link10.setSource(13);
	 link10.setTarget(15);
	 
	 Link link11 = new Link();
	 link11.setLinkName("has painted");
	 link11.setSource(17);
	 link11.setTarget(15);
	 
	 Link link12 = new Link();
	 link12.setLinkName("has painted");
	 link12.setSource(11);
	 link12.setTarget(13);
	 
	 Link link13 = new Link();
	 link13.setLinkName("has painted");
	 link13.setSource(12);
	 link13.setTarget(13);
	 
	 Link link14 = new Link();
	 link14.setLinkName("has painted");
	 link14.setSource(19);
	 link14.setTarget(13);
	 
	 Link link15 = new Link();
	 link15.setLinkName("has painted");
	 link15.setSource(18);
	 link15.setTarget(13);
	 
	 Link link16 = new Link();
	 link16.setLinkName("has painted");
	 link16.setSource(19);
	 link16.setTarget(10);
	 
	 Link link17 = new Link();
	 link17.setLinkName("has painted");
	 link17.setSource(19);
	 link17.setTarget(12);
	 
	 Link link18 = new Link();
	 link18.setLinkName("has painted");
	 link18.setSource(16);
	 link18.setTarget(19);
	 
	 Link link19 = new Link();
	 link19.setLinkName("has painted");
	 link19.setSource(17);
	 link19.setTarget(19);
	 
	 Link link20 = new Link();
	 link20.setLinkName("has painted");
	 link20.setSource(18);
	 link20.setTarget(19);
	 
	 linkList.add(link1);
	 linkList.add(link2);
	 linkList.add(link3);
	 linkList.add(link4);
	 linkList.add(link5);
	 linkList.add(link6);
	 linkList.add(link7);
	 linkList.add(link8);
	 linkList.add(link9);
	 linkList.add(link10);
	 linkList.add(link11);
	 linkList.add(link12);
	 linkList.add(link13);
	 linkList.add(link14);
	 linkList.add(link15);
	 linkList.add(link16);
	 linkList.add(link17);
	 linkList.add(link18);
	 linkList.add(link19);
	 linkList.add(link20);
	 
     return linkList;		 
	}
	
}
