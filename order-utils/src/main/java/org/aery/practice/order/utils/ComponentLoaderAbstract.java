package org.aery.practice.order.utils;

import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.error.GlobalException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class ComponentLoaderAbstract<ComponentType extends Component<InfoType>, InfoType extends ComponentInfo>
        implements ComponentLoader<ComponentType, InfoType> {

    private ConfigurableApplicationContext springContext;

    private ComponentType component;

    @Override
    public ComponentType start(String... args) throws GlobalException {
        if (this.springContext == null) {
            Class<? extends ComponentType> targetType = getTargetType();
            this.springContext = SpringApplication.run(targetType, args);
            this.component = this.springContext.getBean(targetType);
            return this.component;
        } else {
            InfoType info = this.component.getInfo();
            throw ErrorCode.INCORRECT_STATE.thrown(info.name() + " is already started.");
        }
    }

    @Override
    public void stop() throws GlobalException {
        this.springContext.stop();
    }

}
