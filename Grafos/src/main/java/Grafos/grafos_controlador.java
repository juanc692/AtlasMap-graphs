package Grafos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

public class grafos_controlador {
    @FXML private Button btnDistancia;
    @FXML private Button btnPeajes;
    @FXML private Button btnRuta;

    @FXML private Text pereira_bogota;
    @FXML private Text bogota_funza;
    @FXML private Text funza_villao;
    @FXML private Text villao_cali;
    @FXML private Text cali_tunja;
    @FXML private Text cali_pereira;
    @FXML private Text medellin_cali;
    @FXML private Text funza_medellin;

    private Text[] listaKm;

    @FXML
    public void initialize()
    {
        listaKm = new Text[]{
                pereira_bogota,
                bogota_funza,
                funza_villao,
                villao_cali,
                cali_tunja,
                cali_pereira,
                medellin_cali,
                funza_medellin
        };
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
