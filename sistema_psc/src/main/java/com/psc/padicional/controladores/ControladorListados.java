package com.psc.padicional.controladores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.psc.padicional.componentes.Dia;
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.servicios.ServicioEnte;
import com.psc.padicional.servicios.ServicioPeriodo;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioServicio;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
@RequestMapping("/adicional/listados")
public class ControladorListados {
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioServicio")
	private ServicioServicio servicioServicio;
	
	@Autowired
	@Qualifier("servicioEnte")
	private ServicioEnte servicioEnte;
	
	@Autowired
	@Qualifier("servicioPersHab")
	private ServicioPersHab servicioPH;
	
	@Autowired
	@Qualifier("servicioPeriodo")
	private ServicioPeriodo servicioPeriodo;
		
	@GetMapping("/asignados")
	public ModelAndView asignados(@RequestParam(name="estado",required=false) String estado,
			@RequestParam(name="listado",required=false) String listado) {
		ModelAndView mav = new ModelAndView(Vistas.LISTADO_ASIGNADOS);
		List<Periodo> periodos = servicioPeriodo.listar();
		mav.addObject("listado", listado);
		mav.addObject("estado", estado);
		mav.addObject("periodos", periodos);
		return mav;
		}
	
	@GetMapping("/asignados2")
	public ModelAndView asignados2() {
		ModelAndView mav = new ModelAndView(Vistas.LISTADO_ASIGNADOS2);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
		String listado = "PorEfectivo "+sdf.format(timestamp.getTime());
		String dest = "src/main/resources/static/listados/porefectivo/"+listado+".pdf";
		String banner = "src/main/resources/static/imgs/banner.jpg";
		Image img = null;
		try {
			img = new Image(ImageDataFactory.create(banner));
			img.scaleAbsolute(250, 40);
			} 
		catch (MalformedURLException e) 
			{
			e.printStackTrace();
			}
		PdfDocument pdf = null;
		try {
			pdf = new PdfDocument(new PdfWriter(dest));
			} 
		catch (FileNotFoundException e3) 
			{
			e3.printStackTrace();
			}
		
		List<PersonalHabilitado> listPH = servicioPH.listarPorDestinoJerarquia();
		
		Integer mes = Utilidades.mesProximo();
		Integer anio = Utilidades.anioActual();
		if (mes == 1) anio++;
		
		try (Document document = new Document(pdf, PageSize.A4)) {
			boolean primeraPagina = true;
			for (PersonalHabilitado ph : listPH ) {
				List<Servicio> servicios = servicioServicio.serviciosEfectivo(anio,mes, ph.getIdEfectivo());
				if (servicios.size()>0) {
					if (primeraPagina) 
						{
						primeraPagina=false;
						}
					else{
						document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
						}
					for (int i=1; i<=2; i++) {
						Table table; 
						float [] nombresColumnas = {1275F, 230F};
						table = new Table(UnitValue.createPercentArray(nombresColumnas));
						table.setFontSize(10f);
						table.addCell(new Cell().add(img).setBorder(Border.NO_BORDER));  
						document.add(table); 
						document.add(new Paragraph().add(new Text(""
								+ "LISTADO DE SERVICIOS ASIGNADOS - "
								+ "PERÍODO: DICIEMBRE 2019")).setBold().setTextAlignment(TextAlignment.CENTER));
			        	table = new Table(new float []{100F});
			        	table.setWidthPercent(100);
			        	table.setFontSize(10f);
			        	table.addCell(new Cell().add("Nº de Agente: "+ph.getPolicia().getNroAgente()+" - "+ph.getPolicia().getJerarquia().getDescripcion()+" "+ph.getPolicia().getPersona().getNombre()+"\n"+ph.getPolicia().getDestino().getDescripcion()).setBold().setBackgroundColor(Color.LIGHT_GRAY));  
			            document.add(table);
			            table = new Table(new float []{100F,1200F,80F,80F});
			            table.setFontSize(10f);
			            table.setBorder(Border.NO_BORDER);

			            for (Servicio servicio : servicios) {
			            	table.addCell(new Cell().add(servicio.getFecha()).setPaddingBottom(-1).setPaddingTop(-1).setBorder(Border.NO_BORDER));
			            	table.addCell(new Cell().add(servicio.getObjetivo().getEnte().getNombre()+" ("+servicio.getObjetivo().getDescripcion()+")").setPaddingBottom(-1).setPaddingTop(-1).setBorder(Border.NO_BORDER));
			            	table.addCell(new Cell().add(servicio.getHoraEntrada()).setPaddingBottom(-1).setPaddingTop(-1).setBorder(Border.NO_BORDER));
			            	table.addCell(new Cell().add(servicio.getHoraSalida()).setPaddingBottom(-1).setPaddingTop(-1).setBorder(Border.NO_BORDER));
			            	}
        	
			            document.add(table);
        	
			            document.add(new Paragraph().add(new Text("OBS: ORIGINAL CONFORMADO DEVOLVER A DIV.POL.ADIC. "
			            		+ "- COPIA PARA EL PERSONAL").setFontSize(8f)).setTextAlignment(TextAlignment.CENTER));
        	
            table = new Table(new float []{1200F,1200F});
            table.addCell(new Cell().setBorder(Border.NO_BORDER).add("\n\n").setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().setBorder(Border.NO_BORDER).add("\n\n").setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().setBorder(Border.NO_BORDER).add("...............................................\nNotificado").setTextAlignment(TextAlignment.CENTER));
        	table.addCell(new Cell().setBorder(Border.NO_BORDER).add("...............................................\nNotificador").setTextAlignment(TextAlignment.CENTER));
        	document.add(table);
        	
            document.add(new Paragraph().add(new Text("Sistema desarrollado por la Dirección General de Tecnologías de la Información y las Comunicaciones")).setFontSize(8f).setTextAlignment(TextAlignment.CENTER));
            DottedLine line = new DottedLine(1f);
        	line.setColor(Color.DARK_GRAY);
        	LineSeparator ls = new LineSeparator(line);
        	ls.setMarginTop(3);
        	ls.setMarginBottom(0);
        	if (i==1)
        		document.add(ls);
            document.add(new Paragraph().add(new Text(" ")));
            document.add(new Paragraph().add(new Text(" ")));
            document.add(new Paragraph().add(new Text(" ")));
        		}
			}
				
			}
			document.close();
		}
		pdf.close();
		
        try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.addObject("listado", listado);
		return asignados("ok", listado);
		}
	
