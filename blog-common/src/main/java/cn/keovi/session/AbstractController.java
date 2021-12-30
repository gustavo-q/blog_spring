package cn.keovi.session;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    public SessionTemplate sessionTemplate;

    protected UserSession getUser() {
        //获取用户信息 根据票据
        return sessionTemplate.getUserSession();
    }


    protected Long getUserId() {
       return sessionTemplate.getUserSession().getId();
    }
    protected String gwticket() {
        return sessionTemplate.getTicket();
    }

    protected void saveUserSession(String ticket, UserSession userSession) {
        sessionTemplate.saveUserSession(ticket,userSession);
    }
    protected void deleteUserSession(String ticket) {
        sessionTemplate.deleteUserSession(ticket);
    }


//    protected void setUserSession(UserSession userSession) {
//        sessionTemplate.saveUserSession(ticket,userSession);
//    }

}
