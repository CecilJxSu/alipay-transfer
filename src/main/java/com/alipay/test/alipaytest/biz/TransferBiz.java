package com.alipay.test.alipaytest.biz;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 支付宝转账参数
 *
 * @author 苏金兴
 * @date 2019-09-17
 */
public class TransferBiz {

    @JsonProperty("out_biz_no")
    private String outBizNo;

    @JsonProperty("payee_type")
    private String payeeType;

    @JsonProperty("payee_account")
    private String payeeAccount;

    private String amount;

    @JsonProperty("payer_show_name")
    private String payerShowName;

    @JsonProperty("payee_real_name")
    private String payeeRealName;

    private String remark;

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayerShowName() {
        return payerShowName;
    }

    public void setPayerShowName(String payerShowName) {
        this.payerShowName = payerShowName;
    }

    public String getPayeeRealName() {
        return payeeRealName;
    }

    public void setPayeeRealName(String payeeRealName) {
        this.payeeRealName = payeeRealName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