	@GetMapping("/diarios")
	public ModelAndView listado(@RequestParam(name="estado",required=false) String estado,
			@RequestParam(name="listado",required=false) String listado,
			@RequestParam(name="anio",required=true) Integer anio,
			@RequestParam(name="mes",required=true) Integer mes) {
		List<Periodo> periodos = servicioPeriodo.listar();
		ModelAndView mav = new ModelAndView(Vistas.LISTADO_DIARIO);
		String nombreMes = Utilidades.nombreMes(mes);
		int diasDelMes = YearMonth.of(anio, mes).lengthOfMonth();
		List<Dia> dias = new ArrayList<>();
		for (int i=1; i<=diasDelMes; i++)
			{
			String diaFormat;
			if (i<10)
				diaFormat = "0"+i;
			else
				diaFormat = ""+i;
			String diaSemana = Utilidades.diaSemana(i, 12, 2019);
			dias.add(new Dia("Generar listado diario ("+diaSemana+" "+i+" de "+nombreMes+")", anio+"-"+mes+"-"+diaFormat,true));
			}
		mav.addObject("dias", dias);
		mav.addObject("estado", estado);
		mav.addObject("listado", listado);
		mav.addObject("periodos", periodos);
		return mav;
		}
	
	@GetMapping("/diariospdf")
	public ModelAndView hacerpdf(@RequestParam(name="fecha",required=false) String fecha) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
		String listado = "Diario "+sdf.format(timestamp.getTime());
		String dest = "src/main/resources/static/listados/diarios/"+listado+".pdf";
		String esc = "src/main/resources/static/imgs/banner.jpg";
		PdfDocument pdf = null;
		try {
			pdf = new PdfDocument(new PdfWriter(dest));
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}

