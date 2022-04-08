package callcenter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;



public class OperatoreCallCenter {
	private String nome;
	private static int id = 1;
	private List<Telefonata> lista = new ArrayList();
	
	public OperatoreCallCenter() {
	}
	
	public void carica() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("telefonata.bin"));
			this.lista = (ArrayList<Telefonata>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file telefonata.bin");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
	}

	public void salva()  {
		ObjectOutputStream oos = null;
		try {
			// APRO IL FILE IN SCRITTURA
			oos = new ObjectOutputStream(new FileOutputStream("telefonata.bin"));
			// SERIALIZZO L'OGGETTO lista di Telefonata
			oos.writeObject(lista);
			oos.close();
			System.out.println("Dati salvati nel file telefonata.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}
	
	public OperatoreCallCenter(String nome) {
		this.nome = nome;
		OperatoreCallCenter.id++;
		this.lista = new ArrayList<>();
	}
	
	public void inserisciTelefonata(Telefonata t) {
		this.lista.add(new Telefonata(t));
	}
	
	public void cancellaTelefonata(int index) {
		this.lista.remove(index);
	}
	
	public List<Telefonata> telefonatePerIntervalloOrario(LocalDateTime dt1, LocalDateTime dt2){
		
		List<Telefonata> telefonate = new ArrayList<>();
		
		for (Telefonata t : lista) {
			if (t.getInizio().compareTo(dt1) > 0 && t.getFine().compareTo(dt2) < 0) {
				telefonate.add(new Telefonata(t));
			}
		}
		return telefonate;
	}
	
	public List<Telefonata> telefonatePerUtente(String num_telefono_utente){
			
		List<Telefonata> telefonate = new ArrayList<>();
		
		for (Telefonata t : lista) {
			if (num_telefono_utente.equalsIgnoreCase(t.getNum_telefono_chiamante())) {
				telefonate.add(new Telefonata(t));
			}
		}
		return telefonate;
	}
	
	public String report (LocalDateTime dt1, LocalDateTime dt2) {
		float valutazione_media = 0.0F;
		long sec_tot = 0;
		List<Telefonata>report = telefonatePerIntervalloOrario(dt1, dt2);
		
		for (Telefonata t : report) {
			sec_tot += MINUTES.between(t.getInizio(), t.getFine());
			valutazione_media += t.getValutazione_servizio();
		}
		sec_tot /= telefonatePerIntervalloOrario(dt1, dt2).size();
		sec_tot *= 60;
		
		
		return "Numero di chiamate totali: " + report.size() + 
				"\nDurata chiamate media: " +  sec_tot/3600 + " ore, " + sec_tot%3600/60 + " minuti" + 
					"\nValutazione servizio media: " + valutazione_media/report.size() ;
	}
	
	public void ordinaTelefonate() {
		Collections.sort(lista);
	}
	
	
	@Override
	public String toString() {
		String o = "";
		for (Telefonata t : lista) {
			o += (t.getNum_telefono_chiamante() + "\n");
		}
		return o;
	}
	
	public void stampa() {
		String l = "|---Lista telefonate---|\n";
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				l += "|  " + lista.get(i) + "\n";
			}
		}
		System.out.println(l);
	}
	
	
}