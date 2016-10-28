package com.dividend.model;

import java.util.List;

public interface Dividend_interface {
    public void insert(DividendVO dividendVO);
    public void update(DividendVO dividendVO);
    public void delete(DividendVO dividendVO);
    public DividendVO findByPrimaryKey(DividendVO dividendVO);
    public List<DividendVO> getAll();
}