        PdfFont code = null;
		try {
			code = PdfFontFactory.createFont(FontConstants.HELVETICA);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        Style encabezado = new Style()
            .setFont(code)
            .setFontSize(18)
            .setFontColor(Color.BLUE).setBold().setUnderline();
        
        Style estiloEntidad = new Style()
                .setFont(code)
                .setFontSize(14)
                .setFontColor(Color.BLACK).setBold().setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.GRAY);
        
        Image escudo = null;
		try {
			escudo = new Image(ImageDataFactory.create(esc));
			} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		List<Ente> entidades = servicioEnte.listar("");
		Integer mes = Utilidades.mesActual();
		Integer anio = Utilidades.anioActual();
        
        try (Document document = new Document(pdf, PageSize.A4)) {
        	Paragraph parrafo = new Paragraph();
        	document.add(parrafo.add(escudo));
        	
        	Paragraph titulo = new Paragraph();
        	titulo.add(new Text(" LISTADO DIARIO DE SERVICIOS ").addStyle(encabezado));
        	titulo.setTextAlignment(TextAlignment.CENTER);
        	document.add(titulo);
        	
        	SolidLine line = new SolidLine(1f);
        	line.setColor(Color.DARK_GRAY);
        	LineSeparator ls = new LineSeparator(line);
        	ls.setMarginTop(5);
        	ls.setMarginBottom(10);
        	document.add(ls);

        	Table table; 
        	float [] nombresColumnas = {1275F, 230F};
        	table = new Table(UnitValue.createPercentArray(nombresColumnas));
        	List<Servicio> servicios;
     
        	table.addCell(new Cell().setBorder(Border.NO_BORDER).add("PERSONAL DE SERVICIO DE POLICÍA ADICIONAL"));            
            document.add(table);
            document.add(new Paragraph().add(new Text("Día: "+Utilidades.diaSemana(fecha)+" "+Utilidades.formatearFecha(fecha))).setBold());
            
            line = new SolidLine(1f);
        	line.setColor(Color.DARK_GRAY);
        	ls = new LineSeparator(line);
        	ls.setMarginTop(5);
        	ls.setMarginBottom(10);
            document.add(ls);
            
            float [] pointColumnWidths = {300F, 1000F, 100F, 100F}; 
           
        	for (Ente modeloEnte : entidades)
        		{
        		servicios = servicioServicio.listar(anio,mes, modeloEnte.getId(), "", fecha,usuario.getId_codigo_gestion());
        		if (!servicios.isEmpty()) {
        			document.add(new Paragraph().add(new Text(modeloEnte.getNombre()).addStyle(estiloEntidad)));
	        		for (Servicio servicio : servicios)
	        			{
	        			// Sin borde .setBorder(Border.NO_BORDER) entre new Cell y Add
	        			table = new Table(UnitValue.createPercentArray(pointColumnWidths)); 
	        			table.addCell(new Cell().add(servicio.getObjetivo().getDescripcion()));       
	                    table.addCell(new Cell().setBackgroundColor(new DeviceGray(0.90f)).add(servicio.getJerarquia()+" "+servicio.getPolicia().getPersona().getNombre()));  
	                    table.addCell(new Cell().add(servicio.getHoraEntrada()));  
	                    table.addCell(new Cell().add(servicio.getHoraSalida()));
	                    document.add(table);
	        			}
        			}
        		}
        	document.close();
        	}
        catch(NullPointerException e) {
        	return listado("error","",anio,mes);
        	}
        
        pdf.close(); 
        try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listado("ok",listado,anio,mes);
		}

}
