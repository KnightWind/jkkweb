package com.jkkp.modules.system.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.jkkp.modules.system.model.Label;


public interface LabelMapper extends Mapper<Label> {
  public List<Label> getByName();

public List<Label> queryLabelById(Map<String, Object> map);

public List<Label> queryAllLabelById(Map<String, Object> map);
}