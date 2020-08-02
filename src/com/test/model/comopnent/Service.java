package com.test.model.comopnent;

import static com.test.validation.utils.Utils.validateServiceId;
import static com.test.validation.utils.Utils.validateServiceVariationId;

public class Service {

    private int serviceId;
    private int variationId;

    public int getServiceId() {
        return serviceId;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setServiceId(int serviceId) {
        validateServiceId(serviceId);
        this.serviceId = serviceId;
    }

    public void setVariationId(int variationId) {
        validateServiceVariationId(variationId);
        this.variationId = variationId;
    }

    public boolean isMatch(Service service){
        return !this.isSet() || this.getServiceId() == service.getServiceId() &&
                (this.getVariationId() == 0 || this.getVariationId() == service.getVariationId());
    }

    private boolean isSet(){
        return serviceId != 0;
    }
}
