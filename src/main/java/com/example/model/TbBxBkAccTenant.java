/**
* Created by Mybatis Generator on 2024-07-05 16:42:36
*/

package com.example.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * tb_bx_bk_acc_tenant
 * 账号表：租户信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TbBxBkAccTenant implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * ID
     */
    private Long id;

    /**
     * TENANT_UID
     * 租户UID
     */
    private String tenantUid;

    /**
     * NAME
     * 租户名称
     */
    private String name;

    /**
     * PROFESSION
     * 企业行业
     */
    private String profession;

    /**
     * IS_DELETED
     * 删除状态: 0未删除、1已删除
     */
    private Byte isDeleted;

    /**
     * CREATE_TIME
     * 创建时间
     */
    private Long createTime;

    /**
     * UPDATE_TIME
     * 更新时间
     */
    private Long updateTime;

    /**
     * STATUS
     * 租户状态：-1:禁用；0:空异常；1:正常；2:过期
     */
    private Long status;
}