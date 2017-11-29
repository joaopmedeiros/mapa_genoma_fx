package genoma;
import java.util.*;
import java.util.stream.Collectors;

public class Gene {
	private String locus;
	private long pos_ini;
	private long pos_final;
	private ArrayList<Character> basesGeral;
	private ArrayList<LeiturasCodons> leituras;
	private ArrayList<ArrayList<String>> basesAgrupadas;
	private ArrayList<ArrayList<String>> aminoAcidosAgrupados;
	private ArrayList<String> baseCorreta;
		
	public Gene(String locus, long pos_ini, long pos_final, ArrayList<Character> basesGeral) throws ExcepParamConstrutor {
		
		if(locus.equals(null) || basesGeral.size()==0) {
			throw new ExcepParamConstrutor();
		}
		
		this.locus = locus;
		this.pos_ini = pos_ini;
		this.pos_final = pos_final;
		this.basesGeral = basesGeral;
		this.leituras = new ArrayList<LeiturasCodons>();
		this.basesAgrupadas = new ArrayList<ArrayList<String>>();
		this.aminoAcidosAgrupados = new ArrayList<ArrayList<String>>();
		this.baseCorreta = new ArrayList<String>();		
	
		this.leituras.add(new Leitura531());
		this.leituras.add(new Leitura532());
		this.leituras.add(new Leitura533());
		this.leituras.add(new Leitura351());
		this.leituras.add(new Leitura352());
		this.leituras.add(new Leitura353());		
	}
	
	@Override
	public String toString() {
		return 
				"Locus = " + this.locus + " Posicao Inicial = "+ this.pos_ini+ " Posicao Final = " + this.pos_final +"\n" +
				"Sequencia = " + this.basesGeral +"\n" +
				"Bases agrupadas = " + this.basesAgrupadas +"\n" +
				"Aminoacidos agrupados = " + this.aminoAcidosAgrupados +"\n" +
				"Aminoacido correto = "+ this.baseCorreta ;		
	}
	
        public ArrayList<Character> getBasesGeral(){
            return this.basesGeral;
        }
        
        public ArrayList<ArrayList<String>> getaminoAcidosAgrupados(){
            return this.aminoAcidosAgrupados;
        }
        
        public ArrayList<String> getBaseCorreta(){
            return this.baseCorreta;
        }
        
        public ArrayList<ArrayList<String>> getBasesAgrupadas(){
            return this.basesAgrupadas;
        }
        
	public void geraBasesAgrupadas() {
		this.leituras.forEach(l -> basesAgrupadas.add(l.executarLeitura(basesGeral)));		
	}		
	
	 public void geraAminoacidosAgrupados() {
		AminoacidTables a = AminoacidTables.getInstance();
		this.basesAgrupadas.forEach(base -> aminoAcidosAgrupados.add((ArrayList<String>) base.stream().map(x->a.getAminoacid(x)).collect(Collectors.toList())));		
	} 
	
	public void geraAminoacidoCorreto() {
		this.baseCorreta = percorreAminoacidos(aminoAcidosAgrupados);
	}
		
    private ArrayList<String> percorreAminoacidos(ArrayList<ArrayList<String>> aminoacidos) {
    	int maior_distancia = -1;
    	ArrayList<String> lista_amino_correta = null;    	
    	for(ArrayList<String> lista_amino : aminoacidos) {
    		int cont_distancia = -1;
    		boolean contar = false;
    		for(String amino : lista_amino) {
    			if(amino.equalsIgnoreCase("Met")) contar = true;
    			if(contar) cont_distancia++;
    			if(contar && amino.equalsIgnoreCase("Stop")) {
    				if(cont_distancia>maior_distancia) {
    	    			maior_distancia = cont_distancia;
    	    			lista_amino_correta = lista_amino;
    	    		}
    				cont_distancia = -1;
    				contar = false;
    			}
    		}    	
    	}    	
    	return lista_amino_correta;    	
    }
    
    public void completaGene() {
		geraBasesAgrupadas();			
		geraAminoacidosAgrupados();
		geraAminoacidoCorreto();
    }
    
    public void limpaGene() {
    	this.basesAgrupadas.clear();
    	this.aminoAcidosAgrupados.clear();
    	this.baseCorreta.clear();    	
    }

	public String getLocus() {
		return locus;
	}

	public long getPos_ini() {
		return pos_ini;
	}

	public long getPos_final() {
		return pos_final;
	}
}
