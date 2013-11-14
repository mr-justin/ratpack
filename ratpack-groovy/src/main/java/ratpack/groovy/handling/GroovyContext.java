/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.groovy.handling;

import groovy.lang.Closure;
import ratpack.handling.Context;

import static ratpack.groovy.block.GroovyBlocking.SuccessOrError;

/**
 * Subclass of {@link ratpack.handling.Context} that adds Groovy friendly variants of methods.
 */
public interface GroovyContext extends Context {

  /**
   * Used to perform a blocking operation <b>off</b> the request thread.
   * <p>
   * See {@link ratpack.groovy.block.GroovyBlocking} for details.
   * <p>
   * Example usage:
   * <pre class="groovy-chain-dsl">
   * get("some/path") {
   *   blocking {
   *    // a blocking operation
   *    2 // the result of the blocking operation
   *   }.then { Integer result ->
   *     render result.toString()
   *   }
   * }
   * </pre>
   * With error handling:
   * <pre class="groovy-chain-dsl">
   * get("some/path") {
   *   blocking {
   *     // a blocking operation
   *     2
   *   }.onError { Exception exception ->
   *     // do something with the exception
   *   }.then { Integer result ->
   *     getResponse().send result.toString()
   *   }
   * }
   * </pre>
   *
   * @param operation A closure that performs the blocking operation, returning a result
   * @param <T> The type of the result
   * @return An object
   */
  <T> SuccessOrError<T> blocking(Closure<T> operation);

}