package com.mgr.model;

import java.util.List;
import java.util.Set;

import com.stock.model.StockVO;

public interface MGRDAO_interface {
		public void insert (MGRVO mgrVO);
		public void update (MGRVO mgrVO);
		public void delete (MGRVO mgrVO);
		public void deleteByStockNo (Integer stockNo);
		public MGRVO FindByPrimaryKey(MGRVO mgrVO);
		public List<MGRVO> getAll();
		public List<MGRVO> getByStockNo(Integer stockNo);
}
