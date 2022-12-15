package com.coldfier.presentation;

import com.coldfier.domain.use_cases.GenerateCheckUseCase;
import com.coldfier.domain.ParseResultCallback;
import com.coldfier.domain.UserInputParser;
import com.coldfier.domain.models.Check;
import com.coldfier.domain.use_cases.SaveCheckUseCase;
import com.coldfier.utils.reactive.DefaultObservableSource;

import java.io.*;
import java.util.Map;

public class CheckViewModel {

    private final ParseResultCallback callback = new ParseResultCallback() {
        @Override
        public void onSuccess(Map<Long, Integer> itemsMap) {
            generateCheck(itemsMap);
        }

        @Override
        public void onSuccess(Map<Long, Integer> itemsMap, long discountCardId) {
            generateCheck(itemsMap, discountCardId);
        }

        @Override
        public void onSuccess(String fileName) {
            generateCheck(fileName);
        }

        @Override
        public void onError(String message) {
            errorObservable.emit(message);
        }
    };

    public final DefaultObservableSource<Check> checkObservable = new DefaultObservableSource<>();

    public final DefaultObservableSource<String> errorObservable = new DefaultObservableSource<>();

    private final GenerateCheckUseCase generateCheckUseCase;

    private final SaveCheckUseCase saveCheckUseCase;

    public CheckViewModel(
            GenerateCheckUseCase generateCheckUseCase,
            SaveCheckUseCase saveCheckUseCase
    ) {
        this.generateCheckUseCase = generateCheckUseCase;
        this.saveCheckUseCase = saveCheckUseCase;
    }

    public void receiveInput(String[] input) {
        UserInputParser.parseUserInput(input, callback);
    }

    private void generateCheck(String fileName) {

        BufferedReader reader = null;

        try {
            File file = new File("src/main/resources/" + fileName);
            reader = new BufferedReader(new FileReader(file));
            String input = reader.readLine();
            String[] inputArray = input.trim().split(" ");

            receiveInput(inputArray);
        } catch (FileNotFoundException e) {
            errorObservable.emit("File with file name " + fileName + " not found");
        } catch (IOException e) {
            errorObservable.emit("Error is occurred in file reading process. Please check the file data");
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException ignored) {}
        }
    }

    private void generateCheck(Map<Long, Integer> itemsInfo) {
        generateCheck(itemsInfo, -1L);
    }

    private void generateCheck(
            Map<Long, Integer> itemsInfo,
            Long discountCardId
    ) {
        Check check = generateCheckUseCase.generateCheck(itemsInfo, discountCardId, "CASH RECEIPT", 228L);

        saveCheckUseCase.saveCheck(check);
        checkObservable.emit(check);
    }
}