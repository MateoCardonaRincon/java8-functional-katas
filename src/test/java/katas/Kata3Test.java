package katas;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata3Test {

    @Test
    public void testExecute() {
        List<Integer> result= Kata3.execute();
        String typeName = result.get(0).getClass().getTypeName();

        Assert.assertThat(result.size(), equalTo(4));
        Assert.assertThat(typeName, equalTo("java.lang.Integer"));
    }
}
