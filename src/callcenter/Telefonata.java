package callcenter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Telefonata implements Comparable<Telefonata>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String num_telefono_chiamante;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private String descrizione;
	private int valutazione_servizio;
	
	public Telefonata(String num_telefono_chiamante, LocalDateTime inizio, LocalDateTime fine, String descrizione,
			int valutazione_servizio) {
		this.num_telefono_chiamante = num_telefono_chiamante;
		this.inizio = inizio;
		this.fine = fine;
		this.descrizione = descrizione;
		this.valutazione_servizio = controllaValutazione(valutazione_servizio)? valutazione_servizio : 1;
	}
	
	private boolean controllaValutazione(int valutazione_servizio) {
		return valutazione_servizio > 0 && valutazione_servizio <6;
	}
	
	public Telefonata(Telefonata t) {
		this.num_telefono_chiamante = t.num_telefono_chiamante;
		this.inizio = t.inizio;
		this.fine = t.fine;
		this.descrizione = t.descrizione;
		this.valutazione_servizio = controllaValutazione(t.valutazione_servizio)? t.valutazione_servizio : 1;
	}

	public String getNum_telefono_chiamante() {
		return num_telefono_chiamante;
	}

	public void setNum_telefono_chiamante(String num_telefono_chiamante) {
		this.num_telefono_chiamante = num_telefono_chiamante;
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getValutazione_servizio() {
		return controllaValutazione(valutazione_servizio)? valutazione_servizio : 1;
	}

	public void setValutazione_servizio(int valutazione_servizio) {
		this.valutazione_servizio = valutazione_servizio;
	}

	@Override
	public String toString() {
		return num_telefono_chiamante + ", " + inizio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ", "
				+ fine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ", " + descrizione + ", " + valutazione_servizio;
	}

	@Override
	public int compareTo(Telefonata o) {
		return this.getNum_telefono_chiamante().compareToIgnoreCase(o.getNum_telefono_chiamante());
	}
	
	
}
