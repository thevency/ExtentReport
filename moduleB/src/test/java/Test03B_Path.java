import com.report.core.Listen;
import com.report.core.SetPath;
import org.testng.annotations.Test;

public class Test03B_Path extends Listen {

    @Test
    public void testCurrentPathB(){
        System.out.println("======= Test03B_Path =======");
        SetPath.getCurrentDir("..\\framework\\src\\main\\resources\\picture.jpg");
    }
}
