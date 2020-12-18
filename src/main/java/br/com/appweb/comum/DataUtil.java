package br.com.appweb.comum;

import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	public static Date proximaDataMes(Date dataAtual) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataAtual);
		c.add(Calendar.MONTH, 1);
		Date proximaData = c.getTime();
		return proximaData;
	}
}