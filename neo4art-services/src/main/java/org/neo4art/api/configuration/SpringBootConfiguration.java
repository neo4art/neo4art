/**
 * Copyright 2015 the original author or authors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.neo4art.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration used to allow the war to be deployed in a Tomcat
 *
 * @author Gianmarco Laggia
 * @since 5 May 2016
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({ "org.neo4art.api" })
public class SpringBootConfiguration extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfiguration.class, args);
	}
}
