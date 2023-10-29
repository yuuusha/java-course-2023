package edu.hw3.Task6;

import java.util.PriorityQueue;

public class StockQueue implements StockMarket {

    PriorityQueue<Stock> stockQueue;

    public StockQueue() {
        stockQueue = new PriorityQueue<>();
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
