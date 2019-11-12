package org.hscoder.springboot.redis;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * cookie 读写样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/session")

@SessionAttributes("seed")
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    /**
     * 通过注解获取
     *
     * @param counter
     * @param response
     * @return
     */
    @GetMapping("/some")
    @ResponseBody
    public String someSession(@SessionAttribute(value = "seed", required = false) Integer seed, Model model) {

        logger.info("seed:{}", seed);

        if (seed == null) {
            seed = (int) (Math.random() * 10000);
        } else {
            seed += 1;
        }
        model.addAttribute("seed", seed);

        return seed + "";
    }

    /**
     * 获取全部会话值
     * 
     * @param session
     * @param response
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> allSessions(HttpSession session) {

        Map<String, Object> valueMap = new HashMap<String, Object>();

        Enumeration<String> iSession = session.getAttributeNames();

        while (iSession.hasMoreElements()) {
            String sessionName = iSession.nextElement();

            Object sessionValue = session.getAttribute(sessionName);

            valueMap.put(sessionName, sessionValue);
            logger.info("sessoin[{}]={}", sessionName, sessionValue);
        }

        // 写入session
        session.setAttribute("timestmap", new Date());
        return new ResponseEntity<Map<String, Object>>(valueMap, HttpStatus.OK);
    }

    /**
     * 清除全部会话值
     * 
     * @param session
     * @param response
     * @return
     */
    @GetMapping("/clear")
    public ResponseEntity<Map<String, Object>> clearSessions(HttpSession session) {

        Map<String, Object> valueMap = new HashMap<String, Object>();

        Enumeration<String> iSession = session.getAttributeNames();

        while (iSession.hasMoreElements()) {
            String sessionName = iSession.nextElement();

            Object sessionValue = session.getAttribute(sessionName);

            valueMap.put(sessionName, sessionValue);
            logger.info("sessoin[{}]={}", sessionName, sessionValue);

            session.removeAttribute(sessionName);
        }

        return new ResponseEntity<Map<String, Object>>(valueMap, HttpStatus.OK);
    }
}
