package com.varun.yfs.server.common.data;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.varun.yfs.dto.CampScreeningDetailDTO;
import com.varun.yfs.dto.ChapterNameDTO;

public class CampScreeningLocationsData extends AbstractData
{
	public List<ModelData> getModelList()
	{
		List<ModelData> nodes = new ArrayList<ModelData>();

		List<ChapterNameDTO> rootNodes = DataUtil.<ChapterNameDTO> getModelList("ChapterName");
		for (ChapterNameDTO chapterNameDTO : rootNodes)
		{
			ModelData rootNode = new BaseModelData();
			rootNode.set("name", chapterNameDTO.getName());
			rootNode.set("icon", "");
			nodes.add(rootNode);

			List<ModelData> chapterNodes = new ArrayList<ModelData>();
			rootNode.set("children", chapterNodes);

			List<CampScreeningDetailDTO> lstScrDet = DataUtil.getCampScreeningDetail("ChapterName", "id", String.valueOf(chapterNameDTO.getId()));
			for (CampScreeningDetailDTO screeningDetailDTO : lstScrDet)
			{
				ModelData scrNode = new BaseModelData();
				scrNode.set("name", screeningDetailDTO.toString());
				scrNode.set("id", String.valueOf(screeningDetailDTO.getId()));
				scrNode.set("icon", "screeningIndividual");
				chapterNodes.add(scrNode);
			}
		}

		return nodes;
	}
}
