package callcenter;

import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		OperatoreCallCenter op = new OperatoreCallCenter();
		op.carica();
		

		int var = 0;
		do {

			String x = null;
			String y = null;
			String a = null;
			String b = null;
			int s = 0;
			int n = 0;

			System.out.println("1) Inserisci telefonata ");
			System.out.println("2) Cancella telefonata ");
			System.out.println("3) Stampa telefonate in un certo intervallo di tempo ");
			System.out.println("4) Stampa le telefonate di un utente ");
			System.out.println("5) Stampa il report ");
			System.out.println("6) Stampa elenco telefonate");
			System.out.println("7) Termina il programma ");

			var = in.nextInt();
			

			switch (var) {

			case 1:
				System.out.println("Inserire numero telefono chiamante: ");
				in.nextLine();
				x = in.nextLine();
				System.out.println("Inserire '0' per terminare la chiamata: ");
				LocalDateTime c = LocalDateTime.now();
				in.next();
				LocalDateTime cf = LocalDateTime.now();
				System.out.println("Inserire il motivo della chiamata: ");
				in.nextLine();
				y = in.nextLine();
				System.out.println("Valuta il servizio con un voto da 1 a 10: ");
				s = in.nextInt();
				Telefonata t = new Telefonata(x, c, cf, y, s);
				op.inserisciTelefonata(t);
				break;
			case 2:
				System.out.println("Inserire posizione della chiamata da cancellare: ");
				n = in.nextInt();
				op.cancellaTelefonata(n);
				System.out.println("Cancellazione dell'ultima chiamata dall'elenco effettuata");
				break;
			case 3:
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ITALIAN);
				System.out.println("Inserire data ora inizio:");
				in.nextLine();
				a = in.nextLine();
				LocalDateTime data1 = LocalDateTime.parse(a, formatter);
				System.out.println("Inserire data ora fine:");
				b = in.nextLine();
				LocalDateTime data2 = LocalDateTime.parse(b, formatter);
				System.out.println(op.telefonatePerIntervalloOrario(data1, data2));
				break;
			case 4:
				System.out.println("Digitare numero utente da ricercare: ");
				in.nextLine();
				x = in.nextLine();
				System.out.println(op.telefonatePerUtente(x));

				break;
			case 5:
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.ITALIAN);
				System.out.println("Inserire data ora inizio:");
				in.nextLine();
				x = in.nextLine();
				LocalDateTime data3 = LocalDateTime.parse(x, formatter2);
				System.out.println("Inserire data ora fine:");
				y = in.nextLine();
				LocalDateTime data4 = LocalDateTime.parse(y, formatter2);
				System.out.println(op.report(data3, data4));
				break;
			case 6:
				op.stampa();
				break;
			}
		} while (var != 7);
		op.salva();
	}

}
