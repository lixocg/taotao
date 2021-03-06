package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveItem(TbItem item, String desc,String itemParams) throws Exception {
		// 添加商品信息
		TaotaoResult _item = itemService.createItem(item, desc,itemParams);
		return _item;
	}
	
	@RequestMapping("/param/list")
	@ResponseBody
	public EUDataGridResult getParamList(Integer page,Integer rows){
		EUDataGridResult result = itemService.getParamList(page, rows);
		return result;
	} 
	
}
