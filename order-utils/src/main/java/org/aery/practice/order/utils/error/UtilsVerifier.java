package org.aery.practice.order.utils.error;

import org.misty.utils.verify.VerifierErrorMsg;
import org.misty.utils.verify.VerifierLogic;
import org.misty.utils.verify.VerifierThrown;
import org.springframework.stereotype.Component;

@Component
public class UtilsVerifier implements VerifierLogic<GlobalException> {

    @Override
    public <TargetType, MagType extends VerifierErrorMsg<TargetType>> VerifierThrown<TargetType, MagType, GlobalException> getThrower() {
        return error -> ErrorCode.VERIFY_ERROR.thrown(error.getErrorMsg());
    }

}
