package com.backend.project.manage;

import com.backend.project.common.ErrorCode;
import com.backend.project.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 1-引入依赖[sdk]，2-通过配置注入对象[access-key,secret-key]，【3-构造请求参数，4-获取相应结果】封装工具类
 */
@Service
public class AiManager {
    @Resource
    private YuCongMingClient client;

    public String doChat(Long modelId, String message){
        //3-构造请求参数
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
        //4-获取相应结果
        BaseResponse<DevChatResponse> response = client.doChat(devChatRequest);
        //System.out.println(response.getData());
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
}
