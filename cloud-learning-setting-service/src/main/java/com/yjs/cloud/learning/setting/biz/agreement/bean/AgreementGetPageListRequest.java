package com.yjs.cloud.learning.setting.biz.agreement.bean;

import com.yjs.cloud.learning.setting.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 协议文章
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class AgreementGetPageListRequest extends PageRequest {

}
