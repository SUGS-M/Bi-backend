package com.backend.project.service.impl;

import com.backend.project.common.ErrorCode;
import com.backend.project.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.project.model.entity.Chart;
import com.backend.project.service.ChartService;
import com.backend.project.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author 18599
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
* @createDate 2023-07-27 11:00:59
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

    @Override
    public void validChart(Chart chart, boolean add) {
        if (chart == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//        String goal = chart.getGoal();
//        String data = chart.getChartData();
//        String type = chart.getChartType();
//        String chartName = chart.getName();
//
//        // 创建时，所有参数必须非空
//        if (add) {
//            if (StringUtils.isAnyBlank(goal, data, type, chartName)) {
//                throw new BusinessException(ErrorCode.PARAMS_ERROR);
//            }
//        }
//        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
//        }
//        if (reviewStatus != null && !PostReviewStatusEnum.getValues().contains(reviewStatus)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        if (age != null && (age < 18 || age > 100)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "年龄不符合要求");
//        }
//        if (gender != null && !PostGenderEnum.getValues().contains(gender)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "性别不符合要求");
//        }

    }
}




