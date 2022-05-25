package katas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata2Test {

    @Test
    public void testExecute() {
        List<Integer> result= Kata2.execute();
        String typeName = result.get(0).getClass().getTypeName();

        Assert.assertThat(result.size(), equalTo(2));
        Assert.assertThat(typeName, equalTo("java.lang.Integer"));
    }
}
