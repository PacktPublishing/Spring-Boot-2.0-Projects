package com.packtpub.springboot2blog;

import org.junit.Test;
import org.webjars.WebJarAssetLocator;

import static org.assertj.core.api.Assertions.*;

public class WebJarAssetLocatorTest {

    @Test
    public void getFullPath_JQuery_ShouldReturnFullPath()  {
        WebJarAssetLocator locator = new WebJarAssetLocator();
        String fullPathToBootstrap = locator.getFullPath("jquery.min.js");

        assertThat(fullPathToBootstrap).isEqualTo("META-INF/resources/webjars/jquery/3.3.1-1/jquery.min.js");
    }
}
