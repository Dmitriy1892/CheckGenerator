package com.coldfier.domain;

import java.util.Map;

public interface ParseResultCallback {

    void onSuccess(Map<Long, Integer> itemsMap);

    void onSuccess(Map<Long, Integer> itemsMap, long discountCardId);

    void onSuccess(String fileName);

    void onError(String message);
}
