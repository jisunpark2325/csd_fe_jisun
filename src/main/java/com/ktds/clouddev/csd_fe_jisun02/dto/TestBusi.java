package com.ktds.clouddev.csd_fe_jisun02.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor

public class TestBusi {
    private int busi_code;
    private String busi_num;
    private String busi_name;
    private String busi_admin_name;
    private String busi_admin_type;
    private String busi_admin_phone;
    private String busi_admin_email;
    private String busi_date;

    public TestBusi(){
        super();
    }

    public TestBusi(String busi_name){
        this.busi_name = busi_name;
    }
    public TestBusi(String busi_num, String busi_name, String busi_admin_name, String busi_admin_type, String busi_admin_phone, String busi_admin_email, String busi_date){
        this.busi_num = busi_num;
        this.busi_name = busi_name;
        this.busi_admin_name = busi_admin_name;
        this.busi_admin_type = busi_admin_type;
        this.busi_admin_phone = busi_admin_phone;
        this.busi_admin_email = busi_admin_email;
        this.busi_date = busi_date;
    }

}

