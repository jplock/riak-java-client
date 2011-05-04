/*
 * This file is provided to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.basho.riak.client.raw.query;

/**
 * A Map Reduce Query run it via {@link IRiakClient#mapReduce(MapReduceSpec)}
 * 
 * @author russell
 * 
 */
public class MapReduceSpec {

    private final String mapReduceSpecJSON;

    /**
     * @param mapReduceSpecJSON
     */
    public MapReduceSpec(String mapReduceSpecJSON) {
        this.mapReduceSpecJSON = mapReduceSpecJSON;
    }

    public String getJSON() {
        return mapReduceSpecJSON;
    }

}
