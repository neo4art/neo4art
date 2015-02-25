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
package org.neo4art.importer.wikipedia.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Spring configuration class for the database.
 * 
 * It creates the {@link DataSource} for connecting to neo4j.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@Configuration
public class DatabaseConfiguration {

	public static final String NEO4J_URL = System.getProperty("NEO4J_URL","jdbc:neo4j://localhost:7474");
	public static final String NEO4J_USR = System.getProperty("NEO4J_USR","neo4j");
	public static final String NEO4J_PWD = System.getProperty("NEO4J_PWD","neo4art");
	
	@Bean
	public DataSource datasource() {
		return new DriverManagerDataSource(NEO4J_URL, NEO4J_USR, NEO4J_PWD);
	}
}
