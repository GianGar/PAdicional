package com.psc.padicional.componentes;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.icu.text.SimpleDateFormat;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.servicios.ServicioUsuario;

public class Utilidades {
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	public static final String FORMATO_FECHA_MYSQL = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatoFechaSQL(Integer anio, Integer mes) {
		//Por ejemplo, recibe el año 2020 y el mes 6, retorna '2020-06'.
		//Lo uso para consultas del tipo "start with...".
		String numeroMes;
		if (mes<10)
			numeroMes = "0"+mes;
		else
			numeroMes = ""+mes;
		return anio+"-"+numeroMes;
	}
		
	public static double redondear(double valor, int decimales) {
	    if (decimales < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, decimales);
	    valor = valor * factor;
	    long tmp = Math.round(valor);
	    return (double) tmp / factor;
	}
	
	public static Integer anioActual() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_MYSQL);
		String fecha = sdf.format(cal.getTime());
		Integer anio = Integer.parseInt(fecha.substring(0,4));
		return anio;
	}
	
	public static Integer mesActual() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_MYSQL);
		String fecha = sdf.format(cal.getTime());
		Integer mes = Integer.parseInt(fecha.substring(5,7));		
		return mes;
	}
	
	public static Integer mesProximo() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_MYSQL);
		String fecha = sdf.format(cal.getTime());
		Integer mes = Integer.parseInt(fecha.substring(5,7));		
		if (mes == 12)
			mes = 1;
		else
			mes++;
		return mes;
		}
	
	public static Integer cantidadHorasServicios(List <Servicio> servicios) {
		int horas = 0;
		for (Servicio servicio : servicios) {
			horas = horas + Utilidades.getDuracionGuardia(servicio.getHoraEntrada(), servicio.getHoraSalida());
			}
		return horas;
	}
	
	public static String formatoMayuscula(String texto) {
		return WordUtils.capitalize(texto.toLowerCase());
	}
	
	public static Integer getDuracionGuardia(String horaEntrada, String horaSalida){
		int entrada = Integer.parseInt(horaEntrada.substring(0, 2));
		int salida = Integer.parseInt(horaSalida.substring(0, 2));
		if (salida > entrada)
			return salida-entrada;
		else
			return salida-entrada+24;
	}
	
	public static String formatearFecha(String fecha) {
		//Ejemplo: 2019-10-10 a 10/10/2019
		return fecha.substring(8, 10)+"/"+fecha.substring(5,7)+"/"+fecha.substring(0, 4);
		}
	
	public static String formatearFechaGuiones(String fecha) {
		//Ejemplo: 2019-10-10 a 10-10-2019
		return fecha.substring(8, 10)+"-"+fecha.substring(5,7)+"-"+fecha.substring(0, 4);
		}
	
	public static String armarFechaMYSQL(int dia, int mes, int anio) {
		if (dia == 0) return null;
		String numeroDia;
		String numeroMes;
		if (dia<10)
			numeroDia = "0"+dia;
		else
			numeroDia = ""+dia;
		if (mes<10)
			numeroMes = "0"+mes;
		else
			numeroMes = ""+mes;
		return anio+"-"+numeroMes+"-"+numeroDia;
	}
	
	public static int obtenerMes(String periodo) {
		return Integer.parseInt(periodo.substring(5,7));
	}
	
	public static int obtenerAnio(String periodo) {
		return Integer.parseInt(periodo.substring(0,4));
	}
	
	public static String nombreMes(int mes) {
		switch(mes) {
			case 1: return "Enero"; 
			case 2: return "Febrero"; 
			case 3: return "Marzo"; 
			case 4: return "Abril"; 
			case 5: return "Mayo"; 
			case 6: return "Junio"; 
			case 7: return "Julio"; 
			case 8: return "Agosto"; 
			case 9: return "Septiembre"; 
			case 10: return "Octubre"; 
			case 11: return "Noviembre"; 
			case 12: return "Diciembre"; 
			default: return "Error";
			}
		}

	public static String diaSemana(String fechaMYSQL) {
		int anio = Integer.parseInt(fechaMYSQL.substring(0, 4));
		int mes = Integer.parseInt(fechaMYSQL.substring(5, 7));
		int dia = Integer.parseInt(fechaMYSQL.substring(8, 10));
		return diaSemana(dia,mes,anio);
		}
	
	public static String obtenerHora(String fechaHoraMYSQL) {
		return fechaHoraMYSQL.substring(11,16);//2019-11-01 16:00:00
		}

	public static String diaSemana(int dia, int mes, int anio) {
		Calendar calendar = new GregorianCalendar(anio,mes-1,dia);
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		switch (day) {
		    case Calendar.SUNDAY: return "Domingo";
		    case Calendar.MONDAY: return "Lunes";
		    case Calendar.TUESDAY: return "Martes";
		    case Calendar.WEDNESDAY: return "Miércoles";
		    case Calendar.THURSDAY: return "Jueves";
		    case Calendar.FRIDAY: return "Viernes";
		    case Calendar.SATURDAY: return "Sábado";
		    default: return "Error";
			}
	}
	
	public static boolean esNumero(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	        System.out.println(d);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}
