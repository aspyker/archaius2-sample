package com.netflix.archaius.samples.simple;

import com.netflix.archaius.Config;
import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.Property;
import com.netflix.archaius.PropertyListener;
import com.netflix.archaius.annotations.Configuration;
import com.netflix.archaius.config.CompositeConfig;
import com.netflix.archaius.config.DefaultSettableConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Configuration(allowFields = true)
public class SomeService2 {
    private static Logger logger = LoggerFactory.getLogger(SomeService2.class);
    private int intprop;
    //private Property<String> stringprop;
    private boolean boolprop;

    @Inject
    public SomeService2() {
    }

    public void getDirectly() {
        logger.debug("intprop = " + intprop);
    }

    public void getAgainstProperty() {
//        logger.debug("stringprop = " + stringprop.get());
        logger.debug("setting properties via @Configuration not yet supported");
    }

    public void setBoolprop(Boolean boolprop) {
        logger.debug("boolprop being set to " + boolprop);
        this.boolprop = boolprop;
    }
}
