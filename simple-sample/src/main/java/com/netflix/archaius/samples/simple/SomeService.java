package com.netflix.archaius.samples.simple;

import com.netflix.archaius.Config;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.Property;
import com.netflix.archaius.PropertyListener;
import com.netflix.archaius.config.CompositeConfig;
import com.netflix.archaius.config.DefaultSettableConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SomeService {
    private static Logger logger = LoggerFactory.getLogger(SomeService.class);
    private static Config config;
    private Property<Integer> int1prop;
    private Property<Integer> int2prop;

    @Inject
    public SomeService(Config config) {
        this.config = config;
        DefaultPropertyFactory fact = DefaultPropertyFactory.from(config);
        int1prop = fact.getProperty("intprop").asInteger(42);
        int2prop = fact.getProperty("intprop2").asInteger(42);
        int1prop.addListener(new PropertyListener<Integer>() {
            @Override
            public void onChange(Integer value) {
                logger.debug("int1prop changed to " + value);
            }

            @Override
            public void onParseError(Throwable error) {
                logger.debug("int2prop parse error");
            }
        });
    }

    public void getDirectly() {
        int int1 = config.getInteger("intprop");
        logger.debug("int1 = " + int1);
        int int2 = config.getInteger("intprop2", 42);
        logger.debug("int2 = " + int2);
        String string1 = config.getString("stringprop");
        logger.debug("string1 = " + string1);
        String string2 = config.getString("stringprop2", "noThere");
        logger.debug("string2 = " + string2);
        boolean bool1 = config.getBoolean("boolprop");
        logger.debug("bool1 = " + bool1);
        boolean bool2 = config.getBoolean("boolprop2", false);
        logger.debug("bool2 = " + bool2);
    }

    public void getAgainstProperty() {
        logger.debug("int1 = " + int1prop.get());
        logger.debug("int2 = " + int2prop.get());
    }

    // obviously this isn't what you'd do typically, this would be more from a polling source
    public void changePropertyThatIsMonitored() {
        if (config instanceof CompositeConfig) {
            CompositeConfig composite = (CompositeConfig)config;
            Config libsConfig = composite.getConfig("RUNTIME");
            if (libsConfig instanceof DefaultSettableConfig) {
                DefaultSettableConfig settable = (DefaultSettableConfig) libsConfig;
                settable.setProperty("intprop", 42 * 42);
            }
        }
        logger.debug("after change property");
    }
}
