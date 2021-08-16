package priv.austin.common.entity.base;

import lombok.Getter;

/**
 * @author Austin
 * @description 常用错误码
 * @date 2021/8/16 2:27 下午
 */
@Getter
public enum ErrorCode implements IErrorCode{
    SYS_INNER_ERROR("XX", "系统内部异常"),

    /**
     * 流量控制 - 1
     */

    SYS_OUT_OF("11", "当前系统繁忙,请稍后再试。"),

    SYS_CTL_FLOW("12", "触发系统限流处理"),
    SYS_CTL_DEGRADE("13", "触发系统降级处理"),
    SYS_CTL_PARAM_FLOW("14", "触发热点参数限流"),
    SYS_CTL_SYSTEM_BLOCK("15", "触发系统规则限制"),
    SYS_CTL_AUTHORITY("16", "触发授权规则限制"),

    /**
     * 解包 - 2
     */
    SYS_VERTIFY_FAIL("20", "验签失败"),

    REQ_PARAM_FORMAT_FAIL("21", "请求体格式有误"),

    URL_ERROR("22", "请求地址格式错误"),

    ENCRYPTINFO_ERROR("23", "敏感信息解密失败"),

    /**
     * 如是内部系统发送的报文，说明是bug需要修改如果是上、下游外部系统，联系对方开发人员。
     */
    UNPACKAGE_ERROR("24", "解包失败"),

    ACCNO_NOT_DIFFERENT("26", "和原交易卡号不一致"),

    SYS_SIGN_NOTNULL("27", "签名值不能为空"),

    PARAM_TYPE_NULL("28", "类型转换错误"),

//
    /**
     * 组包 - 3
     */
    SYS_SIGN_ERROR("30", "Html组装失败"),

    SYS_DGTLENVLP_FAIL("31","数字信封组装失败"),

    SYS_ENCRYPTINFO_FAIL("32","敏感信息加密失败"),

    PACK_ERROR("3Z","组包失败"),

    /**
     * 风控 -4
     */
    AMT_EXCEED("40", "单笔金额超出限制"),

    AMT_TODAY_EXCEED("41", "交易金额超过每日合计限额"),

    AMT_MONTH_EXCEED("42", "交易金额超过每月合计限额"),

    NUM_TODAY_EXCEED("43", "单日交易笔数达到上限"),

    NUM_MONTH_EXCEED("44", "单月交易笔数达到上限"),

    /**
     * 认证 - 5
     */
    UNAUTHORIZED_BIZ("50", "未授权中台"),

    UNAUTHORIZED_BIZ_ORG("51", "未授权机构"),

    UNAUTHORIZED_BIZ_ORG_NULL("52", "机构不合法"),

    UNAUTHORIZED_BIZ_ORG_IP("53", "机构IP不合法"),

    UNAUTHORIZED_BIZ_MER("54", "找不到对应商户或商户未开通"),

    UNAUTHORIZED_BIZ_BIZTYPE("55", "业务类型不合法"),

    UNAUTHORIZED_BIZ_ORG_MER("56", "提交的商户不属于当前机构"),

    /**
     * 路由 - 6
     */
    UNAUTHORIZED_BIZ_ROUTER_NOTROUTER("60", "找不到路由"),

    UNAUTHORIZED_BIZ_ROUTER_NOTMER("61", "路由相关配置缺失"),
    //
    UNAUTHORIZED_BIZ_ROUTER_BIZTSINFO("62", "路由相关核心参数配置缺失"),
    UNAUTHORIZED_TRADE_TYPE("63", "此渠道不支持此交易类型"),

    UNAUTHORIZED_NO_AVAILABLE_IDC("64", "渠道无可用IDC"),

    TRADE_TYPE_AND_CHANNEL_ROUTER_NO_MATCH("65", "当前交易类型与渠道参数配置不符合"),

    MIDEND_TRADE_CODE_NO_MATCH("66", "交易码或交易类型不符"),


    /**
     * 持久化 - 7
     */
    ADD_JNLS_ERROR("70", "数据库操作失败"),
    ORG_TRACE_ERROR("71", "交易流水不合法"),

    /**
     * 报文收发 - 8
     */
    HTTP_UNKNOWN_EXC("80","http返回码非200或未知错误"),

    HTTP_CONN_EXC("81", "通讯连接超时"),

    HTTP_READ_EXC("82", "读写超时"),

    CHANNEL_PIC_ID_ERROR("84", "请求后台渠道不存在"),

    CHANNEL_ERROR("85", "请求后台渠道异常"),

    PARAM_ERROR("86", "请求参数有误"),

    REQ_SERIAL_REPEAT("87", "请求流水号重复"),

    PARAM_NOT_NULL("88", "必填字段不能为空"),

    RIBBON_CLIENT_ERROR("89", "ribbon客户端异常"),


    /**
     * 业务逻辑处理 - 9
     */
    // 后台核心的注释掉, 留待以后重构 // TODO
    // ORI_TS_JNLS_NOT_FOUND("90", "未查询到原交易流水"),
    // 下面是中台新加
    UNAUTHORIZED_BIZ_PAYTOKEN("90", "找不到相关对应支付标识"),

    ORI_TS_JNLS_ERROR("91", "查询原交易流水失败"),

    ORI_TS_STATE_ERROR("92", "原交易非成功状态"),

    REFUND_GT_CONSUME_ERROR("93", "退款金额超过限额"),

    ORI_PAY_TOKEN_NOFOUND("94", "未查询到协议号"),

    ORG_MERCORD_ERROR("95", "与原交易商户号不一致"),

    ORI_TS_JNLS_NOT_FOUND("96", "未查询到原交易流水"),
    ORI_TS_JNLS_NOT_SUPPORT_REFUND("97", "当前交易不支持退款"),
    BIZ_AMT_NOT_SAME("9A", "交易金额与原订单金额不一致"),
    BIZ_REPEAT_TRADE("9B", "重复交易"),
    ORI_TS_JNLS_NOT_FOUND_FORQUERY("9C", "查询请求未查询到原交易流水"),

    BIZ_INSTALMGMT_INFO_NOT_FOUND("9D", "未查询到分期配置信息"),

    BIZ_CAPTCHA_HAS_EXPIRED("9E", "短信验证码已超过有效时间或者原订单信息有误"),

    BIZ_VERIFICATION_CODE_ERROR("9F", "短信验证码错误"),

    BIZ_VERIFICATION_CODE_AGREEMENTPAY("9G", "纳入风控限制,请先触发短信验证请求"),

    ORI_TS_JNLS_BAD_STATE("9H", "原交易状态不允许当前操作"),

    ;

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
