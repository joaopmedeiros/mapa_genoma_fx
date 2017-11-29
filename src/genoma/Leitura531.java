package genoma;

import java.util.ArrayList;
import java.util.ListIterator;

public class Leitura531 extends LeiturasCodons {
	
	public Leitura531() {
		super();
	}

	@Override
	public ArrayList<String> executarLeitura(ArrayList<Character> basesGeral) {
		ArrayList<String> lista_codons = new ArrayList<String>();
		ListIterator<Character> iterator = basesGeral.listIterator();		
		String codon_temp = "";
		
		while(iterator.hasNext()) {
			for(int i = 0; i<3;i++) {
				codon_temp = codon_temp + iterator.next();
			}
			lista_codons.add(codon_temp);
			codon_temp = "";
		}		
		return lista_codons;
	}



}
