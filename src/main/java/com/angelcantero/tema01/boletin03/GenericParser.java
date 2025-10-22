package com.angelcantero.tema01.boletin03;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class GenericParser {

    public static Empleado[] parseEmpleados() throws IOException, ParserConfigurationException, SAXException {
        Empleado[] empleados = null;

        try (InputStream fis = GenericParser.class.getResourceAsStream("/empleados.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("empleado");
            empleados = new Empleado[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);

                String id = item.getAttribute("id");
                String nombre = item.getElementsByTagName("nombre").item(0).getTextContent();
                String departamento = item.getElementsByTagName("departamento").item(0).getTextContent();

                Element salarioElem = (Element) item.getElementsByTagName("salario").item(0);
                double salario = Double.parseDouble(salarioElem.getTextContent());
                String moneda = salarioElem.getAttribute("moneda");

                String fechaAlta = item.getElementsByTagName("fechaAlta").item(0).getTextContent();

                empleados[i] = new Empleado(id, nombre, departamento, salario, moneda, fechaAlta);
            }
        }

        return empleados;
    }

    public static Libro[] parseLibro() throws IOException, ParserConfigurationException, SAXException {
        Libro[] libros = null;

        try (InputStream fis = GenericParser.class.getResourceAsStream("/biblioteca.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("libro");
            libros = new Libro[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                Autor[] autores = null;
                Genero[] generos = null;
                Prestamo[] prestamos = null;
                boolean disp = false;

                String isbn = item.getAttribute("isbn");

                String titulo = item.getElementsByTagName("titulo").item(0).getTextContent();

                NodeList aut = item.getElementsByTagName("autor");
                autores = new Autor[aut.getLength()];
                for (int j = 0; j < aut.getLength(); j++) {
                    Element autor = (Element) aut.item(j);
                    String nombre = autor.getElementsByTagName("nombre").item(0).getTextContent();
                    String fechaDeNacimiento = autor.getElementsByTagName("nacimiento").item(0).getTextContent();
                    autores[j] = new Autor(nombre, fechaDeNacimiento);

                }

                String anio = item.getElementsByTagName("anio").item(0).getTextContent();

                NodeList gen = item.getElementsByTagName("generos");
                generos = new Genero[gen.getLength()];

                if (gen.getLength() > 0) {
                    Element elementoGeneros = (Element) gen.item(0);


                    NodeList listaHijosGenero = elementoGeneros.getElementsByTagName("genero");


                    generos = new Genero[listaHijosGenero.getLength()];


                    for (int k = 0; k < listaHijosGenero.getLength(); k++) {
                        Element generoIndividual = (Element) listaHijosGenero.item(k);


                        String nombre = generoIndividual.getTextContent();
                        generos[k] = new Genero(nombre);
                    }
                } else {
                    generos = new Genero[0];
                }
                if (item.getElementsByTagName("disponible").item(0).getTextContent().equals("true")) {
                    disp = true;
                }


                NodeList prest = item.getElementsByTagName("prestamos");
                prestamos = new Prestamo[prest.getLength()];

                for (int l = 0; l < prest.getLength(); l++) {
                    Element prestamo = (Element) prest.item(l);
                    String idUsuario = prestamo.getElementsByTagName("usuario").item(0).getTextContent();
                    String fecha = prestamo.getElementsByTagName("fecha").item(0).getTextContent();
                    prestamos[l] = new Prestamo(idUsuario, fecha);
                }
                libros[i] = new Libro(prestamos, isbn, titulo, autores[0], anio, generos, disp);
            }
        }

        return libros;
    }

    public static Pedido[] parsePedidos() throws IOException, ParserConfigurationException, SAXException {
        Pedido[] pedidos = null;
        try (InputStream fis = GenericParser.class.getResourceAsStream("/pedidos.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("pedido");
            pedidos = new Pedido[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                Cliente cliente = null;
                ItemPedido[] items = null;

                String id = item.getAttribute("id");

                String fecha = item.getElementsByTagName("fecha").item(0).getTextContent();

                NodeList cli = item.getElementsByTagName("cliente");

                if (cli.getLength() > 0) {
                    Element clie = (Element) cli.item(0);
                    String nombre = clie.getElementsByTagName("nombre").item(0).getTextContent();
                    String fechaDeNacimiento = clie.getElementsByTagName("email").item(0).getTextContent();
                    cliente = new Cliente(nombre, fechaDeNacimiento);
                }
                // 1. Coge el nodo contenedor <items>
                NodeList itemsContainerList = item.getElementsByTagName("items");

                if (itemsContainerList.getLength() > 0) {
                    // 2. Coge el elemento <items> (el .item(0) de la lista contenedora)
                    Element itemsContainer = (Element) itemsContainerList.item(0);

                    // 3. Coge la lista de hijos <item> (singular)
                    NodeList itemsList = itemsContainer.getElementsByTagName("item");

                    // 4. Inicializa el array con el tamaño correcto
                    items = new ItemPedido[itemsList.getLength()];

                    // 5. Recorre la lista de <item> (singular)
                    for (int j = 0; j < itemsList.getLength(); j++) {

                        Element itemIndividual = (Element) itemsList.item(j);

                        String codigo = itemIndividual.getAttribute("sku");
                        String descripcion = itemIndividual.getElementsByTagName("descripcion").item(0).getTextContent();
                        int cantidad = Integer.parseInt(itemIndividual.getElementsByTagName("cantidad").item(0).getTextContent());

                        Element pUnitario = (Element) itemIndividual.getElementsByTagName("precioUnitario").item(0);

                        double precioUnitario = Double.parseDouble(pUnitario.getTextContent());
                        String moneda = pUnitario.getAttribute("moneda");

                        items[j] = new ItemPedido(codigo, descripcion, cantidad, precioUnitario, moneda);
                    }
                } else {
                    items = new ItemPedido[0]; 
                }

                Element totalElem = (Element) item.getElementsByTagName("total").item(0);
                double total = Double.parseDouble(totalElem.getTextContent());
                String monedaTotal = totalElem.getAttribute("moneda");
                pedidos[i] = new Pedido(id, cliente, fecha, items, total, monedaTotal);



            }
        }
        return pedidos;
    }
    public Alumno[] parseAlumnos() throws IOException {
        Alumno[] alumnos = null;
        Nota[] notas = null;
                StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/alumnos.json");
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray jsonArray = new JSONArray(tokener);
            alumnos = new Alumno[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String nombre = jsonObject.getString("nombre");
                boolean matriculado = jsonObject.getBoolean("matriculado");
                LocalDate fechaNacimiento = LocalDate.parse(jsonObject.getString("fechaNacimiento"));
                JSONArray notasArray = jsonObject.getJSONArray("notas");
                notas = new Nota[notasArray.length()];
                for (int j = 0; j < notasArray.length(); j++) {
                    JSONObject notaObject = notasArray.getJSONObject(j);
                    String asignatura = notaObject.getString("asignatura");
                    double nota = notaObject.getDouble("nota");
                    notas[j] = new Nota(asignatura, nota);
                }
                alumnos[i] = new Alumno(id, nombre, matriculado, fechaNacimiento, notas);
            }
        }
        return alumnos;
    }

}