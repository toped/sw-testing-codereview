/*
 * SAXHandler.java
 *
 */

package metricsanalyzer;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.Vector;

/**
 *
 * @author Byron
 */
public class SAXHandler extends DefaultHandler{
    Vector metricsList = new Vector();
    Vector systemMetrics = new Vector();
    Vector classMetrics = new Vector();
    Vector methodMetrics = new Vector();
    Vector packageMetrics = new Vector();
    String revisionNum = new String();
    int length = 0;
    String temp1, temp2, metric, metricID, metricName, per, total, max, stddev, avg, values;
    String typeName, typeSource, typePackage, typeValue, typeData;
    String directoryName, packageName, packageValue, packageData;
    String methodName, methodSource, methodPackage, methodValue, methodData;
    /** Creates a new instance of SAXHandler */
    public SAXHandler(String revisionNum) {
        this.revisionNum = revisionNum;
    }
    
    public void startDocument() {
        System.out.println("Start document: ");
    }
    public void endDocument()  {
        System.out.println("End document: ");
    }
    
    public void startElement(String uri, String localName, String qname, Attributes attr) {
        //System.out.println("Start element: qname: " + qname);
        length = attr.getLength();
        String zero = "0";
        if (qname.equals("Metric")) {
            metricID = attr.getValue("id");
            metricName = attr.getValue("description");
            metric = metricID+","+metricName;
            if (metricID.equals("NOP") || metricID.equals("TLOC")) {
                per = "---";
            }
            metricsList.addElement(metric);
           // System.out.println(metric);
        } else if (qname.equals("Values")) {
            per = attr.getValue("per");
            total = attr.getValue("total");
            max = attr.getValue("max");
            stddev = attr.getValue("stddev");
            avg = attr.getValue("avg");
            values = metricID+","+revisionNum+","+per+","+total+","+avg+","+stddev+","+max;
            systemMetrics.addElement(values);
           // System.out.println(values);
        } else if (qname.equals("Value")) {
            if (per.equals("type")) {
                typeName = attr.getValue("name");
                typeSource = attr.getValue("source");
                typePackage = attr.getValue("package");
                typeValue = attr.getValue("value");
                typeData = typeName+","+revisionNum+","+metricID+","+typeSource+","+typePackage+","+typeValue;
                classMetrics.addElement(typeData);
              //  System.out.println(typeData);
            } else if (per.equals("packageFragment")) {
                directoryName = attr.getValue("name");
                packageName = attr.getValue("package");
                packageValue = attr.getValue("value");
                packageData = directoryName+","+revisionNum+","+metricID+","+packageName+","+packageValue;
                packageMetrics.addElement(packageData);
               // System.out.println(packageData);
            }
            
            else if (per.equals("method")) {
                methodName = attr.getValue("name");
                methodSource = attr.getValue("source");
                methodPackage = attr.getValue("package");
                methodValue = attr.getValue("value");
                methodData = methodName+","+revisionNum+","+metricID+","+methodSource+","+methodPackage+","+methodValue;
                methodMetrics.addElement(methodData);
               // System.out.println(methodData);
            } else {
                total = attr.getValue("total");
                values = metricID+","+revisionNum+","+zero+","+total+","+zero+","+zero+","+zero;
                systemMetrics.addElement(values);
               // System.out.println(values);
            }
        }
    }
    
    public void endElement(String uri, String localName, String qname) {
        //System.out.println("End element: qname: " + qname);
    }
    
    public void characters(char[] ch, int start, int length) {
        //System.out.println("Characters: " + new String(ch, start, length));
    }
    
    public void ignorableWhitespace(char[] ch, int start, int length) {
        System.out.println("Ignorable whitespace: " + new String(ch, start, length));
    }
    
    public void skippedEntity(String name) {
        System.out.println("WARNING____Skipped Entity: "+name);
    }
    
    public Vector getSysMetrics() {
        return (systemMetrics);
    }
    
    public Vector getClassMetrics() {
        return (classMetrics);
    }
    
    public Vector getPackageMetrics() {
        return (packageMetrics);
    }
    
    public Vector getMethodMetrics() {
        return (methodMetrics);
    }
    
    public Vector geMetricsList() {
        return (metricsList);
    }
    
}
