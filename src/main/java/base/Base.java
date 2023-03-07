package base;

import base.context.LocalDriverContext;
import org.openqa.selenium.support.PageFactory;

public class Base {
    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page)
    {
        Object obj = PageFactory.initElements(LocalDriverContext.getRemoteWebDriver(), page);
        return page.cast(obj);
    }
}