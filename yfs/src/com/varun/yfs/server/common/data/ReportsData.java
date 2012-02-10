package com.varun.yfs.server.common.data;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.varun.yfs.client.reports.rpc.ReportType;

public class ReportsData extends AbstractData
{
	public List<ModelData> getModelList()
	{
		List<ModelData> arrayList = new ArrayList<ModelData>();
		ModelData m1 = newItem("Reports", "");
		arrayList.add(m1);

		List<ModelData> child = new ArrayList<ModelData>();
		m1.set("children", child);
		child.add(newItem(ReportType.School.getValue(), "reportIndividual"));
		child.add(newItem(ReportType.MedicalCamp.getValue(), "reportIndividual"));
		child.add(newItem(ReportType.Clinic.getValue(), "reportIndividual"));
		child.add(newItem(ReportType.Events.getValue(), "reportIndividual"));			

		return arrayList;
	}

	private ModelData newItem(String text, String iconStyle)
	{
		ModelData m = new BaseModelData();
		m.set("name", text);
		m.set("icon", iconStyle);
		return m;
	}
}