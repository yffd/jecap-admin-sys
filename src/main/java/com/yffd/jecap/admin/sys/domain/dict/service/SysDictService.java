package com.yffd.jecap.admin.sys.domain.dict.service;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.exception.DataExistException;
import com.yffd.jecap.admin.sys.domain.constant.SysConsts;
import com.yffd.jecap.admin.sys.domain.dict.entity.SysDict;
import com.yffd.jecap.admin.sys.domain.dict.repo.ISysDictRepo;
import com.yffd.jecap.admin.sys.domain.dict.valobj.SysDictTree;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class SysDictService {
    @Autowired private ISysDictRepo dictRepo;

    private IBaseDao<SysDict> getDao() {
        return this.dictRepo.getDictDao();
    }

    public void add(SysDict dict) {
        if (null == dict || StringUtils.isBlank(dict.getItemCode())) throw SysException.cast("编号不能为空");
        if (this.existByItemCode(dict.getItemCode())) throw DataExistException.cast("编号已存在");
        this.getDao().addBy(dict);
    }

    /**
     * 添加根节点
     * @param dict
     */
    public void addRoot(SysDict dict) {
        if (null == dict) throw SysException.cast("编号不能为空");
        if (this.existByItemCode(dict.getItemCode())) throw DataExistException.cast("编号已存在");
        dict.setPid(SysConsts.DEF_TREE_ROOT_ID);
        this.getDao().addBy(dict);
    }

    /**
     * 添加子节点
     * @param dict
     */
    @Transactional
    public void addChild(SysDict dict) {
        if (null == dict || StringUtils.isBlank(dict.getItemCode())) throw SysException.cast("编号不能为空");
        if (this.existByItemCode(dict.getItemCode())) throw DataExistException.cast("编号已存在");
        SysDict parent = this.getDao().queryById(dict.getPid());
        if (null == parent) throw SysException.cast("父ID不存在");
        if (StringUtils.isBlank(parent.getPath())) {
            dict.setPath(parent.getId());
        } else {
            dict.setPath(parent.getPath() + "," + parent.getId());
        }
        this.getDao().addBy(dict);
    }

    /**
     * 只修改当前节点的信息，不包括子节点
     * @param dict
     */
    public void updateById(SysDict dict) {
        if (null == dict || StringUtils.isBlank(dict.getId())) return;
        dict.setPid(null);//父ID不可修改
        if (StringUtils.isNotBlank(dict.getItemCode())) {
            SysDict entity = this.queryByItemCode(dict.getItemCode());
            if (null != entity && !entity.getId().equals(dict.getId())) {
                throw SysException.cast(String.format("编号【%s】已存在", dict.getItemCode())).prompt();
            }
        }
        this.getDao().modifyById(dict);
    }

    /**
     * 修改当前节点信息，以及其子节点的path
     * @param dict
     * @param pid
     */
    @Transactional
    public void updateById(SysDict dict, String pid) {
        if (null == dict || StringUtils.isBlank(dict.getId())) return;
        SysDict curNode = this.getDao().queryById(dict.getId());
        if (null == curNode) return;
        if (StringUtils.isBlank(pid)) {
            this.updateById(dict);//修改当前节点信息
        } else if (!pid.equals(curNode.getPid())) {//变更父节点
            SysDict parent = this.getDao().queryById(pid);
            if (null == parent) throw SysException.cast("父ID不存在");
            String curPath = curNode.getPath();
            String newPath = StringUtils.isBlank(parent.getPath()) ? parent.getId() : parent.getPath() + "," + parent.getId();
            dict.setPath(newPath);
            dict.setPid(parent.getId());
            if (StringUtils.isNotBlank(dict.getItemCode())) {
                SysDict entity = this.queryByItemCode(dict.getItemCode());
                if (null != entity && !entity.getId().equals(dict.getId())) {
                    throw SysException.cast(String.format("编号【%s】已存在", dict.getItemCode())).prompt();
                }
            }
            this.getDao().modifyById(dict);//修改当前节点信息

            //更改子节点path、pid
            List<SysDict> children = this.queryByPath(curNode.getPath() + "," + curNode.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                for (SysDict child : children) {
                    if (null == child || StringUtils.isBlank(child.getPath())) continue;
                    String suffix = child.getPath().substring(curPath.length() + 1);
                    String tmp = newPath + (StringUtils.isBlank(suffix) ? "" : "," + suffix);
                    child.setPath(tmp);

                    String _pid = child.getPath().substring(child.getPath().lastIndexOf(",") + 1);
                    child.setPid(_pid);
                    this.getDao().modifyById(child);//修改当前节点信息
                }
            }
        }
    }

    @Transactional
    public void deleteById(String dictId) {
        if (null == dictId) return;
        SysDict dict = this.queryById(dictId);
        if (null == dict) return;
        if (StringUtils.isNotBlank(dict.getPath())) {
            this.dictRepo.removeByPath(dict.getPath() + "," + dictId);
            this.getDao().removeById(dictId);
        } else {
            this.dictRepo.removeByPath(dictId);
            this.getDao().removeById(dictId);
        }
    }

    public List<SysDict> queryChildren(String dictId) {
        if (StringUtils.isBlank(dictId)) return Collections.EMPTY_LIST;
        SysDict entity = this.getDao().queryById(dictId);
        if (null == entity || StringUtils.isBlank(entity.getPath())) return Collections.EMPTY_LIST;
        return this.queryByPath(entity.getPath() + "," + entity.getId());
    }

    public SysDict queryByItemCode(String itemCode) {
        if (StringUtils.isBlank(itemCode)) return null;
        return this.getDao().queryOne(new SysDict(null, itemCode));
    }

    public boolean existByItemCode(String itemCode) {
        return null != this.queryByItemCode(itemCode);
    }

    public SysDict queryById(String dictId) {
        if (StringUtils.isBlank(dictId)) return null;
        return this.getDao().queryById(dictId);
    }

    public List<SysDict> queryByPath(String path) {
        if (StringUtils.isBlank(path)) return Collections.EMPTY_LIST;
        SysDict entity = new SysDict();
        entity.setPath(path);
        return this.getDao().queryList(entity);
    }

    /**
     * 查询所有树
     * @return
     */
    public List<SysDictTree> queryTree() {
        List<SysDict> list = this.getDao().queryAll();
        SysDictTree tree = SysDictTree.buildTree(list);
        if (null == tree) return Collections.EMPTY_LIST;
        return tree.getChildren();
    }

    /**
     * 查询单棵树
     * @param dictId
     * @return
     */
    public SysDictTree queryTree(String dictId) {
        if (StringUtils.isBlank(dictId)) return null;
        SysDict entity = this.getDao().queryById(dictId);
        if (null == entity) return null;
        String path = entity.getPath();
        if (StringUtils.isBlank(path)) path = entity.getId();
        List<SysDict> children = this.queryByPath(path);
        return SysDictTree.buildTree(children, entity);
    }
}
