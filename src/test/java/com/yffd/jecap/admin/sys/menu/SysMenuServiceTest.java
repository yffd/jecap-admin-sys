package com.yffd.jecap.admin.sys.menu;

import com.alibaba.fastjson.JSON;
import com.yffd.jecap.admin.sys.BaseTest;
import com.yffd.jecap.admin.sys.domain.menu.entity.SysMenu;
import com.yffd.jecap.admin.sys.domain.menu.service.SysMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysMenuServiceTest extends BaseTest {
    @Autowired private SysMenuService menuService;

    @Test
    public void addRootTest() {
        SysMenu menu = new SysMenu("根菜单");
        this.menuService.addRoot(menu);
    }

    @Test
    public void addChildTest() {
        {
            SysMenu menu = new SysMenu("系统管理");
            menu.setParentId("1017");
            this.menuService.addChild(menu);

            SysMenu menu1 = new SysMenu("用户管理");
            menu1.setParentId(menu.getMenuId());
            this.menuService.addChild(menu1);

            SysMenu menu2 = new SysMenu("角色管理");
            menu2.setParentId(menu.getMenuId());
            this.menuService.addChild(menu2);

            SysMenu menu3 = new SysMenu("菜单管理");
            menu3.setParentId(menu.getMenuId());
            this.menuService.addChild(menu3);
        }

        {
            SysMenu menu1 = new SysMenu("一级菜单-00");
            menu1.setParentId("1017");
            this.menuService.addChild(menu1);

            SysMenu menu2 = new SysMenu("二级菜单-00");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单-01");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单-02");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单-03");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            SysMenu menu3 = new SysMenu("三级菜单-00");
            menu3.setParentId(menu2.getMenuId());
            this.menuService.addChild(menu3);
        }

        {
            SysMenu menu1 = new SysMenu("一级菜单00");
            menu1.setParentId("1017");
            this.menuService.addChild(menu1);

            SysMenu menu2 = new SysMenu("二级菜单00");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单01");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单02");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            menu2 = new SysMenu("二级菜单03");
            menu2.setParentId(menu1.getMenuId());
            this.menuService.addChild(menu2);

            SysMenu menu3 = new SysMenu("三级菜单00");
            menu3.setParentId(menu2.getMenuId());
            this.menuService.addChild(menu3);
        }
    }


    @Test
    public void queryListTest() {
        List<SysMenu> menus = this.menuService.queryAll();
        System.out.println(JSON.toJSONString(menus));
        System.out.println(menus.size());
    }


}
