package com.hthyaq.zybadmin.model.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.zybadmin.common.constants.GlobalConstants;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GlobalResult {
    /**
     * 标志位，true-成功、false-失败
     */
    private Boolean flag;
    private Integer code;
    private String msg;
    private Object realData;

    public static GlobalResult success() {
        return new GlobalResult()
                .setFlag(GlobalConstants.TRUE)
                .setCode(GlobalConstants.SUCCESS)
                .setMsg(GlobalConstants.SUCCESS_MSG);
    }

    public static GlobalResult success(String msg) {
        return success().setMsg(msg);
    }


    public static <T> GlobalResult success(Object data) {
        GlobalResult globalResult = success();
        if (data instanceof Page) {
            Page<T> page = (Page<T>) data;
            int pageSize = (int) page.getSize();
            int totalCount = (int) page.getTotal();
            //计算总页数
            int pageCount = totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1);
            PageData<T> pageData = new PageData<>((int) page.getCurrent(), pageSize, totalCount, pageCount, page.getRecords());
            return globalResult.setRealData(pageData);
        } else {
            return globalResult.setRealData(data);
        }
    }

    public static GlobalResult success(String msg, Object data) {
        return success(data).setMsg(msg);
    }

    public static GlobalResult fail() {
        return new GlobalResult()
                .setFlag(GlobalConstants.FALSE)
                .setCode(GlobalConstants.FAIL)
                .setMsg(GlobalConstants.FAIL_MSG);
    }

    public static GlobalResult fail(String msg) {
        return fail().setMsg(msg);
    }

}