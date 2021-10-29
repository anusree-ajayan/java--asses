package xmltojson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	
    	String result = "";
    	String sum1 = "";
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		try {
    			DocumentBuilder builder = factory.newDocumentBuilder();
    			Document document = builder.parse("em.xml");

    			
    			document.getDocumentElement().normalize();
    			JSONObject obj= new JSONObject();
    			
    			NodeList departmentList=document.getElementsByTagName("department");
    			
    			for(int i=0; i<departmentList.getLength();i++) {
    				Node department = departmentList.item(i);
    				
    							
    				
    				
    				if(department.getNodeType()==Node.ELEMENT_NODE) {
    					Element deptElement = (Element) department;
    			
    					String id = deptElement.getAttribute("id");
    				
    					NodeList deptList = deptElement.getChildNodes();
    					
    					int sum=0;
    					
    					
    					for (int j=0;j<deptList.getLength();j++) {
    						Node employee = deptList.item(j);
    						
    						if(employee.getNodeType()==Node.ELEMENT_NODE) {
    							Element employeeElement = (Element) employee;
    							
    							sum+=Integer.parseInt(employeeElement.getElementsByTagName("salary").item(0).getTextContent());
    							

    							
    						}
    						}
    					 sum1=String.valueOf(sum);
    					 
    					 obj.put(deptElement.getAttribute("id"),sum); 
       					 System.out.println(obj.toString(4));
     					
       					 FileWriter file = new FileWriter("E:/output5.json");
       		             file.write(obj.toString(4));
       		             file.close();
    				 
    				 }
    				
		             
    				
   					 
	          }
    				
    			
				   	
    			
    			
    		} catch (ParserConfigurationException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SAXException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
}