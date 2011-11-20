package com.varun.yfs.client.reports;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.BarDataProvider;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.LineDataProvider;
import com.extjs.gxt.charts.client.model.ScaleProvider;
import com.extjs.gxt.charts.client.model.charts.BarChart;
import com.extjs.gxt.charts.client.model.charts.BarChart.BarStyle;
import com.extjs.gxt.charts.client.model.charts.LineChart;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.varun.yfs.client.reports.rpc.ReportDetailService;
import com.varun.yfs.client.reports.rpc.ReportDetailServiceAsync;
import com.varun.yfs.client.reports.rpc.ReportType;

public class SchoolHealthProgramReport extends LayoutContainer
{
	private ReportDetailServiceAsync reportDetailService = GWT.create(ReportDetailService.class);
	final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>()
	{
		public void handleEvent(MessageBoxEvent ce)
		{
		}
	};

	public SchoolHealthProgramReport()
	{
		setHeight("700");
	}

	@Override
	protected void onRender(Element parent, int index)
	{

		super.onRender(parent, index);
		setScrollMode(Scroll.AUTOY);
		final ListStore<TeamSales> store = new ListStore<TeamSales>();
		TeamSales tmSales = new TeamSales("Requirement Analysis", 0, 10, 20);
		store.add(tmSales);
		tmSales = new TeamSales("Technology Spiking Effort", 12, 2, 3);
		store.add(tmSales);
		tmSales = new TeamSales("UI Prototypes", 12, 2, 3);
		store.add(tmSales);
		tmSales = new TeamSales("FS", 12, 2, 3);
		store.add(tmSales);
		tmSales = new TeamSales("Feature 1", 1, 2, 3);
		store.add(tmSales);
		tmSales = new TeamSales("Feature 2", 10, 232, 354);
		store.add(tmSales);
		tmSales = new TeamSales("Performance Test", 152, 422, 353);
		store.add(tmSales);
		tmSales = new TeamSales("System Test", 2, 20, 365);
		store.add(tmSales);
		tmSales = new TeamSales("Release", 12, 2, 3);
		store.add(tmSales);
		tmSales = new TeamSales("Handover", 1, 204, 305);
		store.add(tmSales);

		String url = "open-flash-chart.swf";
		final Chart chart = new Chart(url);

		ChartModel model = new ChartModel("Project progress report", "font-size: 14px; font-family: Verdana; text-align: center;");
		model.setBackgroundColour("fefefe");
		model.setLegend(new Legend(Position.TOP, true));
		model.setScaleProvider(ScaleProvider.ROUNDED_NEAREST_SCALE_PROVIDER);

		BarChart bar = new BarChart(BarStyle.GLASS);
		bar.setColour("00aa00");
		BarDataProvider barProvider = new BarDataProvider("alphasales", "month");
		barProvider.bind(store);
		bar.setDataProvider(barProvider);
		model.addChartConfig(bar);

		bar = new BarChart(BarStyle.GLASS);
		bar.setColour("0000cc");
		barProvider = new BarDataProvider("betasales");
		barProvider.bind(store);
		bar.setDataProvider(barProvider);
		model.addChartConfig(bar);

		bar = new BarChart(BarStyle.GLASS);
		bar.setColour("ff6600");
		barProvider = new BarDataProvider("gammasales");
		barProvider.bind(store);
		bar.setDataProvider(barProvider);
		model.addChartConfig(bar);

		LineChart line = new LineChart();
		line.setAnimateOnShow(true);
		line.setText("Average");
		line.setColour("FF0000");
		LineDataProvider lineProvider = new LineDataProvider("avgsales");
		lineProvider.bind(store);
		line.setDataProvider(lineProvider);
		model.addChartConfig(line);

		chart.setChartModel(model);

		LayoutContainer layoutContainer = new LayoutContainer();
		layoutContainer.setLayout(new TableLayout(3));

		final DateField dtfldFromDate = new DateField();
		dtfldFromDate.setFieldLabel("From Date");
		LayoutContainer frmpnlFromDate = new LayoutContainer();
		frmpnlFromDate.setLayout(new FormLayout());
		frmpnlFromDate.add(dtfldFromDate, new FormData("100%"));

		TableData td_frmpnlFromDate = new TableData();
		td_frmpnlFromDate.setPadding(5);
		td_frmpnlFromDate.setMargin(5);
		layoutContainer.add(frmpnlFromDate, td_frmpnlFromDate);

		final DateField dtfldToDate = new DateField();
		dtfldToDate.setFieldLabel("To Date");
		LayoutContainer frmpnlToDate = new LayoutContainer();
		frmpnlToDate.setLayout(new FormLayout());
		frmpnlToDate.add(dtfldToDate, new FormData("100%"));

		TableData td_frmpnlToDate = new TableData();
		td_frmpnlToDate.setPadding(5);
		td_frmpnlToDate.setMargin(5);
		td_frmpnlToDate.setHorizontalAlign(HorizontalAlignment.LEFT);
		layoutContainer.add(frmpnlToDate, td_frmpnlToDate);

		LayoutContainer frmpnlRefresh = new LayoutContainer();
		frmpnlRefresh.setLayout(new FormLayout());

		Button btnRefresh = new Button("Refresh");
		frmpnlRefresh.add(btnRefresh, new FormData("100%"));
		TableData td_frmpnlRefresh = new TableData();
		td_frmpnlRefresh.setPadding(5);
		td_frmpnlRefresh.setMargin(5);
		layoutContainer.add(frmpnlRefresh, td_frmpnlRefresh);
		frmpnlRefresh.setBorders(true);
		btnRefresh.addSelectionListener(new SelectionListener<ButtonEvent>()
		{
			@Override
			public void componentSelected(ButtonEvent ce)
			{
				ModelData model = new BaseModelData();
				model.set("dateFrom", dtfldFromDate.getValue());
				model.set("dateTo", dtfldToDate.getValue());
				reportDetailService.getModel(ReportType.School, model, new AsyncCallback<ModelData>()
				{
					@Override
					public void onSuccess(ModelData result)
					{
					}

					@Override
					public void onFailure(Throwable caught)
					{
					}
				});
			}
		});

		add(layoutContainer);

		FormPanel lcReportingParams = new FormPanel();
		lcReportingParams.setHeaderVisible(false);
		lcReportingParams.setHeading("School Health Program Report");
		lcReportingParams.setSize("", "700");

		lcReportingParams.add(chart, new FormData(""));
		chart.setSize("", "300px");

		LabelField lblfldLocations = new LabelField("Location(s) :");
		lcReportingParams.add(lblfldLocations, new FormData("100%"));

		LabelField lblfldTotalScreened = new LabelField("Total Number Screened:");
		lcReportingParams.add(lblfldTotalScreened, new FormData("100%"));
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig clmncnfgNewColumn = new ColumnConfig("name", "Status Of Treatments", 150);
		configs.add(clmncnfgNewColumn);

		ColumnConfig clmncnfgNewColumn_1 = new ColumnConfig("surgery", "Medicines", 150);
		configs.add(clmncnfgNewColumn_1);

		ColumnConfig clmncnfgNewColumn_2 = new ColumnConfig("nonSurgery", "Hospitals", 150);
		configs.add(clmncnfgNewColumn_2);

		ColumnConfig clmncnfgNewColumn_3 = new ColumnConfig("total", "Total", 150);
		configs.add(clmncnfgNewColumn_3);

		Grid<ModelData> gridStatusOfTreatment = new Grid<ModelData>(new ListStore<ModelData>(), new ColumnModel(configs));
		gridStatusOfTreatment.setHeight("150");
		gridStatusOfTreatment.setBorders(true);

		List<ColumnConfig> configsBreakupOfTreatments = new ArrayList<ColumnConfig>();

		ColumnConfig clmncnfgNewColumn_4 = new ColumnConfig("breakUpOfTreatment", "Breakup of Treatments", 150);
		configsBreakupOfTreatments.add(clmncnfgNewColumn_4);

		ColumnConfig clmncnfgScreened = new ColumnConfig("screened", "Screened", 150);
		configsBreakupOfTreatments.add(clmncnfgScreened);

		ColumnConfig clmncnfgfollowUpMedicines = new ColumnConfig("followUpMedicines", "followUpMedicines", 150);
		configsBreakupOfTreatments.add(clmncnfgfollowUpMedicines);
		ColumnConfig clmncnfgReferredToHospital = new ColumnConfig("referredToHospital", "Referred To Hospital", 150);
		configsBreakupOfTreatments.add(clmncnfgReferredToHospital);

		ColumnConfig clmncnfgClosedCases = new ColumnConfig("closedCases", "Closed Cases", 150);
		configsBreakupOfTreatments.add(clmncnfgClosedCases);

		ColumnConfig clmncnfgPendingCases = new ColumnConfig("pendingCases", "Pending Cases", 150);
		configsBreakupOfTreatments.add(clmncnfgPendingCases);

		ColumnConfig clmncnfgReferred = new ColumnConfig("referredForSurgery", "Referred for Surgery", 150);
		configsBreakupOfTreatments.add(clmncnfgReferred);
		ColumnConfig clmncnfgCasesClosed = new ColumnConfig("surgeryCasesClosed", "Surgery Cases Closed", 150);
		configsBreakupOfTreatments.add(clmncnfgCasesClosed);

		ColumnConfig clmncnfgNewColumn_5 = new ColumnConfig("id", "Total", 150);
		configsBreakupOfTreatments.add(clmncnfgNewColumn_5);

		final Grid<ModelData> gridBreakupOfTreatments = new Grid<ModelData>(new ListStore<ModelData>(), new ColumnModel(configsBreakupOfTreatments));
		gridBreakupOfTreatments.setBorders(true);

		FormData fd_gridStatusOfTreatment = new FormData("100%");
		fd_gridStatusOfTreatment.setMargins(new Margins(0, 0, 5, 0));
		lcReportingParams.add(gridStatusOfTreatment, fd_gridStatusOfTreatment);
		FormData fd_gridBreakupOfTreatments = new FormData("100%");
		fd_gridBreakupOfTreatments.setMargins(new Margins(0, 0, 5, 0));
		lcReportingParams.add(gridBreakupOfTreatments, fd_gridBreakupOfTreatments);
		gridBreakupOfTreatments.setHeight("150");

		lcReportingParams.setLayoutData(new Margins(5, 5, 5, 5));
		add(lcReportingParams);

		// storeLoader.getListStore("ReferralType", new
		// AsyncCallback<List<ModelData>>()
		// {
		// @Override
		// public void onSuccess(List<ModelData> result)
		// {
		// gridBreakupOfTreatments.getStore().add(result);
		// }
		//
		// @Override
		// public void onFailure(Throwable caught)
		// {
		// MessageBox.alert("Alert", "Error encountered while loading", l);
		// }
		// });

	}
}
