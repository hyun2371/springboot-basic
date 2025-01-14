package com.prgrms.voucher_manage.domain.voucher.entity;

import static com.prgrms.voucher_manage.domain.voucher.entity.VoucherType.*;

public class PercentDiscountVoucher extends Voucher {
    private static final Long MIN_DISCOUNT_PERCENT = 0L;
    private static final Long MAX_DISCOUNT_PERCENT = 100L;
    public PercentDiscountVoucher(Long discountPercent) {
        super(discountPercent, PERCENT);
    }
    public static boolean isInvalidPercent(Long discountAmount) {
        return (discountAmount < MIN_DISCOUNT_PERCENT) || (discountAmount > MAX_DISCOUNT_PERCENT);
    }
}
