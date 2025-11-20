package Grafos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class grafos_controlador {
    @FXML private Button btnDistancia;
    @FXML private Button btnPeajes;
    @FXML private Button btnRuta;

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

    @FXML
    public void initialize()
    {
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

        ocultarKM();
        setBotones();
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
