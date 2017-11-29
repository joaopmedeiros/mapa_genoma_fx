/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genoma;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLController implements Initializable {
   
    private File file;
    private List<Gene> genes;
    private Map<String,Gene> locus_gene;
    
    @FXML
    public static final ObservableList genesview = FXCollections.observableArrayList();
    @FXML
    private Label fxlocus;
    @FXML
    private ListView listview;
    @FXML
    private TextArea fxBasesGeral;
    @FXML
    private Label fxPosIni;
    @FXML
    private Label fxPosFin;    
    @FXML
    private TextArea  fxLeitura531;    
    @FXML
    private TextArea  fxLeitura532;    
    @FXML
    private TextArea  fxLeitura533;    
    @FXML
    private TextArea  fxLeitura351;    
    @FXML
    private TextArea fxLeitura352;
    @FXML
    private TextArea fxLeitura353;
    @FXML
    private TextArea fxAminoCorreto;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        FileChooser filechooser = new FileChooser();
        file = filechooser.showOpenDialog(new Stage());
        AbrirArquivo lf = AbrirArquivo.getInstance();                
	try{
            genes = new ArrayList<>();
            genes = lf.inicializa(file);
            
            locus_gene = new HashMap<>();
	    genes.forEach(g->locus_gene.put(g.getLocus(),g));            
            
            genes.forEach(gene -> genesview.add(gene.getLocus()));
            listview.setItems(genesview);    
            
        }
        catch (FileNotFoundException e) {
	    System.out.println("Arquivo não encontrado!!");
        }
    }
    
    @FXML
    private void handleListAction(MouseEvent event){        
        String locus_selecionado = listview.getSelectionModel().getSelectedItem().toString();
        Gene geneSel = locus_gene.get(locus_selecionado);
        geneSel.completaGene();
        fxlocus.setText(locus_selecionado);
        fxPosFin.setText(Long.toString(geneSel.getPos_final()));
        fxPosIni.setText(Long.toString(geneSel.getPos_ini()));
        fxBasesGeral.setText(geneSel.getBasesGeral().toString());
        fxLeitura531.setText(geneSel.getBasesAgrupadas().get(0).toString());    
        fxLeitura532.setText(geneSel.getBasesAgrupadas().get(1).toString());   
        fxLeitura533.setText(geneSel.getBasesAgrupadas().get(2).toString());   
        fxLeitura351.setText(geneSel.getBasesAgrupadas().get(3).toString());   
        fxLeitura352.setText(geneSel.getBasesAgrupadas().get(4).toString());
        fxLeitura353.setText(geneSel.getBasesAgrupadas().get(5).toString());
        fxAminoCorreto.setText(geneSel.getBaseCorreta().toString());
        geneSel.getaminoAcidosAgrupados().forEach(base -> System.out.println(base.toString()));       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
