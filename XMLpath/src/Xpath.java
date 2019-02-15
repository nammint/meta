import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Xpath{
	String path;
	XPath xpath = XPathFactory.newInstance().newXPath();
	
	public Xpath(String path) {
		this.path = path;
	}

	public NodeList readId(){
		Document xml=null;
		NodeList nodelist=null;
		try {
			xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
			nodelist = (NodeList)xpath.evaluate("/TABLE/ROWS/ROW/FILE_ID",xml, XPathConstants.NODESET);
			if(nodelist.getLength()==0) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodelist;
	}
	
	public void updateComment(NodeList nodelist) {
		Document xml1=null;
		Document xml2=null;
		NodeList node1=null;
		Node node2=null;
		NodeList childlist=null;
		String file_num=null;
		String temp=null;
		DOMSource source=null;
		StreamResult result=null;
		Map<String,String> map = new HashMap<String,String>();
		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			
			for(int i=0;i<nodelist.getLength();i++) {
				file_num=nodelist.item(i).getTextContent();
				xml1=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("resource/F_"+file_num+"_TB.xml");
				xml2=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("resource/P_"+file_num+"_TB.xml");
				node1=(NodeList)xpath.evaluate("/TABLE/ROWS/ROW[SIMILAR_RATE div 100>15 and P_ID!='']",xml1, XPathConstants.NODESET);//SELECT ROW

				//조건에 해당되는 ROW가 없으면 생략된다.
				for(int j=0;j<node1.getLength();j++) {	
					childlist = node1.item(j).getChildNodes();
					temp = searchNode(childlist,"P_ID").getTextContent();//SEARCH P_ID
					
					//UPDATE COMMENT
					if(map.containsKey(temp)) {
						searchNode(childlist,"COMMENT").setTextContent(map.get(temp));		
					}else {
						node2 = (Node)xpath.evaluate("/TABLE/ROWS/ROW[P_ID="+temp+"]/LICENSE_ID", xml2, XPathConstants.NODE);//SELECT LICENSE_ID		
						//LICENSE_ID NULL CHECK
						if(node2==null) {
							continue;
						}
						map.put(temp,node2.getTextContent());			
						searchNode(childlist,"COMMENT").setTextContent(node2.getTextContent());
					}
				}
				
				//CREATE FILE
				source = new DOMSource(xml1);
				result = new StreamResult(new File("result/T_"+file_num+"_TB.xml"));
				transformer.transform(source, result);
				map.clear();
			}
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Node searchNode(NodeList nodelist,String keyword) {
		Node node=null;
		for(int z=0;z<nodelist.getLength();z++) {
			if(nodelist.item(z).getNodeType() == Node.ELEMENT_NODE && nodelist.item(z).getNodeName().equals(keyword)) {
				node=nodelist.item(z);
			}
		}
		return node;
	}

}
