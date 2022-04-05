/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : blog_spring

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 05/04/2022 03:42:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `top` int(1) NULL DEFAULT 0 COMMENT '0 不置顶  1 置顶',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0 未删除  1  删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, 'test', '1231', 0, 0, '2022-03-30 22:46:41', 8, '2022-03-30 22:46:54', 8);
INSERT INTO `announcement` VALUES (2, '切切切', 'wrwr', 0, 0, '2022-03-30 23:05:09', 8, NULL, NULL);
INSERT INTO `announcement` VALUES (3, 'e', 'eww', 0, 1, '2022-03-30 23:05:13', 8, NULL, NULL);
INSERT INTO `announcement` VALUES (4, '第一条正式的公告', '今天的公告，凡是注册了该博客的用户，都是有眼光的，有前途的靓仔靓女。我希望有朝一日，同各位共同实现自己心中所思，达成梦里所想。', 1, 0, '2022-03-30 23:05:16', 8, '2022-03-30 23:29:14', 8);
INSERT INTO `announcement` VALUES (5, 'ee', 'ee', 1, 0, '2022-03-30 23:05:19', 8, NULL, NULL);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `category_id` int(10) NULL DEFAULT NULL COMMENT '分类id',
  `views` int(20) NULL DEFAULT 0 COMMENT '浏览量',
  `status` int(1) NULL DEFAULT NULL COMMENT '0：草稿   1：发布 2:审核中 3审核失败',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `top` int(10) NULL DEFAULT NULL COMMENT '置顶',
  `comment_enabled` int(1) NULL DEFAULT NULL COMMENT '评论 0否 1是',
  `appreciation` int(15) NULL DEFAULT 0 COMMENT '赞赏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (33, 'SpringBoot实现过滤器、拦截器与切片', '过滤器Filter过滤器概念Filter是J2E中来的，可以看做是Servlet的一种“加强版”，它主要用于对用户请求进行预处理和后处理，拥有一个典型的处理链。Filter也可以对用户请求生成响应，这一点与Servlet相同，但实际上很少会使用Filter向用户请求生成响应。\n使用Filter完整的流程是：Filter对用户请求进行预处理，接着将请求交给Servlet进行预处理并生成响应，最后Filter再对服务器响应进行后处理。过滤器作用\n在JavaDoc中给出了几种过滤器的作用\n\nExamples that have been identified for this design are\n1) Authentication Filters, 即用户访问权限过滤\n2) Logging and Auditing Filters, 日志过滤，可以记录特殊用户的特殊请求的记录等\n3) Image conversion Filters\n4) Data compression Filters\n5) Encryption Filters\n6) Tokenizing Filters\n7) Filters that trigger resource access events\n8) XSL/T filters\n9) Mime-type chain Filter\n对于第一条，即使用Filter作权限过滤，其可以这么实现：定义一个Filter，获取每个客户端发起的请求URL，与当前用户无权限访问的URL列表（可以是从DB中取出）作对比，起到权限过滤的作用。过滤器实现方式\n自定义的过滤器都必须实现javax.Servlet.Filter接口，并重写接口中定义的三个方法：\n1.void init(FilterConfig config)\n用于完成Filter的初始化。\n2.void destory()\n用于Filter销毁前，完成某些资源的回收。\n3.void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)\n实现过滤功能，即对每个请求及响应增加的额外的预处理和后处理。,执行该方法之前，即对用户请求进行预处理；执行该方法之后，即对服务器响应进行后处理。\n值得注意的是，chain.doFilter()方法执行之前为预处理阶段，该方法执行结束即代表用户的请求已经得到控制器处理。因此，如果在doFilter中忘记调用chain.doFilter()方法，则用户的请求将得不到处理。\n\n\n\n\n1\n\nimport org.slf4j.Logger;\n\n\n2\n\nimport org.slf4j.LoggerFactory;\n\n\n3\n\nimport org.springframework.stereotype.Component;\n\n\n4\n\n\n\n\n5\n\nimport javax.servlet.*;\n\n\n6\n\nimport javax.servlet.http.HttpServletRequest;\n\n\n7\n\nimport java.io.IOException;\n\n\n8\n\n\n\n\n9\n\n// 必须添加注解，springmvc通过web.xml配置\n\n\n10\n\n@Component\n\n\n11\n\npublic class TimeFilter implements Filter {\n\n\n12\n\n    private static final Logger LOG = LoggerFactory.getLogger(TimeFilter.class);\n\n\n13\n\n\n\n\n14\n\n    @Override\n\n\n15\n\n    public void init(FilterConfig filterConfig) throws ServletException {\n\n\n16\n\n        LOG.info(\"初始化过滤器：{}\", filterConfig.getFilterName());\n\n\n17\n\n    }\n\n\n18\n\n\n\n\n19\n\n    @Override\n\n\n20\n\n    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {\n\n\n21\n\n        LOG.info(\"start to doFilter\");\n\n\n22\n\n        long startTime = System.currentTimeMillis();\n\n\n23\n\n        chain.doFilter(request, response);\n\n\n24\n\n        long endTime = System.currentTimeMillis();\n\n\n25\n\n        LOG.info(\"the request of {} consumes {}ms.\", getUrlFrom(request), (endTime - startTime));\n\n\n26\n\n        LOG.info(\"end to doFilter\");\n\n\n27\n\n    }\n\n\n28\n\n\n\n\n29\n\n    @Override\n\n\n30\n\n    public void destroy() {\n\n\n31\n\n        LOG.info(\"销毁过滤器\");\n\n\n32\n\n    }\n\n\n33\n\n\n\n\n34\n\n    private String getUrlFrom(ServletRequest servletRequest){\n\n\n35\n\n        if (servletRequest instanceof HttpServletRequest){\n\n\n36\n\n            return ((HttpServletRequest) servletRequest).getRequestURL().toString();\n\n\n37\n\n        }\n\n\n38\n\n\n\n\n39\n\n        return \"\";\n\n\n40\n\n    }\n\n\n41\n\n}\n从代码中可看出，类Filter是在javax.servlet.*中，因此可以看出，过滤器的一个很大的局限性在于，其不能够知道当前用户的请求是被哪个控制器(Controller)处理的，因为后者是spring框架中定义的。在SpringBoot中注册第三方过滤器\n对于SpringMvc，可以通过在web.xml中注册过滤器。但在SpringBoot中不存在web.xml，此时如果引用的某个jar包中的过滤器，且这个过滤器在实现时没有使用@Component标识为Spring Bean，则这个过滤器将不会生效。\n此时需要通过java代码去注册这个过滤器。以上面定义的TimeFilter为例，当去掉类注解@Component时，注册方式为：\n\n\n\n\n1\n\n@Configuration\n\n\n2\n\npublic class WebConfig {\n\n\n3\n\n    /**\n\n\n4\n\n     * 注册第三方过滤器\n\n\n5\n\n     * 功能与spring mvc中通过配置web.xml相同\n\n\n6\n\n     * @return\n\n\n7\n\n     */\n\n\n8\n\n    @Bean\n\n\n9\n\n    public FilterRegistrationBean thirdFilter(){\n\n\n10\n\n        ThirdPartFilter thirdPartFilter = new ThirdPartFilter();\n\n\n11\n\n        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;\n\n\n12\n\n\n\n\n13\n\n        filterRegistrationBean.setFilter(thirdPartFilter);\n\n\n14\n\n        List<String > urls = new ArrayList<>();\n\n\n15\n\n        // 匹配所有请求路径\n\n\n16\n\n        urls.add(\"/*\");\n\n\n17\n\n        filterRegistrationBean.setUrlPatterns(urls);\n\n\n18\n\n\n\n\n19\n\n        return filterRegistrationBean;\n\n\n20\n\n    }\n\n\n21\n\n}\n相比使用@Component注解，这种配置方式有个优点，即可以自由配置拦截的URL。拦截器Interceptor拦截器概念\n拦截器，在AOP(Aspect-Oriented Programming)中用于在某个方法或字段被访问之前，进行拦截，然后在之前或之后加入某些操作。拦截是AOP的一种实现策略。拦截器作用\n\n日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等\n\n权限检查：如登录检测，进入处理器检测检测是否登录\n\n性能监控：通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间。（反向代理，如apache也可以自动记录）；\n\n通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现。拦截器实现\n通过实现HandlerInterceptor接口，并重写该接口的三个方法来实现拦截器的自定义:\n1.preHandler(HttpServletRequest request, HttpServletResponse response, Object handler)\n方法将在请求处理之前进行调用。SpringMVC中的Interceptor同Filter一样都是链式调用。每个Interceptor的调用会依据它的声明顺序依次执行，而且最先执行的都是Interceptor中的preHandle方法，所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。\n该方法的返回值是布尔值Boolean 类型的，当它返回为false时，表示请求结束，后续的Interceptor和Controller都不会再执行；当返回值为true时就会继续调用下一个Interceptor 的preHandle 方法，如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller 方法。\n2.postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)\n在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。\n3.afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex)\n该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。\n\n\n\n\n1\n\n@Component\n\n\n2\n\npublic class TimeInterceptor implements HandlerInterceptor {\n\n\n3\n\n    private static final Logger LOG = LoggerFactory.getLogger(TimeInterceptor.class);\n\n\n4\n\n    @Override\n\n\n5\n\n    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n\n\n6\n\n        LOG.info(\"在请求处理之前进行调用（Controller方法调用之前）\");\n\n\n7\n\n        request.setAttribute(\"startTime\", System.currentTimeMillis());\n\n\n8\n\n        HandlerMethod handlerMethod = (HandlerMethod) handler;\n\n\n9\n\n        LOG.info(\"controller object is {}\", handlerMethod.getBean().getClass().getName());\n\n\n10\n\n        LOG.info(\"controller method is {}\", handlerMethod.getMethod());\n\n\n11\n\n\n\n\n12\n\n        // 需要返回true，否则请求不会被控制器处理\n\n\n13\n\n        return true;\n\n\n14\n\n    }\n\n\n15\n\n\n\n\n16\n\n    @Override\n\n\n17\n\n    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {\n\n\n18\n\n        LOG.info(\"请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后），如果异常发生，则该方法不会被调用\");\n\n\n19\n\n    }\n\n\n20\n\n\n\n\n21\n\n    @Override\n\n\n22\n\n    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {\n\n\n23\n\n        LOG.info(\"在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）\");\n\n\n24\n\n        long startTime = (long) request.getAttribute(\"startTime\");\n\n\n25\n\n        LOG.info(\"time consume is {}\", System.currentTimeMillis() - startTime);\n\n\n26\n\n    }\n与过滤器不同的是，拦截器使用@Component修饰后，在SpringBoot中还需要通过实现WebMvcConfigurer手动注册：\n\n\n\n\n1\n\n// java配置类\n\n\n2\n\n@Configuration\n\n\n3\n\npublic class WebConfig implements WebMvcConfigurer {\n\n\n4\n\n    @Autowired\n\n\n5\n\n    private TimeInterceptor timeInterceptor;\n\n\n6\n\n\n\n\n7\n\n    @Override\n\n\n8\n\n    public void addInterceptors(InterceptorRegistry registry){\n\n\n9\n\n        registry.addInterceptor(timeInterceptor);\n\n\n10\n\n    }\n\n\n11\n\n}\n如果是在SpringMVC中，则需要通过xml文件配置<mvc:interceptors>节点信息。切片Aspect切片概述\n相比过滤器，拦截器能够知道用户发出的请求最终被哪个控制器处理，但是拦截器还有一个明显的不足，即不能够获取request的参数以及控制器处理之后的response。所以就有了切片的用武之地了。切片实现\n切片的实现需要注意@Aspect,@Component以及@Around这三个注解的使用，详细查看官方文档：\ndocs.spring.io/spring/docs/5.0.12.RELEASE/spring-framework-reference/core.html#aop\n\n\n\n\n1\n\n@Aspect\n\n\n2\n\n@Component\n\n\n3\n\npublic class TimeAspect {\n\n\n4\n\n    private static final Logger LOG = LoggerFactory.getLogger(TimeAspect.class);\n\n\n5\n\n\n\n\n6\n\n    @Around(\"execution(* me.ifight.controller.*.*(..))\")\n\n\n7\n\n    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{\n\n\n8\n\n        LOG.info(\"切片开始。。。\");\n\n\n9\n\n        long startTime = System.currentTimeMillis();\n\n\n10\n\n\n\n\n11\n\n        // 获取请求入参\n\n\n12\n\n        Object[] args = proceedingJoinPoint.getArgs();\n\n\n13\n\n        Arrays.stream(args).forEach(arg -> LOG.info(\"arg is {}\", arg));\n\n\n14\n\n\n\n\n15\n\n        // 获取相应\n\n\n16\n\n        Object response = proceedingJoinPoint.proceed();\n\n\n17\n\n\n\n\n18\n\n        long endTime = System.currentTimeMillis();\n\n\n19\n\n        LOG.info(\"请求:{}, 耗时{}ms\", proceedingJoinPoint.getSignature(), (endTime - startTime));\n\n\n20\n\n        LOG.info(\"切片结束。。。\");\n\n\n21\n\n        return null;\n\n\n22\n\n    }\n\n\n23\n\n}过滤器、拦截器以及切片的调用顺序\n如下图，展示了三者的调用顺序Filter->Intercepto->Aspect->Controller。相反的是，当Controller抛出的异常的处理顺序则是从内到外的。因此我们总是定义一个注解@ControllerAdvice去统一处理控制器抛出的异常。\n如果一旦异常被@ControllerAdvice处理了，则调用拦截器的afterCompletion方法的参数Exception ex就为空了。\n\n实际执行的调用栈也说明了这一点：\n\n而对于过滤器和拦截器详细的调用顺序如下图：\n过滤器和拦截器的区别\n最后有必要再说说过滤器和拦截器二者之间的区别：\n\n除此之外，相比过滤器，拦截器能够“看到”用户的请求具体是被Spring框架的哪个控制器所处理。', 4, 2, 1, 0, '2022-01-29 14:48:22', '2022-03-30 23:46:39', 8, 8, 1, 1, 1);
