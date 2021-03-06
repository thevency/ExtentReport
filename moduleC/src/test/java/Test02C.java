import com.report.core.Listen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test02C extends Listen {

    @Test
    public void testcase1(){
        int c = MathC.sum(2, 7);
        Assert.assertEquals(c, 9);
        System.out.println("... running testcase 1 ..... in module B");

    }
    @Test
    public void testcase2() {
        int c = MathC.sum(2, 10);
        Assert.assertEquals(c, 12);
        System.out.println("... running testcase 2 ..... in module B");
    }

    @Test
    public void testcase3() {
        int c = MathC.sum(2, 7);
        Assert.assertEquals(c, 9);
        System.out.println("... running testcase 3 ..... in module B");
    }


}
