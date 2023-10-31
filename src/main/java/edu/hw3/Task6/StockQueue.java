package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockQueue implements StockMarket {

    PriorityQueue<Stock> stockQueue;

    public static final Comparator<Stock> COAST_COMPARATOR = Comparator
        .comparingInt(Stock::getPrice)
        .reversed();

    public StockQueue() {
        stockQueue = new PriorityQueue<>(COAST_COMPARATOR);
    }

    @Override
    public void add(Stock stock) {
        if (!stockQueue.contains(stock)) {
            stockQueue.add(stock);
        }  else {
            throw new RuntimeException("Акция уже существует");
        }
    }

    @Override
    public void remove(Stock stock) {
        if (stockQueue.contains(stock)) {
            stockQueue.remove(stock);
        }  else {
            throw new RuntimeException("Такой акции нет");
        }
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
