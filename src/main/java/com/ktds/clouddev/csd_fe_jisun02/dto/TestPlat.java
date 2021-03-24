package com.ktds.clouddev.csd_fe_jisun02.dto;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
@Data
@EntityScan
public class TestPlat {

    private int plat_code;
    private String plat_name;

    public TestPlat() {
        super();
    }

    public TestPlat(String plat_name) {
        this.plat_name = plat_name;
    }

}
