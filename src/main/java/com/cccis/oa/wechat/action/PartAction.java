package com.cccis.oa.wechat.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cccis.oa.wechat.model.PartModel;

/**
 * Created by CCC on 2016/10/25.
 */
@Controller
@RequestMapping("/part")
public class PartAction {

    private static List<PartModel> partModels;
    private static String key = "29";

    public PartAction() {
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public @ResponseBody
    String apply(@RequestParam final Integer number) {
        int extNum = partModels.size();
        if(number < extNum) {
            extNum = number;
        }
        long s = System.currentTimeMillis();
        long t = System.currentTimeMillis() - s;
        return "Decrypt total " + extNum + ", take time " + t + "(ms)";
    }

}