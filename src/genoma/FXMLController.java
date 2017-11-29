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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private Canvas fxCanvas;
    private GraphicsContext gc;
    
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
        if (geneSel.getBasesAgrupadas().size()==0){
            geneSel.completaGene();
        }        
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
	gc = fxCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 0, 0);
	// grid.add(canvas, 0, 0);
	drawSequence(geneSel.getBasesGeral());
    }
    
    private Color getNucleotideColor(Character n) {
		switch (n) {
		case 'A':
			return Color.BLUE;
		case 'C':
			return Color.YELLOW;
		case 'T':
			return Color.GREEN;
		case 'G':
			return Color.RED;
		default:
			return Color.BLACK;
		}
	}
    
    	private void drawSequence(List<Character> nucl) {
		gc.setStroke(Color.TRANSPARENT);
                gc.setLineWidth(5);
		gc.strokeRect(0, 0, fxCanvas.getWidth(), fxCanvas.getHeight());
		if (nucl == null) {
			return;
		}

		int cx = (int) fxCanvas.getWidth() / 2;
		int cy = (int) fxCanvas.getHeight() / 2;
		int raioInterno = 40;
		int raioExterno = 125;
		gc.setLineWidth(2);
		int i = 0;
		// System.out.println("Size:"+nucl.size());
		for (double ang = 0; ang < Math.PI * 2; ang += (Math.PI * 2) / nucl.size(), i++) {
			// System.out.println(i);
			if (i < nucl.size()) {
				double x1 = raioInterno * Math.cos(ang);
				double y1 = raioInterno * Math.sin(ang);
				double x2 = raioExterno * Math.cos(ang);
				double y2 = raioExterno * Math.sin(ang);
				gc.setStroke(getNucleotideColor(nucl.get(i)));
				gc.strokeLine((int) (cx + x1), (int) (cy + y1), (int) (cx + x2), (int) (cy + y2));
			}
		}
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
