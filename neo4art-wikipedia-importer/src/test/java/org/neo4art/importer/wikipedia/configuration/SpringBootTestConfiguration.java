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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Spring boot configuration class for testing purpose.
 * 
 * @author Lorenzo Speranzoni
 * @since 25.02.2015
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.neo4art"})
@Import(DatabaseConfiguration.class)
public class SpringBootTestConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestConfiguration.class, args);
    }

}