package Grafos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;

public class grafos_controlador {
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
                    Circle primerSeleccionado = seleccionados.iterator().next(); //esto devolvera el primer elemento en el orden
                    primerSeleccionado.setStyle("-fx-stroke: green;");
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
