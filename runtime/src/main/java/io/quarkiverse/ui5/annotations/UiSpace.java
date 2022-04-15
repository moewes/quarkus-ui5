package io.quarkiverse.ui5.annotations;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UiSpace {
    String value();
}
