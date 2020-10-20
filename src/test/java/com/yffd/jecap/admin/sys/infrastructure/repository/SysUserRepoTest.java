package com.yffd.jecap.admin.sys.infrastructure.repository;

import com.yffd.jecap.admin.sys.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class SysUserRepoTest extends BaseTest {
    @Autowired private SysUserRepo userRepo;

    @Test
    public void removeGroupTest() {
        Set<String> groupIds = new HashSet<>();
        groupIds.add("qq");
        groupIds.add("bb");
        this.userRepo.removeGroup("123", groupIds);
    }

    @Test
    public void removeJobTest() {
        Set<String> jobIds = new HashSet<>();
        jobIds.add("qq");
        jobIds.add("bb");
        this.userRepo.removeJob("123", jobIds);
    }
}
