package org.hscoder.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * flash 跳转样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/flash")

public class FlashController {

    private static final Logger logger = LoggerFactory.getLogger(FlashController.class);

    /**
     * 执行跳转，并设置传值
     *
     * @param counter
     * @param response
     * @return
     */
    @GetMapping("/first")
    public String first(final RedirectAttributes redirectAttrs) {

        logger.info("redirect start:{}");

        redirectAttrs.addFlashAttribute("flash", UUID.randomUUID().toString());
        return "redirect:/flash/second";
    }

    /**
     * 获取传值
     * 
     * @param session
     * @param response
     * @return
     */
    @GetMapping("/second")
    @ResponseBody
    public String second(@ModelAttribute("flash") String flash) {

        // Map<String, ?> map = RequestContextUtils.getInputFlashMap(null);

        logger.info("redirect receive {}", flash);
        return flash;
    }

}
