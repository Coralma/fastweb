package com.cccis.oa.wechat.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cccis.base.decrypt.CDS;
import com.cccis.oa.wechat.model.PartModel;
import com.cccis.oa.wechat.service.GZipReader;

/**
 * Created by CCC on 2016/10/25.
 */
@Controller
@RequestMapping("/part")
public class PartAction {

    private static List<PartModel> partModels;
    private static String key = "29";

    public PartAction() {
        partModels = GZipReader.getParts();
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public @ResponseBody
    String apply(@RequestParam final Integer number) {
        int extNum = partModels.size();
        if(number < extNum) {
            extNum = number;
        }
        long s = System.currentTimeMillis();
        for(int i=0; i < extNum; i++) {
            PartModel partModel = partModels.get(i);
            //System.out.println(partModel);
            String laborType = CDS.decrypt(partModel.getLaborType(), key);
            String texture = CDS.decrypt(partModel.getTexture(), key);
            String repairPartFlag = CDS.decrypt(partModel.getRepairPartFlag(), key);
            String paintFlag = CDS.decrypt(partModel.getPaintFlag(), key);
            String lossDegree = CDS.decrypt(partModel.getLossDegree(), key);
            String easyAbradedFlag = CDS.decrypt(partModel.getEasyAbradedFlag(), key);
            String assemblyPartFlag = CDS.decrypt(partModel.getAssemblyPartFlag(), key);
            /*System.out.println("PartName:"+ partModel.getPartName() + ", laborType:" +laborType + " , texture:" + texture +" , repairPartFlag:" + repairPartFlag + " , paintFlag:"
                    + paintFlag + " , lossDegree:"+lossDegree + " , easyAbradedFlag:" + easyAbradedFlag+ " , assemblyPartFlag:" + assemblyPartFlag);*/
        }
        long t = System.currentTimeMillis() - s;
        return "Decrypt total " + extNum + ", take time " + t + "(ms)";
    }

}