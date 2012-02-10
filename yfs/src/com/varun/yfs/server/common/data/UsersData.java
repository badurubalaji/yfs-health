package com.varun.yfs.server.common.data;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.varun.yfs.server.admin.rpc.ModelDataEnum;

public class UsersData extends AbstractData
{
	private static final Logger LOGGER = Logger.getLogger(UsersData.class);
	
	public ModelData getModel()
	{
		ModelData modelData = new BaseModelData();
		List<ModelData> modelList = DataUtil.<ModelData> getModelList("User");
		List<String> lstChapterNames = (List<String>) DataUtil.executeQuery("select name from ChapterName");
		List<String> lstProjects = (List<String>) DataUtil.executeQuery("select name from Project");

		modelData.set("users", modelList);
		modelData.set("lstChapterNames", lstChapterNames);
		modelData.set("lstProjects", lstProjects);
		return modelData;
	}

	public String saveModel(ModelData model)
	{
		String status = "Failed";
		try
		{
			DataUtil.saveUserDetail(model);
			status = "Success";
		} catch (HibernateException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		return status;
	}

	public List<ModelData> getModelList()
	{
		return DataUtil.getModelList("User");
	}
}
