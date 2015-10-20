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

package org.neo4art.graphdb.transaction;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Lock;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Transaction;

/**
 * @author Lorenzo Speranzoni
 * @since 20 Oct 2015
 */
public class TransactionWrapper implements GraphDatabaseTransaction {

  private Transaction wrappedTransaction;

  public TransactionWrapper(GraphDatabaseService graphDatabaseService) {
    this.wrappedTransaction = graphDatabaseService.beginTx();
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#terminate()
   */
  @Override
  public void terminate() {
    this.wrappedTransaction.terminate();
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#failure()
   */
  @Override
  public void failure() {
    this.wrappedTransaction.failure();
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#success()
   */
  @Override
  public void success() {
    this.wrappedTransaction.success();
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#close()
   */
  @Override
  public void close() {
    this.wrappedTransaction.close();
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#acquireWriteLock(org.neo4j.graphdb.PropertyContainer)
   */
  @Override
  public Lock acquireWriteLock(PropertyContainer entity) {
    return this.wrappedTransaction.acquireWriteLock(entity);
  }

  /**
   * @see org.neo4art.graphdb.transaction.GraphDatabaseTransaction#acquireReadLock(org.neo4j.graphdb.PropertyContainer)
   */
  @Override
  public Lock acquireReadLock(PropertyContainer entity) {
    return this.wrappedTransaction.acquireReadLock(entity);
  }

}
