package katas;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Kata1Test {

    @Test
    public void testExecute() {
        List<Map> result = Kata1.execute();
        String typeName = result.get(0).getClass().getTypeName();

        Set keySet = result.get(0).keySet();

        Assert.assertThat(result.size(), equalTo(4));
        Assert.assertThat(typeName, equalTo("com.google.common.collect.RegularImmutableMap"));
        Assert.assertThat(keySet.size(), equalTo(2));
    }
}
