import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final File dataFile = new File(System.getProperty("user.dir")+"/src/test/resources/currencyData.xml");

    @Test
    void updateDataTest(){

        Parser.setDataFile(dataFile);
        Parser.updateData();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile));

            StringBuilder dataFileContent = new StringBuilder();
            StringBuilder urlContent = new StringBuilder();

            String line;

            while((line = bufferedReader.readLine()) != null)
                dataFileContent.append(line).append("\n");

            bufferedReader = new BufferedReader(new InputStreamReader(new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml").openStream()));

            while((line = bufferedReader.readLine()) != null)
                urlContent.append(line).append("\n");

            assertEquals(dataFileContent.toString(), urlContent.toString());

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }


    @Test
    void parseTest(){
        Map<String, Double> exchangeRateMap = Parser.parse();

        assertNull(exchangeRateMap.get("abc"));
        assertNotNull(exchangeRateMap.get("ISK"));
    }

}