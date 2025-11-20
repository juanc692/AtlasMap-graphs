package Grafos;
import Grafos.codigo.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;

public class grafos_controlador {

    @FXML private Label labelCostoTotal;

    @FXML private Button btnDistancia;
    @FXML private Button btnPeajes;
    @FXML private Button btnRuta;

    @FXML private Circle bogota;
    @FXML private Circle funza;
    @FXML private Circle medellin;
    @FXML private Circle cali;
    @FXML private Circle villao;
    @FXML private Circle tunja;
    @FXML private Circle pereira;

    @FXML private Text pereira_bogota;
    @FXML private Text bogota_funza;
    @FXML private Text funza_villao;
    @FXML private Text villao_cali;
    @FXML private Text cali_tunja;
    @FXML private Text pereira_villao;
    @FXML private Text medellin_cali;
    @FXML private Text funza_medellin;

    @FXML private Line pereira_bogota_line;
    @FXML private Line bogota_funza_line;
    @FXML private Line funza_villao_line;
    @FXML private Line villao_cali_line;
    @FXML private Line cali_tunja_line;
    @FXML private Line pereira_villao_line;
    @FXML private Line medellin_cali_line;
    @FXML private Line funza_medellin_line;

    private Text[] listaKm;
    private Map<Text,Line> lineas;
    private Set<Circle> seleccionados;
    private Map<Circle,String> ciudades;
    private Circle destino;

    @FXML
    public void initialize()
    {
        setMapa();
        ocultarKM();
        setBotones();
    }

    private void setMapa()
    {
        seleccionados = new LinkedHashSet<>();

        for(Circle ciudad : Arrays.asList(bogota,funza,medellin,villao,cali,pereira,tunja) )
        {
            ciudad.setUserData("no seleccionado");

            ciudad.setOnMouseClicked(mouseEvent -> {
                Circle circle = (Circle) mouseEvent.getSource();
                String seleccionado = (String) ciudad.getUserData();
                if (seleccionado.equalsIgnoreCase("no seleccionado"))
                {

                    ciudad.setUserData("seleccionado");
                    //añadir estilo /*-fx-stroke: black;*/

                    if (seleccionados.size() == 2) {
                        Circle primerSeleccionado = seleccionados.iterator().next(); //esto devolvera el primer elemento en el orden
                        seleccionados.remove(primerSeleccionado);
                        primerSeleccionado.setStyle("-fx-stroke: transparent;");

                        primerSeleccionado = seleccionados.iterator().next();
                        primerSeleccionado.setStyle("-fx-stroke: green;");
                    }
                    seleccionados.add(ciudad);
                    destino = ciudad;

                    Circle primerSeleccionado = seleccionados.iterator().next(); //esto devolvera el primer elemento en el orden
                    if(circle == primerSeleccionado) {
                        circle.setStyle("-fx-stroke: green;");
                    }
                    else {
                        circle.setStyle("-fx-stroke: red;");
                    }

                }else{
                    ciudad.setUserData("no seleccionado");
                    //eliminar estilo /*-fx-stroke: transparent;*/
                    circle.setStyle("-fx-stroke: transparent;");
                    seleccionados.remove(circle);
                    if(!seleccionados.isEmpty()) {
                        Circle primerSeleccionado = seleccionados.iterator().next(); //esto devolvera el primer elemento en el orden
                        primerSeleccionado.setStyle("-fx-stroke: green;");
                    }
                }
            });
        }

        listaKm = new Text[]{
                pereira_bogota,
                bogota_funza,
                funza_villao,
                villao_cali,
                cali_tunja,
                pereira_villao,
                medellin_cali,
                funza_medellin
        };

        lineas = new HashMap<>();
        lineas.put(pereira_bogota,pereira_bogota_line);
        lineas.put(bogota_funza,bogota_funza_line);
        lineas.put(funza_villao,funza_villao_line);
        lineas.put(villao_cali,villao_cali_line);
        lineas.put(cali_tunja,cali_tunja_line);
        lineas.put(pereira_villao,pereira_villao_line);
        lineas.put(medellin_cali,medellin_cali_line);
        lineas.put(funza_medellin,funza_medellin_line);

        pereira_bogota.setUserData("pereira_bogota");
        bogota_funza.setUserData("bogota_funza");
        funza_villao.setUserData("funza_villao");
        villao_cali.setUserData("villao_cali");
        cali_tunja.setUserData("cali_tunja");
        pereira_villao.setUserData("pereira_villao");
        medellin_cali.setUserData("medellin_cali");
        funza_medellin.setUserData("funza_medellin");

        ciudades = new HashMap<>();
        ciudades.put(bogota,"bogota");
        ciudades.put(medellin,"medellin");
        ciudades.put(cali,"cali");
        ciudades.put(tunja,"tunja");
        ciudades.put(pereira,"pereira");
        ciudades.put(funza,"funza");
        ciudades.put(villao,"villao");

    }

    private void setBotones()
    {
        btnDistancia.setOnAction(actionEvent -> {
            if(btnDistancia.getText().equals("Mostrar distancia en km"))
            {
                btnDistancia.setText("Ocultar distancia en km");
                mostrarKM();
            }else{
                btnDistancia.setText("Mostrar distancia en km");
                ocultarKM();
            }
        });

        btnRuta.setOnAction(actionEvent -> {
            if(seleccionados.size() == 2)
            {
                calulcarDistancia(seleccionados.iterator().next(),destino);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("Seleccione dos ciudades:\nVerde: Punto de partida\nRojo: punto de destino");
                alert.showAndWait();
            }
        });
        btnPeajes.setUserData(false);
        btnPeajes.setOnAction(actionEvent -> {
            boolean estado = (boolean) btnPeajes.getUserData();

            if (estado) {
                btnPeajes.setUserData(false);
                btnPeajes.getStyleClass().remove("peaje");
            } else {
                btnPeajes.setUserData(true);
                btnPeajes.getStyleClass().add("peaje");
            }
        });

    }

    private void calulcarDistancia(Circle partida, Circle destino)
    {
        Main calculo = new Main(ciudades.get(partida),ciudades.get(destino),(boolean) btnPeajes.getUserData());
        List<String> aristas = calculo.getAristas();

        labelCostoTotal.setText("Costo: "+calculo.getCosto()+"km");

        for(Map.Entry<Text,Line> l : lineas.entrySet())
        {
            l.getValue().setStroke(Color.BLUE);
            l.getKey().setVisible(false);
        }

        for (String arista : aristas) {
            for (Map.Entry<Text, Line> linea : lineas.entrySet()) {

                String[] key = ((String) linea.getKey().getUserData()).split("_");
                String[] ruta = arista.split("_");

                Set<String> setKey = new HashSet<>(Arrays.asList(key));
                Set<String> setRuta = new HashSet<>(Arrays.asList(ruta));

                if (setKey.equals(setRuta)) {
                    linea.getValue().setStroke(Color.GREEN);
                    linea.getKey().setVisible(true);
                } else {
//                    linea.getValue().setStroke(Color.BLUE);
                }
            }
        }

    }

    private void mostrarKM()
    {
        for(Text km : listaKm)
        {
            km.setVisible(true);
        }
    }
    private void ocultarKM()
    {
        for(Text km : listaKm)
        {
            km.setVisible(false);
        }
    }
}
