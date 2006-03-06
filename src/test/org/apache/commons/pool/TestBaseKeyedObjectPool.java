/*
 * Copyright 1999-2006 The Apache Software Foundation.
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
package org.apache.commons.pool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Rodney Waldhoff
 * @author Sandy McArthur
 * @version $Revision$ $Date$
 */
public class TestBaseKeyedObjectPool extends TestCase {
    public TestBaseKeyedObjectPool(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestBaseKeyedObjectPool.class);
    }
    
    // tests
    public void testUnsupportedOperations() throws Exception {
        KeyedObjectPool pool = new BaseKeyedObjectPool() { 
            public Object borrowObject(Object key) throws Exception {
                return null;
            }
            public void returnObject(Object key, Object obj) throws Exception {                
            }
            public void invalidateObject(Object key, Object obj) throws Exception {                
            }            
        };   
        
        try {
            pool.addObject("key");
            fail("Expected UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            // expected
        }

        assertTrue("Negative expected.", pool.getNumIdle() < 0);
        assertTrue("Negative expected.", pool.getNumIdle("key") < 0);
        assertTrue("Negative expected.", pool.getNumActive() < 0);
        assertTrue("Negative expected.", pool.getNumActive("key") < 0);

        try {
            pool.clear();
            fail("Expected UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            // expected
        }

        try {
            pool.clear("key");
            fail("Expected UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            // expected
        }

        try {
            pool.setFactory(null);
            fail("Expected UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            // expected
        }

        pool.close(); // a no-op, probably should be remove

    }
}
