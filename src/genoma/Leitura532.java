package genoma;
import java.util.ArrayList;
import java.util.ListIterator;

public class Leitura532 extends LeiturasCodons {
	public Leitura532() {
		super();
	}
	
	@Override
	public ArrayList<String> executarLeitura(ArrayList<Character> basesGeral) {
		ArrayList<String> lista_codons = new ArrayList<String>();
		ListIterator<Character> iterator = basesGeral.listIterator();		
		String codon_temp = "";
		
		iterator.next(); // Pulando o primeiro
		for(int i=2; i<=basesGeral.size()-3;i=i+3) {
			for(int j = 0; j<3;j++) {
				codon_temp = codon_temp + iterator.next();
			}
			lista_codons.add(codon_temp);
			codon_temp = "";			
		}
		return lista_codons;
	}
	

}

