package com.netflix.archaius.samples.simple;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.netflix.archaius.guice.ArchaiusModule;

public class SimpleSample {
    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new ArchaiusModule());
        SomeService ss = injector.getInstance(SomeService.class);
        ss.getDirectly();
        ss.getAgainstProperty();
        ss.changePropertyThatIsMonitored();
    }
}
