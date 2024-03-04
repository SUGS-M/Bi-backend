package com.backend.project.service;

import com.backend.project.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18599
* @description 针对表【chart(图表信息表)】的数据库操作Service
* @createDate 2023-07-27 11:00:59
*/
public interface ChartService extends IService<Chart> {
    void validChart(Chart chart, boolean add);

}
