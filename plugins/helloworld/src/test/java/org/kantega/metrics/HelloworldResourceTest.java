package org.kantega.metrics;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class HelloworldResourceTest {

    @Test
    public void shouldReturnHelloworld() {
        assertThat(new HelloworldResource().hello().getMessage(), is("Hello world"));
    }
}
