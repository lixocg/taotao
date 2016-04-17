package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);

	EUDataGridResult getItemList(int page, int rows);

	void saveItem(TbItem item, String desc, String itemParams) throws Exception;

	TaotaoResult createItem(TbItem item, String desc,String itemParam) throws Exception;

	EUDataGridResult getParamList(Integer page, Integer rows);

}
