import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static File dataFile = new File(System.getProperty("user.dir")+"/src/main/resources/currencyData.xml");

    public static void updateData(){
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(dataFile))) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            StringBuilder urlContent = new StringBuilder();

            while ((line=bufferedReader.readLine())!=null)
                urlContent.append(line).append("\n");

            printWriter.write(urlContent.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Map<String, Double> parse(){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(dataFile);
            NodeList nodeList = document.getElementsByTagName("Cube");
            Map<String, Double> resultMap = new HashMap<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                if(nodeList.item(i).getAttributes().getLength()==2){
                    String currencyCode = nodeList.item(i).getAttributes().item(0).getTextContent();
                    Double exchangeRate = Double.parseDouble(nodeList.item(i).getAttributes().item(1).getTextContent());
                    resultMap.put(currencyCode, exchangeRate);
                }
            }

            return  resultMap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setDataFile(File dataFile) {
        Parser.dataFile = dataFile;
    }

}
