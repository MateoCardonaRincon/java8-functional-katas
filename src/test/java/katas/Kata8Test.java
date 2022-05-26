package katas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata8Test {

    @Test
    public void testExecute() {

        List<Map> result = Kata8.execute();
        String typeName = result.get(0).getClass().getTypeName();

        Set keySet = result.get(0).keySet();

        Assert.assertThat(result.size(), equalTo(3));
        Assert.assertThat(typeName, equalTo("com.google.common.collect.RegularImmutableMap"));
        Assert.assertThat(keySet.size(), equalTo(2));
    }
}
