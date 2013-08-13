/*
 * Copyright 2013 Basho Technologies Inc.
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
package com.basho.riak.client.query.indexes;

import com.basho.riak.client.query.RiakObject;
import com.basho.riak.client.util.ByteArrayWrapper;
import java.nio.charset.Charset;

/**
 * An index using Strings
 * <p>
 * Data in Riak including secondary indexes is stored as bytes. This implementation 
 * of {@code RiakIndex} provides access to those bytes by converting to 
 * and from {@code String} values.  
 * </p>
 * <p>
 * Static factory methods {@link StringBinIndex#named(java.lang.String) } and 
 * {@link StringBinIndex#StringBinIndex(java.lang.String, java.nio.charset.Charset) }
 * are provided to create instances of this index and provide the appropriate {@link IndexType#BIN} type.
 * A fluent interface is then provided for adding values:
 * <pre>
 * {@code
 * StringBinIndex index = StringBinIndex.named("my_colors")
 *                                  .add("red")
 *                                  .add("blue");
 * riakObject.getIndexes().add(index);
 * }
 * </pre>
 * </p>
 * @author Brian Roach <roach at basho dot com>
 * @since 2.0
 * @see RiakIndexes
 * @see RiakObject
 */
public class StringBinIndex extends RiakIndex<String>
{
    private final Charset charset;
    
    private StringBinIndex(Name name)
    {
        super(name);
        this.charset = name.charset;
    }
    
    /**
     * Static factory method returning a StringBinIndex builder.
     * <p>
     * Values will be converted to/from bytes using the default {@code Charset}
     * <p>
     * @param name the name for this index
     * @return a {@code StringBinIndex#Named } with the provided name and {@link IndexType#BIN} type.
     */
    public static StringBinIndex named(String name)
    {
        return new Name(name).createIndex();
    }
    
    /**
     * Static factory method that returns a StringBinIndex Name.
     * <p>
     * Values will be converted to/from bytes using the provided {@code Charset}
     * <p>
     * @param name the name for this index
     * @return a {@link Builder} with the provided name and {@link IndexType#BIN} type.
     */
    public static StringBinIndex named(String name, Charset charset)
    {
        return new Name(name, charset).createIndex();
    }
    
    @Override
    protected ByteArrayWrapper convert(String value)
    {
        return ByteArrayWrapper.unsafeCreate(value.getBytes(charset));
    }

    @Override
    protected String convert(ByteArrayWrapper value)
    {
        return value.toString(charset);
    }
    
    public static class Name extends RiakIndex.Name<String>
    {
        private final Charset charset;
        
        public Name(String name)
        {
            this(name, Charset.defaultCharset());
        }
        
        public Name(String name, Charset charset)
        {
            super(name, IndexType.BIN);
            this.charset = charset;
        }
        
        @Override
        public StringBinIndex createIndex()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    
}
