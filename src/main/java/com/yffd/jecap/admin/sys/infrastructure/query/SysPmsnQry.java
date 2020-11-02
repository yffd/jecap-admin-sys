package com.yffd.jecap.admin.sys.infrastructure.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yffd.jecap.admin.base.helper.PageHelper;
import com.yffd.jecap.admin.base.page.PageData;
import com.yffd.jecap.admin.sys.domain.pmsn.entity.SysPmsn;
import com.yffd.jecap.admin.sys.infrastructure.dao.ISysPmsnDao;
import com.yffd.jecap.admin.sys.infrastructure.query.param.PmsnQryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysPmsnQry {
    @Autowired private ISysPmsnDao pmsnDao;

    public PageData<SysPmsn> queryBy(PmsnQryParam model) {
        if (null == model) return null;
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(model.getPmsnName())) wrapper.like("pmsn_name", model.getPmsnName());
        if (StringUtils.isNotBlank(model.getPmsnCode())) wrapper.eq("pmsn_code", model.getPmsnCode());
        if (StringUtils.isNotBlank(model.getPmsnType())) wrapper.eq("pmsn_type", model.getPmsnType());
        if (StringUtils.isNotBlank(model.getPmsnStatus())) wrapper.eq("pmsn_status", model.getPmsnStatus());
        wrapper.orderByDesc("pmsn_add_time");
        Page<SysPmsn> page = this.pmsnDao.selectPage(PageHelper.toPage(model), wrapper);
        return PageHelper.fromPage(page);
    }

}
