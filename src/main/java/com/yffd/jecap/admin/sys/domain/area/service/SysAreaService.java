package com.yffd.jecap.admin.sys.domain.area.service;

import com.yffd.jecap.admin.base.dao.IBaseDao;
import com.yffd.jecap.admin.base.exception.DataExistException;
import com.yffd.jecap.admin.sys.domain.area.entity.SysArea;
import com.yffd.jecap.admin.sys.domain.area.repo.ISysAreaRepo;
import com.yffd.jecap.admin.sys.domain.area.valobj.SysAreaTree;
import com.yffd.jecap.admin.sys.domain.constant.SysConsts;
import com.yffd.jecap.admin.sys.domain.exception.SysException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class SysAreaService {
    @Autowired private ISysAreaRepo areaRepo;

    private IBaseDao<SysArea> getDao() {
        return this.areaRepo.getAreaDao();
    }

    public void add(SysArea area) {
        if (null == area || StringUtils.isBlank(area.getAreaName())) throw SysException.cast("名称不能为空");
        if (null != this.queryByAreaName(area.getAreaName())) throw DataExistException.cast("名称已存在");
        this.getDao().addBy(area);
    }

    /**
     * 添加根节点
     * @param area
     */
    public void addRoot(SysArea area) {
        if (null == area || StringUtils.isBlank(area.getAreaName())) throw SysException.cast("名称不能为空");
        if (null != this.queryByAreaName(area.getAreaName())) throw DataExistException.cast("名称已存在");
        area.setPid(SysConsts.DEF_TREE_ROOT_ID);
        this.getDao().addBy(area);
    }

    /**
     * 添加子节点
     * @param area
     */
    @Transactional
    public void addChild(SysArea area) {
        if (null == area || StringUtils.isBlank(area.getAreaName())) throw SysException.cast("名称不能为空");
        if (null != this.queryByAreaName(area.getAreaName())) throw DataExistException.cast("名称已存在");
        SysArea parent = this.getDao().queryById(area.getPid());
        if (null == parent) throw SysException.cast("父ID不存在");
        if (StringUtils.isBlank(parent.getPath())) {
            area.setPath(parent.getId());
        } else {
            area.setPath(parent.getPath() + "," + parent.getId());
        }
        this.getDao().addBy(area);
    }

    /**
     * 只修改当前节点的信息，不包括子节点
     * @param area
     */
    public void updateById(SysArea area) {
        if (null == area || StringUtils.isBlank(area.getId())) return;
        area.setPid(null);//父ID不可修改
        if (StringUtils.isNotBlank(area.getAreaName())) {
            SysArea entity = this.queryByAreaName(area.getAreaName());
            if (null != entity && !entity.getId().equals(area.getId())) {
                throw SysException.cast(String.format("名称【%s】已存在", area.getAreaName())).prompt();
            }
        }
        this.getDao().modifyById(area);
    }

    /**
     * 修改当前节点信息，以及其子节点的path
     * @param area
     * @param pid
     */
    @Transactional
    public void updateById(SysArea area, String pid) {
        if (null == area || StringUtils.isBlank(area.getId())) return;
        SysArea curNode = this.getDao().queryById(area.getId());
        if (null == curNode) return;
        if (StringUtils.isBlank(pid)) {
            this.updateById(area);//修改当前节点信息
        } else if (!pid.equals(curNode.getPid())) {//变更父节点
            SysArea parent = this.getDao().queryById(pid);
            if (null == parent) throw SysException.cast("父ID不存在");
            String curPath = curNode.getPath();
            String newPath = StringUtils.isBlank(parent.getPath()) ? parent.getId() : parent.getPath() + "," + parent.getId();
            area.setPath(newPath);
            area.setPid(parent.getId());
            if (StringUtils.isNotBlank(area.getAreaName())) {
                SysArea entity = this.queryByAreaName(area.getAreaName());
                if (null != entity && !entity.getId().equals(area.getId())) {
                    throw SysException.cast(String.format("名称【%s】已存在", area.getAreaName())).prompt();
                }
            }
            this.getDao().modifyById(area);//修改当前节点信息

            //更改子节点path、pid
            List<SysArea> children = this.queryByPath(curNode.getPath() + "," + curNode.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                for (SysArea child : children) {
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
    public void deleteById(String areaId) {
        if (null == areaId) return;
        SysArea area = this.queryById(areaId);
        if (null == area) return;
        if (StringUtils.isNotBlank(area.getPath())) {
            this.areaRepo.removeByPath(area.getPath() + "," + areaId);
            this.getDao().removeById(areaId);
        } else {
            this.areaRepo.removeByPath(areaId);
            this.getDao().removeById(areaId);
        }
    }

    public List<SysArea> queryByPath(String path) {
        if (StringUtils.isBlank(path)) return Collections.EMPTY_LIST;
        SysArea entity = new SysArea();
        entity.setPath(path);
        return this.getDao().queryList(entity);
    }

    public SysArea queryById(String areaId) {
        if (StringUtils.isBlank(areaId)) return null;
        return this.getDao().queryById(areaId);
    }

    public List<SysArea> queryByAreaLevel(String areaLevel) {
        if (StringUtils.isBlank(areaLevel)) return Collections.emptyList();
        return this.getDao().queryList(new SysArea(areaLevel, null));
    }

    public SysArea queryByAreaName(String areaName) {
        if (StringUtils.isBlank(areaName)) return null;
        return this.areaRepo.queryByAreaName(areaName);
    }

    public List<SysArea> queryChildren(String areaId) {
        if (StringUtils.isBlank(areaId)) return Collections.EMPTY_LIST;
        SysArea entity = this.getDao().queryById(areaId);
        if (null == entity || StringUtils.isBlank(entity.getPath())) return Collections.EMPTY_LIST;
        return this.queryByPath(entity.getPath() + "," + entity.getId());
    }

    public List<SysArea> queryAll() {
        return this.getDao().queryAll();
    }

    public List<SysArea> queryProvince() {
        return this.queryByAreaLevel("2");
    }

    public List<SysArea> queryCity() {
        return this.queryByAreaLevel("3");
    }

    public List<SysArea> queryCounty() {
        return this.queryByAreaLevel("4");
    }

    /**
     * 查询所有树
     * @return
     */
    public List<SysAreaTree> queryTree() {
        List<SysArea> list = this.getDao().queryList(new SysArea());
        SysAreaTree tree = SysAreaTree.buildTree(list);
        if (null == tree) return Collections.EMPTY_LIST;
        return tree.getChildren();
    }

    /**
     * 查询单棵树
     * @param areaId
     * @return
     */
    public SysAreaTree queryTree(String areaId) {
        if (StringUtils.isBlank(areaId)) return null;
        SysArea entity = this.getDao().queryById(areaId);
        if (null == entity) return null;
        String path = entity.getPath();
        if (StringUtils.isBlank(path)) path = entity.getId();
        List<SysArea> children = this.queryByPath(path);
        return SysAreaTree.buildTree(children, entity);
    }


}
