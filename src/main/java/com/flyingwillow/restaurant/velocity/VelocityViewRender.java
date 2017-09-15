package com.flyingwillow.restaurant.velocity;

import com.flyingwillow.restaurant.util.Config;
import org.apache.commons.digester.Digester;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * velocty渲染生成HTML文件工具类
 * 
 * @author btshen
 * 
 */
public class VelocityViewRender implements InitializingBean {
    protected Logger logger = LogManager.getLogger();

    private VelocityConfigurer velocityConfigurer;

    private VelocityEngine velocityEngine;

    private String TOOLBOXFILE = "velocity/toolbox.xml";

    private Map contextRow = new HashMap();

    private String basePath = "";

    /**
     * @return the velocityEngine
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    /**
     * @param velocityEngine the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * @return the velocityConfigurer
     */
    public VelocityConfigurer getVelocityConfigurer() {
        return velocityConfigurer;
    }

    /**
     * @param velocityConfigurer the velocityConfigurer to set
     */
    public void setVelocityConfigurer(VelocityConfigurer velocityConfigurer) {
        this.velocityConfigurer = velocityConfigurer;
    }

    /**
     * 用来添加解析的XML数据
     */
    public void addTool(ToolBoxBean tbb) {
        if ("application".equals(tbb.getScope())) {
            try {
                Object obj =
                        this.getClass().getClassLoader().loadClass(tbb.getClassName())
                                .newInstance();
                contextRow.put(tbb.getKey(), obj);
            } catch (Exception e) {
                logger.error("实例化类" + tbb.getClassName() + "出错！", e);
            }
        }
    }

    /**
     * 
     * @param filePath 生成的文件，包括路径和文件名
     * @param tplName 模板名（如果不在CLASSPATH下，则包括路径）
     * @param row
     * @throws Exception
     */
    public void render(String filePath, String tplName, String encode, Map row, boolean isShare) {
        //filePath = isShare ? (DealerConstant.DS_SITE_SHARE_PATH + filePath) : (basePath + filePath); // 补上成为完整路径
        StringWriter out = new StringWriter();
        //VelocityContext context = new VelocityContext(contextRow);
        Map base = new HashMap(contextRow);
        VelocityContext context = new VelocityContext(base);
        if (row != null) {
            context.put("row", row);
            context.put("dirs",getDirs());
        }
        try {
            Template tpl = this.getVelocityEngine().getTemplate(tplName);
            long startTime = System.currentTimeMillis();
            tpl.merge(context, out);
            logger.debug("获取模板数据合成消耗时间：" + (System.currentTimeMillis() - startTime) + "毫秒.");
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file = new File(filePath);
            if (StringUtils.isEmpty(encode)) {
                encode = Config.gets("DEFAULT_FILE_ENCODE");
            }
            logger.debug("生成文件的路径：" + filePath);
            FileUtils.writeStringToFile(file, out.toString(), encode);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得render的输出内容
     * 
     * @param tplName
     * @param context
     * @return
     */
    public String getContentStr(String tplName, VelocityContext context) {
        StringWriter out = new StringWriter();
        try {
            //System.out.println(this.getVelocityEngine().getProperty("file.resource.loader.path"));
            Template tpl = this.getVelocityEngine().getTemplate(tplName);
            long startTime = System.currentTimeMillis();
            tpl.merge(context, out);
            logger.debug("获取模板数据合成消耗时间：" + (System.currentTimeMillis() - startTime) + "毫秒.");
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getContentStr(String tplName, Map context) {
        return getContentStr(tplName,new VelocityContext(context));
    }

    /**
     * 
     * @param writer 将结果直接输出流
     * @param tplName 模板名（如果不在CLASSPATH下，则包括路径）
     * @param row
     * @throws Exception
     */
    public void render(OutputStreamWriter writer, String tplName, Map row) {
        //VelocityContext context = new VelocityContext(contextRow);
        Map base = new HashMap(contextRow);
        VelocityContext context = new VelocityContext(base);
        if (row != null) {
            context.put("row", row);
            context.put("dirs",getDirs());
        }
        try {
            Template tpl = this.getVelocityEngine().getTemplate(tplName);
            long startTime = System.currentTimeMillis();
            tpl.merge(context, writer);
            logger.debug("获取模板数据合成消耗时间：" + (System.currentTimeMillis() - startTime) + "毫秒.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private Map getDirs(){
        Map row = new HashMap();
        Map configRow = Config.getInstance().getRow();
        for(Object key : configRow.keySet()){
            String strKey = (String) key;
            if(strKey.contains("_DIR")){
                row.put(key,configRow.get(key));
            }
        }

        row.put("URL_PREFIX",configRow.get("URL_PREFIX"));

        return row;
    }

	@Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        setVelocityEngine(getVelocityConfigurer().getVelocityEngine());
        // 配置toolbox工具类的引入
        try {
            Digester digester = new Digester();
            digester.push(this);
            digester.addObjectCreate("toolbox/tool", ToolBoxBean.class);
            // 先解板scope用来做判断用的，可以判断是否需要初初化类并加入到Row中去
            digester.addBeanPropertySetter("toolbox/tool/scope", "scope");
            digester.addBeanPropertySetter("toolbox/tool/key", "key");
            digester.addBeanPropertySetter("toolbox/tool/class", "className");
            digester.addSetNext("toolbox/tool", "addTool");
            digester.parse(new ClassPathResource(TOOLBOXFILE).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析toolbox.xml文件出错！");
        }
    }

}