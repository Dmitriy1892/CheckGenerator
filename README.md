# CheckGenerator

The application can generate purchase checks. Sample application.

## Application features
- checks generating with discount if discount card entered;
- check positions may have a personal promo discounts;
- generated check prints in console and saves in ".txt" files in "src/main/output" folder.

## How to use
Download and compile the project. Input point is a "CheckRunner" class, it's located in "src/main/java/com/coldfier/" directory.
Start programm with arguments, you can pass in arguments the next data:
- "ItemId-Quantity" pairs (e.g. "5-2") separated with whitespaces and (optional) discount cards in format "card-1234" 
- file name.

**Examples:**
- for itemId-quantity with\without discount card console input looks like: "1-3 4-2 5-7" or "3-5 4-1 card-1234";
- for file name input looks like: "fileRequest.txt".

>**Warning**: Data in file must contain only one row (another rows won't be read) and it should look in the format for console input, which was described above. File must be located in "src/main/resources" directory.

>**Warning**: You must input one of (itemId-quantity with\without discount card) or (file name). If you use both variants in the same time, programm will not generate check and will show error message for user.

> **Note**
> **Sample for start compiled programm from console:**
>- "java CheckRunner 1-3 4-2 5-7"
>- "java CheckRunner 3-5 4-1 card-1234"
>- "java CheckRunner someFile"


> **Note**
> If you can add/delete some saved data, you can change that data in files from "src/main/java/com/coldfier/data/data_sources" directory.
> - Discount cards located in "DiscountCardsDataSource" class in "discountCards" collection;
> - Items for check located in "ItemsDataSource" class in "itemsMap" collection;
> - Merchant info located in "MerchantInfoDataSource" class in "merchantInfo" field;
> - Promo discount settings located in "PromoSettingsDataSource" class in "promoSettings" field.

## Tech stack
- Java  17;
- Gradle 7.5;
- Junit5 and Mockito for tests;
- MVVM;
- Clean architecture;
- Dependency injection (Service locator);
- SOLID;
- Patterns:
  - Builder;
  - Factory;
  - Decorator;
  - Callback;
  - Observer;
  - Repository;
  - Singleton;
  - Facade;
