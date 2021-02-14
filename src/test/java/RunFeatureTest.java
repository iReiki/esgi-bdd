import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/fr/esgi/wallet/features"},
        glue = {"fr.esgi.wallet.features"},
        tags = "not @wip"
)
public class RunFeatureTest {
}