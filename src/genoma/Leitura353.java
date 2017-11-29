package genoma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Leitura353 extends Leitura533 {
	
	public Leitura353() {
		super();
	}
	
	
	@Override
	public ArrayList<String> executarLeitura(ArrayList<Character> basesGeral) {
		ArrayList<String> lista_codons = new ArrayList<String>();		
		Collections.reverse(basesGeral);		
		lista_codons = super.executarLeitura(basesGeral);		
		Collections.reverse(basesGeral);		
		return lista_codons;
	}

}
