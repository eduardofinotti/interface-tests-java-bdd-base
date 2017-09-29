package br.com.atlantico.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public String geraStringComMomento() {
		
		SimpleDateFormat momento = new SimpleDateFormat("HHmmssSSS");
		String complemento = momento.format(new Date());
		
		return complemento.toString();
		
	}


}
