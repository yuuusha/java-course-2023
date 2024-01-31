package edu.hw9.Task1;

public class StatsResult {
    private final String metricName;
    private final double sum;
    private final double average;
    private final double max;
    private final double min;

    public StatsResult(String metricName, double sum, double average, double max, double min) {
        this.metricName = metricName;
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public String getMetricName() {
        return metricName;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
