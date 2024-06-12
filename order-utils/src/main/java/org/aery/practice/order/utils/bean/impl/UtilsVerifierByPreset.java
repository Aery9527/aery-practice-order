package org.aery.practice.order.utils.bean.impl;

import org.aery.practice.order.utils.bean.api.UtilsVerifier;
import org.aery.practice.order.utils.error.ErrorCode;
import org.aery.practice.order.utils.error.GlobalException;
import org.misty.utils.verify.VerifierErrorMsg;
import org.misty.utils.verify.VerifierThrown;
import org.springframework.stereotype.Component;

@Component
public class UtilsVerifierByPreset implements UtilsVerifier {

    @Override
    public <TargetType, MagType extends VerifierErrorMsg<TargetType>> VerifierThrown<TargetType, MagType, GlobalException> getThrower() {
        return error -> ErrorCode.VERIFY_ERROR.thrown(error.getErrorMsg());
    }

}
