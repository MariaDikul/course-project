package core;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.core.PortLookup;

import java.io.IOException;

public class PortLookupTests {
    static PortLookup portLookup;

    public PortLookupTests() throws IOException {
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        portLookup = new PortLookup();
    }

    @Test
    public void testPortLookup() {
        Assertions.assertEquals(8088, PortLookup.portLookup());
    }

}
