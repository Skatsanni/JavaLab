package Valentinaa.Kostyukhina.ivt.ch.makery.adress.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.model.VPersonK;

public class VBirthdayStatisticsControllerK {
	 @FXML
	    private BarChart<String, Integer> vbarChartk;

	    @FXML
	    private CategoryAxis vxAxisk;

	    private ObservableList<String> vmonthNamesk = FXCollections.observableArrayList();

	    @FXML
	    private void initialize() {

	        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
	        
	        vmonthNamesk.addAll(Arrays.asList(months));

	       
	        vxAxisk.setCategories(vmonthNamesk);
	    }

	    public void vsetPersonDataK(List<VPersonK> persons) {
	        
	        int[] monthCounter = new int[12];
	        for (VPersonK p : persons) {
	            int month = p.getBirthday().getMonthValue() - 1;
	            monthCounter[month]++;
	        }

	        XYChart.Series<String, Integer> series = new XYChart.Series<>();

	        
	        for (int i = 0; i < monthCounter.length; i++) {
	            series.getData().add(new XYChart.Data<>(vmonthNamesk.get(i), monthCounter[i]));
	        }

	        vbarChartk.getData().add(series);
	    }
}