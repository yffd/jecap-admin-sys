package com.yffd.jecap.admin.sys.domain.dict.entity;

import com.yffd.jecap.admin.base.entity.IBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-字典属性表
 * </p>
 *
 * @author ZhangST
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SysDictProps implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 字典ID
     */
    private String dictId;

    /**
     * 属性名称
     */
    private String propsName;

    /**
     * 属性编号
     */
    private String propsCode;

    /**
     * 属性值
     */
    private String propsValue;

    /**
     * 属性类型，1=简单类型、2=复杂类型-JSON
     */
    private String propsType;


}
