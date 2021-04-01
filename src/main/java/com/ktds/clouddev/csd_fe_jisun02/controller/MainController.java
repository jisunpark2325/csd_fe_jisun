package com.ktds.clouddev.csd_fe_jisun02.controller;

import com.ktds.clouddev.csd_fe_jisun02.dto.TestBusi;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jisun/fe")
public class MainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/businessMgmt")
    @ResponseBody
    public void businessMgmt(@RequestBody TestBusi busi){
        log.info(busi.toString());
    }
}
