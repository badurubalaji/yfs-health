package com.varun.yfs.server.common.data;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;

public class EntityData extends AbstractData
{
	public List<ModelData> getModelList()
	{
		return DataUtil.getModelList("Entities");
	}
}