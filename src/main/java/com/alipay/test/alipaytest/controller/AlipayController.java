package com.alipay.test.alipaytest.controller;

import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.test.alipaytest.biz.TransferBiz;
import com.alipay.test.alipaytest.req.TransferReq;
import com.alipay.test.alipaytest.util.JsonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author 苏金兴
 * @date
 */
@RestController
@RequestMapping("api")
public class AlipayController {

//    private final String host = "https://openapi.alipay.com/gateway.do";

    private final String basePath = "/opt/config/cert/";

    @PostMapping("transfer")
    public AlipayFundTransToaccountTransferResponse transfer(@RequestBody TransferReq req) throws Exception {
        // 构造 alipay client
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(req.getGateway());
        certAlipayRequest.setAppId(req.getAppId());
        certAlipayRequest.setPrivateKey(req.getPrivateKey());
        certAlipayRequest.setFormat("json");
        certAlipayRequest.setCharset("utf-8");
        certAlipayRequest.setSignType("RSA2");
        certAlipayRequest.setCertPath(basePath + req.getAppCertPath());
        certAlipayRequest.setAlipayPublicCertPath(basePath + req.getAlipayCertPath());
        certAlipayRequest.setRootCertPath(basePath + req.getAlipayRootCertPath());
        // 创建 alipay client
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);

        // 设置业务参数
        TransferBiz biz = new TransferBiz();
        biz.setOutBizNo(req.getOutBizNo());
        biz.setPayeeType(req.getPayeeType());
        biz.setPayeeAccount(req.getPayeeAccount());
        biz.setAmount(req.getAmount());
        biz.setPayerShowName(req.getPayerShowName());
        biz.setPayeeRealName(req.getPayeeRealName());
        biz.setRemark(req.getRemark());
        // 序列化成 JSON 字符串
        String bizContent = JsonUtils.beanToJson(biz);

        // 构建请求
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent(bizContent);

        // 请求和响应
        AlipayFundTransToaccountTransferResponse response = alipayClient.certificateExecute(request);
        return response;
    }
}
