package com.coldfier.data.data_sources;

import com.coldfier.data.models.MerchantInfo;

public class MerchantInfoDataSource {
    private final MerchantInfo merchantInfo = new MerchantInfo(
            "SUPERMARKET 123",
            "12, MILKYWAY Galaxy/Earth",
            "123-456-7890"
    );

    public MerchantInfo getMerchantInfo() {
        return merchantInfo;
    }
}
