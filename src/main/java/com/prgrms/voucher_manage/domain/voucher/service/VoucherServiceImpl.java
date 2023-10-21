package com.prgrms.voucher_manage.domain.voucher.service;

import com.prgrms.voucher_manage.domain.voucher.dto.CreateVoucherDto;
import com.prgrms.voucher_manage.domain.voucher.entity.Voucher;
import com.prgrms.voucher_manage.domain.voucher.repository.VoucherRepository;
import com.prgrms.voucher_manage.console.OutputUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final OutputUtil outputUtil;


    @Override
    public void createVoucher(CreateVoucherDto dto) {
        voucherRepository.save(dto.of());
    }

    @Override
    public void getVouchers() {
        List<Voucher> vouchers = voucherRepository.findAll();
        vouchers.forEach(voucher -> {
            switch (voucher.getVoucherType()) {
                case FIXED -> outputUtil.printFixedVoucherInfo(voucher);
                case PERCENT -> outputUtil.printPercentVoucherInfo(voucher);
            }
        });
    }
}
