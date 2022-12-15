package com.coldfier.data.data_sources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerchantInfoDataSourceTest {

    @Test
    void getMerchantInfo() {
        MerchantInfoDataSource merchantInfoDataSource = new MerchantInfoDataSource();

        Assertions.assertNotNull(merchantInfoDataSource.getMerchantInfo());
    }
}