INSERT INTO `article` VALUES (35, '我爱你', '![IMG_1507.JPG](http://180.163.101.78:9000/public/c58e196c3b264b1c894392df9eedd30b_IMG_1507.JPG)![6987t8g.jpg](http://180.163.101.78:9000/public/1581326251c849668f7997296abf7556_6987t8g.jpg)', 4, 4, 1, 0, '2022-01-31 00:55:19', '2022-01-31 00:58:22', 8, 8, 1, 0, 0);
INSERT INTO `article` VALUES (36, 'test1111', '::: hljs-center\n\n# 今天\nfsdf发送到啥地方说点啥电风扇啥地方***^::: hljs-left\n\n::: hljs-center\n\n::: hljs-right\n\nfsdf::: hljs-left\n\n居左\n\n:::\n\n\n:::\n\n\n:::\n\n\n:::\n^***\n ## 我看见一个小矮人\n==##### 标记==\n\n###### 范德萨发\n\n:::\n', 4, 17, 1, 0, '2022-02-23 14:27:23', '2022-03-24 22:13:40', 21, 8, 1, 1, 1);
INSERT INTO `article` VALUES (38, '三四十', '范德萨发撒地方fff### 三级标题', 5, 99, 1, 0, '2022-03-28 23:42:38', NULL, 8, NULL, 1, 1, 1);
INSERT INTO `article` VALUES (39, 'tsfsdx', 'woaini', 5, 0, 0, 0, '2022-03-30 15:36:53', '2022-03-30 15:47:16', 8, 8, 1, 1, 1);
INSERT INTO `article` VALUES (40, 'f', 'f', 2, 4, 1, 0, '2022-03-30 15:44:40', NULL, 8, NULL, 0, 0, 0);
INSERT INTO `article` VALUES (42, 'zq', 'zq', 7, 9, 1, 0, '2022-03-30 17:01:15', NULL, 25, NULL, 1, 1, 1);
INSERT INTO `article` VALUES (43, 'f', 'f![photo_20210420_143458.jpg](http://180.163.101.78:9000/public/8501528540cc4267bb6eda5a4f5464c2_photo_2021-04-20_14-34-58.jpg)', 6, 69, 1, 0, '2022-03-30 19:43:24', '2022-03-30 23:51:21', 8, 8, 1, 1, 1);
INSERT INTO `article` VALUES (44, '初见·初恋', '楔子\n\n五月的风，吹散了春末的最后一抹寒意，又吹皱了未名湖的一池碧波。粼粼的波光之间，几只杂色的野鸭追逐嬉戏，沿着河滩的砂石坡道上，另有鸥鸟闲庭信步，春江水暖，景色宜人。\n\n阿素仰面朝天，微风抚过她侧脸娇俏的轮廓，又轻轻撩起她的长发，肆意地飞舞着。几绺俏皮的发丝，挂落在阿素长长的睫毛边缘，她没有立即伸手拨开，任凭它们在额间飞舞。她今天的妆容一如既往地精致，妆前面膜、水、乳、精华、粉底、遮瑕、防晒喷雾，当然还有她的挚爱香奈儿五号口红，复杂如是的工序一道也没有落下。阿素对于穿衣打扮的严谨仅次于她的事业心，没能在这个特别的日子穿上她最为钟意的那件波西米亚长裙出门或许是她最大的遗憾。\n\n我向阿素挪近了两步，伸出一只手，轻轻握住她的柔荑，阿素转过脸，挣扎良久，有些话终究没有说出口，红唇微动，执拗地弯出了一道浅浅的笑容。\n\n或是被我任性的举动挑动了神经，身后的喧嚣声骤然高亢了几分，乌泱泱的围观人群众星捧月一般将我和阿素困在众人视线的焦点。这些人里有看热闹不怕事大的好奇者，他们本意是善良的，即使有一丝丝的幸灾乐祸也仅是出于本能；当然也少不了那些恶毒诅咒过我们的债主们，曾几何时，阿素让他们赚得盆满钵满的时候，他们将阿素供若神明，当着阿素的面，半个屁也不敢放，如今也有了胆量趾高气昂地对阿素指手画脚；离我们不远的位置，一位民警同志正苦口婆心地说着劝导的话，我必须为他的敬业说声对不起，他耐心而且细致，可惜，对于我和阿素这样意志坚定的人而言，他的努力注定将是一场徒劳。\n\n阿素转过脸来看我，我读懂了她的眼神：是时候去做一个了断了。\n\n阿素拉我的手紧了紧，我心领神会，与她对看了一眼，展颜一笑，随即两道身影如同一对翻飞的蝴蝶，毅然决然地从桥边纵身而下。\n\n这不是我所期待的人生，但能和心爱的人相伴而行，终究也算不得落寞。在生命最后的时刻，我俯瞰湛蓝背景下的阿素，阿素自在的表情与湖面融为一体，她的嘴唇又动了动，我的耳朵被呼呼的风声遮挡，不过这一次她只说了两个字，我恰巧读懂了她的唇形，她说：“再见。”\n\n我心中莫名地一揪，记忆电光火石、闪转腾挪，曾记得，我与阿素的初见也是在五月。\n\n1、\n\n虽才是初夏，正午的阳光已变得热辣起来，连累着园区广场上的行人也没了踪迹。我悠闲地坐在商贸广场西侧背阴的露天咖啡馆的藤制长椅上，点了一杯廉价的卡布基诺，享受着午后片刻的闲散时光。\n\n园区的咖啡从咖啡豆到浇盖的奶油都充斥着敷衍的气息，可我还是愿意花费速溶咖啡十倍的价钱逃离那个让人窒息的办公室，听一听风的躁动，瞧几眼偶尔被风掀起的春色。\n\n从对面商务楼走出来的女孩子，多似眼高于顶的天鹅，眉宇之间透着与薪水极不相称的自信，又或是踩着五公分至七公分不等的高跟鞋，在大理石的地面上砸出与她们敲击机械键盘打字时韵律相符的撞击声。她们服装考究，妆容精致，腰杆笔直，走路飒飒有风，额头和胸部都高高且平行地挺拔着。如我一般，薪水单上的铅打数字只能勉强高出她们几十个百分点的男人永远都不要指望会出现在她们的约会名单里，菜鸟互啄，浪费的只有生命，换一种文艺点的说法就是：即便同是身在地狱，层次也会大有不同。\n\n真正的女神，自带另一类的清新脱俗，不用浓妆艳抹、挺直身板，贵气同样逼人，不同于小家碧玉的故作姿态，那是一种不靠装点的雍容。这样的女子同样不会对我这样的穷小子多看上一眼，但她们不会挑动我荷尔蒙的涌动，谨守本分是蝼蚁与生俱来的觉悟，我也不是例外。\n\n我的对面正有一个这样的女子款款而来，长发微卷随风轻摇，一如微波荡漾，一袭深色的中性套装完美地衬出她无可挑剔的曲线，但我脑中竟陡得生出一个莫名的念头，如果这时候她能搭配一条带褶皱连体长裙当属绝配。她脚步轻盈，手臂自由摆动，既不显拘束也不太放肆，真真的恰到好处。\n\n我下意识地把腿收了收，顺手拉了拉上衣的襟口，我这么做的目的完全是出于一个自卑者的本能，并不恣意她能多瞧上我一眼。可是，她不但瞧了我，竟还飘飘然地坐到了我的面前。手足无措和心猿意马像一对凶恶的暴徒兄弟，他们一齐对我发起了猛攻，我的防守溃不成军，拳头在我的脸上肆意挥洒，焦透了皮，滚滚发烫。\n\n“我叫安素，安静的安，朴素的素，你还不认识我，我是你未来的妻子”说这话的时候，她特意亮了亮无名指上的婚戒，似乎急于证明些什么。\n\n“什么？”我无处安放的慌乱暂时被解救了出来，取而代之是莫名的惊诧。\n\n“你没有听错，我，安素，是你未来的妻子。”她轻抚额头，撇了撇嘴，露出一副不屑的表情。\n\n“美女，我们是第一次见面，你大概是认错人了吧？”我变得警惕起来，这般有气质的酒托实属罕见。\n\n“我长得不好看？”她答非所问，情绪显然有些激动。\n\n我不知所措地摇了摇头。\n\n“你觉得我配不上你？”她继续不依不饶。\n\n我木讷地看着她，仿佛遇见了一头怪兽。\n\n“你有女朋友？”她换了一种狐疑的态度。\n\n我点了点头，但马上又补充道：“但和这事没有关系。”\n\n“怎么能没有关系？你过去可不是这么说的，你这个渣男。”她的情绪陡然激烈起来，脸上堆积起愤怒。\n\n我愣在当时，一万个问号大军在脑中肆意奔腾。\n\n“袁野，你真是个王八蛋。”这个名叫安素的女子不顾体面地拍着桌子站了起来。\n\n我被她的气势吓到了，目瞪口呆地问她：“为什么你会知道我的名字？”\n\n她没有回答我的问题，而是踩着椅子，一跃跳上桌面，在我反应过来之前，甩起一脚重重地踢在了我的脸上。\n\n我没有受伤，也没有昏厥，而是一个打挺从床上坐起身，耳边传来熟悉的闹铃，我放眼四顾，简陋的家具、老旧的电视机还有散落一地的袜子和漫画书，没错，这是我的卧室，我不禁暗自庆幸：谢天谢地，只是一场噩梦而已。\n\n关掉闹铃，女友佳慧的月野兔的头像占据了手机屏幕的中央的位置欢快地舞动：“阳光明媚的一天，要加油哦！”讯息的末尾又加上了一串可爱的小红心。\n\n我满意地笑了笑，回复：“知道了，包租婆，我会努力挣钱，争取早日攒够嫁妆，把你打包回家的，爱你哦！”学着她发信息的样子，我在末尾加了一串舔狗。\n\n我放下手机，刚刚换好上衣，佳慧的信息又回了过来：“晚上有要紧事和你商议，下班之后，老地方见，不见不散!”\n\n要紧事？我微微愣了一下，什么要紧事？脑袋里莫名地又浮现出那个名叫安素的女子的身影。我用力搓了搓脸上的起床气，随即又回了佳慧的短讯：“遵命，女皇陛下！”\n\n2、\n\n兵荒马乱了一整天，错过了班车、漏发了邮件、填岔了报表，所有的坏运气都集中到了一处，我把这些无妄之灾归罪于那个扰人清梦的陌生女子，当然佳慧所说的“重要的事”也难辞其咎。\n\n我和佳慧是一年前认识的，我俩参加了同一期的相亲联谊会，她在超市做出纳，收入一般，长相不很出挑，瘦瘦小小的，得益于性格天真率直，在我眼里很是讨喜。我的条件与佳慧大体相仿，长相马马虎虎，收入也马马虎虎，毕业之后应聘在一家外贸公司做海外销售，入行三年，业绩平平，升职遥遥无期。\n\n说起来，我和佳慧都到了谈婚论嫁的年纪，但在结婚这个问题上，我们俩又都心照不宣地选择了沉默，不是不想，我的条件摆在那里，得等一个水到渠成。在我看来，结婚就像是往储蓄罐里存钱，今天存一点，明天攒一点，等哪天储蓄罐里挤得满满当当再容不下一个子的时候，咣当一声砸开，那便是时候到了。\n\n晚上八点，我准时出现在了老地方，老地方真就叫“老地方”，是一个缩在犄角旮旯里的烧烤摊，老板手艺精湛，兼营中式的川菜和西式的铁板牛排，我常取笑他不务正业，不能专心经营，老板对我嗤之以鼻，意味深长地教育我，让我不要死心眼，要懂得鸡蛋不能只放在一个篮子里的道理。\n\n佳慧当天到得很晚，她来时我已经和老板尬聊了两个小时，其间我拨打了她两次电话，都是忙音。她坐下时一脸地疲惫和歉意，说是临下班时，总部临时通知清点库存，所有员工都上缴了手机，所以没能及时通知我，害我空等了这么许久。\n\n对于空等，我并不特别介意，看着她辛苦奔波的样子，反而隐隐有些心疼。\n\n“过几天，我妈要来，我和她提起过你，她这次过来想顺道见见你。”佳慧说这话的时候，故意不去看我，用力地盘了盘手指，脸上的表情比来时更加羞涩。\n\n“哦，好呀。”我一边撸串，一边回答，脸上风轻云淡，心下忐忑难安。\n\n“我妈那人，有些势力眼，可能不太好相处。”佳慧的表情有些沮丧。\n\n“我一定好好表现，争取让咱妈满意。”我特意将“你妈”叫成了“咱妈”，为的只是想让佳慧更安心一些。\n\n佳慧是单亲家庭，父亲很早就因病离世，母女二人相依为命，但是佳慧和她妈的关系算不上好，他妈把她当做下半生的筹码，一直希望佳慧能钓个乘龙快婿让她倚靠，我这不入流的条件自然入不了她的法眼。\n\n“她可能会提起彩礼的事情。”\n\n“没事，穷女婿早晚得见丈母娘，逃得过初一，逃不过十五。”\n\n“她大概会要一个不小的数目。”\n\n“我会尽量满足她的要求，但是你知道，我的收入也不高。”\n\n佳慧沉默了一会，最后像是鼓足了勇气，用坚定的眼神看着我，对我说：“袁野，我知道你是真心对我好，你答应我，不管我妈提出多么过分的要求，都不要和她当面闹翻，你不用操心，剩下的事情，都交给我来解决，可以吗？”\n\n佳慧的话让我特别感动，我认真地点了点头，为了安慰她，我又故作轻松地调侃：“别太担心了，说不准咱妈看我长得帅气，又前途无量，就不为难我了。”\n\n“要真是这样就好了。”佳慧无力地叹了口气，完全没有开玩笑的心思，随即又像是安慰我似的说道：“放心吧，见面的时候，我会坚定地站在你这边帮你说话的，她要是死活不答应，大不了我和你去私奔，让她一个人哭去。”\n\n佳慧虽然说的是气话，但有了她的承诺，那一夜我睡得特别踏实。\n\n3、\n\n安素的一脚正中我的侧脸，刺痛袭来，我连人带椅子重重地翻倒在地板上，倒下的瞬间，胳膊无意间带到了桌面上的咖啡，咖啡杯在空中划出一道优雅的弧线，铿锵落地，碎瓷片撒了一地，土黄色的咖啡四溢乱飞。\n\n安素灵活得像一只小鹿，欢快地踩着椅子，反身轻轻一跃，安然落地，然后没事人一样坐回到我的对面，志得意满地盯着我，嘴角含笑，心情大好。\n\n我愤怒地从地上起身，撸起袖子，意欲发作，脑中一个念头闪过：不是在做梦吗？怎么还演成连续剧？惊诧之余，火气也消了不少。\n\n“怎么了？被我一脚踢坏了脑子，痴傻了。”行凶者肆无忌惮地开着玩笑。\n\n“美女，你到底谁啊？要不是看你长得怪好看的，我一定胖揍你一顿。”我抬手在被踢中的脸颊上揉了揉，娘哎，梦里被踢中也会这么疼吗？太他娘的真实了吧？\n\n“你瞧你这记性，我和你说过的，我叫安素，是你未来的妻子。”\n\n“你是上帝派来考验我的吗？行吧，我和你说，就是在梦里，我也得和你讲得明明白白，我有女朋友，她叫佳慧，我爱她，她也爱我，我和她是真爱，海枯石烂、至死不渝的那种。”\n\n“和我比，谁更好看？”她玩味地看着我，像一个颇有魅力的小狐狸精。\n\n我微微一愣，佳慧的长相满打满算也只能称为小家碧玉，单论颜值，眼前的这位美女明显要更能打，爱情会让人失去理智，而我只是在为爱情赌气，我拧着脖子回道：“当然是我女朋友好看，我跟你说……”\n\n“你犹豫了，所以不算。”她在我说完之前不讲道理地打断了我的解释，趾高气昂的样子让人生厌。\n\n“你们不可能在一起。”她又补充了一句。\n\n“为什么？”\n\n“我最终会和你结婚，虽然你这人长相普通，能力一般，又没什么野心，大家都说我是瞎了眼才看上你，但是没有办法，这就是事实。”\n\n“好吧，你是对的，我配不上你，你现在让我彻底认清了我自己，放心吧，你不会看上我，而我也会选择我的佳慧。”\n\n“你不相信我说的话？”\n\n“我为什么要相信？你不过是我想象中的人物，也许是我马路记忆里的一个剪影，虽然我也不得不承认你非常漂亮，但是，我这人很务实，在现实里，你这样的美女不会和我这样的穷小子有半毛钱的交集。”\n\n“我真没有想到，这时候的你竟然如此自卑，当年追我时候，那个又自信又没脸没皮的袁野和现在这个一身丧气的穷小子竟然是同一个人。好吧，为了证明我说的话，我得放一放大招了。你叫袁野，你爸袁大狗，你妈是个中俄混血，名字太长，记不住了，你96年出身，处女座，临海大学毕业，学的是国际贸易，毕业之后做产品外销，因为做事注重原则，做人又死板，经常得罪人，花了整整六年时间才爬上部门经理的位置，升职之后倒是顺风顺水，踩了狗屎运一样，被你们集团老总器重，一路平步青云。”\n\n“我真的能升职吗？”我觉得升职这事若是真的倒不是件坏事。\n\n“瞧你这德行，这么点出息。”\n\n“你是我自己幻想出来的人物，知道我的家底不足为奇。”我垂死挣扎。\n\n“你过来，我证明给你看。”\n\n没等我有所反应，安素一把拽住我的领带把我扯到她的面前，在我没有反应过来之前，她的红唇就已经紧紧地映在了我的唇上。\n\n柔软的唇在我的齿前摩挲，口红温润之后粘连在我俩微不可见的距离里，散发出浓烈的荷尔蒙的气息，耳畔萦绕着她轻微地喘息，我深吸一口粗气予以回击，头皮阵阵发麻，脑袋成了摆设，像休克一样空空无物。我被自己想象出来的艳遇迷得神魂颠倒，并且暂时将佳慧放到了一边，这算是背叛吗？不，这只是一场春梦，不用讲伦理道德，我给自己找到了借口，昂起头像一头小兽一样回击，忘乎所以地索取，肆无忌惮地放纵。\n\n第二天，睁开眼的时候，哈喇子湿了半个枕头，终究只是黄粱一梦。我默默地起身，没来由地叹了一口气，这一声叹息到底是遗憾还是失落，我自己也说不清楚。\n\n我在梦中背叛了佳慧，所以，我的报应来了，我把佳慧丢了。\n\n起床之后，我收到了一条来自佳慧的短讯：“袁野，对不起，原谅我的不辞而别，我思前想后，终于下了决定，我走了，不要找我。”\n\n我拨打佳慧的电话，她恰时已经关了机，我又尝试着给她发简讯，消息无法发出，我在她的通讯录里被拉黑。\n\n我的脑中一片空白，昨晚见面时我们还在你侬我侬地谈婚论嫁，仅仅过了一夜怎么就变成了诀别。我理不清里面的头绪，更想不通其中的逻辑。\n\n我不能接受这样一个不明不白的结果，直接去了佳慧工作的超市打听，她的主管告诉我，佳慧上午打电话告知她辞职的事，并且放弃了半个月的薪水。我又慌乱地跑去她租住的房子，恰巧遇到房东带着新租客看房，房东也是大清早收到佳慧的电话退租的，佳慧签的是年租合同，这时候退租，房东便可以名正言顺地吞掉她剩余的租金和押金。\n\n我把佳慧丢了，在这个我初遇她的城市里，丢失了我曾经自以为很了解的那个人，兜兜转转一整圈，她清空了我和她的所有交集。她抛弃了我，义无反顾，没有丝毫地拖泥带水，她放弃了薪水，放弃了房屋押金，只为快刀斩乱麻地断绝和我的联系，各种古怪的想法开始在我的脑中汇聚，现在的她对于我来说竟是如此地陌生。\n\n4、\n\n佳慧为什么不辞而别？显然这不是她一早的图谋，如果她早有准备，那就不会有我们前一晚的对话，而且从她离开时形色匆匆的各种迹象中表明，事情来得极为突然，她的离开充满了慌乱。\n\n佳慧是不是被绑架了？她会不会有危险？我不由得为佳慧的安全担心起来，我想到过要去报警，但又立刻意识到自己完全不具备报警的条件。我和佳慧的关系算什么？曾经的男女朋友，让警方帮我寻找失踪的分手女友，这样的想法简直就是天方夜谭。\n\n从佳慧租住的房里出来的时候，潜意识告诉我，我被人跟踪了。\n\n对方是一个身形彪悍的高个子男人，他隐藏得并不高明。我去佳慧单位打听消息的时候，他就在我旁边，当时他看我的眼神有些不合时宜的惊诧，我当时急于寻找佳慧，并没有放在心上，可当我从佳慧租住的房子出来的时候，又在不远处发现了他躲躲闪闪的身影。\n\n或许是被慌乱冲昏了头脑，又或者是愣头青的冲动占据了主导，我没有任何躲避的意思，径直走向了那个男人，既没有顾虑对方是什么身份，也没有在意对方的危险性。\n\n当我距离对方几步路的距离的时候，对方显然也觉察到了我的意图，不过他并没有任何躲闪的意思，而是颇为尴尬地微笑着目送我走到他的面前。\n\n“你在跟踪我，是吗？”我开门见山。\n\n“你很直接，不过请放心，我们对你没有恶意。”高个子男人如是回答。\n\n“你们？佳慧和你们有什么关系，你们把佳慧怎么样了？”我狠狠地瞪着对方，展露出一副我也并不好惹的样子，这里是闹市区，我丝毫也不担心自己的安全。\n\n“事实上，我们也在寻找佳慧小姐，准确地说，是在找和她在一起的那个男人。”高个男人说着递了一张名片给我。\n\n“男人，什么男人？”对于突然出现的变故，我有些措手不及，慌乱不由自主地表现了出来。直到这时，我始终没有往劈腿上去想，这是我唯一不能承受的结果。\n\n“我们公司有一个刚被辞退的员工名叫梁辰，他离职前偷走了我们一件非常重要的东西，我们有足够的证据可以证明，佳慧小姐和梁辰一起躲了起来，我在佳慧小姐工作的单位调查的时候，恰巧听见了你和她部门主管的对话，恕我冒昧，请问你的姓名以及和佳慧小姐的关系？”高个子男人审讯罪犯一样的语气让我很不舒服。\n\n我瞄了一眼手中的名片，上面写着：XX科技有限公司 保密安全部主管 王栋。\n\n“袁野，我是佳慧的男朋友。”这个回答让我有些促狭，佳慧已经发短讯说要分手，可是在我心里，她并没有当面对我做出过说明，在这之前，分手并不作数。\n\n“啊！”王栋意味深长地感叹了一声，颇有些心照不宣的意味，于是知趣地转移话题道：“袁先生有什么线索吗？比如，佳慧小姐去了哪里？她父母的住址，还有她有没有什么特别要好的朋友？如果有请务必告诉我们，如果线索有足够的价值，我们会为你提供一份价值对等的报酬作为感谢。”\n\n“没有，你说的这些，我完全不知道，恕我爱莫能助。”我没有说谎，只是有些力不从心，他让我觉得想要找到佳慧几乎成了一件无法完成的任务。\n\n“不要灰心，我有种预感，我觉得你一定能找到她将事情问清楚。不多说了，如果有消息，希望你可以联系我们，报酬的事我们是认真的。”\n\n王栋自顾自地说完，又悻悻然地转身离开，他那副自以为是的态度着实让人不愉快，在他转身的一刹那，那张印着他名字的小卡片便被我随手丢进了垃圾桶。\n\n佳慧的离开，让我没有了安心工作的心情，好在外贸业务员有些特权，我胡乱编造了一个客户拜访的借口，并说明之后的几天要出趟远门拜访一个外地的客户，从公司顺利地得到了几天公假，因为这事并不是特例，再加上我在公司的口碑一向很好，所以很容易就敷衍了过去。\n\n我浑浑噩噩地度过了一天，甚至记不起自己去过哪里，等回过神来，天色已晚，我竟不知不觉走到了“老地方”的门口，我随意点了几样烧烤，又要了一箱啤酒，自斟自饮，酒不醉人人自醉，我所需要的不过是一场大梦不醒的宿醉。\n\n老板一边烤着串，一边时不时地将余光瞄向我，我说：“老板，你烤串可得认真点，别东倒西歪地站着，摔倒在炉子上可不是闹着玩的。”\n\n东倒西歪的其实不是老板，我的脸啪得一声拍在酒桌上，瞬间不省人事。\n\n“袁野，袁野！”\n\n我迷迷糊糊听见有人叫我的名字，脑袋里昏昏沉沉的，半醉半醒，听着是个女声，佳慧吗？\n\n我一个激灵睁开了眼，面前坐着安素，老板仍在不远处烤串。\n\n“老张，十串羊肉，十串豆腐，一份烤茄子，茄子要熟，要蒜泥不要辣椒。”\n\n烧烤摊的老板在炉子前挥了挥手，回了句“马上”，我不知道老板究竟是真的姓张，又或是他只在我的梦里被迫姓了张。\n\n“几年后的老张依然在摆摊烧烤，你也常去照顾他的生意，虽然店铺不成个样子，做的炒菜和牛排口碑也很差，但烤串确实是一绝。”安素眨了眨眼睛，像是在追忆一件难忘的往事。\n\n“安素是吧？你说你是从未来来的，是不是？”\n\n“是啊，如假包换。”\n\n“那你应该知道佳慧去了哪里，对不对？”我紧盯着她的眼睛，想以此来判定她说的话是不是真实。\n\n“我不知道，你没有提过。”安素嘟囔着嘴，不像是撒谎，倒像是在赌气。\n\n“你当然不知道，你是我幻想出来的，我真是傻瓜，竟然会奢求你来告诉我答案，我多想听到你告诉我一个结果，哪怕是骗骗我也好。”我像一只泄了气的皮球，蜷缩着身子，眼角竟还擎着泪水。\n\n“我是不知道你的佳慧在哪里，但是，他可能知道，他比我早认识你好些年，应该知道你一些过去的事情。”她一指烧烤摊的老板。\n\n“老张，你知道佳慧去了哪里吗？”我不抱希望地对着烧烤摊喊了一嗓子。\n\n“谁？”烧烤摊那头老板拉扯着烟灰嗓回了我一句。\n\n“佳慧，我女朋友。”\n\n“她呀，知道……”他紧接着说了什么，我没有听清，我想靠近了再问一次，这时候，我的身体一阵剧烈地摇晃，随即便从梦里醒了过来。\n\n“帅哥，收摊了。”烧烤摊的老板一边收拾桌子，一边和我说话。\n\n我迷迷糊糊地站起身，边掏钱包边问他：“多少钱？”\n\n“一百六，老客户了，算你一百，烧烤都没怎么吃，怎么的，我手艺退步了还是你吃腻味了？”老板的语气里颇有些不高兴。\n\n“不是不是，今天心情不好，没有食欲。”我赶忙解释。\n\n“酒可没少喝，怎么了？失恋了？常和你一起过来的那个姑娘今天没来，那姑娘我看着挺不错的，不嫌弃你穷，脾气也好，要不是什么大事，就给人家道个歉，这要是分了，到了我这个年纪，你一准后悔。”老板随手开了一瓶啤酒，给我倒了一杯，自顾自地吹瓶，想来也一定是个有故事的人。\n\n我把佳慧失踪的事情说给他听，他听完摇了摇头，我起身要走，他突然说道：“你昨晚扫码结账的时候，那姑娘接了一个电话，我在旁边隐隐约约听见一个地名。”\n\n“哪里？”我像是抓住了一根救命稻草，摇着他的膀子，死死不肯放手。\n\n“我记得好像是临海市桐山巷，那地方我去过，所以听她提到那个地方时，就记住了。”老板给了我一个肯定的答复。\n\n一个身影出现在我的脑海里，吴悦，佳慧的发小和闺蜜，半年前过来旅游时找过佳慧，我和她匆匆见过一面，而她恰巧就住在临海，我怎么把她给忘了？\n\n“谢了，老张。”我抓起衣服就走。\n\n“喂，你怎么知道我姓张，我可没告诉过你啊？”老张在我身后扯着嗓子喊。\n\n“老地方”的老板竟然真的姓张。\n\n5、\n\n“佳慧没有找过我，她也没有和我说你们俩分手的事情，前天她确实给我打过一个电话，问了我的近况，当时也没有说什么特别的事情，这两天我打她电话，一直都是关机状态，之前我只是以为你们俩闹矛盾，她故意关了机，要不是你来找我，我甚至都不知道她失踪的事情。”吴悦的解释合情合理，我竟找不到理由反驳。\n\n我隔天一早坐了三个小时的高铁赶到临海，等来的却是这样一个结果，实在有些不甘心，却又无可奈何。吴悦在桐山巷开了一家规模不大服装店，找到她并没有花费我多少时间，她当着我的面拨通了佳慧的电话，一样回复对方已关机，这并不让我意外，但当她给我看佳慧简讯信息时，她的信息一样发送不出去，显然她也已被佳慧拉黑。\n\n我落寞地从吴悦的店里离开，我进店的时候尚不知道结果如何，可是当我走出店门离开时，便已可以断定这趟算是没有白来。\n\n其实逻辑很简单，佳慧的手机打不通是合理的，但在简讯上拉黑吴悦就完全说不过去了，吴悦的回答没有破绽，但她的表情过于镇定，以她和佳慧的关系，佳慧发生了这么大的变故，她却依然能够平静地对答如流，并且心安理得地做生意，这本身就是一种反常。\n\n我在桐山巷另找了一家服装店，买了一套不同款式的衣服换上，继续在吴悦的服装店的周围蹲点监视。功夫不负有心人，一个小时之后，吴悦把头从店里探出来四下里张望了一圈，我赶紧将自己藏好，连头都不敢露一下。吴悦快速地锁好门，匆匆转身离去，我起先担心她会打车离开，自己没有专业跟踪的技巧，多半会跟丢。不过天随人愿，她似乎并没有打车的打算，一路步行，路上行人不少，虽然她数次回头张望，始终没能觉察到我在她身后跟随。\n\n弯弯绕绕地走了十分钟左右，吴悦转身进了一个老院子。桐山巷前街是商铺，后街是老式的居住的小院，这样的老街商区在临海很常见。我没有立即跟进院子，吴悦走进院子五分钟之后，我才悄悄摸了进去。院子很老旧，过道里堆满了杂物，我垫着脚尖，小心翼翼地往里走，生怕碰着什么东西，惊动了里面的人。院子里靠门口的几个房门都关着，越往里面走，光线也越发昏暗，我心里有些发毛，但是事关佳慧，我只能强打着精神往里搜索。\n\n靠着最里间的一间房间，房门虚掩着，隐隐有对话的声音从里面传出来，我加倍小心地靠到门边偷听里面的人说话。\n\n“王八蛋，你这么做还有点男人的样子吗？”声音是吴悦的。\n\n“那我也没有办法嘛，都被逼到这个份上了，我能怎么样？”声音是一个陌生男人的，听声音这人约莫四十上下，中气不足。\n\n“你一个大男人，怎么这么没用。”吴悦有些生气，我听见啪地一下，似乎是怒极打了那个男人一下。\n\n“你这个女人怎么回事，还动上手了，我告诉你，我不还手，不代表我打不过你，好男不跟女斗。”\n\n原来是小情人私下见面，我脸上一阵燥热，刚想转身离开，吴悦接下说的话让我把脚黏在了地上，她怒吼着说道：“你怎么能让佳慧一个人去接头？对方可都是玩命之徒，姓梁的，佳慧要是有个三长两短，老娘跟你拼命。”\n\n“佳慧在哪里？你们把佳慧怎么了？”我堵在门口，防止他们逃跑，屋内除了吴悦，另有一个中年男人，和我刚刚猜想得差不多，四十上下，微微有些发福，看到他的长相，我心下不禁腹诽：佳慧和我分手就是为了这样一个男人？\n\n“袁野，你听我说，事情不是你想的那样。”吴悦想要把我从门口推开，我双手撑开，死死顶着门框，将门口堵了个严实，那男人也上来推我，被我一脚揣倒在地上，同时质问他：“你就是梁辰？”\n\n听到我一口说出男人的名字，吴悦和梁胖子同时露出惊讶的表情。\n\n“你怎么知道我的名字，佳慧告诉你的？”男人露出一副幡然醒悟的表情，惊道：“我知道了，你们合起伙来骗我，佳慧这个贱女人，她以为她把我拿捏得死死的，我告诉你们，想诓我的钱，门都没有，我也不怕告诉你们，她手上的那份数据是假的，对方可都是狠角色，要是拿不到真实数据，我可不保证他们能做出什么事情来。”\n\n“你什么意思？你到底让佳慧去和谁接头？佳慧是不是有危险？”我一把抓住梁辰的衣领，他挣扎了几下，见没有效果，于是干脆躺倒在地上不说话。\n\n吴悦急得直跳脚，看来她并不知道梁辰的图谋。梁辰干脆摆出一副死猪不怕开水烫的姿态，他以为我拿他没办法，我便告诉他，是一个叫王栋的男人把他的名字告诉我，并且同时威胁他说，如果他不把佳慧去的交易地点告诉我，我不介意直接打电话把王栋找来，用他在王栋那里换取一份酬劳。\n\n梁辰一听王栋的名字，脸瞬间变得煞白，其实他不知道，王栋的那张名片当时就已经被我丢进了垃圾桶，我根本没办法和王栋联系。这不是重点，重点是我的威胁有了效果，梁辰思量再三，最终说出了事情的经过：\n\n他是XX科技公司新能源项目的负责人之一，新的研究项目成功之后，公司为了节省开支直接将他踢出局。为了报复公司，他伺机窃取了试验数据，并且联系了一家海外的竞品公司，对方委托了一个国内第三方与他交易。也就在这个时候，他盗取公司机密的事情被XX科技公司发现，公司启动了保密安全部的人追查他和泄露出去的资料的下落。\n\n梁辰是在一次去超市采购时认识佳慧的，他对佳慧一见倾心，无论死缠烂打还是软磨硬泡，一律都被佳慧拒绝。这次出逃之前，他将事情的原委告诉了佳慧，并声称，只要完成这次交易，他就能得到一笔一辈子都用不完的钱。佳慧最终同意了和他一起出逃，条件是拿到钱之后分她三分之一，梁胖子对此没有提出异议，之后佳慧建议将交易地点定在临海，吴悦住在那边，有个熟人方便接应，这里面自然有佳慧的私心，但对梁辰而言，这个方案也确实是最保险的一种。\n\n到了交易时间，梁辰又动了脑筋，他说防止对方使诈，他提议让佳慧去接头，确认收到钱之后，他再从网上把资料发给对方。佳慧不放心，梁辰把一个U盘交给了她，梁胖子声称，这个U盘里存有原始数据，如遇危险，佳慧可以交出U盘保命。佳慧当然不知道，梁辰一开始就没打算把真的数据交给她，U盘里的数据缺少了最核心的一段代码，对方拿到手根本就毫无用处，他这么做让自己处于最安全的位置，却把佳慧推到了极其危险的境地。\n\n听完梁胖子的陈述，我气不打一处来，对着死胖子就是一顿老拳，梁胖子虽然皮糙肉厚，压根就不经打，硬扛了两拳之后就开始求饶，吴悦怕我真把这胖子打伤了没法去救佳慧，随便踢了他两脚之后，便把我拉到了一边。\n\n“死胖子，说，交易地点在哪里？”我对这个一肚子坏水的胖子没有半点怜悯。\n\n“我也不知道啊，对方一直是通过我的手机联络的，联系的手机被佳慧带走了。”梁胖子蜷缩着身子，一脸的委屈相。\n\n我照着他脸上又是一拳，梁胖子疼得嗷嗷乱号，我心里暗骂他是个窝囊废，又同时替佳慧不值。\n\n“交易地点在哪里？说还是不说。”我说着话，又给了他一脚。\n\n“我真的不知道。”梁胖子像一只惊弓之鸟，说话的语气里带着哭腔。就在这个时候，他的口袋里传出手机铃声，他慌忙地掏出一部手机看了一眼，劫后余生一般对我说：“是佳慧。”\n\n6、\n\n交易方识破了梁胖子的图谋，要求他带着资料前去见面，并且警告他，如果他胆敢报警或者玩什么花样，对方就会立即将佳慧撕票，之后天涯海角地追杀他。梁胖子这时候反倒没有胆怯，不但答应了对方的要求，还咬死之前说好的交易金额不松口，对方思量再三，最终也答应了他的要求。交易地点定在了二号码头的废弃仓库，一手交钱，一手交货。\n\n我担心梁胖子唯利是图，不会顾忌佳慧的生死，便要求他把资料交给我，让我替他去交易。梁胖子反而害怕我和佳慧卷了交易金跑路，坚持要自己去，两人争执了几句，最终我和他都做了妥协，妥协的结果就是：我和他一起去交易，他负责收钱，我负责救人。\n\n吴悦有一辆二手的奥拓，载着我们去到交易地点，我和梁胖子在隔着二号码头几十米的门外下车，我让吴悦等在外面开着发动机随时接应我们，临下车的时候，我对她挤了挤眼睛，她心领神会地眨了眨眼睛回应，我期待她能体会我挤眼睛的用意。\n\n两个社会人一样的彪形大汉押着我和梁辰进了仓库，一路上我四顾观察地形，规划逃跑路线。令我沮丧的是，这个仓库四面环墙，只有一个出入口，四周倒是有几扇窗户，但是天晓得窗外是什么。我们被带到仓库的三楼，那里有一个被称呼为刚哥的男人正等着我们，刚哥四五十岁的模样，瘦瘦小小的，没有黑墨镜，也没有大金链子，穿着儒雅得体，一副人畜无害的模样。\n\n佳慧缩在仓库的一角，身边另有一个彪形大汉负责看守，我看她时，她恰恰也在抬头看我，那双有些憔悴的眸子骤然一亮，随即又暗淡了下来，她把头压低，不敢再看我。\n\n刚哥给梁辰转了账，然后等待梁胖子交出资料。黄胖子说资料在网盘里，刚哥立刻拿出笔记本电脑，让他现场操作。我被带到佳慧的身边，我和佳慧的命运现在完全掌握在梁辰手里，但凡他藏着一丝花花肠子，那个一身儒生做派的刚哥铁定会毫不犹豫地送我们去喂鱼。\n\n“他们没有为难你吧？”我关切地问佳慧。\n\n佳慧摇了摇头，低声说道：“我做下这么一件事情，一定很让你失望吧？”\n\n“不要紧，一切等从这里出去之后再说。”我拉了拉她的手，安抚她的心情，又四下里观察了一下地形。\n\n“我并不是真的想要和你分手，我也有我的苦衷。”佳慧猛地抬起头，她的眼睛里擎着泪水，哽咽道：“我妈狮子大开口，提了很高的条件，你的情况，我再清楚不过，我当时很纠结，既不能说服我妈，又不想给你造成那么大的负担。这时候梁辰找到了我，他追求了我很久，我对他没有半点好感，但这次，他和我说了这件事情之后，我觉得这是个机会，正所谓富贵险中求，我盘算着等分了我应得的那一份，便再回去找你。”\n\n我不知道该怎么回应佳慧说的话，以我对她的了解，她并没有说谎，她做这件事情也是在替我考虑，可是总觉得有什么东西抵在我的胸口，让我无法说出令人释怀的安慰的话。\n\n“真精彩，人都到齐了。”一个略微有些熟悉的声音打破了仓库三楼的宁静，王栋领着一帮人闯了进来。\n\n“你是跟踪他们到的这里？”刚哥眼睛微微一眯，话语里露出几分狠辣来。\n\n“跟踪？我们公司一连丢了好几份资料，我可是被下了死命令，大家都不过是混口饭吃，各为其主，应该相互理解才好，刚哥，你说是不是这个理？”王栋说话的腔调还是那么让人厌恶，他的身后也跟着一帮身形魁梧大汉，看着都不是善茬。\n\n梁胖子趁刚哥不注意，连滚带爬地躲到王栋的身后，对王栋谄媚道：“王主管，我的表现不错吧，引蛇出洞，这次应该记一个首功。”\n\n王栋似乎也看不上梁胖子的为人，也没去接梁胖子的话茬，只是不耐烦地摆了摆手。\n\n我这时才明白过来，梁辰其实是王栋的人，这一切都是王栋为刚哥预备的陷阱，而佳慧仅仅是他利用的一件工具。王栋四处追查梁胖子的下落也是做戏给刚哥看，为的只是让刚哥相信，梁胖子是真的背叛了公司，偷了公司的数据，真心实意地要和他们交易，从而达到引蛇出洞的目的。\n\n“梁胖子，你个王八蛋，原来你一直都在骗我。”佳慧跳起来，指着梁辰的鼻子叫骂。\n\n“苍蝇不叮无缝的鸡蛋，还不是你自己贪钱，我不坑你坑谁？”梁胖子虽觉得这事做得不够磊落，却没有半点愧疚，他又补充道：“你也不用脑子想想，XX科技这么大的一家公司，那种绝密资料，是我这种人说偷就能偷出来的吗？”\n\n“行，算爷认栽，说吧，你想怎么样？”刚哥自认倒霉，形势比人强，他此时的想法自然是化干戈为玉帛。\n\n“两条路，一条是死路，我拿了你送公检法，按照这套资料的市值，你下半辈子就在监狱里将牢底坐穿。另一条自然是活路，你去XX控股，把他们的核心数据拿出来给我，咱们两清。”王栋摆出一副稳操胜券的姿态来。\n\n“你就这么有信心能留住我？”刚哥拧着脖子狠狠道。\n\n“留不留得住，试过才知道。”王栋针锋相对。\n\n“看样子，你早就为我想好了出路，不过，孙子，你听清楚了，刚哥自打踏进江湖的那天开始，就没有做过二五仔，人在江湖，活的就是个脸面，咱们还得刀口上见真章。”说完，刚哥喊了一声抄家伙，一帮兄弟提着短刀铁棍就跟着他冲向了王栋。王栋不以为意，冷笑一声，手下的兄弟也纷纷拿出了家伙，两拨人马迅速冲撞到了一起。\n\n我一看情形不对，眼下的情形已经远远超出了我这种小人物的控制范围，王栋一方和刚哥的兄弟们缠斗在一起，双方斗得难解难分，各种只有电视上才能见到的械斗场景不断在我的面前上演。\n\n我看准时机，拉起佳慧想着趁乱离开，却不想被原先负责看守佳慧的壮硕汉子拦住了去路。我自恃打架不是那人的对手，也来不及多想，拉起佳慧就往相反的方向跑去，仓库的最内侧靠墙的位置有一扇破了玻璃窗户，那是我之前观察地形时寻找的出路，虽然不知道窗外是什么，此刻也顾不了那么许多，只能死马当活马医。\n\n我们在前面跑，那个壮汉就在后面追，我暗暗将这孙子十八代祖宗骂了遍，他一个混黑社会的，做事情竟然比我一个上班族都敬业？何苦来？\n\n佳慧跟着我跑，等到窗户的面前，这才发现，那窗外根本不是什么出路，楼下是一片广阔的水域，从这三层楼上看下去，十几米的高度，堪堪让人心底发毛。前是绝路，后有追兵，真正的进退维谷。\n\n“佳慧，你会游泳吗？”我看了看佳慧。\n\n“嗯！”她点了点头。\n\n“那我的命可就交在你手里了。”\n\n“什么？”\n\n佳慧还没有理解我说这话的意思，我一咬牙，对着佳慧喊了一声：“跳！”便拉着她纵身一跃，飞身扑向窗外。\n\n我和佳慧一头扎进水里，因为不通水性，我在水中胡乱扑腾，佳慧试图让我冷静下来，却越发激起了我的恐慌，没过多久我的体力渐渐不支，河水不断地灌进我的口鼻，呼吸困难导致我的意识越发地模糊起来。\n\n7、\n\n我彻底放弃了抵抗，四肢完全不听使唤，身体变得很沉，徐徐下坠，阳光穿透河水，在我的眼前映出一个轮廓模糊的金色圆盘。噗通一声，一道黑影从圆盘中间蹿入水中，将那抹靓丽的金色击得粉碎。一张美得不可方物的脸在我的面前越拉越近，水波荡漾起她微卷的长发，粼粼的波光衬得她的眸子格外地闪亮，又折射在她白皙的皮肤上，形成一道道深浅不一的水波纹，煞是好看。\n\n安素拉着我的手，将我拽上岸边，即便是在梦里，她竟有这般力气，也足够让我大跌眼镜。\n\n金色的沙滩从我的脚下一直绵延到视线的尽头，海滩上没有第三个人。我安静地躺着，一面享受着阳光沙滩的惬意，一面欣赏着美人如玉。\n\n“这是在梦里，我竟然可以做到任何我在外面想都不敢想的事情。”安素看出了我的疑惑，狡猾地对我一笑。\n\n我坐起身，是啊，这里并不是现实，只是一个梦。\n\n“我在梦里吗？”\n\n“是啊，我是来和你道别的。”\n\n“你要去哪里？”\n\n安素茫然地看着远方，说道：“我不知道，但我们还会再见的，在不久的将来。”\n\n“我们的将来是怎样的？幸福吗？”\n\n“很幸福，但是也很糟糕。”\n\n“怎么说？”\n\n“你对我很温柔，也很体贴，所以我很幸福。但我性格太过强势，从来不会顾忌你的感受，即便事业因为我的执拗江河日下的时候，我依然我行我素，听不进你的任何建议。我一意孤行地把所有的资金豪赌在一船钻石上，货轮遭遇海难沉没，公司破产，债主上门，糟糕到不能再糟糕了。”\n\n“是个过不去的坎吗？”\n\n“是啊，我们一起跳了湖。”\n\n“结果呢？”\n\n“我也不知道结果，从桥上跳下去后，我便莫名其妙地进入了你的梦里，之后便一直困在这个奇妙的世界里。”\n\n“我和佳慧呢？分手了吗？”\n\n“不知道，你从来没有提过佳慧这个名字。”\n\n“你为什么回来？想要改变命运吗？”\n\n“或许吧，我本打算劝你远离我，你如果没有和我结婚，就不会发生后面的悲剧。你原本有一个不错的女朋友，佳慧应该是一个比我更好的选择，虽然最近发生的事情让你陷入了麻烦，但是，和我们经历的那些事情想比，这些都只是小儿科。并且，从你的描述之中，我可以看得出来，你爱她，而她也更加爱你。”\n\n“你呢？你爱我吗？”\n\n“当然，我以前一直沉浸在你对我的爱中却不自觉，直到来到这里，我才惊厥，你是一个天生拥有阳光和爱的人，你的爱对我来说太奢侈了，我太过于自我，这样的爱，我不配。”\n\n“改变人生会怎么样？”\n\n“我不知道，有人说，改变命运会导致失控错乱，引发蝴蝶效应，可我想来想去，再坏还能坏到哪里去呢？难道还能比死更难吗？”\n\n“不，我不会改变命运，我等着你的出现，我要去经历你刚才所说的一切。”\n\n“为什么？袁野，你疯了吗？我向你发誓，我说的每一个字都是真心的。想想佳慧，她也需要你。”\n\n“ 我相信你说的话，可是我也同样相信自己的选择，既然人生让我选择了你，那一定是我深思熟虑的结果。安素，我还不了解你，但我了解我自己，我对我自己的选择有信心。”\n\n“傻瓜。”\n\n阳光铺洒在沙滩上，安素蜷缩着身子，把头埋在两腿之间，我听见了她的抽泣声，我可以肯定，此时的她，脸上一定有泪也有笑。\n\n8、\n\n我醒时，眼皮上有些微光浮动，我费了好大力气撑开眼皮，阳光透过百叶窗的缝隙溜进病房，有几丝恰巧落在了我的脸上。我摘下脸上的呼吸面罩，用尽全身的力气坐起身，缓缓下地，已记不清自己到底昏迷了多久，走路的时候，脚步微微有些漂浮，但我还是坚持着扶着墙壁小心地移步到百叶窗边，将百叶窗的扇叶缓缓打开，窗外有棵樱花树，正值花开的季节，花瓣堆满了枝头，我一时竟被那团盎然的紫色惊得说不出话来。\n\n再转身时，我一低头，看到了另一张病床上躺着的阿素，阳光衬着她脸上白皙的皮肤，一如我十年前初见她时一般的好看。阿素的睫毛动了动，我怕阳光刺激到她的眼睛，赶紧将百叶窗拉好，再看她时，她正睁着大大的眼睛看着我，她说：“你好，袁先生。”\n\n我笑着答她：“你好，袁太太。”\n\n我和佳慧落水之后，马上有警察赶来将我们救起，吴悦读懂了我的眼神里的深意，在我和梁胖子进入二号仓库之后，她立刻拨通了报警电话，她的这一举动直接救了我和佳慧的命。\n\n梁胖子在冲突中被打成重伤，王栋、刚哥一伙人因为聚众斗殴和扰乱社会治安被刑事拘留，至于他们之间的纷争以什么方式结束，就不得而知，当然他们最终如何，我也并不关心。\n\n我原谅了佳慧，并且以为我和她能安然度过这场危急，但事与愿违，佳慧最终决意与我分手，我和她之间有过一次灵魂对话：\n\n“袁野，我还是好喜欢你啊！”\n\n“我们把这件事情忘记，重新开始吧。”\n\n“不了，经过这件事情之后，我觉得我还是太不成熟了，考研、读博士、博士后，我要做一个知识女性。”\n\n“什么乱七八糟理由？我又一次被你莫名其妙地甩了。”\n\n“袁野，我是真心觉得，我不是那个最适合你的人，我不能太自私了，我冥冥之中有种感觉，你会遇到一个更好更值得你付出的人。”\n\n佳慧说这话的时候，我仿佛感觉到阿素就在我的身边。\n\n“好吧，你用了一段经典台词拒绝一个男人的真心，既然这样，现在，我只能祝你一路顺风了。”\n\n“你不是应该痛哭流涕的吗？”\n\n“为什么，你不是深思熟虑之后才选择和我分手的吗？”\n\n“不，袁野，我反悔了，我们重新开始吧。”\n\n“你……”\n\n“我开玩笑呢？”\n\n“……”\n\n佳慧说到做到，她真的辞职拿起了书本，因为成绩优秀，后来又选择了出国进修。三年之后，我又偶遇过一次吴悦，她说佳慧已经在国外结婚，老公是一个外国人，既不很帅，也不是土豪，性格倒是有几分像我，笨笨的，还有点执着。\n\n自从在梦里遇见了安素，我的事业也开始被好运眷顾，升职加薪，平步青云，一路做到了大区域的经理，慢慢开始在上流社会崭露头角，跻身青年才俊的行列。\n\n我有时候也会怀疑当初那段梦里的记忆到底算不算真实，直到有一天，我在一场合作企业的大型嘉年华上遇见安素，那场群星闪烁的晚会上安素气场十足，我在人群中一刻不离地看着她在众人面前侃侃而谈，我等了五年，终于等到了她的出现，那一刻，我选择不再退缩，我坚定地向她走去，我相信，这世上的一切都有天意。\n\n之后的岁月，我和阿素结婚，共同创立了自己的事业，她智慧超群，我踏实肯干，成功来得顺顺利利，随后我们人生的抛物线急转直下，公司破产，最后的押宝也随着货轮的沉没黯然落幕。一切的一切都和多年前阿素的描述如出一辙，我没有做任何抵抗，随波逐流，最后陪着阿素从未名湖的小桥上一跃而下，那一刻，我和她的人生又都回到了原点。\n\n“你去了我过去的梦里？”憋在心里许多年，我终于问出了那个问题。\n\n“你怎么知道？”阿素惊讶地看着我，愣了几秒之后，又恍然大悟，“是呀，你怎么会不知道，你一直都知道的，是不是？”\n\n我点了点头，答道：“当然，我等了五年，只为和你相遇，结婚之后，我把秘密又放在心里藏了这么多年，只为了等一个未知的结果。”\n\n“可是有什么办法呢？大错已经铸成，你真傻，你要是当时选择了佳慧，就不会像现在这样落魄，虽然我们逃过了一劫，但是以后的日子该怎么过下去呢？外面那些债主可不会因为我们自杀过一次就会放弃债务的。”安素哀叹一声，长长的吐出一口气。\n\n“佳慧说，我和她并不是最好的选择，我觉得她是对的，这些多年的相处，我越发地认可了她的观点，你是不是觉得我为你做的一切都很憋屈，不，我所做的一切都是心甘情愿的，不要问我理由，爱情是最不讲道理的。最后告诉你一件事情，我给我们的货轮买了保险，我们并非一无所有。”\n\n“你买了保险？袁野，你疯了吗？你这么做会破坏时空秩序的，我的天，万一世界因为你这个行为而毁灭，你会不会愧疚。”\n\n“我并没有改变时空秩序，你穿越到我的梦里是在我们跳入未名湖之后，在这之前，所有事情都是它原本的结果，一切都没有任何变化，我改变的是未来，回头再来看，既然你被命运安排去见了过去的我，谁又能保证改变未来不同样也是命运的安排的结果？”\n\n“既然你买了保险，为什么还要选择跳湖，万一真的因此丧了命，岂不是得不偿失？”\n\n“我们不去跳湖，你就去不到我过去的梦里，那我就可能错过和你的相遇，那才是得不偿失。”\n\n“好吧，我们来算算，我们还有多少钱。”\n\n“去掉各种债务，大概还能剩余个小九位数。”\n\n“这么多，我们的事业又可以重头再来了。”阿素又信心满满，对未来充满了希望。\n\n“不。”这一次我说得斩钉截铁，“我要选择财务自由，我要用余生带着你去旅游，莫斯科、巴黎、伊斯坦布尔，看好望角的海、乞力马扎罗的山还有南极的冰天雪地，我们一起去环游世界，我要你余生的每分每秒都陪在我的身边。”\n\n阿素微微一愣，随即微笑着把头靠到了我的肩膀上，她体贴地回道：“好的，这一次，听你的。”\n\n“我还有一个问题得问你。”我郑重其事地看着她，问道“你曾经说，要改变命运让我和佳慧在一起，千万不要再遇见你，你说这话是真心的吗？”\n\n阿素的笑灿若朝阳，狡猾地眨了眨眼睛，反唇道：“你猜！”\n\n【终】', 7, 19, 1, 0, '2022-04-02 13:21:12', '2022-04-03 11:51:49', 21, 21, 0, 0, 0);
INSERT INTO `article` VALUES (45, 'w', 'w', 8, 0, 2, 0, '2022-04-04 00:15:10', '2022-04-04 01:21:01', 21, 21, 1, 0, 0);
INSERT INTO `article` VALUES (46, '1', '2', 2, 0, 2, 0, '2022-04-04 01:22:35', '2022-04-04 01:36:47', 21, 21, 0, 0, 0);

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0 启动  1 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (1, 'test22', 1, '2022-01-27 17:14:25', '2022-04-03 12:00:06', 8, 21, 1);
INSERT INTO `article_category` VALUES (2, 'test1', 0, '2022-01-27 17:07:04', '2022-01-27 17:19:22', 8, 8, 0);
INSERT INTO `article_category` VALUES (3, 'test3', 1, '2022-01-27 17:16:01', '2022-01-27 17:26:20', 8, 8, 1);
INSERT INTO `article_category` VALUES (4, 'Java', 0, '2022-01-27 18:18:59', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (5, 'android', 0, '2022-01-27 18:19:05', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (6, 'vue', 0, '2022-01-27 18:19:11', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (7, '日常', 0, '2022-01-27 18:19:21', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (8, '1', 0, '2022-03-31 15:28:10', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (9, '2', 0, '2022-03-31 15:28:13', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (10, '3', 0, '2022-03-31 15:28:17', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (11, '4', 0, '2022-03-31 15:28:21', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (12, '5', 0, '2022-03-31 15:28:25', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (13, '1', 1, '2022-03-31 15:28:28', '2022-03-31 15:28:37', 8, 8, 1);
INSERT INTO `article_category` VALUES (14, '6', 0, '2022-03-31 15:28:43', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (15, '7', 1, '2022-03-31 15:28:47', '2022-03-31 16:48:35', 8, 8, 1);
INSERT INTO `article_category` VALUES (16, '8', 0, '2022-03-31 15:28:51', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (17, '9', 0, '2022-03-31 15:28:55', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (18, '10', 0, '2022-03-31 15:28:59', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (19, '11', 0, '2022-03-31 15:31:06', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (20, '12', 0, '2022-03-31 15:31:09', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (21, '我是你', 0, '2022-03-31 15:31:14', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (22, '你是谁', 0, '2022-03-31 15:31:21', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (23, '你是谁啊', 0, '2022-03-31 15:31:26', NULL, 8, NULL, 0);
INSERT INTO `article_category` VALUES (24, '你是谁你是谁', 1, '2022-03-31 15:31:30', '2022-03-31 15:51:56', 8, 8, 1);
INSERT INTO `article_category` VALUES (25, '你是谁你是谁你是谁', 0, '2022-03-31 15:31:36', '2022-04-03 11:59:32', 8, 21, 0);

-- ----------------------------
-- Table structure for article_tags
-- ----------------------------
DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag_id` bigint(15) NULL DEFAULT NULL COMMENT '标签id',
  `article_id` bigint(15) NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tags
-- ----------------------------
INSERT INTO `article_tags` VALUES (9, 3, 21);
INSERT INTO `article_tags` VALUES (12, 4, 31);
INSERT INTO `article_tags` VALUES (13, 7, 30);
INSERT INTO `article_tags` VALUES (14, 6, 30);
INSERT INTO `article_tags` VALUES (15, 2, 30);
INSERT INTO `article_tags` VALUES (16, 1, 30);
INSERT INTO `article_tags` VALUES (22, 4, 26);
INSERT INTO `article_tags` VALUES (23, 1, 26);
INSERT INTO `article_tags` VALUES (25, 6, 28);
INSERT INTO `article_tags` VALUES (26, 8, 28);
INSERT INTO `article_tags` VALUES (27, 6, 22);
INSERT INTO `article_tags` VALUES (28, 3, 22);
INSERT INTO `article_tags` VALUES (29, 2, 22);
INSERT INTO `article_tags` VALUES (34, 4, 29);
INSERT INTO `article_tags` VALUES (37, 9, 32);
INSERT INTO `article_tags` VALUES (38, 3, 34);
INSERT INTO `article_tags` VALUES (40, 7, 27);
INSERT INTO `article_tags` VALUES (41, 3, 24);
INSERT INTO `article_tags` VALUES (42, 6, 35);
INSERT INTO `article_tags` VALUES (44, 10, 36);
INSERT INTO `article_tags` VALUES (45, 7, 37);
INSERT INTO `article_tags` VALUES (46, 2, 37);
INSERT INTO `article_tags` VALUES (47, 6, 38);
INSERT INTO `article_tags` VALUES (48, 2, 38);
INSERT INTO `article_tags` VALUES (50, 7, 40);
INSERT INTO `article_tags` VALUES (51, 7, 39);
INSERT INTO `article_tags` VALUES (52, 11, 41);
INSERT INTO `article_tags` VALUES (53, 12, 42);
INSERT INTO `article_tags` VALUES (54, 11, 42);
INSERT INTO `article_tags` VALUES (57, 1, 33);
INSERT INTO `article_tags` VALUES (58, 7, 43);
INSERT INTO `article_tags` VALUES (65, 10, 44);
INSERT INTO `article_tags` VALUES (74, 10, 45);
INSERT INTO `article_tags` VALUES (75, 15, 45);
INSERT INTO `article_tags` VALUES (80, 10, 46);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(10) NULL DEFAULT NULL COMMENT '父类id',
  `topic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 0, '36', '热萨法', 0, '2022-03-29 21:01:38', NULL, 8, NULL);
INSERT INTO `comment` VALUES (2, 1, '36', '放放风', 0, '2022-03-28 13:28:08', NULL, 9, NULL);
INSERT INTO `comment` VALUES (3, 4, '38', '热萨法', 0, '2022-03-28 13:28:08', '2022-03-28 13:28:08', 9, NULL);
INSERT INTO `comment` VALUES (4, 0, '38', '放放风', 0, '2022-03-28 13:28:08', '2022-03-28 13:28:08', 8, NULL);
INSERT INTO `comment` VALUES (5, 0, '36', '现在是北京时间23:26分，我被隔离了', 0, '2022-03-29 23:26:23', NULL, 25, NULL);
INSERT INTO `comment` VALUES (6, 0, '36', '123', 0, '2022-03-29 23:35:38', NULL, 25, NULL);
INSERT INTO `comment` VALUES (7, 0, '36', 'qqq', 0, '2022-03-29 23:35:41', NULL, 25, NULL);
INSERT INTO `comment` VALUES (8, 0, '38', 'woaini', 1, '2022-03-30 00:06:56', NULL, 25, NULL);
INSERT INTO `comment` VALUES (9, 8, '38', 'fddd', 1, '2022-03-30 00:13:45', NULL, 25, NULL);
INSERT INTO `comment` VALUES (10, 0, '38', 'ddd', 1, '2022-03-30 00:16:36', '2022-03-30 00:20:48', 25, 25);
INSERT INTO `comment` VALUES (11, 0, '38', 'fff', 1, '2022-03-30 00:29:44', '2022-03-30 00:29:51', 25, 25);
INSERT INTO `comment` VALUES (12, 0, '38', 'sdd', 0, '2022-03-30 00:29:45', NULL, 25, NULL);
INSERT INTO `comment` VALUES (13, 11, '38', 'fsddfsdf', 1, '2022-03-30 00:29:49', '2022-03-30 00:29:54', 25, 25);
INSERT INTO `comment` VALUES (14, 0, '38', 'dsfasdf', 0, '2022-03-30 00:29:56', NULL, 25, NULL);
INSERT INTO `comment` VALUES (15, 0, '38', 'sdfsdf', 0, '2022-03-30 00:29:57', NULL, 25, NULL);
INSERT INTO `comment` VALUES (16, 0, '38', 'ddd', 0, '2022-03-30 00:30:05', NULL, 25, NULL);
INSERT INTO `comment` VALUES (17, 0, '38', 'fdsfsdf', 0, '2022-03-30 00:30:07', NULL, 25, NULL);
INSERT INTO `comment` VALUES (18, 0, '38', 'sfsdfd', 0, '2022-03-30 00:30:08', NULL, 25, NULL);
INSERT INTO `comment` VALUES (19, 0, '38', 'dsaf', 0, '2022-03-30 00:34:31', NULL, 25, NULL);
INSERT INTO `comment` VALUES (20, 0, '38', 'q', 0, '2022-03-30 00:34:33', NULL, 25, NULL);
INSERT INTO `comment` VALUES (21, 0, '38', 'w', 0, '2022-03-30 00:34:34', NULL, 25, NULL);
INSERT INTO `comment` VALUES (22, 0, '38', 'a', 1, '2022-03-30 00:34:36', '2022-04-02 16:15:18', 25, 8);
INSERT INTO `comment` VALUES (23, 0, '38', 'n', 0, '2022-03-30 00:34:39', NULL, 25, NULL);
INSERT INTO `comment` VALUES (24, 18, '38', 'fdsf', 0, '2022-03-30 00:34:52', NULL, 25, NULL);
INSERT INTO `comment` VALUES (25, 23, '38', 'f', 0, '2022-03-30 00:38:22', NULL, 8, NULL);
INSERT INTO `comment` VALUES (26, 23, '38', 'f', 0, '2022-03-30 00:38:46', NULL, 8, NULL);
INSERT INTO `comment` VALUES (27, 23, '38', 'sd', 0, '2022-03-30 00:38:51', NULL, 8, NULL);
INSERT INTO `comment` VALUES (28, 23, '38', 'fffff', 0, '2022-03-30 00:38:55', NULL, 8, NULL);
INSERT INTO `comment` VALUES (29, 0, '35', '嘿嘿', 0, '2022-03-30 09:59:01', NULL, 8, NULL);
INSERT INTO `comment` VALUES (30, 0, '42', '正确', 0, '2022-03-30 23:43:32', NULL, 25, NULL);
INSERT INTO `comment` VALUES (31, 0, '43', '可爱极了', 0, '2022-03-30 23:51:45', NULL, 25, NULL);
INSERT INTO `comment` VALUES (32, 31, '43', '是的', 0, '2022-03-30 23:51:51', NULL, 25, NULL);
INSERT INTO `comment` VALUES (33, 0, '22', '日日日', 0, '2022-03-31 23:31:49', NULL, 8, NULL);
INSERT INTO `comment` VALUES (34, 0, '43', '低调低调大夫山大对方是发顺丰是的是的分手发送到发生的发生的发收到饭都烦死点睡的发的 ', 0, '2022-03-31 23:37:12', NULL, 8, NULL);
INSERT INTO `comment` VALUES (35, 0, '43', 'fff', 0, '2022-04-01 00:35:12', NULL, 8, NULL);
INSERT INTO `comment` VALUES (36, 0, '42', 'mmm', 0, '2022-04-01 12:26:09', NULL, 8, NULL);
INSERT INTO `comment` VALUES (37, 36, '42', '借记卡', 0, '2022-04-01 12:26:15', NULL, 8, NULL);
INSERT INTO `comment` VALUES (38, 0, '44', '放放风', 0, '2022-04-02 14:22:50', NULL, 8, NULL);
INSERT INTO `comment` VALUES (39, 0, '38', 'woshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaa', 1, '2022-04-02 14:43:33', '2022-04-02 16:10:49', 8, 8);
INSERT INTO `comment` VALUES (40, 39, '38', '我哈迪斯发放放放放', 1, '2022-04-02 14:49:26', '2022-04-02 16:14:55', 8, 8);
INSERT INTO `comment` VALUES (41, 39, '38', 'sfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfawefweaaaaaaaaaaaaaaaaaaaaaawoshifsfsdfawfaw', 1, '2022-04-02 16:09:06', '2022-04-02 16:14:52', 8, 8);
INSERT INTO `comment` VALUES (42, 23, '38', ';color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde', 0, '2022-04-02 16:22:18', NULL, 8, NULL);
INSERT INTO `comment` VALUES (43, 0, '38', 'bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bbde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bbde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bbde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6bde;color: #6b6b', 0, '2022-04-02 16:23:19', NULL, 8, NULL);
INSERT INTO `comment` VALUES (44, 0, '43', '放放', 0, '2022-04-02 16:35:43', NULL, 8, NULL);
INSERT INTO `comment` VALUES (45, 0, '43', '适分上帝符', 1, '2022-04-02 16:35:45', '2022-04-02 16:36:10', 8, 8);
INSERT INTO `comment` VALUES (46, 0, '43', '时代发生的', 1, '2022-04-02 16:35:46', '2022-04-02 16:36:02', 8, 8);
INSERT INTO `comment` VALUES (47, 35, '43', '时代发生的', 0, '2022-04-02 16:35:54', NULL, 8, NULL);
INSERT INTO `comment` VALUES (48, 46, '43', '士大夫司法', 1, '2022-04-02 16:35:57', '2022-04-02 16:36:02', 8, 8);
INSERT INTO `comment` VALUES (49, 45, '43', '是非得失地方', 1, '2022-04-02 16:36:06', '2022-04-02 16:36:10', 8, 8);
INSERT INTO `comment` VALUES (50, 44, '43', '暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 ', 0, '2022-04-02 16:40:02', NULL, 8, NULL);
INSERT INTO `comment` VALUES (51, 0, '43', '灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊 暗室逢灯撒啊啊啊啊啊啊啊啊啊啊啊啊啊啊', 0, '2022-04-02 22:52:56', NULL, 8, NULL);
INSERT INTO `comment` VALUES (52, 0, '43', 'ddd', 0, '2022-04-03 01:56:02', NULL, 15, NULL);

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名字',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小',
  `extension` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀  .jpg',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES (5, 'IMG_1503.JPG', 'http://180.163.101.78:9000/public/0beac4135ae34bd1952517a2d3d96a51_IMG_1503.JPG', 75185, 'JPG', 0, '2022-02-02 01:12:29', NULL, 8, NULL);
INSERT INTO `files` VALUES (6, 'IMG_1503.JPG', 'http://180.163.101.78:9000/public/6a596827ca18407cb25d451091585d0b_IMG_1503.JPG', 75185, 'JPG', 0, '2022-02-02 01:12:41', NULL, 8, NULL);
INSERT INTO `files` VALUES (7, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/fff1bd6bb06441de9368e141a4018702_IMG_1500.JPG', 146081, 'JPG', 0, '2022-02-02 01:13:32', NULL, 8, NULL);
INSERT INTO `files` VALUES (8, 'IMG_1504.JPG', 'http://180.163.101.78:9000/public/67c45ebfa33b4066b9517ea65e338203_IMG_1504.JPG', 100464, 'JPG', 0, '2022-02-02 01:14:15', NULL, 8, NULL);
INSERT INTO `files` VALUES (9, 'IMG_1502.JPG', 'http://180.163.101.78:9000/public/92b8083166ef47da83fc2317a5a6bcaa_IMG_1502.JPG', 127705, 'JPG', 0, '2022-02-02 01:17:59', NULL, 8, NULL);
INSERT INTO `files` VALUES (10, 'IMG_1481.JPG', 'http://180.163.101.78:9000/public/87bd7c6ae0ce49f4a140938e277640f6_IMG_1481.JPG', 82285, 'JPG', 0, '2022-02-02 01:18:25', NULL, 8, NULL);
INSERT INTO `files` VALUES (11, '6987t8g.jpg', 'http://180.163.101.78:9000/public/8f2245cb602c4347a961692b47d0e644_6987t8g.jpg', 11661, 'jpg', 0, '2022-02-02 01:18:49', NULL, 8, NULL);
INSERT INTO `files` VALUES (12, 'IMG_1503.JPG', 'http://180.163.101.78:9000/public/a4a70f4640154698b135df934bbed0b3_IMG_1503.JPG', 75185, 'JPG', 0, '2022-02-02 01:19:33', NULL, 8, NULL);
INSERT INTO `files` VALUES (13, '2.JPG', 'http://180.163.101.78:9000/public/18ab05a665b648a18b4653477e0585aa_2.JPG', 92558, 'JPG', 0, '2022-02-02 15:57:48', NULL, 21, NULL);
INSERT INTO `files` VALUES (14, 'IMG_1505.JPG', 'http://180.163.101.78:9000/public/afc66b2a2ad4480596e84fe7af58fb67_IMG_1505.JPG', 135689, 'JPG', 0, '2022-02-23 13:55:50', NULL, 8, NULL);
INSERT INTO `files` VALUES (15, 'IMG_1502.JPG', 'http://180.163.101.78:9000/public/fab5c610fd0542769ffe363a4587e250_IMG_1502.JPG', 127705, 'JPG', 0, '2022-02-23 14:27:03', NULL, 21, NULL);
INSERT INTO `files` VALUES (16, 'Snipaste_2022-02-11_20-49-11.png', 'http://180.163.101.78:9000/public/3e007b7f40ee451d86249d56caec6ee7_Snipaste_2022-02-11_20-49-11.png', 1072061, 'png', 0, '2022-03-24 21:07:18', NULL, 8, NULL);
INSERT INTO `files` VALUES (17, 'photo_2021-04-20_14-34-58.jpg', 'http://180.163.101.78:9000/public/b734fc78340742dbb7a4b45ae3bd3828_photo_2021-04-20_14-34-58.jpg', 6084, 'jpg', 0, '2022-03-24 21:44:51', NULL, 8, NULL);
INSERT INTO `files` VALUES (18, 'photo_2021-04-20_14-34-58.jpg', 'http://180.163.101.78:9000/public/9c662d8fb1764c1e8d9f869f0a8dc235_photo_2021-04-20_14-34-58.jpg', 6084, 'jpg', 0, '2022-03-24 21:45:23', NULL, 8, NULL);
INSERT INTO `files` VALUES (19, 'photo_2022-02-12_22-46-06.jpg', 'http://180.163.101.78:9000/public/0a8a257aba1f4778be442843959c232a_photo_2022-02-12_22-46-06.jpg', 96022, 'jpg', 0, '2022-03-24 22:06:27', NULL, 8, NULL);
INSERT INTO `files` VALUES (20, 'photo_2021-04-20_19-44-25.jpg', 'http://180.163.101.78:9000/public/e427bf5830aa459aad9e57a14b38e7d0_photo_2021-04-20_19-44-25.jpg', 6400, 'jpg', 0, '2022-03-24 22:06:45', NULL, 8, NULL);
INSERT INTO `files` VALUES (21, '微信图片_20210208234348.jpg', 'http://180.163.101.78:9000/public/f7321484ee58414f9ef4ca1ec85e2331_微信图片_20210208234348.jpg', 5458, 'jpg', 0, '2022-03-24 22:07:39', NULL, 8, NULL);
INSERT INTO `files` VALUES (22, 'photo_2021-04-20_19-36-38.jpg', 'http://180.163.101.78:9000/public/fe0488ea15874d74abb20340057dda39_photo_2021-04-20_19-36-38.jpg', 5499, 'jpg', 0, '2022-03-24 22:07:50', NULL, 8, NULL);
INSERT INTO `files` VALUES (23, 'photo_2021-04-20_14-34-58.jpg', 'http://180.163.101.78:9000/public/3fe2001d1a4348d58bd7e53354780d2a_photo_2021-04-20_14-34-58.jpg', 6084, 'jpg', 0, '2022-03-24 22:09:54', NULL, 8, NULL);
INSERT INTO `files` VALUES (24, 'photo_2022-02-12_22-47-33.jpg', 'http://180.163.101.78:9000/public/4252343df2f14ffba22b93e37ae50e0e_photo_2022-02-12_22-47-33.jpg', 39272, 'jpg', 0, '2022-03-30 15:49:12', NULL, 25, NULL);
INSERT INTO `files` VALUES (25, 'photo_2021-12-28_19-53-20.jpg', 'http://180.163.101.78:9000/public/2e156b294c794e48aff8378a11746048_photo_2021-12-28_19-53-20.jpg', 57509, 'jpg', 0, '2022-03-30 23:48:45', NULL, 8, NULL);
INSERT INTO `files` VALUES (26, 'photo_2021-12-28_19-53-20.jpg', 'http://180.163.101.78:9000/public/df535f57758c4c6ebd6859f0ca79ad8e_photo_2021-12-28_19-53-20.jpg', 57509, 'jpg', 0, '2022-03-30 23:49:10', NULL, 8, NULL);
INSERT INTO `files` VALUES (27, 'photo_2021-04-20_14-34-58.jpg', 'http://180.163.101.78:9000/public/8501528540cc4267bb6eda5a4f5464c2_photo_2021-04-20_14-34-58.jpg', 6084, 'jpg', 0, '2022-03-30 23:51:15', NULL, 8, NULL);
INSERT INTO `files` VALUES (28, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/2221322e77a04a948dcb97233cd1815c_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 17:20:00', NULL, 8, NULL);
INSERT INTO `files` VALUES (29, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/6f6cc4e5d6ca49b4a9d36a563dcc8e37_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 17:21:55', NULL, 8, NULL);
INSERT INTO `files` VALUES (30, 'IMG_1489.JPG', 'http://180.163.101.78:9000/public/e0a49c006f094455bac62dd75eedf49c_IMG_1489.JPG', 120932, 'JPG', 0, '2022-04-01 18:50:53', NULL, 8, NULL);
INSERT INTO `files` VALUES (31, 'IMG_1502.JPG', 'http://180.163.101.78:9000/public/77a6824c47254b9e969da49b74819cfe_IMG_1502.JPG', 127705, 'JPG', 0, '2022-04-01 19:52:53', NULL, 8, NULL);
INSERT INTO `files` VALUES (32, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/2a07bb9fee5e4f69b1bbde904710c6ca_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 19:56:16', NULL, 8, NULL);
INSERT INTO `files` VALUES (33, '6987t8g.jpg', 'http://180.163.101.78:9000/public/635319e7a544481fae5c9ceb423d2858_6987t8g.jpg', 11661, 'jpg', 0, '2022-04-01 19:58:27', NULL, 8, NULL);
INSERT INTO `files` VALUES (34, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/9ec9da4e627e4ed2a34c3c6c6e0518c8_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:01:07', NULL, 8, NULL);
INSERT INTO `files` VALUES (35, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/aed5d53eb0c64e1cabd8495b13202aaf_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:02:57', NULL, 8, NULL);
INSERT INTO `files` VALUES (36, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/b580937e2030415e83ece3c4452344ee_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:03:40', NULL, 8, NULL);
INSERT INTO `files` VALUES (37, 'photo_2021-10-18_14-50-31.jpg', 'http://180.163.101.78:9000/public/25292affb46f480c8c698f812039d147_photo_2021-10-18_14-50-31.jpg', 67329, 'jpg', 0, '2022-04-01 20:03:54', NULL, 8, NULL);
INSERT INTO `files` VALUES (38, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/c8e5900a11ae4c3dabe78dd19b05b3e6_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:05:51', NULL, 8, NULL);
INSERT INTO `files` VALUES (39, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/7ca18945e81f4f5295cf32e3054f4a19_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:08:35', NULL, 8, NULL);
INSERT INTO `files` VALUES (40, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/24ef1629665f44d1a486321fccaa989b_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:09:06', NULL, 8, NULL);
INSERT INTO `files` VALUES (41, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/f0ba06e9599b41279265b12faaccc649_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:13:35', NULL, 8, NULL);
INSERT INTO `files` VALUES (42, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/fb368d05643d471d988d1b7217f82c20_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:15:11', NULL, 8, NULL);
INSERT INTO `files` VALUES (43, 'photo_2021-08-24_13-03-43.jpg', 'http://180.163.101.78:9000/public/550b1f90c2584ae19f7191223c0e3f87_photo_2021-08-24_13-03-43.jpg', 33920, 'jpg', 0, '2022-04-01 20:17:35', NULL, 8, NULL);
INSERT INTO `files` VALUES (44, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/26711ac96872488fbd3c011ddbc0bf28_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:20:04', NULL, 8, NULL);
INSERT INTO `files` VALUES (45, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/17779981cdfd44a99bc4dfca06bc4d0d_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:22:05', NULL, 8, NULL);
INSERT INTO `files` VALUES (46, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/9e0fda9db6854a409813a530a9744438_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 20:22:21', NULL, 8, NULL);
INSERT INTO `files` VALUES (47, 'IMG_1502.JPG', 'http://180.163.101.78:9000/public/1a94edc4b00b47189f08f7c2d4201a4b_IMG_1502.JPG', 127705, 'JPG', 0, '2022-04-01 20:24:22', NULL, 8, NULL);
INSERT INTO `files` VALUES (48, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/17714ee664094a0e9de3e65b75c23316_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:24:38', NULL, 8, NULL);
INSERT INTO `files` VALUES (49, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/adda34edadbe462bb1940f5b8528b6da_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:25:22', NULL, 8, NULL);
INSERT INTO `files` VALUES (50, '2.JPG', 'http://180.163.101.78:9000/public/8175e4e4c45f49acae91b1b0000b9ced_2.JPG', 92558, 'JPG', 0, '2022-04-01 20:27:16', NULL, 8, NULL);
INSERT INTO `files` VALUES (51, 'IMG_1489.JPG', 'http://180.163.101.78:9000/public/a0bbc56439a944fb92565f10fe6f7b1c_IMG_1489.JPG', 120932, 'JPG', 0, '2022-04-01 20:29:51', NULL, 8, NULL);
INSERT INTO `files` VALUES (52, 'IMG_1504.JPG', 'http://180.163.101.78:9000/public/de94c4542d304a96a65cfebf27a496e1_IMG_1504.JPG', 100464, 'JPG', 0, '2022-04-01 20:30:27', NULL, 8, NULL);
INSERT INTO `files` VALUES (53, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/0770fc05959249938f453bc08203a5c7_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:32:15', NULL, 8, NULL);
INSERT INTO `files` VALUES (54, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/0a477b85267f4746a01fa867623f78e0_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:34:23', NULL, 8, NULL);
INSERT INTO `files` VALUES (55, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/8c1caf0f028e4dad9e22fb07927ca46c_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:35:37', NULL, 8, NULL);
INSERT INTO `files` VALUES (56, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/20c34a8ce7a449fdb07ad3dabcd6b9d0_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:37:43', NULL, 8, NULL);
INSERT INTO `files` VALUES (57, 'IMG_1489.JPG', 'http://180.163.101.78:9000/public/439dd3ec2c06464da1c98aed447c496f_IMG_1489.JPG', 120932, 'JPG', 0, '2022-04-01 20:39:45', NULL, 8, NULL);
INSERT INTO `files` VALUES (58, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/0237192f7ae6401dbd7fd7aab33a435d_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:41:03', NULL, 8, NULL);
INSERT INTO `files` VALUES (59, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/4c07dbc30869477bb447eaa06b72e6af_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:42:27', NULL, 8, NULL);
INSERT INTO `files` VALUES (60, '2.JPG', 'http://180.163.101.78:9000/public/d641aca86cbb4329968f95e41ead3b03_2.JPG', 92558, 'JPG', 0, '2022-04-01 20:43:18', NULL, 8, NULL);
INSERT INTO `files` VALUES (61, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/146268b90fa148f487e9c1196290ee12_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:45:20', NULL, 8, NULL);
INSERT INTO `files` VALUES (62, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/1f6fa7fa6df4408286f9f09002975953_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:46:12', NULL, 8, NULL);
INSERT INTO `files` VALUES (63, '1.JPG', 'http://180.163.101.78:9000/public/43b88112e579457eb313ed38d55081d2_1.JPG', 158737, 'JPG', 0, '2022-04-01 20:48:25', NULL, 8, NULL);
INSERT INTO `files` VALUES (64, '1.JPG', 'http://180.163.101.78:9000/public/da686173ecd84fcbaa43b4811e26fbe7_1.JPG', 158737, 'JPG', 0, '2022-04-01 20:48:40', NULL, 8, NULL);
INSERT INTO `files` VALUES (65, 'IMG_1488.JPG', 'http://180.163.101.78:9000/public/1b790563029c4d02914fea202adb34c7_IMG_1488.JPG', 113686, 'JPG', 0, '2022-04-01 20:49:39', NULL, 8, NULL);
INSERT INTO `files` VALUES (66, 'photo_2021-10-18_14-50-31.jpg', 'http://180.163.101.78:9000/public/12383b4a00a2464198a39bd6fedab3c9_photo_2021-10-18_14-50-31.jpg', 67329, 'jpg', 0, '2022-04-01 22:52:52', NULL, 8, NULL);
INSERT INTO `files` VALUES (67, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/984db29d0bc543c491b9671095efd3f8_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-01 22:53:06', NULL, 8, NULL);
INSERT INTO `files` VALUES (68, 'd10855d12a39827dfcf18ab947e7ab7.jpg', 'http://180.163.101.78:9000/public/e859d13d2b8443129edc5e1be7b51e76_d10855d12a39827dfcf18ab947e7ab7.jpg', 212965, 'jpg', 0, '2022-04-01 23:30:22', NULL, 21, NULL);
INSERT INTO `files` VALUES (69, 'IMG_1500.JPG', 'http://180.163.101.78:9000/public/6389501ead8b47939aba637ea94b2915_IMG_1500.JPG', 146081, 'JPG', 0, '2022-04-04 12:03:55', NULL, 21, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `parent_id` bigint(15) NULL DEFAULT NULL COMMENT '父类id',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0 启用  1 禁用',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '软删除 0 未删除  1  已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'Order-index', '订单管理', '/user/pageList', 0, 1, 1, '2022-01-18 09:57:44', 8, '2022-01-27 16:13:06', 8);
INSERT INTO `menu` VALUES (3, 'System', '系统设置', '/article/pageList', 0, 0, 0, '2022-01-18 09:59:24', 8, '2022-01-29 14:14:59', 8);
INSERT INTO `menu` VALUES (5, 'Login', '登录页', '/login', 0, 0, 0, '2022-01-20 20:29:48', 8, '2022-01-21 10:34:16', 8);
INSERT INTO `menu` VALUES (8, 'Permission', '权限许可', '/permission/page-use', 0, 0, 0, '2022-01-21 10:23:32', 8, '2022-01-21 10:34:58', 8);
INSERT INTO `menu` VALUES (9, 'PageUser', '用户权限', '/permission/page-user', 8, 0, 0, '2022-01-21 10:42:21', 8, '2022-01-21 10:42:21', 8);
INSERT INTO `menu` VALUES (10, 'PageAdmin', '管理员权限', '/permission/page-admin', 8, 0, 0, '2022-01-21 10:43:12', 8, '2022-01-21 10:43:12', 8);
INSERT INTO `menu` VALUES (11, 'Roles', '权限设置', '/permission/roles', 8, 0, 0, '2022-01-21 10:43:36', 8, '2022-01-21 10:43:36', 8);
INSERT INTO `menu` VALUES (12, 'System-menu', '菜单管理', '/system/menu-Setting', 3, 0, 0, '2022-01-26 10:34:43', 8, '2022-01-26 10:34:43', 8);
INSERT INTO `menu` VALUES (13, 'System-user', '用户管理', '/system/user-Setting', 3, 0, 0, '2022-01-26 10:35:21', 8, '2022-01-29 11:37:48', 8);
INSERT INTO `menu` VALUES (14, '404', '404', '/404', 0, 0, 0, '2022-01-29 11:38:57', 8, '2022-01-29 11:38:57', 8);
INSERT INTO `menu` VALUES (15, 'Home', 'Home', '/', 0, 0, 0, '2022-01-29 11:39:25', 8, '2022-01-29 13:42:59', 8);
INSERT INTO `menu` VALUES (16, 'dashbord', '首页', '/dashbord', 15, 0, 0, '2022-01-29 11:41:24', 8, '2022-01-29 13:42:56', 8);
INSERT INTO `menu` VALUES (17, 'Personal', 'Personal', '/personal', 0, 0, 0, '2022-01-29 11:42:19', 8, '2022-01-29 14:23:23', 8);
INSERT INTO `menu` VALUES (18, 'Personal-index', '个人中心', '/personal/index', 17, 0, 0, '2022-01-29 11:42:44', 8, '2022-01-29 14:23:28', 8);
INSERT INTO `menu` VALUES (19, 'Article', '博客管理', '/article', 0, 0, 0, '2022-01-29 11:43:36', 8, '2022-02-02 17:03:54', 8);
INSERT INTO `menu` VALUES (20, 'Article-index', '文章列表', '/article/article-index', 19, 0, 0, '2022-01-29 11:44:07', 8, '2022-01-29 11:44:07', 8);
INSERT INTO `menu` VALUES (21, 'Article-input', '新增文章', '/article/article-input', 19, 0, 0, '2022-01-29 11:45:08', 8, '2022-01-29 11:45:08', 8);
INSERT INTO `menu` VALUES (22, 'Category-index', '分类管理', '/article/category-index', 19, 0, 0, '2022-01-29 11:45:27', 8, '2022-01-29 11:45:27', 8);
INSERT INTO `menu` VALUES (23, 'Tags-index', '标签管理', '/article/tags-index', 19, 0, 0, '2022-01-29 11:46:09', 8, '2022-01-29 11:46:09', 8);
INSERT INTO `menu` VALUES (24, 'notFound', 'notFound', '/404', 0, 0, 0, '2022-01-29 13:41:28', 8, '2022-01-29 22:28:14', 8);
INSERT INTO `menu` VALUES (25, 'Excel', 'Excel', '/excel-operate/excel-out', 0, 0, 0, '2022-01-29 14:26:13', 8, '2022-01-29 14:26:13', 8);
INSERT INTO `menu` VALUES (26, 'Excel-out', 'Excel导出', '/excel-operate/excel-out', 25, 0, 0, '2022-01-29 14:26:35', 8, '2022-01-29 14:26:35', 8);
INSERT INTO `menu` VALUES (27, 'Excel-in', 'Excel导入', '/excel-operate/excel-in', 25, 0, 0, '2022-01-29 14:27:10', 8, '2022-01-29 14:27:10', 8);
INSERT INTO `menu` VALUES (28, 'Mutiheader-out', '多级表头导出', '/excel-operate/mutiheader-out', 25, 0, 0, '2022-01-29 14:27:28', 8, '2022-01-29 14:27:28', 8);
INSERT INTO `menu` VALUES (29, 'Table', 'Table', '/table/base-table', 0, 0, 0, '2022-01-29 14:30:18', 8, '2022-01-29 14:30:18', 8);
INSERT INTO `menu` VALUES (30, 'BaseTable', '普通表格', '/table/common-table', 29, 0, 0, '2022-01-29 14:30:42', 8, '2022-01-29 14:30:42', 8);
INSERT INTO `menu` VALUES (31, 'ComplexTable', '复杂表格', '/table/complex-table', 29, 0, 0, '2022-01-29 14:31:04', 8, '2022-01-29 14:31:04', 8);
INSERT INTO `menu` VALUES (32, 'Icons', 'Icons', '/icons', 0, 0, 0, '2022-01-29 14:31:53', 8, '2022-01-29 14:31:53', 8);
INSERT INTO `menu` VALUES (33, 'Icons-index', 'Icons图标', '/icons', 32, 0, 0, '2022-01-29 14:32:20', 8, '2022-01-29 14:32:20', 8);
INSERT INTO `menu` VALUES (34, 'Echarts', 'Echarts', '/echarts', 0, 0, 0, '2022-01-29 14:33:00', 8, '2022-01-29 14:33:00', 8);
INSERT INTO `menu` VALUES (35, 'Sldie-chart', '滑动charts', '/echarts/slide-chart', 34, 0, 0, '2022-01-29 14:33:18', 8, '2022-01-29 14:33:18', 8);
INSERT INTO `menu` VALUES (36, 'Dynamic-chart', '切换charts', '/echarts/dynamic-chart', 34, 0, 0, '2022-01-29 14:33:39', 8, '2022-01-29 14:33:39', 8);
INSERT INTO `menu` VALUES (37, 'Map-chart', 'map', '/echarts/map-chart', 34, 0, 0, '2022-01-29 14:33:59', 8, '2022-01-29 14:34:00', 8);
INSERT INTO `menu` VALUES (38, 'Components', '部分组件', '/components', 0, 0, 0, '2022-01-29 14:34:26', 8, '2022-01-29 14:34:26', 8);
INSERT INTO `menu` VALUES (39, 'Sldie-yz', '滑动验证', '/components/slide-yz', 38, 0, 0, '2022-01-29 14:34:45', 8, '2022-01-29 14:34:45', 8);
INSERT INTO `menu` VALUES (40, 'Upload', '上传图片', '/components/pushImg', 38, 0, 0, '2022-01-29 14:35:05', 8, '2022-01-29 14:35:05', 8);
INSERT INTO `menu` VALUES (41, 'Carousel', '轮播', '/components/carousel', 38, 0, 0, '2022-01-29 14:35:21', 8, '2022-01-29 14:35:21', 8);
INSERT INTO `menu` VALUES (42, 'Announcement', '公告管理', '/announcement', 0, 0, 0, '2022-03-30 20:58:35', 8, '2022-03-30 20:58:35', 8);
INSERT INTO `menu` VALUES (43, 'Announcement-index', '公告列表', '/announcement/index', 42, 0, 0, '2022-03-30 20:59:58', 8, '2022-03-30 20:59:58', 8);
INSERT INTO `menu` VALUES (44, 'Article-audit', '审核文章', '/article/article-audit', 19, 0, 0, '2022-04-04 00:51:25', 8, '2022-04-04 00:51:25', 8);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0未删除  1已删除',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '你好呀', 0, 8, '2022-03-28 13:28:08', NULL, NULL);
INSERT INTO `message` VALUES (2, '你也好', 0, 8, '2022-03-28 13:51:16', NULL, NULL);
INSERT INTO `message` VALUES (3, '我不太好', 0, 21, '2022-03-28 13:52:16', NULL, NULL);
INSERT INTO `message` VALUES (4, '疯了吧', 0, 21, '2022-03-28 13:52:23', NULL, NULL);
INSERT INTO `message` VALUES (5, '我真的真的好爱你', 0, 25, '2022-03-28 22:32:42', NULL, NULL);
INSERT INTO `message` VALUES (6, '好想好想', 1, 8, '2022-03-28 22:39:41', NULL, NULL);
INSERT INTO `message` VALUES (7, '好想\n', 0, 8, '2022-03-28 22:39:58', NULL, NULL);
INSERT INTO `message` VALUES (8, '好想好想\n', 0, 8, '2022-03-28 22:40:34', NULL, NULL);
INSERT INTO `message` VALUES (9, '好想在哪里见过你', 0, 8, '2022-03-28 22:40:42', NULL, NULL);
INSERT INTO `message` VALUES (10, '你为什么这么好看呢', 0, 8, '2022-03-28 22:40:49', NULL, NULL);
INSERT INTO `message` VALUES (11, '我觉得你超级好看', 0, 8, '2022-03-28 22:41:07', NULL, NULL);
INSERT INTO `message` VALUES (12, '是吗', 0, 25, '2022-03-28 22:41:30', NULL, NULL);
INSERT INTO `message` VALUES (13, '我也这样觉得', 0, 25, '2022-03-28 22:41:36', NULL, NULL);
INSERT INTO `message` VALUES (14, '嗯嗯\n', 0, 25, '2022-03-28 22:41:41', NULL, NULL);
INSERT INTO `message` VALUES (15, '加油吧', 0, 25, '2022-03-28 22:41:45', NULL, NULL);
INSERT INTO `message` VALUES (16, '努力！奋斗！', 0, 25, '2022-03-28 22:42:01', NULL, NULL);
INSERT INTO `message` VALUES (17, '这一路上走走停停\n顺着少年漂流的痕迹\n迈出车站的前一刻\n竟有些犹豫\n不禁笑这近乡情怯\n仍无法避免\n而长野的天\n依旧那么暖\n风吹起了从前\n从前初识这世间\n万般流连\n看着天边似在眼前\n也甘愿赴汤蹈火去走它一遍\n如今走过这世间\n万般流连\n翻过岁月不同侧脸\n措不及防闯入你的笑颜\n我曾难自拔于世界之大\n也沉溺于其中梦话\n不得真假 不做挣扎 不惧笑话\n我曾将青春翻涌成她\n也曾指尖弹出盛夏\n心之所动 且就随缘去吧\n逆着光行走 任风吹雨打\n短短的路走走停停\n也有了几分的距离\n不知抚摸的是故事 还是段心情\n也许期待的不过是 与时间为敌\n再次见到你\n微凉晨光里\n笑得很甜蜜\n从前初识这世间\n万般流连\n看着天边似在眼前\n也甘愿赴汤蹈火去走它一遍\n如今走过这世间\n万般流连\n翻过岁月不同侧脸\n措不及防闯入你的笑颜\n我曾难自拔于世界之大\n也沉溺于其中梦话\n不做真假 不做挣扎 不惧笑话\n我曾将青春翻涌成她\n也曾指尖弹出盛夏\n心之所动 且就随缘去吧\n晚风吹起你鬓间的白发\n抚平回忆留下的疤\n你的眼中 明暗交杂 一笑生花\n暮色遮住你蹒跚的步伐\n走进床头藏起的画\n画中的你 低着头说话\n我仍感叹于世界之大\n也沉醉于儿时情话\n不剩真假 不做挣扎 无谓笑话\n我终将青春还给了她\n连同指尖弹出的盛夏\n心之所动 就随风去了\n以爱之名 你还愿意吗', 0, 25, '2022-03-28 22:42:26', NULL, NULL);
INSERT INTO `message` VALUES (18, 'wo \\n fsfsd', 0, 25, '2022-03-28 22:55:51', NULL, NULL);
INSERT INTO `message` VALUES (19, 'ni /n sfsdf', 0, 25, '2022-03-28 22:55:59', NULL, NULL);
INSERT INTO `message` VALUES (20, 'ff\nfffsss', 0, 25, '2022-03-28 22:56:06', NULL, NULL);
INSERT INTO `message` VALUES (21, '人群中哭着,\n你只想变成透明的颜色 ,\n你再也不会梦或痛或心动了,\n你已经决定了,\n你已经决定了,\n就静静忍着,\n紧紧把昨天在拳心握着,\n而回忆越是甜就是越伤人的,\n越是在手心留下,\n密密麻麻深深浅浅的刀割,\n你不是真正的快乐,\n你的笑只是你穿的保护色,\n你决定不恨了 也决定不爱了,\n把你的灵魂,\n关在永远锁上的躯壳,\n这世界笑了,\n于是你合群的一起笑了,\n当生存是规则,\n不是你的选择,\n于是你含着眼泪,\n飘飘荡荡跌跌撞撞地走着,\n你不是真正的快乐,\n你的伤从不肯完全的愈合,\n我站在你左侧,\n却像隔着银河,\n难道就真的抱着遗憾一直到老了,\n然后才后悔着,\n你不是真正的快乐,\n你的笑只是你穿的保护色,\n你决定不恨了 也决定不爱了,\n把你的灵魂关在永远锁上的躯壳,\n你不是真正的快乐,\n你的伤从不肯完全的愈合,\n我站在你左侧 却像隔着银河,\n难道就真的抱着遗憾一直到老了,\n你值得真正的快乐,\n你应该脱下你穿的保护色,\n为什么失去了 还要被惩罚呢,\n能不能就让悲伤全部结束在此刻,\n重新开始活着,\n你的伤我知道我明了,\n我想要你快乐,\n我要你快乐 我要你快乐,\n我知道我明了我想要你快乐,\n我要你快乐 我要你快乐,\n我知道我明了我想要你快乐,\n我要你快乐 我要你快乐,\n我知道我明了我想要你快乐,\n我要你快乐 我要你快乐,\n我知道我明了我想要你快乐,\n我要你快乐 我要你快乐,\n我知道我明了我想要你快乐,\n我要你快乐 我要你快乐,\n要你快乐 我要你快乐,\n要你快乐 我要你快乐,', 0, 25, '2022-03-28 22:57:45', NULL, NULL);
INSERT INTO `message` VALUES (22, '都可以随便的\n你说的我都愿意去\n小火车摆动的旋律\n都可以是真的\n你说的我都会相信\n因为我完全信任你\n细腻的喜欢\n毛毯般的厚重感\n晒过太阳熟悉的安全感\n分享热汤\n我们两支汤匙一个碗\n左心房暖暖的好饱满\n我想说其实你很好\n你自己却不知道\n真心的对我好\n不要求回报\n爱一个人希望他过更好\n打从心里暖暖的\n你比自己更重要\n我想说其实你很好\n你自己却不知道\n从来都很低调\n自信心不高\n爱一个人希望他过更好\n打从心里暖暖的\n你比自己更重要\n你不知道\n真心的对我好\n不要求回报\n爱一个人希望他过更好\n打从心里暖暖的\n你比自己更重要\n我也希望变更好', 0, 25, '2022-03-28 22:59:50', NULL, NULL);
INSERT INTO `message` VALUES (23, '你很好\n我不知道\n\n', 0, 25, '2022-03-28 23:00:53', NULL, NULL);
INSERT INTO `message` VALUES (24, '呢 /\\n fsdf', 1, 25, '2022-03-28 23:05:58', NULL, NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(15) NULL DEFAULT NULL COMMENT 'role_id',
  `menu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 742 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (542, NULL, 'Login');
INSERT INTO `role_menu` VALUES (543, NULL, '404');
INSERT INTO `role_menu` VALUES (544, NULL, 'Dashbord');
INSERT INTO `role_menu` VALUES (545, NULL, 'Personal-index');
INSERT INTO `role_menu` VALUES (546, NULL, 'Article');
INSERT INTO `role_menu` VALUES (547, NULL, 'Article-index');
INSERT INTO `role_menu` VALUES (548, NULL, 'Article-input');
INSERT INTO `role_menu` VALUES (549, NULL, 'Category-index');
INSERT INTO `role_menu` VALUES (550, NULL, 'Tags-index');
INSERT INTO `role_menu` VALUES (551, NULL, 'Components');
INSERT INTO `role_menu` VALUES (552, NULL, 'Sldie-yz');
INSERT INTO `role_menu` VALUES (553, NULL, 'Upload');
INSERT INTO `role_menu` VALUES (554, NULL, 'Carousel');
INSERT INTO `role_menu` VALUES (555, NULL, 'System');
INSERT INTO `role_menu` VALUES (556, NULL, 'System-menu');
INSERT INTO `role_menu` VALUES (557, NULL, 'System-user');
INSERT INTO `role_menu` VALUES (558, NULL, 'Permission');
INSERT INTO `role_menu` VALUES (559, NULL, 'PageUser');
INSERT INTO `role_menu` VALUES (560, NULL, 'PageAdmin');
INSERT INTO `role_menu` VALUES (561, NULL, 'Roles');
INSERT INTO `role_menu` VALUES (562, NULL, 'notFound');
INSERT INTO `role_menu` VALUES (643, 1, 'Login');
INSERT INTO `role_menu` VALUES (644, 1, '404');
INSERT INTO `role_menu` VALUES (645, 1, 'Dashbord');
INSERT INTO `role_menu` VALUES (646, 1, 'Personal-index');
INSERT INTO `role_menu` VALUES (647, 1, 'Article');
INSERT INTO `role_menu` VALUES (648, 1, 'Article-index');
INSERT INTO `role_menu` VALUES (649, 1, 'Article-input');
INSERT INTO `role_menu` VALUES (650, 1, 'Category-index');
INSERT INTO `role_menu` VALUES (651, 1, 'Tags-index');
INSERT INTO `role_menu` VALUES (652, 1, 'Announcement');
INSERT INTO `role_menu` VALUES (653, 1, 'Announcement-index');
INSERT INTO `role_menu` VALUES (654, 1, 'Icons');
INSERT INTO `role_menu` VALUES (655, 1, 'Icons-index');
INSERT INTO `role_menu` VALUES (656, 1, 'Excel');
INSERT INTO `role_menu` VALUES (657, 1, 'Excel-out');
INSERT INTO `role_menu` VALUES (658, 1, 'Excel-in');
INSERT INTO `role_menu` VALUES (659, 1, 'Mutiheader-out');
INSERT INTO `role_menu` VALUES (660, 1, 'Table');
INSERT INTO `role_menu` VALUES (661, 1, 'BaseTable');
INSERT INTO `role_menu` VALUES (662, 1, 'ComplexTable');
INSERT INTO `role_menu` VALUES (663, 1, 'Components');
INSERT INTO `role_menu` VALUES (664, 1, 'Sldie-yz');
INSERT INTO `role_menu` VALUES (665, 1, 'Upload');
INSERT INTO `role_menu` VALUES (666, 1, 'Carousel');
INSERT INTO `role_menu` VALUES (667, 1, 'Echarts');
INSERT INTO `role_menu` VALUES (668, 1, 'Sldie-chart');
INSERT INTO `role_menu` VALUES (669, 1, 'Dynamic-chart');
INSERT INTO `role_menu` VALUES (670, 1, 'Map-chart');
INSERT INTO `role_menu` VALUES (671, 1, 'System');
INSERT INTO `role_menu` VALUES (672, 1, 'System-menu');
INSERT INTO `role_menu` VALUES (673, 1, 'System-user');
INSERT INTO `role_menu` VALUES (674, 1, 'PageAdmin');
INSERT INTO `role_menu` VALUES (675, 1, 'Roles');
INSERT INTO `role_menu` VALUES (676, 1, 'notFound');
INSERT INTO `role_menu` VALUES (718, 3, 'Login');
INSERT INTO `role_menu` VALUES (719, 3, '404');
INSERT INTO `role_menu` VALUES (720, 3, 'Dashbord');
INSERT INTO `role_menu` VALUES (721, 3, 'Personal-index');
INSERT INTO `role_menu` VALUES (722, 3, 'Article-index');
INSERT INTO `role_menu` VALUES (723, 3, 'Article-input');
INSERT INTO `role_menu` VALUES (724, 3, 'Tags-index');
INSERT INTO `role_menu` VALUES (725, 3, 'PageUser');
INSERT INTO `role_menu` VALUES (726, 3, 'notFound');
INSERT INTO `role_menu` VALUES (727, 2, 'Login');
INSERT INTO `role_menu` VALUES (728, 2, '404');
INSERT INTO `role_menu` VALUES (729, 2, 'Dashbord');
INSERT INTO `role_menu` VALUES (730, 2, 'Personal-index');
INSERT INTO `role_menu` VALUES (731, 2, 'Article');
INSERT INTO `role_menu` VALUES (732, 2, 'Article-index');
INSERT INTO `role_menu` VALUES (733, 2, 'Article-input');
INSERT INTO `role_menu` VALUES (734, 2, 'Article-audit');
INSERT INTO `role_menu` VALUES (735, 2, 'Category-index');
INSERT INTO `role_menu` VALUES (736, 2, 'Tags-index');
INSERT INTO `role_menu` VALUES (737, 2, 'Announcement');
INSERT INTO `role_menu` VALUES (738, 2, 'Announcement-index');
INSERT INTO `role_menu` VALUES (739, 2, 'System-user');
INSERT INTO `role_menu` VALUES (740, 2, 'PageAdmin');
INSERT INTO `role_menu` VALUES (741, 2, 'notFound');

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT 'ip地址',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0 未删除  1已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES (2, '0:0:0:0:0:0:0:1', 0, '2021-12-03 15:25:03');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0 启用  1 禁用',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, 'qq', 0, 0, '2022-01-27 22:07:23', '2022-01-27 22:09:00', 8, 8);
INSERT INTO `tags` VALUES (2, 'qa', 0, 0, '2022-01-27 22:09:05', NULL, 8, NULL);
INSERT INTO `tags` VALUES (3, 'qaz', 0, 0, '2022-01-27 22:09:08', NULL, 8, NULL);
INSERT INTO `tags` VALUES (4, 'qazq', 0, 0, '2022-01-27 22:09:12', NULL, 8, NULL);
INSERT INTO `tags` VALUES (5, 'aa', 1, 0, '2022-01-27 22:09:24', NULL, 8, NULL);
INSERT INTO `tags` VALUES (6, 's', 0, 0, '2022-01-28 20:31:34', NULL, 8, NULL);
INSERT INTO `tags` VALUES (7, 'j', 0, 0, '2022-01-28 20:32:31', NULL, 8, NULL);
INSERT INTO `tags` VALUES (8, 'dfgdgsdf', 0, 0, '2022-01-28 20:34:19', NULL, 8, NULL);
INSERT INTO `tags` VALUES (9, 'ff', 0, 0, '2022-01-28 21:11:29', NULL, 8, NULL);
INSERT INTO `tags` VALUES (10, 'test', 0, 0, '2022-02-23 14:27:23', '2022-02-23 14:28:42', 21, 21);
INSERT INTO `tags` VALUES (11, 'love', 0, 0, '2022-03-30 15:50:39', NULL, 25, NULL);
INSERT INTO `tags` VALUES (12, 'zq', 0, 0, '2022-03-30 17:01:15', NULL, 25, NULL);
INSERT INTO `tags` VALUES (13, 'f', 0, 0, '2022-03-31 15:55:52', NULL, 8, NULL);
INSERT INTO `tags` VALUES (14, 'd', 0, 0, '2022-03-31 15:55:56', NULL, 8, NULL);
INSERT INTO `tags` VALUES (15, 'rrr', 0, 0, '2022-04-04 00:15:10', NULL, 21, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(15) NOT NULL COMMENT '角色编号',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` int(1) NULL DEFAULT NULL COMMENT '男 0   女 1',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `qq` int(15) NULL DEFAULT NULL COMMENT 'QQ',
  `wechat` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'WeChat',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` int(11) NULL DEFAULT NULL COMMENT '电话',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0:未删除  1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '0 启动  1  关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 1, 'admin', 0, 'biUC66KSICy5YqxZB1uWSwcVLSNLcA==', 'http://180.163.101.78:9000/public/984db29d0bc543c491b9671095efd3f8_IMG_1500.JPG', 1574802397, 'llllllxxxxxxxxxxxxxxxx', 'admin', 1231232132, '帅气 21', 0, '2021-12-28 00:00:00', '2022-04-01 22:53:06', 8, 0);
INSERT INTO `user` VALUES (9, 2, 'test', 0, '628c362300d6d9e403a69d764defcc72', NULL, 0, '', '123@123.com', 0, NULL, 1, '2021-12-28 16:04:52', '2022-01-20 15:17:08', 8, 0);
INSERT INTO `user` VALUES (10, 2, 'test14', NULL, '09417189bd3909f93a928608f3915d8d', NULL, NULL, NULL, '1223@12.com', NULL, '', 1, '2021-12-28 16:06:34', '2022-03-28 15:12:52', 8, 1);
INSERT INTO `user` VALUES (11, 3, 'test2', NULL, '14e1b600b1fd579f47433b88e8d85291', NULL, 222, '22', '121231113@123.com', 222, '3', 0, '2021-12-28 16:07:35', '2022-01-20 11:40:39', 8, 0);
INSERT INTO `user` VALUES (12, 2, 'test17', NULL, '9eaee6263ab7810d2b088b07395035bc', NULL, NULL, NULL, '111213@123.com', NULL, NULL, 0, '2021-12-28 00:00:00', NULL, NULL, 0);
INSERT INTO `user` VALUES (13, 2, 'test5', 0, '09417189bd3909f93a928608f3915d8d', NULL, 3213123, '21312', '12332@12.com', 1321312, '32131', 0, '2021-12-28 16:06:34', NULL, 8, 1);
INSERT INTO `user` VALUES (14, 2, 'test16', NULL, '628c362300d6d9e403a69d764defcc72', NULL, NULL, NULL, '11213@123.com', NULL, NULL, 0, '2021-12-28 16:04:52', NULL, NULL, 0);
INSERT INTO `user` VALUES (15, 2, 'test4', NULL, '5llfTUUpICy5YqxZB1uWSwcVLSNLcA==', NULL, NULL, NULL, '124444@123.com', NULL, NULL, 1, '2021-12-28 16:07:35', '2022-01-02 19:09:10', 8, 0);
INSERT INTO `user` VALUES (16, 2, 'test127', 0, 'EBUegTZF4QrcOUm6Wau+VuBX8g+IPg==', NULL, NULL, NULL, '1112213@123.com', NULL, '', 0, '2021-12-28 00:00:00', '2022-03-28 12:32:14', 8, 0);
INSERT INTO `user` VALUES (17, 2, 'test216', 1, '628c362300d6d9e403a69d764defcc72', NULL, NULL, NULL, '112213@123.com', NULL, '', 0, '2021-12-28 16:04:52', NULL, 8, 0);
INSERT INTO `user` VALUES (18, 2, 'tes3t5', NULL, '09417189bd3909f93a928608f3915d8d', NULL, NULL, NULL, '1233@123.com', NULL, '', 0, '2021-12-28 16:06:34', NULL, 8, 0);
INSERT INTO `user` VALUES (19, 2, 'tes3t4', 0, '5d5be40ca9bb7445b53d28e97495a8df', NULL, 3123123, '312321312', '1244244@123.com', 213213213, '12312', 1, '2021-12-28 16:07:35', '2022-01-20 15:21:43', 8, 1);
INSERT INTO `user` VALUES (20, 2, 'testa', 0, '14e1b600b1fd579f47433b88e8d85291', NULL, 22, '11', '13123123123', 11, '22', 1, NULL, '2022-01-20 16:04:01', 8, 0);
INSERT INTO `user` VALUES (21, 2, 'username', 0, '5llfTUUpICy5YqxZB1uWSwcVLSNLcA==', 'http://180.163.101.78:9000/public/18ab05a665b648a18b4653477e0585aa_2.JPG', 123123, '213213', '123@123.com', 123123, '21321d', 0, '2022-01-20 16:08:07', '2022-04-02 00:48:28', 21, 0);
INSERT INTO `user` VALUES (22, 3, 'dd', NULL, 'YRicaVj6RvlMjeFPs2aAhQdo/xt/Kg==', NULL, NULL, NULL, '1574802397@qq.com', NULL, '', 0, '2022-03-28 18:56:32', NULL, 8, 0);
INSERT INTO `user` VALUES (25, 3, 'zq', NULL, 'TC2T6oJLICy5YqxZB1uWSwcVLSNLcA==', 'http://180.163.101.78:9000/public/4252343df2f14ffba22b93e37ae50e0e_photo_2022-02-12_22-47-33.jpg', NULL, NULL, 'l1574802397@163.com', NULL, '', 0, '2022-03-28 22:27:41', '2022-04-03 17:47:52', 25, 0);

-- ----------------------------
-- Table structure for user_donate
-- ----------------------------
DROP TABLE IF EXISTS `user_donate`;
CREATE TABLE `user_donate`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `donate_json` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '赞赏信息',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_donate
-- ----------------------------
INSERT INTO `user_donate` VALUES (1, 'http://180.163.101.78:9000/public/12383b4a00a2464198a39bd6fedab3c9_photo_2021-10-18_14-50-31.jpg', 0, '2022-04-01 20:49:41', '2022-04-01 22:52:52', 8, 8);
INSERT INTO `user_donate` VALUES (2, 'http://180.163.101.78:9000/public/6389501ead8b47939aba637ea94b2915_IMG_1500.JPG', 0, '2022-04-01 23:30:22', '2022-04-04 12:03:55', 21, 21);

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `like` int(1) NULL DEFAULT NULL COMMENT '未点赞：0，点赞：1',
  `article_id` bigint(15) NULL DEFAULT NULL COMMENT '文章id',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0:未删除   1:删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(15) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(15) NULL DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_like
-- ----------------------------
INSERT INTO `user_like` VALUES (11, 1, 38, 0, '2022-03-29 16:54:12', 8, 8, '2022-03-29 17:02:26');
INSERT INTO `user_like` VALUES (12, 1, 38, 0, '2022-03-29 17:02:37', 25, 25, '2022-03-29 17:27:57');
INSERT INTO `user_like` VALUES (13, 1, 42, 0, '2022-03-30 17:05:06', 25, NULL, NULL);
INSERT INTO `user_like` VALUES (14, 1, 43, 0, '2022-03-30 23:51:38', 25, NULL, NULL);
INSERT INTO `user_like` VALUES (15, 1, 43, 0, '2022-03-31 12:06:09', 8, 8, '2022-04-01 21:16:27');
INSERT INTO `user_like` VALUES (16, 1, 29, 0, '2022-04-01 12:31:06', 8, NULL, NULL);
INSERT INTO `user_like` VALUES (17, 1, 44, 0, '2022-04-02 14:32:10', 8, NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '0：未删除   1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint(10) NULL DEFAULT NULL COMMENT '创建人',
  `last_update_by` bigint(10) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 'superAdmin', '超级管理员', 0, NULL, '2022-03-30 21:00:08', NULL, 8);
INSERT INTO `user_role` VALUES (2, 'admin', '管理员', 0, '2022-03-28 19:42:54', '2022-04-04 02:46:24', 8, 8);
INSERT INTO `user_role` VALUES (3, 'user', '普通用户', 0, NULL, '2022-04-04 02:46:10', NULL, 8);

SET FOREIGN_KEY_CHECKS = 1;
