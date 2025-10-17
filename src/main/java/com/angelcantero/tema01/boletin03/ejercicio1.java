package com.angelcantero.tema01.boletin03;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class Empleado{
        private String id;
        private String nombre;
        private Double salario;

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                '}';
    }

    public Empleado(String id , String nombre, Double salario){
        this.id=id;
        this.nombre=nombre;
        this.salario=salario;

    }

}

public class ejercicio1 {
    public static Empleado[] parse() throws IOException, ParserConfigurationException,
            SAXException {
        Empleado[] empleados = null;
        try (InputStream fis =  ejercicio1.class.getResourceAsStream("/empleados.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();
            NodeList node = dom.getElementsByTagName("empleado");
            empleados = new Empleado[node.getLength()];

            for (int i = 0; i < node.getLength(); i++) {
                Element item = (Element) node.item(i);

                Node idNode = item.getAttributes().getNamedItem("id");
                String id = idNode.getTextContent();
                NodeList nombrenodes = item.getElementsByTagName("nombre");
                Node nombrenode = nombrenodes.item(0);
                String nombre = nombrenode.getTextContent();
                NodeList salarioNodes = item.getElementsByTagName("salario");
                Node salarioNode = salarioNodes.item(0);
                Double salario = Double.parseDouble(salarioNode.getTextContent());

                empleados[i] = new Empleado(id, nombre, salario);


            }
            return empleados;


        }


    }

    public static void main(String[] args) {
        Empleado[] empleados = null;
        try{
             empleados = parse();

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }
        for (Empleado empleado : empleados) {

        System.out.println(empleados);
        }

    }
}
