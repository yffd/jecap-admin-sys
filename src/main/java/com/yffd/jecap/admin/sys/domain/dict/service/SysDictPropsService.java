package com.yffd.jecap.admin.sys.domain.dict.service;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDictProps;
import com.yffd.jecap.admin.sys.domain.dict.repo.ISysDictRepo;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SysDictPropsService {
    @Autowired
    private ISysDictRepo dictRepo;

    private IBaseDao<SysDictProps> getDao() {
        return this.dictRepo.getDictPropsDao();
    }

    public void add(SysDictProps props) {
        if (null == props || StringUtils.isAnyBlank(props.getDictId(), props.getPropsName()))
            throw SysException.cast("字典ID | 属性名称 不能为空").prompt();
        this.getDao().addBy(props);
    }

    public void updateById(SysDictProps props) {
        if (null == props || StringUtils.isBlank(props.getId())) return;
        this.getDao().modifyById(props);
    }

    public void deleteById(String propsId) {
        if (StringUtils.isBlank(propsId)) return;
        this.getDao().removeById(propsId);
    }

    public void deleteByDictId(String dictId) {
        if (StringUtils.isBlank(dictId)) return;
        SysDictProps props = new SysDictProps();
        props.setDictId(dictId);
        this.getDao().removeBy(props);
    }

    public SysDictProps queryById(String propsId) {
        if (StringUtils.isBlank(propsId)) return null;
        return this.getDao().queryById(propsId);
    }

    public List<SysDictProps> queryByDictId(String dictId) {
        if (StringUtils.isBlank(dictId)) return Collections.emptyList();
        SysDictProps props = new SysDictProps();
        props.setDictId(dictId);
        return this.getDao().queryList(props);
    }

}
