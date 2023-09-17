
	
	import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartUtilities;
	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.JFreeChart;
	import org.jfree.data.general.DefaultPieDataset;

	public class PieChart {
	   
	   public static void main( String[ ] args ) throws Exception {
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      dataset.setValue("Servicios no asignados", new Double( 286 ) );
	      dataset.setValue("Servicios asignados", new Double( 105 ) );
	      JFreeChart chart = ChartFactory.createPieChart("Estado de los servicios (Enero 2020)",   // chart title
	         dataset,          // data
	         true,             // include legend
	         true,
	         false);
	      int width = 640;   /* Width of the image */
	      int height = 480;  /* Height of the image */ 
	      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
	      String listado = "Grafico "+sdf.format(timestamp.getTime());
	      File pieChart = new File("src/main/resources/static/graficos/"+listado+".jpeg" ); 
	      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
	   }
	}


