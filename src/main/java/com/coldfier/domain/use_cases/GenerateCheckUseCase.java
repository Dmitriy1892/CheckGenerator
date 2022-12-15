package com.coldfier.domain.use_cases;

import com.coldfier.data.models.DiscountCard;
import com.coldfier.data.models.Item;
import com.coldfier.data.models.MerchantInfo;
import com.coldfier.data.StorageRepository;
import com.coldfier.domain.factories.CheckPositionFactory;
import com.coldfier.domain.models.Check;
import com.coldfier.domain.models.check_position.CheckPosition;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class GenerateCheckUseCase {

    private final MerchantInfo merchantInfo;
    private final StorageRepository storageRepository;
    private final CheckPositionFactory checkPositionFactory;

    public GenerateCheckUseCase(
            StorageRepository storageRepository,
            CheckPositionFactory checkPositionFactory
    ) {
        this.merchantInfo = storageRepository.getMerchantInfo();
        this.storageRepository = storageRepository;
        this.checkPositionFactory = checkPositionFactory;
    }

    public Check generateCheck(
            Map<Long, Integer> itemsInfo,
            Long discountCardId,
            String checkTitle,
            long cashierId
    ) throws NoSuchObjectException {
        int discountCardPercentage = getDiscountCardPercentage(discountCardId);
        int vatPercentage = getVatPercentage();

        List<CheckPosition> checkPositions = new ArrayList<>();

        itemsInfo.forEach((itemId, quantity) -> {
            try {
                Item item = getItemById(itemId);
                CheckPosition checkPosition = checkPositionFactory.create(item, quantity);
                checkPositions.add(checkPosition);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        });

        if (checkPositions.isEmpty())
            throw new NoSuchObjectException("Items pairs input incorrect or items does not exist on storage");

        Check.Builder builder = new Check.Builder()
                .setTitle(checkTitle)
                .setMerchantName(merchantInfo.name())
                .setAddress(merchantInfo.address())
                .setPhone(merchantInfo.phone())
                .setCashierId(cashierId)
                .setVatPercentage(vatPercentage)
                .setCardDiscountPercentage(discountCardPercentage)
                .addCheckPositions(checkPositions);

        return builder.build();
    }

    private int getDiscountCardPercentage(Long discountCardId) {
        DiscountCard discountCard = storageRepository.getDiscountCardById(discountCardId);
        return  (discountCard != null) ? discountCard.discountPercentage() : 0;
    }

    private int getVatPercentage() {
        return storageRepository.getVatPercentage();
    }

    private Item getItemById(long itemId) {
        Item item = storageRepository.getItemById(itemId);

        if (item != null) {
            return item;
        } else  {
            throw new NoSuchElementException("No item found for itemId: " + itemId);
        }
    }
}
