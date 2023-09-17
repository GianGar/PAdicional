import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.psc.padicional.componentes.Utilidades;

public class Probando {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("123456"));
		
		System.out.println("Mes actual: "+Utilidades.mesActual());
		System.out.println("Anio actual: "+Utilidades.anioActual());

}

	

}
