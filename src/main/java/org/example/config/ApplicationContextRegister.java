package org.example.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author thflo
 * Define a registration strategy class to facilitate loading resource files later.
 * ApplicationContextAware is an interface provided by the Spring framework,
 * also known as an enhancer of the Spring context, which is executed when the project starts
 * and will be processed by Spring.
 * When a bean implements this interface, all beans in the Spring container
 * can be directly accessed through the setApplicationContext method.
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {

    private ApplicationContext applicationContext = null;

    /**
     * When the Spring container starts, the setApplicationContext method will be called back,
     * passing in the ApplicationContext object, and then you can operate
     * on this object (such as obtaining all beans in the Spring container).
     *
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * Provide a method for loading SQL script files.
     *
     * @param url the location of the SQL file
     * @return
     */
    public Resource getResource(String url) {
        return this.applicationContext.getResource(url);
    }
}
