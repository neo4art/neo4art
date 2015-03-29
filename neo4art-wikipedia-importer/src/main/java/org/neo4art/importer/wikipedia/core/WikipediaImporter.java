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
package org.neo4art.importer.wikipedia.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * This interfare represents the point for this library.
 * 
 * It imports into neo4j the provided wikipedia dump file (both in xml and gzip as well as bz2). 
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
public interface WikipediaImporter {
	
	long importOrUpdateDump(File dumpFile) throws IOException, SAXException, ParserConfigurationException;
}
