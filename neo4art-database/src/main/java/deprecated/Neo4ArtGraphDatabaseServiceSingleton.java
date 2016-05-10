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

package deprecated;

import java.io.File;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * @author Lorenzo Speranzoni
 * @since 29 Mar 2015
 */
@Deprecated
public class Neo4ArtGraphDatabaseServiceSingleton extends Neo4ArtGraphDatabase
{
  private static GraphDatabaseService graphDatabaseServiceInstance;
  private static Transaction          transactionInstance;

  public static void newInstance()
  {
    if (graphDatabaseServiceInstance == null)
    {
      graphDatabaseServiceInstance = new GraphDatabaseFactory().newEmbeddedDatabase(new File(NEO4J_STORE_DIR));

      registerShutdownHook(graphDatabaseServiceInstance);
    }
  }

  /**
   * 
   * @param graphDb
   */
  private static void registerShutdownHook(final GraphDatabaseService graphDb)
  {
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      @Override
      public void run()
      {
        if (graphDb != null && graphDb.isAvailable(1000))
        {
          graphDb.shutdown();
        }
      }
    });
  }

  /**
   * @return
   */
  public static void beginTransaction()
  {
    if (transactionInstance == null)
    {
      transactionInstance = getGraphDatabaseService().beginTx();
    }
  }

  /**
   * @param tx
   */
  public static void commitTransaction()
  {
    if (transactionInstance != null)
    {
      transactionInstance.success();
      transactionInstance.close();

      transactionInstance = null;
    }
  }

  /**
   * @param tx
   */
  public static void rollbackTransaction()
  {
    if (transactionInstance != null)
    {
      transactionInstance.failure();
      transactionInstance.close();

      transactionInstance = null;
    }
  }

  /**
   * 
   */
  public static void shutdownInstance()
  {
    if (graphDatabaseServiceInstance != null)
    {
      rollbackTransaction();

      graphDatabaseServiceInstance.shutdown();

      graphDatabaseServiceInstance = null;
    }
  }

  /**
   * 
   * @param node
   * @param labels
   * @return
   */
  public static long createNode(org.neo4art.graphdb.Node node)
  {
    GraphDatabaseService graphDatabaseService = getGraphDatabaseService();

    Node newNode = graphDatabaseService.createNode(node.getLabels());

    for (String key : node.getProperties().keySet())
    {
      newNode.setProperty(key, node.getProperties().get(key));
    }

    return newNode.getId();
  }

  /**
   * 
   * @param properties
   * @param labels
   * @return
   */
  public static long createRelationship(long startNodeId, long endNodeId, RelationshipType relationshipType, Map<String, Object> properties)
  {
    GraphDatabaseService graphDatabaseService = getGraphDatabaseService();

    Node startNode = graphDatabaseService.getNodeById(startNodeId);
    Node endNode = graphDatabaseService.getNodeById(endNodeId);

    Relationship relationship = startNode.createRelationshipTo(endNode, relationshipType);

    for (String key : properties.keySet())
    {
      relationship.setProperty(key, properties.get(key));
    }

    return relationship.getId();
  }

  /**
   * 
   * @return
   */
  public static GraphDatabaseService getGraphDatabaseService()
  {
    if (graphDatabaseServiceInstance == null)
    {
      newInstance();
    }

    return graphDatabaseServiceInstance;
  }
}
